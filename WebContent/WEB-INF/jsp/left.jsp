<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title></title>
<link type="text/css" rel="stylesheet" href="${basePath}/css/admin.css" />
<link rel="stylesheet" href="${basePath}/css/zTreeStyle/zTreeStyle.css"
	type="text/css" />
<style>
.menu_title {
	color: #006DA5;
	font-weight: bold;
}
</style>
</head>
<body class="lbody">
	<table width="100%" cellpadding="2" cellspacing="10" border="0">
		<tr>
			<td>
				<ul id="treeD" class="ztree"></ul>

			</td>
		</tr>
	</table>
	<script type="text/javascript"
		src="${basePath}/js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript"
		src="${basePath}/js/jquery/jquery.ztree.all-3.1.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/util.js"></script>
	<script type="text/javascript">
	var setting = {
			view: {
				dblClickExpand: false,
				showLine: true,
				selectedMulti: false,
				expandSpeed: ($.browser.msie && parseInt($.browser.version)<=6)?"":"fast"
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	var zNodes =[];
	var i = 1;
	zNodes[0] = { id:0, name:"功能菜单(点击刷新)",click:"location.reload()", open:true};
<c:forEach items="${list}" var="module">
	zNodes[i] = {id:${module.id},pId:${module.parentId==null?0:module.parentId}, name:"${module.name}",url:"<c:if test="${module.url!=''}">${basePath}${module.url}?mid=${module.id}</c:if>",target:"rightFrame"};
	i++;
</c:forEach>
	$(document).ready(function(){
		$.fn.zTree.init($("#treeD"), setting, zNodes);
	});	
		</script>
</body>
</html>