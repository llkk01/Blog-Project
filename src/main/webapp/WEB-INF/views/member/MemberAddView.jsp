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
}


.container {
	padding: 20px;
}

.cancelbtn2 {
	width: auto;
	padding: 10px 10x;
	margin-left: 384px;
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


<title>회원가입</title>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
	//우편번호 조회(다음에서 제공하는 src활용)
	function postReference(){
		new daum.Postcode({
        	oncomplete: function(data) {
            	document.querySelector("#post").value=data.zonecode;
            	document.querySelector("#address").value=data.roadAddress;
            	document.querySelector("#detailAddress").focus();
       	 }
  	  }).open();
	}
	
	//id 중복검사
	function idCheck() {
		document.querySelector("#id").value="";
		window.open("/id","","width=600,height=400");
	}
	
	//id 받기
	function ids(id) {
		document.querySelector("#id").value=id;
	}
	
	/* onsubmit true/false에 따라 submit버튼 동작여부 이벤트 발생(name,id,password 필수입력 이벤트) */
	//회원등록()
	function memberADD() {
		let name = document.querySelector("#name").value;
		if(name.trim()==""){
			alert("성명을 입력해주세요.");
			return false; //false > submit 실행안함
		}
		
		let id = document.querySelector("#id").value;
		if(id.trim()==""){
			alert("id를 입력해주세요.");
			return false; //false > submit 실행안함
		}
		
		let password = document.querySelector("#password").value;
		if(password.trim()==""){
			alert("password를 입력해주세요.");
			return false; //false > submit 실행안함
		}
		
		let password2 = document.querySelector("#password2").value;
		if(password2.trim()==""){
			alert("위에 입력한 password를 다시 입력해주세요.");
			return false; //false > submit 실행안함
		}
		
		if(password!=password2){
			alert("password는 동일해야 합니다.");
			return false; //false > submit 실행안함
		}
		
		//email 하나로 합치기
		let email1 = document.querySelector("#email1").value;
		let email2 = document.querySelector("#email2").value;
		let email = document.querySelector("#email");
		email.value=email1+"@"+email2;
		
		return true; //submit 동작
	}
	
</script>

</head>
<body>

<h2 align="center" onclick="location.href='/main'">BLOG 회원가입</h2>
	<form action="/memberAdd" method="post" onsubmit="return memberADD()">
	<div class="container"> 
		이름 <input type="text" name="name" id="name" placeholder="이름을 입력해주세요"/><br>
		ID <br><input type="text" name="id" id="id"  placeholder="ID를 입력해주세요" />
      		<input type="button" class="cancelbtn" onclick="idCheck()" value="ID중복검사" /><br>
		Password <input type="password" name="password" id="password" placeholder="비밀번호를 입력해주세요"/><br>
		Password 재확인 <input type="password" name="password2" id="password2" placeholder="비밀번호를 한번 더 입력해주세요"/><br>
		우편번호 <br><input type="text" name="post" id="post" required placeholder="우편번호를 조회해주세요"/>
     		<input type="button" class="cancelbtn" onclick="postReference()" value="우편번호조회" /><br>
		주 소 <input type="text" name="address" id="address" size=30 required placeholder="주소를 입력해주세요"/><br>
		상세 주소 <input type="text" name="detailAddress" id="detailAddress" required placeholder="상세 주소를 입력해주세요"/><br>
		Email <br><input type="text" id="email1" required placeholder="예: email" />&nbsp;<b>@</b> &nbsp;
				<select id="email2">
				<option value="naver.com">naver.com</option>
				<option value="daun.net">daun.net</option>
				<option value="gmail.com">gmail.com</option>
				<option value="직접입력">직접입력</option>
				</select>
		<input type="hidden" name="email" id="email"><br>
		<input type="submit" value="회원가입">
		<a class="cancelbtn" onclick="location.href='/main'">
		<input type="button" value="홈으로">
		</a>
		<a class="cancelbtn2" onclick="location.href='/login'">
		<input type="button" value="로그인">
		</a>
	
	</div>
	</form>

</body>
</html>