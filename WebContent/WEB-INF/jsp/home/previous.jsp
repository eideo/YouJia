<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>往届回顾</title>
	<link rel="stylesheet" href="css/previous.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="css/common.css" type="text/css" media="screen" />
</head>
<body>
	<div id="container">
		<div id="nav">
			<input id="nav1" type="image" src="images/nav1.gif" onclick="location.href='${basePath}/index.do'"/>
			<input id="nav2" type="image" src="images/nav2.gif" onclick="register()" />
			<input id="nav3" type="image" src="images/nav3.gif" onclick="location.href='${basePath}/rank.do'"/>
			<input id="nav4" type="image" src="images/nav4_sel.gif" onclick="location.href='${basePath}/previous.do'"/>
			<input id="nav5" type="image" src="images/nav5.gif" onclick="location.href='${basePath}/reward.do'"/>	
		</div>
		<div id="pre_main">
			<div id="pre_left">
			</div>
			<div id="pre_right">
				<img src="images/wjlb.gif" />
				<div>
					<div class="show_dis unsel" style="margin-left:7px;margin-right:5px;">
						<div class="a"></div>
						<div class="b" style="width:75px" id="pre2011" onclick="getDetail(2011);return false;">2011</div>
					</div>
					<div class="show_dis unsel" style="margin-left:7px;margin-right:5px;">
						<div class="a"></div>
						<div class="b" style="width:75px" id="pre2010" onclick="getDetail(2010);return false;">2010</div>
					</div>
					<div class="show_dis unsel" style="margin-left:7px;margin-right:5px;">
						<div class="a"></div>
						<div class="b" style="width:75px" id="pre2009" onclick="getDetail(2009);return false;">2009</div>
					</div>																												
				</div>		
			</div>
		</div>
		<div style="clear:both"></div>
		<div class="copyright"></div>
	</div>
	<jsp:include page="../include/js.jsp"></jsp:include>
	<script type="text/javascript">
		var preSel = null;
		function getDetail(year){
			$("#pre_left").load("static/" + year + ".htm",function(){
				if(preSel != null){
					$("#pre" + preSel).parent().removeClass("sel").addClass("unsel");
				}
				$("#pre" + year).parent().removeClass("unsel").addClass("sel");
				preSel = year;				
			});
		}
		$(document).ready(function(){
			getDetail(2011);
		});
	</script>
</body>
</html>