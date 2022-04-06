package com.stone.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.board.common.Board;
import com.stone.board.dataservice.BoardDAO;
import com.stone.board.dataservice.BoardDAOImpl;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDAO boardDAO;
	
	//게시물 작성이 가능한지 준비
	@Override
	public boolean boardPrepare() {
		
		return true;
	}
	
	//게시물 등록
	@Override
	public void inserts(Board board) {
		
		boardDAO.insert(board);
	}
	
	//게시물 변경
	@Override
	public void updates(Board board) {
		
		boardDAO.update(board);
	}
	
	//게시물 삭제
	@Override
	public void deletes(Board board) {
		
		boardDAO.delete(board);
	}
	
	//게시물 목록 수집
	@Override
	public List<Board> selects() {
		
		return boardDAO.select();
	}

	//게시물 조회수 증가
	@Override
	public Board viewCounts(int num) {

		return boardDAO.viewCount(num);
	}

}
