package com.rapido.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {

	@RequestMapping("/showpage")
	public ModelAndView doSignup() {
		System.out.println("doSignup().......");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("signup-page");
		return mv;
	}
}
