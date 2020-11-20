<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
	<%@include file="/WEB-INF/views/layout/commonLib.jsp" %>

	
</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">

		<!-- Navbar -->
		<%@include file="/WEB-INF/views/layout/main_header.jsp" %>
		<!-- /.navbar -->


		<!-- Main Sidebar Container -->
		<%@include file="/WEB-INF/views/layout/left.jsp" %>


		<div id="if_list_div" style="position: relative; padding: 0; overflow: hidden;">
			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">

				<!-- Main content -->
				<section class="content register-page" style="height:100%;">
					<div class="container-fluid">
						<div class="login-logo">
							<b>회원 수정</b>
						</div>
						<!-- form start -->
						<div class="card">
							<div class="register-card-body">
								<form id="frm" role="form" class="form-horizontal" action="${cp }/member/memberUpdate" method="POST"
					  					enctype="multipart/form-data" >
									<div class="input-group mb-3">
										<div class="mailbox-attachments clearfix" style="text-align: center; width:100%;">
											<div class="mailbox-attachment-icon has-img" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;">
												<img id="pictureViewImg" style="width:100%; height:100%;"/>
											</div>
											<div class="mailbox-attachment-info">
												<div class="input-group input-group-sm">
													<input id="realFilename" class="form-control"
														   type="file" name="realFilename" accept=".gif, .jpg, .png" style="height:37px;"/>
												</div>
											</div>
										</div>
										<br />
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">
											아이디
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span  class="input-group-append-sm">${memberVo.userid }</span>
											<input class="form-control" name="userid" type="hidden" class="form-control" value="${memberVo.userid }" />
										</div>
									</div>
									
									<div class="form-group row">
										<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>패스워드</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="pass" type="password" class="form-control" id="pass" placeholder="비밀번호" />
										</div>
									</div>
									
									<div class="form-group row">
										<label for="name" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>이 름
										</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="usernm" type="text" id="usernm" placeholder="이름" value="${memberVo.usernm }"/>
										</div>

									</div>
									<div class="form-group row">
										<label for="alias" class="col-sm-3" style="font-size: 0.9em;">별명</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="alias" type="text" id="alias" placeholder="별명" value="${memberVo.alias }">
										</div>
									</div>
									<div class="form-group row">
										<label for="addr1" class="col-sm-3 control-label">주소</label>
										<div class="col-sm-3 input-group-sm">
											<input id="addr1" name="addr1" type="text" class="form-control" placeholder="주소" readonly value="${memberVo.addr1 }">
										</div>
										<div class="col-sm-3 input-group-sm">
											<input id="addr2" name="addr2" type="text" class="form-control" id="addr2" placeholder="상세주소" value="${memberVo.addr2 }">	
										</div>
										
										<div class="col-sm-2 input-group-sm">
											<input name="zipcode" type="text" class="form-control" id="zipcode" placeholder="우편번호" readonly value="${memberVo.zipcode }">
										</div>
										<div class="col-sm-1 input-group-sm">
											<span class="input-group-append-sm">
												<button type="button" id="zipcodeBtn" class="btn btn-info btn-sm btn-append">주소검색</button>
											</span>
										</div>
									</div>
									
									<div class="card-footer">
										<div class="row">
											<div class="col-sm-6">
												<button type="button" id="updateBtn" class="btn btn-info">수정</button>
											</div>

											<div class="col-sm-6">
											  <a href="/member/view?userid=${memberVo.userid }">
												<button type="button" id="cancelBtn" class="btn btn-default float-right">&nbsp;&nbsp;&nbsp;취 &nbsp;&nbsp;소&nbsp;&nbsp;&nbsp;</button>
											  </a>
											</div>

										</div>
									</div>
								</form>
							</div>
							<!-- register-card-body -->
						</div>
					</div>
				</section>
				<!-- /.content -->
			</div>
			<!-- /.content-wrapper -->
		</div>
	</div>

	<!-- Main Footer -->
	<footer class="main-footer">
		<!-- To the right -->
		<div class="float-right d-none d-sm-inline">Anything you want</div>
		<!-- Default to the left -->
		<strong>Copyright &copy; 2014-2019 <a href="https://adminlte.io">AdminLTE.io</a>.
		</strong> All rights reserved.
	</footer>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->

	<!-- jQuery -->
	<script src="/resources/bootstrap/plugins/jquery/jquery.min.js"></script>

	<!-- Bootstrap 4 -->
	<script src="/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="/resources/bootstrap/dist/js/adminlte.min.js"></script>
	<!-- 우편번호검색 -->
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	
	<script>
		$("#zipcodeBtn").on('click',function(){
			new daum.Postcode({
				oncomplete : function(data) {
					console.log(data);
	
					$('#addr1').val(data.roadAddress);
					$('#zipcode').val(data.zonecode);
				}
			}).open();
		});
	
		$("#updateBtn").on('click',function(){
			var userid = $('#userid').val();
			var usernm = $('#usernm').val();
			var pass = $('#pass').val();

			
			if(userid=="" || usernm=="" ||pass==""){
				alert("id, pass, 이름은 필수입력사항입니다.")
			}else{
				$("#frm").submit();
			}
		});

		
		// picture input의 파일 변경시 이벤트 
		$("#realFilename").change(function(){
			  readURL(this);
		});
		
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
			  
				reader.onload = function (e) {
					$('#pictureViewImg').attr('src', e.target.result);  
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
	</script>
</body>
</html>







    