package com.stone.member.dataservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stone.member.common.Member;

import config.DBConfig;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	MemberDAO memberDAO;
	
	//회원정보 저장
	@Override
	public void insert(Member member) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					DBConfig.DBURL,DBConfig.ID,DBConfig.PASSWORD);
			
			PreparedStatement pstmt = con.prepareStatement(
					"insert into member(name,id,password,post,address,detailAddress,email) values(?,?,?,?,?,?,?)");
			
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getId());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getPost());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getDetailAddress());
			pstmt.setString(7, member.getEmail());

			pstmt.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	//회정정보 삭제
	@Override
	public void delete(Member member) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					DBConfig.DBURL,DBConfig.ID,DBConfig.PASSWORD);
			
			PreparedStatement pstmt = con.prepareStatement(
					"delete from visitor where no = ?");
			
			pstmt.setInt(1, member.getNo());
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	//회원정보 리스트 목록(페이징)
	@Override
	public Object[] select(int pageNum, int maxSize) {
		List<Member> list = new ArrayList<Member>();
		Connection con = null;
		int listCount = 0; //게시물수
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					DBConfig.DBURL,DBConfig.ID,DBConfig.PASSWORD);

			PreparedStatement pstmt2 = con.prepareStatement(
					"select count(*) from member");
			
			ResultSet rs2 = pstmt2.executeQuery();
			rs2.next();
			listCount = rs2.getInt(1);
			
			rs2.close();
			pstmt2.close();
			
			PreparedStatement pstmt = con.prepareStatement(
					"select * from member order by no desc limit ?,?"); 
			
			pstmt.setInt(1, pageNum-1);
			pstmt.setInt(2, maxSize);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String id = rs.getString("id");
				String post = rs.getString("post");
				String address = rs.getString("address");
				String detailAddress = rs.getString("detailAddress");
				String email = rs.getString("email");
				
				Member members = new Member();
				
				members.setNo(no);
				members.setName(name);
				members.setId(id);
				members.setPost(post);
				members.setAddress(address);
				members.setDetailAddress(detailAddress);
				members.setEmail(email);
				
				list.add(members);
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Object memberListCount[]= {list, listCount};
		return memberListCount;
	}

	//회원정보 번호 조회
	@Override
	public Member selectNum(int no) {
		Member member = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					DBConfig.DBURL,DBConfig.ID,DBConfig.PASSWORD);
			
			PreparedStatement pstmt = con.prepareStatement(
					"select no,name,id,password,post,address,detailAddress,email,state from member where no=?");
			
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString("name");
				String id = rs.getString("id");
				String password = rs.getString("password");
				String post = rs.getString("post");
				String address = rs.getString("address");
				String detailAddress = rs.getString("detailAddress");
				String email = rs.getString("email");
				String state = rs.getString("state");
				
				member = new Member();
				
				member.setNo(no);
				member.setName(name);
				member.setId(id);
				member.setPassword(password);
				member.setPost(post);
				member.setAddress(address);
				member.setDetailAddress(detailAddress);
				member.setEmail(email);
				member.setState(state);
			
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return member;
	}
	
	//회원 ID,PW 조회
	@Override
	public Member selectIDPW(String id, String password) {
		Member member = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DBConfig.DBURL, DBConfig.ID, DBConfig.PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(
					"select no,name from member where id=? and password=?");

			pstmt.setString(1, id);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");

				member = new Member();
				
				member.setNo(no);
				member.setName(name);
			}
			rs.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return member;
	}

	//ID중복검사
	@Override
	public boolean usableID(String id) {
		boolean usableid = false;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DBConfig.DBURL, DBConfig.ID, DBConfig.PASSWORD); // DBURL,USER_NAME과 PASSWORD
			
			PreparedStatement pstmt = con.prepareStatement(
					"select count(*) from member where id=?");
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getInt(1)>0) {
					usableid = true;
				}
				System.out.println("DB확인"); 
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(usableid); 
		return usableid;
	}

	//회원 번호 찾기
	@Override
	public Member selectByNum(int num) {
		Member member = null; 
		Connection con = null;		 
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con=DriverManager.getConnection(DBConfig.DBURL, DBConfig.ID, DBConfig.PASSWORD);        
	        PreparedStatement pstmt = con.prepareStatement(
	        		"select no,name,id from member where no=?");
	        
	        pstmt.setInt(1, num);
	        ResultSet rs = pstmt.executeQuery();
	        if(rs.next()) {
	        	int no = rs.getInt("no");
	        	String name = rs.getString("name");
	        	String id = rs.getString("id");
	        	
	        	member = new Member();
	        	member.setNo(no);
	        	member.setName(name);
	        	member.setId(id);
	        }
	        rs.close();
	        con.close();
	   }
	   catch(Exception ex){ ex.printStackTrace(); }
		return member;
    }

}
