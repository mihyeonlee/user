<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		

		<div id="if_list_div" style="position: relative; padding: 0; overflow: hidden; height: 750px;">
			<div class="content-wrapper" style="min-height: 584px;">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<div class="container-fluid">
						<div class="row md-2">
							<div class="col-sm-6">
								<h1>회원리스트</h1>
							</div>
							<div class="col-sm-6">
								<ol class="breadcrumb float-sm-right">
									<li class="breadcrumb-item">회원리스트</li>
									<li class="breadcrumb-item">목록</li>
								</ol>
							</div>
						</div>
					</div>
				</section>
				<!-- Main content -->
				<section class="content">
					<div class="card">
					 <form id="sform" action="/member/memberSearch">
						<div class="card-header with-border">
							<button type="button" class="btn btn-primary" id="memberRegist">회원등록</button>
							<div id="keyword" class="card-tools" style="width: 550px;">
								<div class="input-group row">
									<!-- sort num -->
										<select class="form-control col-md-3" name="pageSize" id="pageSize">
											<option value="">정렬개수</option>
											<option value="3">3개씩</option>
											<option value="5">5개씩</option>
											<option value="7">7개씩</option>
										</select>
										<!-- search bar -->
										<select class="form-control col-md-3" name="searchType" id="searchType">
											<option value="">검색구분</option>
											<option value="userid">아이디</option>
											<option value="usernm">이름</option>
											<option value="alias">별명</option>
										</select> <input class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${keyword }"> <span class="input-group-append">
											<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search">
												<i class="fa fa-fw fa-search"></i>
											</button>
										</span>
									<!-- end : search bar -->
								</div>
							</div>
						</div>
					 </form>
						<div class="card-body" style="text-align: center;">
							<div class="row">
								<div class="col-sm-12">
									<table class="table table-bordered">
										<tr>
											<th>아이디</th>
											<th>패스워드</th>
											<th>별명</th>
											<th>도로주소</th>
											<th>등록날짜</th>
										</tr>
										<tbody id="memberList">
											<c:forEach items="${memberList }" var="member">
												<tr data-userid="${member.userid }">
													<td class="getUser" name="${member.userid }">${member.userid }</td>
													<td>${member.pass }</td>
													<td>${member.alias }</td>
													<td>${member.addr1 }</td>
													<td><fmt:formatDate value="${member.reg_dt }"
															pattern="yyyy-MM-dd" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<!-- col-sm-12 -->
							</div>
							<!-- row -->
						</div>
						<!-- card-body -->
						<div class="card-footer">
							<nav aria-label="member list Navigation">
								<ul class="pagination justify-content-center m-0">
									<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/member/memberSearch?page=1&pageSize=${pageSize}&searchType=${searchType }&keyword=${keyword}"><i class="fas fa-angle-double-left"></i></a></li>
									
									<c:if test="${page-1  > 0 }">
									<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/member/memberSearch?page=${page-1}&pageSize=${pageSize}&searchType=${searchType }&keyword=${keyword}"><i class="fas fa-angle-left"></i></a></li>
									</c:if>
									
									<c:if test="${page-1  == 0 }">
									<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-left"></i></a></li>
									</c:if>
									
									<c:forEach begin="1" end="${pages }" var="i">
										<c:choose>
											<c:when test="${i== page}">
												<li class="page-item active"><span class="page-link">${i }</span></li>
											</c:when>
											<c:otherwise>
												<li class="page-item "><a class="page-link" href="${pageContext.request.contextPath }/member/memberSearch?page=${i}&pageSize=${pageSize}&searchType=${searchType }&keyword=${keyword}">${i }</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									
									<c:if test="${page < pages }">
										<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/member/memberSearch?page=${page+1}&pageSize=${pageSize}&searchType=${searchType }&keyword=${keyword}"><i class="fas fa-angle-right"></i></a></li>
									</c:if>
								
									<c:if test="${page  == pages }">
										<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-right"></i></a></li>
									</c:if>
									
									
									<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/member/memberSearch?page=${pages}&pageSize=${pageSize}&searchType=${searchType }&keyword=${keyword}"><i class="fas fa-angle-double-right"></i></a></li>
								</ul>
							</nav>

						</div>
						<!-- card-footer -->
					</div>
					<!-- card  -->
				</section>
			</div>
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
		// 회원 상세정보 조회
		$('.getUser').on('click', function() {
			// data-userid <tr>태그에 적어놓음
			var userid = $(this).attr('name');
			console.log("userid:" + userid);

			document.location = "/member/view?userid=" + userid;

		});

		$('#perPageNum').on('click',function(){
			$(this).val();
		})
		
		// 회원등록
		$('#memberRegist').on('click',function(){
			document.location="/member/memberRegistView";
		})
		
		$('#searchBtn').on('click',function(){
			$('#sform').submit();
		})
		
		// 검색타입
		var searchType = "${searchType}";
		$('#searchType').val(""+searchType+"").attr("selected","selected");

		var pageSize ="${pageSize}";
		$('#pageSize').val(""+pageSize+"").attr("selected","selected");
		
		

		
	</script>

</body>
</html>







