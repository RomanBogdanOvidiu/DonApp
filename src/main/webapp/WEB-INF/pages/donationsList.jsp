<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
</c:import>

<html>

<head>


<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<script src="<c:url value="/resources/core/jquery.1.10.2.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.autocomplete.min.js" />"></script>

</head>
<body>
<p>&nbsp;</p>
	<div align="center">
		<form:form method="post"
			action="${pageContext.request.contextPath}/user/donation/search"
			modelAttribute="search" cssClass="form-horizontal">
			<label class="control-label" for="donationsTitle"><strong></strong></label>
			<div class="controls">
				<form:input id="w-input-search" path="donationsTitle"
					cssClass="span3" cssErrorClass="error" />
			</div>

			<input id="w-button-search" type="submit" class="btn" value="Search"> <a
				href="${pageContext.request.contextPath}/user/donation/search"></a>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form:form>

		</span>
	</div>

	<script>
		$(document)
				.ready(
						function() {

							$('#w-input-search')
									.autocomplete(
											{
												serviceUrl : '${pageContext.request.contextPath}/getDonations',
												paramName : "donationsTitle",
												delimiter : ",",
												transformResult : function(
														response) {

													return {
														//response=response;
														//$("#div1").html(response);
														suggestions : $
																.map(
																		$
																				.parseJSON(response),
																		function(
																				item) {

																			return {
																				value : item.donationsTitle,
																				data : item.donationsId
																			};
																		})

													};

												}

											});
						});
	</script>
	<h2>
		<div id="w-input-search"></div>
	</h2>
	<table class="table table-striped table-bordered table-condensed">
		<tr>
			<th>Title</th>
			<th>Donor Name</th>
			<th>City</th>
		</tr>
		<c:forEach items="${donations}" var="donation">
			<tr>
				<td><a
					href='${pageContext.request.contextPath}/user/donation/view/${donation.donationsId}'>${(donation.donationsTitle)}</a></td>


				<td>${(donation.user.username)}</td>
				<td>${(donation.city) }</td>

				<!-- <td><a
					href='${pageContext.request.contextPath}/user/donation/view/${donation.donationsId}'
					class="btn btn-primary"> View Details</a></td> -->
			</tr>
		</c:forEach>
	</table>
	<a href='${pageContext.request.contextPath}/user/donation' class="btn btn-info">Back
	</a>

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


