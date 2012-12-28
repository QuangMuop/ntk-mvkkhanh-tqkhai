package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {
	@RequestMapping(value="/login/",method = GET) 
	
    protected ModelAndView login(HttpServletRequest arg0,
			HttpServletResponse arg1)
    {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		modelAndView.addObject("success", "1");
		return modelAndView;
    }
	
	@RequestMapping(value="/failed", method = GET)
	public ModelAndView loginFail(HttpServletRequest arg0,
			HttpServletResponse arg1){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		modelAndView.addObject("success", "0");
		return modelAndView;
	}
	
	@RequestMapping(value="/logout", method = GET)
	public ModelAndView logout(HttpServletRequest arg0,
			HttpServletResponse arg1){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	

}
