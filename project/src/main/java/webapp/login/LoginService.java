package webapp.login;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	public boolean validateUser(String user,String password){
		if(user.equals("Jon") && password.equals("wall"))
			return true;
		else
			return false;
	}
}