package com.arora.account.web;

import java.security.Principal;

import com.arora.account.model.User;
import com.arora.account.repository.UserRepository;
import com.arora.account.service.SecurityService;
import com.arora.account.service.UserService;
import com.arora.account.validator.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;
    
    /* REGISTRATION */
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    /* LOGIN */
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    /* WELCOME PAGE */
    
    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    
    
    /* EDIT PROFILE */

    @RequestMapping(value = "/edit-profile", method = RequestMethod.GET)
    public String editProfile(Principal principal, Model model) {
    	    	
    	if (principal != null) {
    		model.addAttribute("userForm", userService.findByUsername(principal.getName()));
    	}
    	
        return "myprofile";
    }

    @RequestMapping(value = "/edit-profile", method = RequestMethod.POST)
    public String editProfile(Principal principal, @ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        
    	User user2 = userRepository.findByUsername(principal.getName());
    	userValidator.validateUpdateProfile(userForm, bindingResult, user2);

        if (bindingResult.hasErrors()) {
            return "myprofile";
        }
        
        userService.updateProfile(userForm);

        return "redirect:/welcome";
    } 
    
    /* CHANGE PASSWORD */    
    
    @RequestMapping(value = "/change-password", method = RequestMethod.GET)
    public String changePassword(Principal principal, Model model) {
    	    	
    	if (principal != null) {
    		
    		User u = userService.findByUsername(principal.getName());
    		u.setPassword("");
    		
    		model.addAttribute("userForm", u);
    	}
    	
        return "change-password";
    }   
    
    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public String changePassword(Principal principal, @ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        
    	userValidator.validatePassword(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "change-password";
        }
        
        userService.updatePassword(userForm, principal);

        return "redirect:/welcome";
    }     
  
    /* DELETE PROFILE */
    
    @RequestMapping(value = "/delete-profile", method = RequestMethod.GET)
    public String deleteProfile(Principal principal) {
        
        userService.deleteProfile(principal);

        return "redirect:/login";
    }       
    
}
