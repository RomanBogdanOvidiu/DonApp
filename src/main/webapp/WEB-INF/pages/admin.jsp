<%@page session="true"%><%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
</c:import>

<html>
<body>
	<a href='${pageContext.request.contextPath}/signUp'
		class="btn btn-primary">Create a new user</a>
	<table class="table table-striped table-bordered table-condensed">
		<tr>
			<th>UserName</th>
			<th>FirstName</th>
			<th>LastName</th>
			<th>Email</th>
			<th>Contact Info</th>

			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${(user.username)}</td>
				<td>${(user.firstName)}</td>
				<td>${(user.lastName)}</td>
				<td>${(user.email) }</td>
				<td>${(user.contactInfo) }</td>

				<td><a
					href='${pageContext.request.contextPath}/admin/edituser/${user.username}'
					class="btn btn-primary">edit</a> <a
					href='${pageContext.request.contextPath}/admin/delete/${user.username}'
					class="btn btn-primary">delete</a>
			</tr>
		</c:forEach>
	</table>

	<a href='${pageContext.request.contextPath}/' class="btn btn-info">Back</a>

	<p>&nbsp;</p>
	<!-- Footer -->
	<footer class="text-center">

		<div class="footer-below">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">Donation Web Application</div>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>

