<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title></title>
		<link type="text/css" rel="stylesheet" href="${basePath}/css/admin.css"/>
	</head>
	<body>
		<div class="body-box">
			<div class="rhead">
				<div class="rpos">当前位置： ${currentPosition}</div>
				<form class="ropt" method="post">
					<input type="button" value=" 返 回 " onclick="history.back();"/>
				</form>
				<div class="clear"></div>
			</div>
				<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">姓名：</td>
						<td width="38%" class="pn-fcontent">${comment.name }</td>
						<td width="12%" class="pn-flabel pn-flabel-h">电子邮件：</td>
						<td width="38%" class="pn-fcontent">${comment.email}</td>
					</tr>
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">评论时间：</td>
						<td colspan="3" class="pn-fcontent">
							${comment.createTime}
						</td>
					</tr>
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">个人简介：</td>
						<td colspan="3" class="pn-fcontent">
							${comment.comment}
						</td>
					</tr>
				</table>
		</div>
	</body>
</html>