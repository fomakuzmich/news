<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="by.htp.ex.localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="addNews.newsListLink"
	var="addNews_newsListLink" />
<fmt:message bundle="${loc}" key="addNews.title" var="addNews_title" />
<fmt:message bundle="${loc}" key="addNews.brief" var="addNews_brief" />
<fmt:message bundle="${loc}" key="addNews.content" var="addNews_content" />
<fmt:message bundle="${loc}" key="addNews.addNewsButton"
	var="addNews_addNewsButton" />
<fmt:message bundle="${loc}" key="addNews.cancelButton"
	var="addNews_cancelButton" />

<div class="body-title">
	<a href="controller?command=go_to_news_list">${addNews_newsListLink }
	</a>
</div>


<div class="add-table-margin">
	<form action="controller" method="post">

		<input type="hidden" name="command" value="add_news" />

		<table class="news_text_format">
			<tr>
				<td class="space_around_title_text">${addNews_title}</td>
				<td class="space_around_view_text">
					<div class="word-breaker">
						<input type="text" name="title" />
					</div>
				</td>
			</tr>

			<tr>
				<td class="space_around_title_text">${addNews_brief}</td>
				<td class="space_around_view_text">
					<div class="word-breaker">
						<input type="text" name="brief" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="space_around_title_text">${addNews_content}</td>
				<td class="space_around_view_text">
					<div class="word-breaker">
						<textarea rows="7" cols="60" name="content">
							
						</textarea>
					</div>
				</td>
			</tr>
		</table>



		<c:if test="${sessionScope.role eq 'admin'}">
			<div class="first-view-button">
				<input type="submit" value="${addNews_addNewsButton}" />
			</div>
		</c:if>

	</form>
</div>



<c:if test="${sessionScope.role eq 'admin'}">
	<div class="second-view-button">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_news_list" /> <input
				type="submit" value="${addNews_cancelButton}" />
		</form>
	</div>
</c:if>
