<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<html>
<head>
<title>公司网站管理后台</title>
<link type="text/css" rel="stylesheet" href="${basePath}/css/admin.css"/>
<script language="javascript">
	function reload(){
		window.location.reload();
	}
</script>
</head>
<frameset rows="93,*,0" cols="*" frameborder="no" frameborder="0" border="0" framespacing="0">
	<frame src="${basePath}/admin/sys_top.do" name="topFrame" scrolling="NO" noresize border="0" frameborder="no" framespacing="0">
	<frameset id="mainFrame" cols="170,90,*" border="0" framespacing="0" rows="*">
		<frame name="leftFrame" scrolling="auto" noresize src="${basePath}/admin/sys_left.do">
		<frame id="middleFrame" name="midFrame" scrolling="no" noresize src="${basePath}/admin/sys_middle.do">
		<frame name="rightFrame" scrolling="auto" noresize src="${basePath}/admin/sys_right.do">
	</frameset>
</frameset>
</html> 