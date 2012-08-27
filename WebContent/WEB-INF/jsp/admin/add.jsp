<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>add</title>
<link type="text/css" rel="stylesheet" href="${basePath}/css/admin.css" />
<jsp:include page="../include/jquery.jsp" />
</head>
<body>
<div class="body-box">
<div class="rhead">
<div class="rpos">当前位置： ${currentPosition}</div>
<form class="ropt" method="post">
	<input type="button" value=" 返 回 " onclick="history.back();" />
</form>
<div class="clear"></div>
</div>
<form id="myForm" method="post" action="admin_save.do">
<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">登&nbsp;陆&nbsp;名：</td>
		<td width="80%" class="pn-fcontent"><input type="text"
			name="admin.username" class="required" /></td>
	</tr>
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">真实姓名：</td>
		<td width="80%" class="pn-fcontent"><input type="text"
			name="admin.name" class="required" /></td>
	</tr>
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">用户角色：</td>
		<td width="80%" class="pn-fcontent"><c:forEach
			items="${roleList}" var="item">
			<input type="checkbox" name="roles" value="${item.id}" />${item.name}
			</c:forEach></td>
	</tr>
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
		<td width="80%" class="pn-fcontent"><input type="password"
			id="password" name="admin.password" class="required" /></td>
	</tr>
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">重复密码：</td>
		<td width="80%" class="pn-fcontent"><input type="password"
			name="admin.password2" class="required" equalTo="#password" /></td>
	</tr>
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
		<td width="80%" class="pn-fcontent">
			<input type="radio" name="admin.sex" value="男" checked />男 
			<input type="radio" name="admin.sex" value="女" />女</td>
	</tr>
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</td>
		<td width="80%" class="pn-fcontent"><input type="text" name="admin.email" /></td>
	</tr>
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">电&nbsp;&nbsp;&nbsp;&nbsp;话：</td>
		<td width="80%" class="pn-fcontent"><input type="text" name="admin.phone" /></td>
	</tr>
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">是否禁用：</td>
		<td width="80%" class="pn-fcontent"><input type="radio"
			name="admin.valid" value="1" checked />否 <input type="radio"
			name="admin.valid" value="0" />是</td>
	</tr>
	<tr>
		<td colspan="2" class="pn-fbutton"><input type="submit"
			value=" 保 存 " /> &nbsp; <input type="reset" value=" 重 置 " /></td>
	</tr>
</table>
</form>
</div>
</body>
</html>