package com.heo.springboot.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController  {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {	
		model.put("name",getLoggedinUsername()); 
		// 하드코딩된 것에서 getLoggedinUsername()으로 바꾼다.
		return "welcome";
	}
	
	private String getLoggedinUsername() { // private
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// 인증된 사용자를 반환
		return authentication.getName();
		// 인증된 사용자의 이름을 반환
	}
}
