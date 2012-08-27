<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<ul style="margin-top: 20px">
<c:forEach items="${allNews}" var="news">
	<li><a href="news.do?id=${news.id}">${news.title}</a></li>
</c:forEach>
</ul>
<jsp:include page="../include/news_pager.jsp"></jsp:include>