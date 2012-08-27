<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title></title>
<link type="text/css" rel="stylesheet" href="${basePath}/css/admin.css" />
<link rel="stylesheet" href="${basePath}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<jsp:include page="../include/jquery.jsp" />
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
<form id="myForm" method="post">
<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">角色名称：</td>
		<td width="80%" class="pn-fcontent"><input type="text" name="role.name" value="${role.name}" class="required"/></td>
	</tr>
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
		<td width="80%" class="pn-fcontent"><textarea name="role.remark" rows="3" cols="50">${role.remark}</textarea></td>
	</tr>
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">角色权限：</td>
		<td width="80%" class="pn-fcontent">
			<ul id="treeD" class="ztree"></ul>
		</td>
	</tr>
	<tr>
		<td colspan="2" class="pn-fbutton">
			<input type="hidden" name="role.id" value="${role.id}"/>
			<input type="button" value=" 保 存 " onclick="javascript:update()" /> &nbsp;<input type="hidden" name="modules" id="modules" />
			<input type="reset" value=" 重 置 "/></td>
	</tr>
</table>
</form> 
</div>
<script type="text/javascript" src="${basePath}/js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery/jquery.ztree.all-3.1.min.js"></script>
<script type="text/javascript">
	var setting = {
			check:{
				chkboxType : { "Y": "ps", "N": "ps" },
				enable:true,
				chkStyle:"checkbox"
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	var zNodes =[];
	var i = 1;
	zNodes[0] = { id:0, name:"功能菜单", open:true};
<c:forEach items="${list}" var="module">
	<c:set var="contains" value="false" />
	<c:forEach items="${role.modules}" var="r">	
	<c:if test="${r.id == module.id}"> 
	<c:set var="contains" value="true" />
	</c:if> 
	</c:forEach>
	zNodes[i] = {id:${module.id},pId:${module.parentId==null?0:module.parentId}, name:"${module.name}",checked:${contains}, open:true};
	i++;
</c:forEach>
	$(document).ready(function(){
		$.fn.zTree.init($("#treeD"), setting, zNodes);
	});
	function update(){
		var zTree = $.fn.zTree.getZTreeObj("treeD");
		var nodes = zTree.getCheckedNodes(true);
		var modules = "";
		for(var i =0;i <nodes.length;i++){
			if(i == nodes.length -1){
				modules += nodes[i].id;
			}else{
				modules += nodes[i].id + "，";
			}
		}
		$("#modules").val(modules);
		$("#myForm").attr("action","role_update.do");
		$("#myForm").submit();
	}
</script>
</body>
</html>