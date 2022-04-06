package com.stone.loginOut.service;

import com.stone.member.common.Member;

public interface LoginOutService {

	//로그인준비
	public boolean loginUsablePrepare();
	//로그인조회
	public Member loginUsable(String id, String password);
	
}
