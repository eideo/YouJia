<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="pager">
<c:if test="${pager.hasPrevious}">
<img src="images/previous.gif" id="previous" onclick="getNewsList('${pager.currentPage-1}')" />
</c:if>
<c:if test="${pager.hasNext}">
<img src="images/next.gif" id="next" onclick="getNewsList('${pager.currentPage+1}')" />
</c:if>
</div>