<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="by.htp.ex.localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="registration.enterName"
	var="registration_enterName" />
<fmt:message bundle="${loc}" key="registration.enterSurname"
	var="registration_enterSurname" />
<fmt:message bundle="${loc}" key="registration.enterLogin"
	var="registration_enterLogin" />
<fmt:message bundle="${loc}" key="registration.enterPassword"
	var="registration_enterPassword" />
<fmt:message bundle="${loc}" key="registration.enterEmail"
	var="registration_enterEmail" />
<fmt:message bundle="${loc}" key="registration.applyButton"
	var="registration_applyButton" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="controller" method="post">
		<input type="hidden" name="command" value="do_registration" />
		${registration_enterName}<br /> <input type="text" name="firstName"
			value="" /><br /> ${registration_enterSurname}<br /> <input
			type="text" name="surname" value="" /><br />

		${registration_enterLogin}<br /> <input type="text" name="login"
			value="" /><br /> ${registration_enterEmail}<br /> <input
			type="text" name="email" value="" /><br />
		${registration_enterPassword}<br /> <input type="password"
			name="password" value="" /><br />

		<c:if test="${not (requestScope.RegistrationError eq null)}">
			<font color="red"> <c:out
					value="${requestScope.RegistrationError}" />
			</font>
			<br />
		</c:if>

		<input type="submit" value="${registration_applyButton}"><br />

	</form>
</body>
</html>