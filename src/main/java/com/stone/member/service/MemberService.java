package com.stone.member.service;

import com.stone.member.common.Member;

public interface MemberService {
	
	//회원등록 준비
	public void prepare();
	//회원등록
	public void insters(Member member);
	//회원 목록 
	public Object[] selects(int pageNum, int maxSize);
	//회원 번호 조회
	public Member selectNums(int no);
	//회원 ID,PW 조회
	public Member selectIDPWs(String id, String password);
	//Id중복검사
	public boolean usableIDs(String id);

}
