package com.stone.home.presentation;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stone.member.common.Member;

@Controller
public class HomeController {

	//메인페이지
	@GetMapping("/main")
	public ModelAndView mainPage(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Member member = new Member();
		
		mv.setViewName("main/main");		
		
		boolean loginIn = (session.getAttribute("id")!=null)?true:false;
		
		mv.addObject("isLogin",loginIn);
		mv.addObject("member", member);
		
		return mv;
	}
	
	
}
