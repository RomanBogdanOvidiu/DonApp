<%@ page import="com.users.model.User"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8"> </c:import>


<html>
<body>
Insufficient founds! You don't have enough money to pay that bill !!!

<a  href="${pageContext.request.contextPath}/">Back to main page</a>
			

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