<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" language="java"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title></title>
<link type="text/css" rel="stylesheet" href="${basePath}/css/admin.css" />
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
		<form method="post" action="user_update.do" id="myForm">
			<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
				border="0">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">姓名：</td>
					<td width="80%" class="pn-fcontent"><input type="text"
						name="user.username" value="${user.username }" /></td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">昵称：</td>
					<td width="80%" class="pn-fcontent"><input type="text"
						name="user.nickname" value="${user.nickname }" /></td>
				</tr>				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
					<td width="80%" class="pn-fcontent"><input type="text"
						name="user.password" value="${user.password}" /></td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">是否有效：</td>
					<td width="80%" class="pn-fcontent"><select name="user.valid">
							<option value="">请选择</option>
							<option value="false" <c:if test="${user.valid==false}">selected</c:if>>未审核</option>
							<option value="true" <c:if test="${user.valid}">selected</c:if>>已审核</option>
					</select></td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">联系电话：</td>
					<td width="80%" class="pn-fcontent"><input type="text"
						name="user.telephone" value="${user.telephone}" /></td>
				</tr>
												<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">手机号码：</td>
					<td width="80%" class="pn-fcontent"><input type="text"
						name="user.mobile" value="${user.mobile}" /></td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">电子邮箱：</td>
					<td width="80%" class="pn-fcontent"><input type="text"
						name="user.email" value="${user.email}" /></td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">海选票数：</td>
					<td width="80%" class="pn-fcontent"><input type="text"
						name="user.rank1" value="${user.rank1}" /></td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">决赛票数：</td>
					<td width="80%" class="pn-fcontent"><input type="text"
						name="user.rank2" value="${user.rank2}" /></td>
				</tr>								
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">参赛地区：</td>
					<td width="80%" class="pn-fcontent"><select name="user.regionId">
							<option value="">请选择</option>
							<c:forEach items="${regions}" var="region">
							<option value="${region.id}" <c:if test="${region.id == user.regionId}">selected</c:if>>${region.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">年&nbsp;&nbsp;&nbsp;&nbsp;龄：</td>
					<td width="80%" class="pn-fcontent"><input type="text"
						name="user.age" value="${user.age}" /></td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">初赛图片：</td>
					<td width="80%" class="pn-fcontent"><img src="${basePath}${user.pic1}" /><input type="hidden" name="user.pic1" value="${user.pic1}" /></td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">决赛图片：</td>
					<td width="80%" class="pn-fcontent">
					<select multiple="multiple" style="width:80px;height:60px" size="3">

					</select>
					</td>
				</tr>								
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">参赛宣言：</td>
					<td width="80%" class="pn-fcontent"><textarea rows="5"
							cols="40" name="user.bio1">${user.bio1}</textarea></td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">决赛宣言：</td>
					<td width="80%" class="pn-fcontent"><textarea rows="5"
							cols="40" name="user.bio2">${user.bio2}</textarea></td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">备注：</td>
					<td width="80%" class="pn-fcontent"><textarea rows="5"
							cols="40" name="user.remark">${user.remark}</textarea></td>
				</tr>								
				<tr>
					<td colspan="2" class="pn-fbutton"><input type="hidden"
						name="user.id" value="${user.id}" /> <input type="submit"
						value=" 保 存 " /> &nbsp; <input type="reset" value=" 重 置 " /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>