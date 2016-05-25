<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
</c:import>

<html>
<body>


<p>&nbsp;</p>
FirstName:${(clients.firstName)}
<p>&nbsp;</p>
LastName:${(clients.lastName)}
<p>&nbsp;</p>
Email:${(clients.email) }
<p>&nbsp;</p>
Contact Info:${(clients.contactInfo) }
<p>&nbsp;</p>

	<a href='${pageContext.request.contextPath}/' class="btn btn-primary">Back</a>
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
