<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
</c:import>
<html>
<body>
	
	<table class="table table-striped table-bordered table-condensed">
		<tr>
			<th>DonationID</th>
			<th>Title</th>
			<th>Description</th>
			<th>City</th>

		</tr>
		<c:forEach items="${donationes}" var="donation">
			<tr>
				<td>${(donation.donationsId)}</td>
				<td>${(donation.donationsTitle)}</td>
				<td>${(donation.donationsDesc)}</td>
				<td>${(donation.city)}</td>

				<td><a
					href='${pageContext.request.contextPath}/user/donation/edit/${donation.donationsId}'
					class="btn btn-primary">edit</a> <a
					href='${pageContext.request.contextPath}/user/donation/delete/${donation.donationsId}'
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
