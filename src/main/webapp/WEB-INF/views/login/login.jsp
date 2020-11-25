<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- icheck bootstrap -->
<!-- Theme style -->
<link rel="stylesheet"
	href="/resources/bootstrap/dist/css/adminlte.min.css">
<!-- Google Font: Source Sans Pro -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">




<style>
body.login-page {
	/* 	background-image: url('/resources/images/intro.jpg'); */
	background-position: center;
	background-size: cover;
	background-repeat: no-repeat;
}
</style>

</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="#"><b>관리자 로그인</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">Sign in to start your session</p>

				<form action="/login/process" method="post">
					<div class="form-group has-feedback">
						<input type="text" class="form-control" name="userid"
							placeholder="아이디를 입력하세요." value="" id="inputEmail"> <span
							class="glyphicon glyphicon-envelope form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" class="form-control" name="pass"
							placeholder="패스워드를 입력하세요." value=""> <span
							class="glyphicon glyphicon-lock form-control-feedback"></span>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="checkbox icheck">
								<label> <input type="checkbox" name="remember" value="">
									Remember Me
								</label>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-sm-4">
							<button type="submit" class="btn btn-primary btn-block btn-flat"
								id="signIn">로그인</button>
						</div>
						<!-- /.col -->
					</div>
				</form>

			</div>
			<!-- /.login-box-body -->
		</div>
	</div>
	<!-- /.login-box -->
	
	<c:if test="${userId eq null}">
		<!-- 카카오로그인버튼 -->
		<a id="custom-login-btn" href="https://kauth.kakao.com/oauth/authorize?client_id=fb1e3340689135a8cf8184caf0474ec3&redirect_uri=http://192.168.44.62:80/login/kakao&response_type=code"> 
			<img src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg" width="222" />
		</a>
    </c:if>
    <c:if test="${userId ne null}">
        <h1>로그인 성공입니다</h1>
        <input type="button" value="로그아웃" onclick="location.href='/logout'">
    </c:if>
	
	




	<!-- jQuery -->
	<script src="/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="/resources/bootstrap/dist/js/adminlte.min.js"></script>
	<script type="text/javascript"
		src="/resources/bootstrap/dist/js/js.cookie-2.2.1.min.js"></script>
	<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
	<script>
		$(function() {
			// 1. REMEMBERME쿠키값이 Y로 설정되어 있는지 확인
			// 2. REMEMBERME체크박스를 체크상태로 변경
			// 3. USERNM쿠키값을 확인하여 inputEmail아이디값을 가진 input태그에 추가
			if (Cookies.get('REMEMBERME') == 'Y') {
				$('input[name="remember"]').attr('checked', true);

				var usernm = Cookies.get("USERNM");

				$('#inputEmail').val(usernm);
			}

			//signin 버튼이 클릭되었을때  
			$('#signIn').on('click', function() {

				//1. Remember Me 체크박스가 체크되어 있으면
				//2. USERNM쿠키를 inputMail input 태그에 입력된 값으로 설정
				//3. REMEMBERME쿠키값을 Y로 설정
				if ($('input[name="remember"]').prop('checked')) {

					var mail = $('#inputEmail').val();
					Cookies.set("USERNM", mail);
					Cookies.set("REMEMBERME", "Y");

					//4. Remember me 체크박스가 체크안되어 있으면 
					//5. RMEMBERME, USERNM 쿠키를 삭제 
				} else {
					Cookies.remove("USERNM");
					Cookies.remove("REMEMBERME");
				}

				//6. form태크에 대한 submit처리 (추후에)
				$('form').submit();

			})//signin버튼

		})//function

		//쿠키값 가져오기
		function getCookieValue(cookieName) {
			var cookieValue;
			var cookies = document.cookie.split("; ")

			for (i = 0; i < cookies.length; i++) {
				var cookie = cookies[i].split("=");

				if (cookie[0] == cookieName) {
					cookieValue = cookie[1];
					return cookieValue;
				}
			}
			return "없는 쿠키입니다";
		}

		//쿠키 추가(값 설정)
		function setCookie(cookieName, cookieValue, expires) {
			var today = new Date();

			//현재날짜에서 미래로 + expires만큼 한 날짜 구하기
			today.setDate(today.getDate() + expires);

			//넣고싶은걸 쿠키에 대입하면 들어간다.
			// 			document.cookie = cookieName + "=" + endcodeURIComponent(cookieValue) + "; path=/; expires=" + today.toGMTString();
			document.cookie = cookieName + "=" + cookieValue
					+ "; path=/; expires=" + today.toGMTString();
			console.log(document.cookie);

		}

		//쿠키 삭제 - 해당쿠키의 expires속성을 과거날짜로 변경 
		function deleteCookie(cookieName) {
			setCookie(cookieName, "", -1);
		}

		function componentDidMount() {
			Kakao.init('618ebf186b4df4f606b91208afbebec7');

		}
	</script>


</body>
</html>








