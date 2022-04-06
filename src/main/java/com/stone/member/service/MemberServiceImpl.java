package com.stone.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.member.common.Member;
import com.stone.member.dataservice.MemberDAO;
import com.stone.member.dataservice.MemberDAOImpl;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO memberDAO;
	
	//회원등록 준비
	@Override
	public void prepare() {
	}

	//회원 등록
	@Override
	public void insters(Member member) {
		memberDAO.insert(member);
	}

	//회원 목록 
	@Override
	public Object[] selects(int pageNum, int maxSize) {
		
		return memberDAO.select(pageNum, maxSize);
	}

	//회원번호 조회
	@Override
	public Member selectNums(int no) {
		
		return memberDAO.selectNum(no);
	}
	
	//회원 ID,PW 조회
	@Override
	public Member selectIDPWs(String id, String password) {
		
		return memberDAO.selectIDPW(id, password);
	}
	
	//ID중복검사
	@Override
	public boolean usableIDs(String id) {
		
		return !memberDAO.usableID(id);
	}

}
