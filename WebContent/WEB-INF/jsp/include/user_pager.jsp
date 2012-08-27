<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="pager">
<c:if test="${userPager.hasPrevious}">
<img src="images/previous.gif" id="previous" onclick="getUsers2('${userPager.currentPage-1}')" />
</c:if>
<c:if test="${userPager.hasNext}">
<img src="images/next.gif" id="next" onclick="getUsers2('${userPager.currentPage+1}')" />
</c:if>
</div>