<%@ page import="com.users.model.Donation"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
</c:import>

<html>
<body>
	<div align="center">
		<form:form method="post"
			action="${pageContext.request.contextPath}/donation/${donation.user.username}"
			modelAttribute="donation" enctype="multipart/form-data"
			cssClass="form-horizontal">


			<label class="control-label" for="donationsTitle"><strong>Title</strong></label>
			<div class="controls">
				<form:input path="donationsTitle" cssClass="span3"
					cssErrorClass="error" />
			</div>


			<label class="control-label" for="donationsDesc"><strong>Description</strong></label>
			<div class="controls">
				<form:input path="donationsDesc" cssClass="span3"
					cssErrorClass="error" />
			</div>

			<label class="control-label" for="city"><strong>City</strong></label>
			<div class="controls">
				<form:input path="city" cssClass="span3" cssErrorClass="error" />
			</div>
			
			<label for="image">Image (in JPEG format only):</label>
 				<input name="image" type="file"/>
 				
 				
 				
 				
 				
 				
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<form:hidden path="donationsId" />

			<input type="submit" class="btn" value="Submit">&nbsp; <a
				href="${pageContext.request.contextPath}/donation/${donation.user.username}"></a>





		</form:form>
	</div>
</body>
</html>