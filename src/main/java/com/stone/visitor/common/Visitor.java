package com.stone.visitor.common;

import java.sql.Date;

import com.stone.member.common.Member;

public class Visitor {
	
	//방명록
	int no;				//번호
	String title;		//제목
	String contents;	//내용
	String nickname;	//닉네임
	Member member_no;	//회원의 번호(ref)
	Date rdate;			//작성일
	String writer;		//작성자
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Member getMember_no() {
		return member_no;
	}
	public void setMember_no(Member member_no) {
		this.member_no = member_no;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
}
