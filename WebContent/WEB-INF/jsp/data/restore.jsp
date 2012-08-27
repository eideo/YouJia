<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<title></title>
</head>
<body>
<form action="dba_reload.do" method="post" enctype="multipart/form-data">
<table align="center">
	<caption>恢复数据库</caption>
	<tr><td>${reloadTip }</td></tr>
	<tr>
		<td><input type="file" name="upload"></td>
	</tr>
	<tr>
		<td><input type="submit" value="恢复"></td>
	</tr>
</table>
</form>
</body>
</html>
