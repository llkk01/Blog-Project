<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>

body {
	padding: 30px;
	background-color: #edf1da;
	width: 700px;
	height: 900px;
	margin: auto;
	font-family: Arial, Helvetica, sans-serif;
}

form {
	border: 6px solid #9db589;
}


input{
	width: 100%;
	padding: 10px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #9db589;
	box-sizing: border-box;
}

#id{
	width: 75%;
}

#post{
	width: 75%;
}

#email1{
	width: 60%;
}

#email2 {
	width: 34%;
    padding: 7px 10px;
	font-size: 15px;
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

#ano{
	color: black;
	text-decoration: none;
	background-color: #9db589;
}

#ano:hover{
	color: white;
	text-decoration: underline;		
}

</style>

<title>회원가입 완료</title>
</head>
<body>

	<div class="container" style="text-align: center;">  
	<h2 align="center">회원가입이 완료되었습니다.</h2><br>
		<a href="/login" id="ano">로그인</a>
		<a href="/main" id="ano">홈으로</a>
	</div>

</body>
</html>