<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="by.htp.ex.localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="header.newsManagement"
	var="header_newsManagement" />
<fmt:message bundle="${loc}" key="header.enLink" var="header_enLink" />
<fmt:message bundle="${loc}" key="header.ruLink" var="header_ruLink" />
<fmt:message bundle="${loc}" key="header.enterEmail"
	var="header_enterEmail" />
<fmt:message bundle="${loc}" key="header.enterPassword"
	var="header_enterPassword" />
<fmt:message bundle="${loc}" key="header.registrationLink"
	var="header_registrationLink" />
<fmt:message bundle="${loc}" key="header.singInButton"
	var="header_singInButton" />
<fmt:message bundle="${loc}" key="header.signOutButton"
	var="header_signOutButton" />

<div class="wrapper">
	<div class="newstitle">${header_newsManagement}</div>

	<div class="local-link">

		<div align="right">

			<a href="controller?command=change_locale&locale=en">
				${header_enLink} </a> &nbsp;&nbsp; <a
				href="controller?command=change_locale&locale=ru">
				${header_ruLink} </a> <br /> <br />
		</div>

		<c:if test="${not (sessionScope.user eq 'active')}">

			<div align="right">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_in" />
					${header_enterEmail} <input type="text" name="email" value="" /><br />
					${header_enterPassword} <input type="password" name="password"
						value="" /><br />

					<c:if test="${not (requestScope.AuthenticationError eq null)}">
						<font color="red"> <c:out
								value="${requestScope.AuthenticationError}" />
						</font>
					</c:if>

					<a href="controller?command=go_to_registration_page">${header_registrationLink}</a>
					<input type="submit" value="${header_singInButton}" /><br />
				</form>
			</div>

		</c:if>

		<c:if test="${sessionScope.user eq 'active'}">

			<div align="right">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_out" /> <input
						type="submit" value="${header_signOutButton}" /><br />
				</form>
			</div>

		</c:if>
	</div>

</div>
