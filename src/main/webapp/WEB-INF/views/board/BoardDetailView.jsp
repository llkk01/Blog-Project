<%@page import="java.util.List"%>
<%@page import="com.stone.board.common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
    
<%
	Board board = (Board)request.getAttribute("board");
	Boolean isWriters = (Boolean)request.getAttribute("isWriters");
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


<script>
	
	//admin 확인
	function adminCheck() {
		var id = document.getElementNyId("admin");
		if(id==member.getName.)
		alert('hi');
		
	}
	
</script>

<title>게시물 상세창</title>
</head>
<body>

<!-- 	header영역 -->
<header>
		
<ul id="loginHome">
	<li id="loginHome2" onclick="location.href='/home'">
		<a id="loginHome3"><b>HOME</b></a>
	</li>
	
	<li id="loginHome2" onclick="location.href='/main'">
		<a id="loginHome3"><b>MAIN</b></a>
	</li>
	
	<li id="loginHome2" onclick="location.href='/logOut'">
		<a id="loginHome3"><b>LOGOUT</b></a>
	</li>
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

<h1 align="center" style="padding-top: 35px; padding-bottom: 15px">게시물 상세</h1>
	<form action="/boardUpdate?no=<%=board.getNo() %>" method="post">
	<table border="1" cellpadding="1" cellspacing="1" width="650" 
		style="border: 1px solid #dddddd; margin-left:auto; margin-right:auto;">
	<tr>
		<td align="center" bgcolor="#9db589" width="70">번호</td>
		<td align="left"> &nbsp;<%=board.getNo()%></td>
	</tr>
	<tr>
		<td align="center" bgcolor="#9db589" width="70">제목</td>
		<td align="left"> 
		<input type="text" name="title" size="80" style="margin-left: 4px" value="<%=board.getTitle()%>"/>
		</td>
	</tr>
	<tr>
		<td align="center" bgcolor="#9db589" width="70">내용</td>
		<td align="left">
		<textarea rows="10" cols="81" name="contents" style="margin-left: 4px">
		<%=board.getContents() %>
		</textarea>
		</td>
	</tr>
	<tr>
		<td align="center" bgcolor="#9db589" width="70">닉네임</td>
		<td align="left"> 
		<input type="text" name="writer" size="80" style="margin-left: 4px" value="<%=board.getWriter()%>"/>
	</td>
	<tr>
		<td align="center" bgcolor="#9db589" width="70">작성자</td>
		<td align="left"> &nbsp;<%=board.getWriters().getName()%>
	</td>
	<tr>
		<td align="center" bgcolor="#9db589" width="70">조회수</td>
		<td align="left"> &nbsp;<%=board.getViews()%>
	</td>
	<tr>
		<td align="center" bgcolor="#9db589" width="70">날짜</td>
		<td align="left"> &nbsp;<%=board.getRdate()%>
	</td>
	</tr>

</table>


<%
	if(isWriters!=null && isWriters==true){
%>
	<input type="submit" value="수정하기"/>
	<a onclick="location.href='/boardDelete?no=<%=board.getNo() %>'">
		<input type="button" value="삭제하기">
	</a>
<%
	}
%>	
<input type="button" value="목록가기" style="margin-top: 10px" onclick="location.href='/boardList'">
<!-- <button style="margin-top: 10px" onclick="location.href='/boardList'">목록가기</button> -->
</form>

</div>
</section>
	
<!-- 	footer영역 -->
	<footer>
		KYEONG'S BLOG.<br>
		Welcome to my blog!<br>
	</footer>


</body>
</html>