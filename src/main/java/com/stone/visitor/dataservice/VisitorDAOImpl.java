package com.stone.visitor.dataservice;

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
import com.stone.visitor.common.Visitor;

import config.DBConfig;

@Repository
public class VisitorDAOImpl implements VisitorDAO{
	
	@Autowired
	MemberDAO memberDAO;
	
	
	//방명록 저장
	@Override
	public void insert(Visitor visitor) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					DBConfig.DBURL,DBConfig.ID,DBConfig.PASSWORD);
			
			PreparedStatement pstmt = con.prepareStatement(
					"insert into visitor(title,contents,nickname,member_no,writer) values(?,?,?,?,?)");
			
			pstmt.setString(1, visitor.getTitle());
			pstmt.setString(2, visitor.getContents());
			pstmt.setString(3, visitor.getNickname());
			pstmt.setInt(4, visitor.getMember_no().getNo());
			pstmt.setString(5, visitor.getWriter());
		
			pstmt.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//방명록 변경
	@Override
	public void update(Visitor visitor) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					DBConfig.DBURL,DBConfig.ID,DBConfig.PASSWORD);
			
			PreparedStatement pstmt = con.prepareStatement(
					"update visitor set title=?, contents=?, nickname=? where no=?");
			
			pstmt.setString(1, visitor.getTitle());
			pstmt.setString(2, visitor.getContents());
			pstmt.setString(3, visitor.getNickname());
			pstmt.setInt(4, visitor.getNo());
			
			pstmt.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//방명록 삭제
	@Override
	public void delete(Visitor visitor) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					DBConfig.DBURL,DBConfig.ID,DBConfig.PASSWORD);
			
			PreparedStatement pstmt = con.prepareStatement(
					"delete from visitor where no=?");
			
			pstmt.setInt(1, visitor.getNo());
			
			pstmt.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//방명록 목록 수집
	@Override
	public List<Visitor> select() {
		List<Visitor> list = new ArrayList<Visitor>();
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					DBConfig.DBURL,DBConfig.ID,DBConfig.PASSWORD);
			
			PreparedStatement pstmt = con.prepareStatement(
					"select no,title,contents,nickname,member_no,rdate,writer from visitor order by no desc");
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String nickname = rs.getString("nickname");
				int member_no = rs.getInt("member_no");
				Date rdate = rs.getDate("rdate");
				String writer = rs.getString("writer");
				
				Member memberW = memberDAO.selectByNum(member_no);
				Visitor visitor = new Visitor();
				
				visitor.setNo(no);
				visitor.setTitle(title);
				visitor.setContents(contents);
				visitor.setNickname(nickname);
				visitor.setMember_no(memberW); //Membe의 번호 참조
				visitor.setRdate(rdate);
				visitor.setWriter(writer);
				
				list.add(visitor);
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	//방명록 조회
	@Override
	public Visitor selectsByNum(int num) {
		Visitor visitor = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					DBConfig.DBURL,DBConfig.ID,DBConfig.PASSWORD);

			PreparedStatement pstmt = con.prepareStatement(
					"select no,title,contents,nickname,member_no,rdate,writer from visitor where no=?");
			
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String nickname = rs.getString("nickname");
				int member_no = rs.getInt("member_no");
				Date rdate = rs.getDate("rdate");
				String writer = rs.getString("writer");
	
				
				Member memberW = memberDAO.selectByNum(member_no);
				visitor = new Visitor();
			
				visitor.setNo(no);
				visitor.setTitle(title);
				visitor.setContents(contents);
				visitor.setNickname(nickname);
				visitor.setMember_no(memberW); //Membe의 번호 참조
				visitor.setRdate(rdate);
				visitor.setWriter(writer);
				
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return visitor;
	}

}
