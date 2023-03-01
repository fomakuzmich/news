<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="by.htp.ex.localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="newsList.newsLink"
	var="newsList_newsLink" />
<fmt:message bundle="${loc}" key="newsList.editLink"
	var="newsList_editLink" />
<fmt:message bundle="${loc}" key="newsList.viewLink"
	var="newsList_viewLink" />
<fmt:message bundle="${loc}" key="newsList.deleteButton"
	var="newsList_deleteButton" />
<fmt:message bundle="${loc}" key="newsList.noNews" var="newsList_noNews" />

<div class="body-title">
	<a href="controller?command=go_to_news_list">${newsList_newsLink} </a>
</div>

<form action="controller" method="post">
	<c:forEach var="news" items="${requestScope.news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<c:out value="${news.title}" />
				</div>
				<div class="news-date">
					<c:out value="${news.newsDate}" />
				</div>

				<div class="news-content">
					<c:out value="${news.briefNews}" />
				</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<c:if test="${sessionScope.role eq 'admin'}">
							<a
								href="controller?command=go_to_edit_news_page&id=${news.idNews}">${newsList_editLink}
							</a>
						</c:if>

						<a href="controller?command=go_to_view_news&id=${news.idNews}">${newsList_viewLink}
						</a>

						<c:if test="${sessionScope.role eq 'admin'}">
							<input type="checkbox" name="idNews" value="${news.idNews }" />
						</c:if>
					</div>
				</div>

			</div>
		</div>

	</c:forEach>
	<br> <br>

	<c:if test="${sessionScope.role eq 'admin'}">
		<input type="hidden" name="command" value="delete_news" />
		<input type="submit" value="${newsList_deleteButton}" />
	</c:if>

	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
        ${newsList_noNews}
	</c:if>
	</div>
</form>
