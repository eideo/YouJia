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
						<td width="38%" class="pn-fcontent">${user.username }</td>
						<td width="12%" class="pn-flabel pn-flabel-h">昵称：</td>
						<td width="38%" class="pn-fcontent">${user.nickname}</td>
					</tr>
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">是否有效：</td>
						<td width="38%" class="pn-fcontent">
							<c:choose>
								<c:when test="${user.valid}">已审核</c:when>
								<c:otherwise>未审核</c:otherwise>
							</c:choose>
						</td>
						<td width="12%" class="pn-flabel pn-flabel-h">年龄：</td>
						<td width="38%" class="pn-fcontent">
							${user.age}
						</td>
					</tr>
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">联系电话：</td>
						<td width="38%" class="pn-fcontent">${user.telephone }</td>
						<td width="12%" class="pn-flabel pn-flabel-h">手机号码：</td>
						<td width="38%" class="pn-fcontent">${user.mobile}</td>
					</tr>					
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">电子邮箱：</td>
						<td width="38%" class="pn-fcontent">
							${user.email}
						</td>
						<td width="12%" class="pn-flabel pn-flabel-h">参赛地区：</td>
						<td width="38%" class="pn-fcontent">
							${user.regionName}
						</td>
					</tr>
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">海选票数：</td>
						<td width="38%" class="pn-fcontent">${user.rank1 }</td>
						<td width="12%" class="pn-flabel pn-flabel-h">决赛票数：</td>
						<td width="38%" class="pn-fcontent">${user.rank2}</td>
					</tr>					
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">初赛图片：</td>
						<td width="80%" colspan="3" class="pn-fcontent"><img src="${basePath}${user.pic1}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">决赛图片：</td>
						<td width="80%" colspan="3" class="pn-fcontent">
						<c:forEach items="${pictures}" var="pic">
							<img src="${basePath}/users/${pic}" />
						</c:forEach>
						</td>
					</tr>					
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">参赛宣言：</td>
						<td width="38%" class="pn-fcontent">${user.bio1 }</td>
						<td width="12%" class="pn-flabel pn-flabel-h">决赛宣言：</td>
						<td width="38%" class="pn-fcontent">${user.bio2}</td>
					</tr>					
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">备注：</td>
						<td colspan="3" class="pn-fcontent">
							${user.remark}
						</td>
					</tr>
				</table>
		</div>
	</body>
</html>