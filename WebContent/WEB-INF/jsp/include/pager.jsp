<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language="javascript">
function gotoPage(pageNo) {
	if(pageNo>0)
		get("pager.currentPage").value = pageNo;
	get("myForm").submit();
}
</script>
	
<div class="pn-sp">
	<div class="pn-sp-left">共 ${pager.totalSize} 条 &nbsp;每页
	<input type="text" name="pager.pageSize" value="${pager.pageSize}" size="2" onfocus="this.select();"/> 条</div>
	<div class="pn-sp-right">
		<input type="button" value=" 首 页 " onclick="gotoPage('1');" <c:if test="${pager.hasFirst==false}">disabled</c:if>/>&nbsp;
		<input type="button" value=" 上一页 " onclick="gotoPage('${pager.currentPage-1}');"
		<c:if test="${pager.hasPrevious==false}">disabled</c:if> />&nbsp;
		<input type="button" value=" 下一页 " onclick="gotoPage('${pager.currentPage+1}');"
	  	<c:if test="${pager.hasNext==false}">disabled</c:if> />&nbsp;
		<input type="button" value=" 尾 页 " onclick="gotoPage('${pager.totalPage}');"
		<c:if test="${pager.hasLast==false}">disabled</c:if> />&nbsp;
		当前 ${pager.currentPage}/${pager.totalPage} 页 &nbsp;转到第 
		<input type="text" name="pager.currentPage" size="2" value="${pager.currentPage}" onfocus="this.select();"/> 页
		<input type="button" value=" 转 " onclick="gotoPage(-1);"/>
	</div>
	<div class="clear"></div>
</div>