package com.stone.loginOut.presentation;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.stone.loginOut.service.LoginOutService;
import com.stone.loginOut.service.LoginOutServiceImpl;
import com.stone.member.common.Member;

@Controller
public class LoginOutController {

	@Autowired
	LoginOutService loginOutService;
	
	//로그인준비
	@GetMapping("/login")
	public String loginPrepare() {
		if(loginOutService.loginUsablePrepare()) {
			return "login/LoginView";
		}
		return null;
	}
	
	//로그인
	@PostMapping("/login")
	public String logins(String id, String password, HttpSession session) {
		//로그인 성공 시
		Member loginUser = loginOutService.loginUsable(id, password);
		if(loginUser!=null) {
			//로그인정보 저장
			session.setAttribute("id", id);
			session.setAttribute("userNo", loginUser.getNo());
			session.setAttribute("userName", loginUser.getName());
			
			return "redirect:/main";
		}
		//로그인 실패 시
		return "login/LoginErrorView";
	}
	
	//로그아웃
	@GetMapping("/logOut")
	public String logOuts(HttpSession session) {
		
		//로그인 되어있을 경우
		if(session!=null && session.getAttribute("id")!=null) {
			//세션 객체를 무효화 시킴
			session.invalidate();
		}
		return "redirect:/main";
	}
	
	
}
