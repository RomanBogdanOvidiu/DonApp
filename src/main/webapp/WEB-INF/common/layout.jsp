<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>



<!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/freelancer.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">




<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="author" content="">

<style type="text/css">
.container {
	text-align: center;
}

.nav {
	float: left;
	text-align: right;
}
</style>
</head>




<body id="page-top" class="index">

	<div align="center">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header page-scroll">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>

				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">

						<li class="hidden"><a href="#page-top"></a></li>

						<li class="page-scroll"><a href="">Welcome
								${(pageContext.request.userPrincipal.name)}</a></li>

						<li class="page-scroll"><sec:authorize
								access="hasRole('ROLE_ADMIN') ">

								<a href='${pageContext.request.contextPath}/admin/adm'>Admin
									Page</a>

							</sec:authorize></li>

						<li class="page-scroll"><sec:authorize
								access="hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')">
								<a
									href='${pageContext.request.contextPath}/donation/${pageContext.request.userPrincipal.name}'>Make
									a donation</a>
							</sec:authorize></li>

						<li class="page-scroll"><a
							href="${pageContext.request.contextPath}/user/donation">Donations
								List</a></li>

						<li class="page-scroll"><sec:authorize
								access="hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')">
								<a
									href="${pageContext.request.contextPath}/user/donation/${pageContext.request.userPrincipal.name}">Check
									Your Donations</a>
							</sec:authorize></li>

						<li class="page-scroll"><a
							href='${pageContext.request.contextPath}/'>Home</a></li>

						<li class="page-scroll"><c:choose>
								<c:when test="${pageContext.request.userPrincipal.name != null}">
									<a href='${pageContext.request.contextPath}/logout'>Logout</a>
									<br />
								</c:when>
								<c:otherwise>
									<a href='${pageContext.request.contextPath}/logout'>Login</a>
									<br />
								</c:otherwise>
							</c:choose></li>

						<li class="page-scroll"><c:choose>
								<c:when test="${pageContext.request.userPrincipal.name != null}">
									<a href=''></a>
									<br />
								</c:when>
								<c:otherwise>
									<a>Don't have an account ?</a>
									<a href='${pageContext.request.contextPath}/signUp'>Register
										now!</a>
									<br />
								</c:otherwise>
							</c:choose></li>


					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>


		<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
		<div class="scroll-top page-scroll visible-xs visible-sm">
			<a class="btn btn-primary" href="#page-top"> <i
				class="fa fa-chevron-up"></i>
			</a>
		</div>

		<div class="content">
			<div class="page-header"></div>
			<div class="row">${param.body}</div>
		</div>
		<p>&nbsp;</p>
		<!-- Header -->
		<header>
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="intro-text">
							<span class="name">Donations Application</span>
							<hr class="star-light">
							<span class="skills">“It's not how much we give but how
								much love we put into giving.”</span>
							<p>&nbsp;</p>
							<span class="owner">- Mother Teresa</span>
						</div>
					</div>
				</div>
			</div>
			<p>&nbsp;</p>
		</header>



		<!-- jQuery -->
		<script
			src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script
			src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

		<!-- Plugin JavaScript -->
		<script
			src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/classie.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/cbpAnimatedHeader.js"></script>

		<!-- Contact Form JavaScript -->
		<script
			src="${pageContext.request.contextPath}/resources/js/jqBootstrapValidation.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/contact_me.js"></script>

		<!-- Custom Theme JavaScript -->
		<script
			src="${pageContext.request.contextPath}/resources/js/freelancer.js"></script>
</body>

</html>