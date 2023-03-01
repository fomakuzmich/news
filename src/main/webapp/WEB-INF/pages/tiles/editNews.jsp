<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="by.htp.ex.localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="editNews.title" var="editNews_title" />
<fmt:message bundle="${loc}" key="editNews.brief" var="editNews_brief" />
<fmt:message bundle="${loc}" key="editNews.content"
	var="editNews_content" />
<fmt:message bundle="${loc}" key="editNews.saveButton"
	var="editNews_saveButton" />
<fmt:message bundle="${loc}" key="editNews.cancelButton"
	var="editNews_cancelButton" />




<form action="controller" method="post">
	<input type="hidden" name="command" value="save_news" /> <input
		type="hidden" name="idNews" value="${requestScope.news.idNews}" />

	<div class="add-table-margin">
		<table class="news_text_format">
			<tr>
				<td class="space_around_title_text">${editNews_title}</td>

				<td class="space_around_view_text"><div class="word-breaker">
						<input type="text" name="title" value="${requestScope.news.title}" />
					</div></td>
			</tr>

			<tr>
				<td class="space_around_title_text">${editNews_brief}</td>
				<td class="space_around_view_text"><div class="word-breaker">
						<input type="text" name="brief"
							value="${requestScope.news.briefNews }" />
					</div></td>
			</tr>
			<tr>
				<td class="space_around_title_text">${editNews_content}</td>
				<td class="space_around_view_text">
					<div class="word-breaker">
						<textarea rows="7" cols="60" name="content">
					${requestScope.news.content}
					</textarea>
					</div>
				</td>
			</tr>
		</table>
	</div>


	<c:if test="${sessionScope.role eq 'admin'}">
		<div class="first-view-button">

			<input type="submit" value="${editNews_saveButton}" />

		</div>
	</c:if>
</form>

<c:if test="${sessionScope.role eq 'admin'}">
	<div class="second-view-button">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_news_list" /> <input
				type="submit" value="${editNews_cancelButton}" />
		</form>
	</div>
</c:if>