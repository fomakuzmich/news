<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="by.htp.ex.localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="footer" var="footer" />

<center>${footer}</center>