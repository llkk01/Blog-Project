<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Boolean usable = (Boolean)request.getAttribute("usable");
	String id = (String)request.getAttribute("id");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>

body {
	padding: 30px;
	background-color: #edf1da;
	width: 500px;
	height: 700px;
	margin: auto;
	font-family: Arial, Helvetica, sans-serif;
}

form {
	
}


input{
	width: 70%;
	padding: 10px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #9db589;
	box-sizing: border-box;
}

#check{
	width: 20%;
}


input[type=button]{
	width: 20%;
}

button{
	background-color: #9db589;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
	font-size: 15px;
}


button:hover {
	opacity: 0.8;
}

.cancelbtn {
	width: auto;
	padding: 10px 10x;
	background-color: ##9db589;
}


.container {
	padding: 20px;
}

.cancelbtn2 {
	width: auto;
	padding: 10px 10x;
	background-color: #9db589;
	float: right;
}

@media screen and (max-width: 300px) {
	span.add {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
  }
}

</style>


<script>

	//id 사용
	function idUse(){
		var id = document.querySelector("#id").value;
		opener.ids(id);
		close();
	}
	
	//취소버튼 클릭시 창 닫기
	function back(){
			window.close();
	}
	
</script>

<title>Insert title here</title>
</head>
<body>

<h2 align="center">아이디 중복검사</h2>
<form action="/id" method="post">
	아이디<br>
	<input type="text" name="id" id="id" value="<%=(id==null)?"":id%>"/>
		<input type="submit" value="중복검사" id="check"/>
		<br>
</form>

<!-- 사용가능여부/사용,취소 버튼 생성 -->
<% 
	if(usable!=null) {
		if(usable){
%>
	사용 가능한 id입니다. <input type="button" onclick="idUse()" value="사용"/>
<%
	} else {
%>
	id가 이미 사용 중 입니다.
<%
	} }
%>
	<input type="button" value="취소" onclick="back()"/>
	
</body>
</html>