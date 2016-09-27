package webapp.todo;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import webapp.login.LoginService;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	TodoService service;
	
	@RequestMapping(value ="/list-todos", method = RequestMethod.GET)
//	@ResponseBody
	public String listTodos(ModelMap model){
		
		model.addAttribute("todos", service.retrieveTodos("in28Minutes"));
		return "list-todos";
	}
	
	@RequestMapping(value ="/add-todo", method = RequestMethod.GET)
//	@ResponseBody
	public String showTodoPage(){
		
		//model.addAttribute("todos", service.retrieveTodos("in28Minutes"));
		return "todo";
	}
	
	@RequestMapping(value ="/add-todo", method = RequestMethod.POST)
//	@ResponseBody
	public String addTodo(ModelMap model, @RequestParam String desc){
		model.clear();
		service.addTodo("in28Minutes", desc , new Date(), false);
		return "redirect:list-todos";
	}

	
}