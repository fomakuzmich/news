<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="by.htp.ex.localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="menu.news" var="menu_news" />
<fmt:message bundle="${loc}" key="menu.newsListLink"
	var="menu_newsListLink" />
<fmt:message bundle="${loc}" key="menu.addNewsLink"
	var="menu_addNewsLink" />

<div class="menu-wrapper">
	<div class="menu-title-wrapper">
		<div class="menu-title">${menu_news}</div>
	</div>

	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper" style="float: right;">
			<ul style="list-style-image: url(images/img.jpg); text-align: left;">
				<li style="padding-left: 15px;"><a
					href="controller?command=go_to_news_list">${menu_newsListLink}</a><br />
				</li>

				<c:if test="${sessionScope.role eq 'admin'}">
					<li style="padding-left: 15px;"><a
						href="controller?command=go_to_add_news_page">${menu_addNewsLink}
					</a> <br /></li>
				</c:if>
			</ul>
		</div>
		<div class="clear"></div>
	</div>
	<!--  grey free space at the bottom of menu -->
	<div style="height: 25px;"></div>
</div>