<%@page import="com.stone.member.common.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    List<Member> members = (List<Member>)request.getAttribute("members");
	//현재페이지번호
	int pageNo = (int)request.getAttribute("pageNo");
	//마지막페이지번호
	int lastPageNo = (int)request.getAttribute("lastPageNo");
	//블럭당최대페이지수
	int maxSize = 5;
	//블럭시작페이지번호 
	int firstPageNo = pageNo - ((pageNo-1) % maxSize);
	//예상블럭끝페이지번호
	int predictFinallyPageNos = firstPageNo + (maxSize-1);
	//블럭끝페이지번호
	int finallyPageNo = (predictFinallyPageNos<=lastPageNo) ? predictFinallyPageNos : lastPageNo;
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

b:hover {
  text-decoration: underline;
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

<title>회원목록</title>
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
		
	<h1 align="center" style="padding-top: 35px; padding-bottom: 15px">회원 목록</h1>
<!-- 	<div style="margin: auto; text-align:center;"> -->
		
		<table cellpadding="1" cellspacing="1" width="700" 
		style="border: 1px solid #dddddd; margin-left:auto; margin-right:auto;">
		<tr>
			<td align="right">
				<select name="searchlist" >
					<option value="all">전체</option> 
                	<option value="no">회원번호</option> 
                	<option value="title">이름</option> 
                	<option value="writer">아이디</option> 
				</select>
				<input name="searchkeyword" type="text" />
				<input type="button" value="검색"/>
			</td>
		</tr>
	</table>

	<table cellpadding="1" cellspacing="1" width="700" 
		style="border: 1px solid #dddddd; margin-left:auto; margin-right:auto;">
	<thead>
		<tr>
			<th bgcolor="#9db589" width="50">번호</th>
			<th bgcolor="#9db589" width="100">이름</th>
			<th bgcolor="#9db589" width="100">아이디</th>
		</tr>		
	</thead>

		<%
			for (int i = 1; i <= members.size(); i++) {
			Member member = members.get(i - 1);
		%>	
		<tr>
			<td align="center">
				<%=member.getNo() %>
			</td>
			<td align="center">
				<a href="memberDetail?no=<%=member.getNo()%>"> <%=member.getName()%>
			</a>
			</td>
			<td align="center">
				<%=member.getName() %>
			</td>
		</tr>
		<%
			}
		%>
	</table>


<!-- 	<< 이동출력 -->
	<%
		if(firstPageNo>1){
	%>
		<a href="memberList?pageno=<%=firstPageNo-1%>"> << </a>
	<% } %>
	
<!-- 	페이지번호출력 -->
	<%
		for(int pageNum = firstPageNo; pageNum<=finallyPageNo; pageNum++){
			//해당 페이지일때[]를 출력하기 위한 작업([1][2]...)
			String outPageNum = (pageNum==pageNo)?"["+pageNum+"]":" "+pageNum+" ";
	%>
	
			<a href="memberList?pageno=<%=pageNum%>">
			<%=outPageNum%>
			</a> &nbsp;&nbsp;
	<%		
		}
	%>
	
	<!-- 	>> 이동출력 -->
	<%
		if(finallyPageNo<lastPageNo){
	%>
		<a href="memberList?pageno=<%=lastPageNo+1%>"> >> </a>
	<% } %>
	
</div>	
</section>


<!-- 	footer영역 -->
	<footer>
		KYEONG'S BLOG.<br>
		Welcome to my blog!<br>
	</footer>



</body>
</html>