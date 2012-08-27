<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>${news.title}</title>
	<link rel="stylesheet" href="css/news.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="css/common.css" type="text/css" media="screen" />
</head>
<body>
	<div id="container">
		<div id="nav">
			<input id="nav1" type="image" src="images/nav1.gif" onclick="location.href='${basePath}/index.do'"/>
			<input id="nav2" type="image" src="images/nav2.gif" onclick="register()" />
			<input id="nav3" type="image" src="images/nav3.gif" onclick="location.href='${basePath}/rank.do'"/>
			<input id="nav4" type="image" src="images/nav4.gif" onclick="location.href='${basePath}/previous.do'"/>
			<input id="nav5" type="image" src="images/nav5.gif" onclick="location.href='${basePath}/reward.do'"/>		
		</div>
		<div id="pre_main">
			<div id="pre_left">
				<div style="text-align:center;font-size:16px">${news.title}</div>
				<hr/>
				<div style="text-align:center;font-size:9px"> ${news.createTime}</div>
				${news.content}
			</div>
			<div id="pre_right">

			</div>
		</div>
		<div style="clear:both"></div>
		<div class="copyright"></div>
	</div>
	<jsp:include page="../include/js.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(function(){
			$.get('list_news.do?r='+Math.random(), function(data) {
			  	$('#pre_right').html();
			  	$('#pre_right').html(data);
			});			
		});
		function getNewsList(pageNo){
			$.get('list_news.do?pn='+pageNo+'&r='+Math.random(), function(data) {
			  	$('#pre_right').html();
			  	$('#pre_right').html(data);
			});			
		}
	</script>
</body>
</html>