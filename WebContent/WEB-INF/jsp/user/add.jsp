<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
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
			<form method="post" action="user_save.do" id="myForm">
				<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
						<td width="80%" class="pn-fcontent"><input type="text" name="user.username"/></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
						<td width="80%" class="pn-fcontent"><input type="text" name="user.password"/></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">电子邮箱：</td>
						<td width="80%" class="pn-fcontent">
							<input type="text" name="user.email"/>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">地&nbsp;&nbsp;&nbsp;&nbsp;区：</td>
						<td width="80%" class="pn-fcontent">
							<select name="user.region">
				  				<option value="">请选择</option>
				  				<option value="广东">广东</option>
				  				<option value="广西">广西</option>
				  			</select>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">年&nbsp;&nbsp;&nbsp;&nbsp;龄：</td>
						<td width="80%" class="pn-fcontent">
							<input type="text" name="user.age"/>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">个人简介：</td>
						<td width="80%" class="pn-fcontent">
						    <textarea rows="5" cols="40" name="user.description"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="pn-fbutton">
							<input type="submit" value=" 保 存 "/> &nbsp;
						<input type="reset" value=" 重 置 "/></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>