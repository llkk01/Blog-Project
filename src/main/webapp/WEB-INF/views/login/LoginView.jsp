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
	width: 800px;
	height: 1000px;
	margin: auto;
	font-family: Arial, Helvetica, sans-serif;
}

form {
	border: 6px solid #9db589;
}

input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #9db589;
	box-sizing: border-box;
}

button {
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
	background-color: ##9db589;
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

</head>
<body>

<h2 align="center">BLOG ID로그인</h2>

<form action="/login" method="post">

  <div class="container">
    <label for="name"><b>ID</b></label>
    <input type="text" placeholder="Enter ID" id="id" name="id" required>

    <label for="password"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" id="password" name="password" required>
        
    <button type="submit">Login</button>
    <label>
      <input type="checkbox" checked="checked" name="remember">ID저장
    </label>
  </div>

  <div class="container" style="background-color:#edf1da">
    <button type="button" class="cancelbtn" onclick="location.href='/main'">홈으로</button>
    <button type="button" class="cancelbtn2" onclick="location.href='/memberAdd'">회원가입</button>
  </div>
  
</form>


</body>
</html>
