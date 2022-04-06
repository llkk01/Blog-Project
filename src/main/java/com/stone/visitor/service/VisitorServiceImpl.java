package com.stone.visitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.visitor.common.Visitor;
import com.stone.visitor.dataservice.VisitorDAO;

@Service
public class VisitorServiceImpl implements VisitorService {

	@Autowired
	VisitorDAO visitorDAO;
	
	//방명록 작성이 가능한지 준비
	@Override
	public boolean visitorPrepare() {
		
		return true;
	}
	
	//방명록 등록
	@Override
	public void inserts(Visitor visitor) {
		
		visitorDAO.insert(visitor);
	}

	//방명록 수정
	@Override
	public void updates(Visitor visitor) {
		
		visitorDAO.update(visitor);
	}

	//방명록 삭제
	@Override
	public void deletes(Visitor visitor) {
		
		visitorDAO.delete(visitor);
	}

	//방명록 목록
	@Override
	public List<Visitor> selects() {
		
		return visitorDAO.select();
	}

	@Override
	public Visitor selectNums(int num) {
		return visitorDAO.selectsByNum(num);
	}
	



}
