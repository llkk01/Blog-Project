package com.stone.board.common;

import java.sql.Date;

import com.stone.member.common.Member;

public class Board {

	//게시물
	int no; 			//번호
	String title; 		//제목
	String contents; 	//내용
	String writer; 		//작성자닉네임
	Date rdate; 		//날짜
	int views; 			//조회수
	Member writers; 	//Member의  no참조 (ref)
	
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Member getWriters() {
		return writers;
	}
	public void setWriters(Member writers) {
		this.writers = writers;
	}
	
}
