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
							<b>회원 정보</b>
						</div>
						<!-- form start -->
						<div class="card">
							<div class="register-card-body">
								<form id="frm" role="form" class="form-horizontal" action="${cp }/member/memberUpdateView" method="POST"
					  					enctype="multipart/form-data" >
									<div class="input-group mb-3">
										<div class="mailbox-attachments clearfix" style="text-align: center; width:100%;">
											<div class="mailbox-attachment-icon has-img" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;">
												<img src="${cp}/profileImgView?userid=${memberVo.userid }"  style="width:100%; height:100%;"/>
											</div>
												<button type="button" class="btn btn-default float-right" id="profileDownBtn" >다운로드 : ${memberVo.realfilename }</button>
											
											
										</div>
										<br />
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">
											아이디
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span id="duserid" class="input-group-append-sm">${memberVo.userid }</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">
											패스워드
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">******</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">
											이름
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">${memberVo.usernm }</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">
											별명
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">${memberVo.alias }</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">
											주소
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">${memberVo.addr1}</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">
											상세주소
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">${memberVo.addr2}</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">
											우편번호
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">${memberVo.zipcode }</span>
										</div>
									</div>
									
									<div class="card-footer">
										<div class="row">
											<div class="col-sm-6">
												<a href="${cp }/member/memberUpdateView?userid=${memberVo.userid}">
												<button type="button" id="updateBtn" class="btn btn-info">수정</button>
												</a>
												<button type="button" id="deleteBtn" class="btn btn-info" name="${memberVo.userid }">삭제</button>
											</div>

											<div class="col-sm-6">
												<button type="button" id="cancelBtn" class="btn btn-default float-right">&nbsp;&nbsp;&nbsp;목 &nbsp;&nbsp;록&nbsp;&nbsp;&nbsp;</button>
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
	
	<script>
		$('#cancelBtn').on('click',function(){
			document.location="/member/memberList"
		})
	
		$('#deleteBtn').on('click',function(){
			if(confirm("삭제한 회원은 복구할 수 없습니다. 정말 삭제하시겠습니까?")){
				var userid = $(this).attr('name');
				deleteMember(userid);
			}else{

			}
		})
	


		$('#profileDownBtn').on("click",function(){
			document.location="/profileImgDownloadView?userid=${memberVo.userid}"
		})
		
		function deleteMember(userid){
			$.ajax({url : "/member/memberDelete",
					data : {userid:userid},
					method : "GET",
					success : function(data){
						document.location="/member/memberList"
					}
			})
		}

		
	</script>
</body>
</html>







    