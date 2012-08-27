<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>活动介绍</title>
	<link rel="stylesheet" href="css/previous.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="css/common.css" type="text/css" media="screen" />
</head>
<body>
	<div id="container">
		<div id="nav">
			<input id="nav1" type="image" src="images/nav1.gif" onclick="location.href='${basePath}/index.do'"/>
			<input id="nav2" type="image" src="images/nav2.gif" onclick="register()" />
			<input id="nav3" type="image" src="images/nav3.gif" onclick="location.href='${basePath}/rank.do'"/>
			<input id="nav4" type="image" src="images/nav4.gif" onclick="location.href='${basePath}/previous.do'"/>
			<input id="nav5" type="image" src="images/nav5_sel.gif" onclick="location.href='${basePath}/reward.do'"/>		
		</div>
		<div id="pre_main">
			<img src="images/can1.gif" style="float:left;width:960px" />
			<img src="images/can2.gif" style="float:left;width:960px" />
			<img src="images/can3.gif" style="float:left;width:960px" />
		</div>
		<div style="clear:both"></div>
		<div class="copyright"></div>
	</div>
</body>
</html>