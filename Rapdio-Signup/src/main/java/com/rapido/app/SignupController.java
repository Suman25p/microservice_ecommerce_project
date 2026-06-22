package com.rapido.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {

	@RequestMapping("showPage")
	public ModelAndView showSignupPage() {
		System.out.println("ShowSignupPage()......");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sign-up"); //display sign-up.jsp
		return mv;
	}
	
	@RequestMapping("signup")
	@ResponseBody
	public String signup(@RequestParam("mobile") String mobile,@RequestParam("email") String email,
			@RequestParam("location") String location,@RequestParam("otp") String otp)
	{
		System.out.println(" Request Received and data received as part of request: mobile :" + mobile
				+ ", Email: " + email + ", Location: " + location + ", OTP: " + otp);
		
		String uniqueId = String.format("%08d", Math.abs((mobile + email).hashCode()) % 100000000);
		System.out.println("Generated Ref Id: " + uniqueId);
		
		return "Registration Successful and It is pending for verification and ref id is: " + uniqueId;
	}
}
