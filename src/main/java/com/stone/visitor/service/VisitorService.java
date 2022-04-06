package com.stone.visitor.service;

import java.util.List;

import com.stone.visitor.common.Visitor;

public interface VisitorService {

	//방명록 작성이 가능한지 준비
	public boolean visitorPrepare();
	//방명록 등록
	public void inserts(Visitor visitor);
	//방명록 변경
	public void updates(Visitor visitor);
	//방명록 삭제
	public void deletes(Visitor visitor);
	//방명록 목록 수집
	public List<Visitor> selects();
	//방명록 조회
	public Visitor selectNums(int num);
	
}
