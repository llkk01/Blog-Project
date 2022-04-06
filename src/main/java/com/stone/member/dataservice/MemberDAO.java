package com.stone.member.dataservice;

import java.util.List;

import com.stone.member.common.Member;

public interface MemberDAO {

	//회원정보 저장
	public void insert(Member member);
	//회원정보 삭제
	public void delete(Member member);
	//회원정보 목록
	public Object[] select(int pageNum, int maxSize);
	//회원정보 번호
	public Member selectNum(int no);
	//회원 ID,PW 조회
	public Member selectIDPW(String id, String password);
	//아이디 중복검사
	public boolean usableID(String id);
	//회원 번호 찾기
	Member selectByNum(int num);
}
