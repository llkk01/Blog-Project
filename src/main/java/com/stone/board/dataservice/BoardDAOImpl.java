package com.stone.board.dataservice;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stone.board.common.Board;
import com.stone.member.common.Member;
import com.stone.member.dataservice.MemberDAO;

import config.DBConfig;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	MemberDAO memberDAO;
	
	//게시물 글 저장
	@Override
	public void insert(Board board) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					DBConfig.DBURL,DBConfig.ID,DBConfig.PASSWORD);
			
			PreparedStatement pstmt = con.prepareStatement(
					"insert into board(title,contents,writer,writers) values(?,?,?,?)");
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContents());
			pstmt.setString(3, board.getWriter());
			pstmt.setInt(4, board.getWriters().getNo());
			
			pstmt.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	//게시물 글 수정
	@Override
	public void update(Board board) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					DBConfig.DBURL,DBConfig.ID,DBConfig.PASSWORD);
			
			PreparedStatement pstmt = con.prepareStatement(
					"update board set title=?, contents=?, writer=? where no=?");
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContents());
			pstmt.setString(3, board.getWriter());
			pstmt.setInt(4, board.getNo());
			
			pstmt.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	//게시물 글 삭제
	@Override
	public void delete(Board board) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					DBConfig.DBURL,DBConfig.ID,DBConfig.PASSWORD);
			
			PreparedStatement pstmt = con.prepareStatement(
					"delete from board where no=?");
			
			pstmt.setInt(1, board.getNo());
			
			pstmt.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	//게시물 목록수집
	@Override
	public List<Board> select() {
		List<Board> list = new ArrayList<Board>();
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					DBConfig.DBURL,DBConfig.ID,DBConfig.PASSWORD);
			
			PreparedStatement pstmt = con.prepareStatement(
					"select no,title,contents,writer,rdate,views,writers from board order by no desc");
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String writer = rs.getString("writer");
				Date rdate = rs.getDate("rdate");
				int views = rs.getInt("views");
				int writers = rs.getInt("writers");
				
				Member memberW = memberDAO.selectByNum(writers);
				Board board = new Board();
				
				board.setNo(no);
				board.setTitle(title);
				board.setContents(contents);
				board.setWriter(writer);
				board.setRdate(rdate);
				board.setViews(views);
				board.setWriters(memberW); //Membe의 번호 참조
				
				list.add(board);
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	//게시물 찾기,조회수 증가
	@Override
	public Board viewCount(int num) {
		Board board = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					DBConfig.DBURL,DBConfig.ID,DBConfig.PASSWORD);
			
			PreparedStatement pstmt2 = con.prepareStatement(
					"update board set views=views+1 where no=?");
			
			pstmt2.setInt(1, num);
			pstmt2.executeUpdate();
			pstmt2.close();
			
			PreparedStatement pstmt = con.prepareStatement(
					"select no,title,contents,writer,rdate,views,writers from board where no=?");
			
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String writer = rs.getString("writer");
				Date rdate = rs.getDate("rdate");
				int views = rs.getInt("views");
				int writers = rs.getInt("writers");
				
				Member memberW = memberDAO.selectByNum(writers);
				board = new Board();
				
				board.setNo(no);
				board.setTitle(title);
				board.setContents(contents);
				board.setWriter(writer);
				board.setRdate(rdate);
				board.setViews(views);
				board.setWriters(memberW); //Membe의 번호 참조
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return board;
	}

	//게시물 찾기
//	@Override
//	public Board boardByMum(int num) {
//		Board board = null;
//		Connection con = null;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			con = DriverManager.getConnection(
//					DBConfig.DBURL,DBConfig.ID,DBConfig.PASSWORD);
//			
//			PreparedStatement pstmt = con.prepareStatement(
//					"select no,title,contents,writer,rdate,views,writers from board where no=?");
//			pstmt.setInt(1, num);
//			
//			ResultSet rs = pstmt.executeQuery();
//			if(rs.next()) {
//				int no = rs.getInt("no");
//				String title = rs.getString("title");
//				String contents = rs.getString("contents");
//				String writer = rs.getString("writer");
//				Date rdate = rs.getDate("rdate");
//				int views = rs.getInt("views");
//				int writers = rs.getInt("writers");
//				
//				Member memberW = memberDAO.selectByNum(writers);
//				board = new Board();
//				
//				board.setNo(no);
//				board.setTitle(title);
//				board.setContents(contents);
//				board.setWriter(writer);
//				board.setRdate(rdate);
//				board.setViews(views);
//				board.setWriters(memberW); //Membe의 번호 참조
//				
//			}
//			con.close();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return board;
//	}

}
