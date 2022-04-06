package com.stone.visitor.dataservice;

import java.util.List;

import com.stone.visitor.common.Visitor;

public interface VisitorDAO {
	
	//방명록 저장
	public void insert(Visitor visitor);
	//방명록 변경
	public void update(Visitor visitor);
	//방명록 삭제
	public void delete(Visitor visitor);
	//방명록 목록 수집
	public List<Visitor> select();
	//방명록 조회
	public Visitor selectsByNum(int num);

}
