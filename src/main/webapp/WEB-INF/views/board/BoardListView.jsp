<%@page import="com.stone.board.common.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<% 
	List<Board> boards = (List<Board>)request.getAttribute("boards");
	boolean isLogin = (boolean)request.getAttribute("isLogin"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>

/* margin,padding 여백을 0으로 설정 및 배경 body설정 */
* {
	margin: 0;
	padding: 0;
}

body {
	padding: 30px;
	background-color: #edf1da;
	width: 1000px;
	height: 1200px;
	margin: auto;
}

#loginHome{
	list-style: none;
	text-align: right;
	margin-bottom: 1px;
	padding-top: 10px;
	padding-bottom: 0px;
	font-size: 20px;
}

#loginHome2{
	display: inline;
	margin-left: 1em;
	letter-spacing: 1px;
}

#loginHome3{
	text-decoration: none;
	color: #4d4d4d;
}

header {
	padding: 20px;
	text-align: left;
	padding-top: 5px;
	padding-bottom: 0px;
	
}

header h1 a {
	color: black;
	text-decoration: none;
}


/* nav영역 */
nav {
	margin-top: 50px;
	margin-bottom: 50px;
}

nav ul {
	list-style: none;
	border-top: 3px solid #9db589;
	border-bottom: 3px solid #9db589;
	text-align: center;
	padding-top: 10px;
	padding-bottom: 10px;
}

nav ul li {
	display: inline;
	margin-left: 3em;
	letter-spacing: 10px;
	padding-top: 10px;
	padding-bottom: 10px;
	font-size: 15px;
}

nav ul li a {
	text-decoration: none;
	color: black;
}


/* aside#left 왼쪽 */
aside#left {
	float: left;
	width: 100px;
	height : 600px;
	margin-right: 1em;
}

aside#left ul {
	list-style: none;
}

aside#left ul li a{
	color: black;
	text-decoration: none;
}

#divleft{
	background-color: #e4eac8;
	width: 150px;	
	height: 550px;
	text-align: center;
	font-size: 16.5px;
}


/* aside 가운데 본문영역*/
section#center {
	float: right;
}

#divcenter {
	background-color: #e4eac8;
	text-align: center;
	width: 800px;	
	height: 550px;
	margin-left: auto;
	margin-right: auto;
}


aside#right {
	float: right;
}

/* footer 영역 */
footer {
	clear: both;
	text-align: center;
	padding: 3em;
	background-color: #9db589;
	color: white;
}

</style>

<title>게시물 목록창</title>
</head>
<body>

<!-- 	header영역 -->
<header>
		
<ul id="loginHome">
<%if(isLogin){ %>
	<li id="loginHome2" onclick="location.href='/home'">
		<a id="loginHome3"><b>HOME</b></a>
	</li>
	
	<li id="loginHome2" onclick="location.href='/main'">
		<a id="loginHome3"><b>MAIN</b></a>
	</li>
	
	<li id="loginHome2" onclick="location.href='/logOut'">
		<a id="loginHome3"><b>LOGOUT</b></a>
	</li>

<%}else{ %>
	<li id="loginHome2" onclick="location.href='/home'">
		<a id="loginHome3"><b>HOME</b></a>
	</li>
	
	<li id="loginHome2" onclick="location.href='/main'">
		<a id="loginHome3"><b>MAIN</b></a>
	</li>
	
	<li id="loginHome2" onclick="location.href='/login'">
		<a id="loginHome3"><b>LOGIN</b></a>
	</li>
<%} %>	
</ul>
	
	<h1>
		<a href="main"><h2 align="left">KYEONG'S BLOG!<h2></a>
	</h1>
	<hr color="#9db589" width="400" size="5">

</header>


<!-- 	nav영역 -->
	<nav>
		<ul>
			<li><a href="/main"><b>HOME</b></a></li>
			<li><a href="/boardList"><b>BOARD</b></a></li>
			<li><a href="/main"><b>REPORT</b></a></li>
			<li><a href="/main"><b>COMMENT</b></a></li>
			<li><a href="/visitorList"><b>VISITOR</b></a></li>
		</ul>
	</nav>
	
	
<!-- 	aside 왼쪽영역 -->
	<aside id="left">
		<div id="divleft">
		<ul>
			<li>&nbsp;</li>
			<img src="/img/profile.png" style="width: 120px; height: 80px;">
			<li><a href="main" style="font-size: 15px; color: #9db589;">Profile</a></li>
			<li>&nbsp;</li>
			<hr color="#9db589">
			<li>&nbsp;</li>
			<li><a href="main">전체보기</a></li>
			<li style="color: #9db589">--------------</li>
			
			<li><a href="/boardList">게시판</a></li>
			<li><a href="/main">활동정보</a></li>
			<li><a href="/main">최신댓글</a></li>
			<li style="color: #9db589">--------------</li>
			<li><a href="/visitorList">방명록</a></li>			
			<li>&nbsp;</li>
			<li>&nbsp;</li>
			<li>&nbsp;</li>
			<li>&nbsp;</li>
			<li>&nbsp;</li>
			<li>&nbsp;</li>
			<hr color="#9db589">	
		</ul>
		</div>
	</aside>


<!-- aside 가운데 본문 영역 -->
<section id="center">
<div id="divcenter">
<% 
	if(boards.isEmpty()) {
%>
	<br><br><br>
	<h3>아직 작성된 글이 없습니다.</h3><br>
	<p>문득 스치는 생각이나 기분, 일기 등 다양한 이야기로 <br>
	나만의 공간을 채워보세요!</p>
<%
	}else {
%>	
	<!-- 게시물목록출력 -->
	<h1 align="center" style="padding-top: 35px; padding-bottom: 15px">게시물 목록</h1>
	<table cellpadding="1" cellspacing="1" width="700" 
		style="border: 1px solid #dddddd; margin-left:auto; margin-right:auto;">
		<thead>
		<tr>
			<th bgcolor="#9db589" width="30">번호</th>
			<th bgcolor="#9db589" width="250">제목</th>
			<th bgcolor="#9db589" width="100">닉네임</th>
			<th bgcolor="#9db589" width="100">글쓴이</th>
			<th bgcolor="#9db589" width="100">조회수</th>
		</tr>
		</thead>
		<%
			for(int i=0; i<boards.size(); i++) {
			Board board = boards.get(i);
		%>
		<tr>
		<td align="center">
		<%=board.getNo() %>
		</td>
		<td align="center">
		<a href="/boardAdd/<%=board.getNo() %>"><%=board.getTitle()%></a>
		</td>
		<td align="center">
			<%=board.getWriter() %>
		</td>
		<td align="center">
			<%=board.getWriters().getName()%>
		</td>
		<td align="center">
			<%=board.getViews()%> 
		</td>
		</tr>
		<%
			}
		%>
	</table>
<%
	} 
%>

<%
	if(isLogin){
		if((int)session.getAttribute("userNo")==1){
%>
<button style="margin-top: 10px;" onclick="location.href='/boardAdd'">새글등록</button>
<%
	} }
%>

</div>
</section>
	
<!-- 	footer영역 -->
	<footer>
		KYEONG'S BLOG.<br>
		Welcome to my blog!<br>
	</footer>

</body>
</html>