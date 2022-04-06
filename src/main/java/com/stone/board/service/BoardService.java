package com.stone.board.service;

import java.util.List;

import com.stone.board.common.Board;

public interface BoardService {

	//게시물 작성이 가능한지 준비
	public boolean boardPrepare();
	//게시물 등록
	public void inserts(Board board);
	//게시물 변경
	public void updates(Board board);
	//게시물 삭제
	public void deletes(Board board);
	//게시물 목록 수집
	public List<Board> selects();
	//게시물 조회수 증가
	public Board viewCounts(int num);
	
}
