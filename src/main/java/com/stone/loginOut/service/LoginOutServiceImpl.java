package com.stone.loginOut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.member.common.Member;
import com.stone.member.dataservice.MemberDAOImpl;
import com.stone.member.service.MemberService;
import com.stone.member.service.MemberServiceImpl;

@Service
public class LoginOutServiceImpl implements LoginOutService {

	@Autowired
	MemberService memberService;
	
	//로그인준비
	@Override
	public boolean loginUsablePrepare() {
		
		return true;
	}

	//로그인조회
	@Override
	public Member loginUsable(String id, String password) {
	
		return memberService.selectIDPWs(id, password);
	}

}
