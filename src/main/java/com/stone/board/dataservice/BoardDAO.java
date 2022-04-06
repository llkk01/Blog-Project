package com.stone.board.dataservice;

import java.util.List;

import com.stone.board.common.Board;

public interface BoardDAO {
	
	//게시물 저장
	public void insert(Board board);
	//게시물 변경
	public void update(Board board);
	//게시물 삭제
	public void delete(Board board);
	//게시물 목록수집
	public List<Board> select();
	//게시물 조회수 증가
	public Board viewCount(int num);
	//게시물 찾기
	//public Board boardByMum(int num);

}
