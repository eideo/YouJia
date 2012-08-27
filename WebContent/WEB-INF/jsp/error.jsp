<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title></title>
<link type="text/css" rel="stylesheet" href="${basePath}/css/admin.css"/>
<script type="text/javascript" src="${basePath}/js/util.js"></script>
</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos"><b>操作失败</b></div>
	<div class="clear"></div>
</div>
<table width="100%" cellpadding="2" cellspacing="1" border="0" class="pn-ftable">
	<tr>
		<td class="pn-flabel pn-flabel-h" width="10%">错误原因</td>
		<td width="90%" height="50" class="pn-fcontent" >
		${message}
		<c:if test='${fn:indexOf(exception,"org.springframework.dao.DataIntegrityViolationException")>-1}'>
		该信息系统正在使用中，无法删除
		</c:if>
		</td>
	</tr>
	<tr id="tr_detail" style="display: none">
		<td class="pn-flabel pn-flabel-h">详细信息</td>
		<td class="pn-fcontent" height="100">${exception}</td>
	</tr>
	<tr>
		<td class="pn-fcontent" colspan="2" style="padding-left:200px;">
			<input type="button" value=" 返 回 " onclick="history.back();"/>&nbsp;&nbsp;
			<input type="button" value=" 查看详细信息 " onclick='get("tr_detail").style.display="";'/>
		</td>
	</tr>
	
</table>
</div>
</body>
</html>