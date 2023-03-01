<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="by.htp.ex.localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="viewNews.newsLink"
	var="viewNews_newsLink" />
<fmt:message bundle="${loc}" key="viewNews.title" var="viewNews_title" />
<fmt:message bundle="${loc}" key="viewNews.date" var="viewNews_date" />
<fmt:message bundle="${loc}" key="viewNews.brief" var="viewNews_brief" />
<fmt:message bundle="${loc}" key="viewNews.content"
	var="viewNews_content" />
<fmt:message bundle="${loc}" key="viewNews.editButton"
	var="viewNews_editButton" />
<fmt:message bundle="${loc}" key="viewNews.deleteButton"
	var="viewNews_deleteButton" />

<div class="body-title">
	<a href="controller?command=go_to_news_list">${viewNews_newsLink} </a>
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">${viewNews_title}</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.title }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${viewNews_date}</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.newsDate }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${viewNews_brief}</td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.briefNews }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${viewNews_content}</td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.content }" />
				</div></td>
		</tr>
	</table>
</div>

<c:if test="${sessionScope.role eq 'admin'}">
	<div class="first-view-button">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_edit_news_page" />
			<input type="hidden" name="id" value="${news.idNews}" /> <input
				type="submit" value="${viewNews_editButton}" />
		</form>
	</div>

	<div class="second-view-button">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="delete_news" /> <input
				type="hidden" name="idNews" value="${news.idNews}" /> <input
				type="submit" value="${viewNews_deleteButton}" />
		</form>
	</div>
</c:if>