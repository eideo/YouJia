<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="comment_pager" style="text-align:right;position:absolute;right:0;bottom:0;">
<c:if test="${commentPager.hasPrevious}">
<img src="images/left.gif" style="cursor:pointer" onclick="gotoPage('${commentPager.currentPage-1}')" />
</c:if>
<c:if test="${commentPager.hasNext}">
<img src="images/right.gif" style="cursor:pointer" onclick="gotoPage('${commentPager.currentPage+1}')" />
</c:if>
	
	
</div>