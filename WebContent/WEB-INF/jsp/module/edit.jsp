<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title>edit</title>
<link type="text/css" rel="stylesheet" href="${basePath}/css/admin.css" />
<link rel="stylesheet" href="${basePath}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
</head>
<body>
	<div class="body-box">
		<div class="rhead">
			<div class="rpos">当前位置： ${currentPosition}</div>
			<div class="clear"></div>
		</div>
		<form id="myForm" method="post" action="module_update.do">
			<table cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td width="300" valign="top"
						style="padding-left: 30px; padding-top: 20px;">
						<ul id="treeD" class="ztree"></ul>
					</td>

					<td width="550" valign="top" style="padding-top: 20px;">
						<table width="100%" class="pn-ftable" cellpadding="2"
							cellspacing="1" border="0">
							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h">上级模块：</td>
								<td width="80%" class="pn-fcontent"><select
									name="module.parentId">
										<option value="0" selected>根目录</option>
										<c:forEach items="${list}" var="item">
											<option value="${item.id}">${fn:substring("││││││││││",0,item.level-1)}├${item.name}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h">模块名称：</td>
								<td width="80%" class="pn-fcontent"><input type="text"
									name="module.name" /></td>
							</tr>
							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h">首页地址：</td>
								<td width="80%" class="pn-fcontent"><input type="text"
									name="module.url" size="50" /></td>
							</tr>
							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h">功能集合：</td>
								<td width="80%" class="pn-fcontent"><textarea
										name="module.functions" rows="8" cols="50"></textarea><br>
										使用逗号";"隔开</td>
							</tr>
							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h">排&nbsp;&nbsp;&nbsp;&nbsp;序：</td>
								<td width="80%" class="pn-fcontent"><input type="text"
									name="module.priority" value="10" /></td>
							</tr>
							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h">是否隐藏：</td>
								<td width="80%" class="pn-fcontent"><input type="radio"
									name="module.display" value="1" checked />否 <input type="radio"
									name="module.display" value="0" />是</td>
							</tr>
							<tr>
								<td width="20%" class="pn-flabel pn-flabel-h">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
								<td width="80%" class="pn-fcontent"><input type="text"
									name="module.remark" size="50" /></td>
							</tr>
							<tr>
								<td colspan="2" class="pn-fbutton"><input type="hidden"
									name="module.id" /> <input type="hidden" name="module.level" />
									<input type="button" onclick="add();" value=" 添 加 " /> &nbsp; <input
									type="button" onclick="update();" value=" 修 改 " /> &nbsp; <input
									type="button" onclick="del();" value=" 删 除 " /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript" src="${basePath}/js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/jquery/jquery.ztree.all-3.1.min.js"></script>	
	<script type="text/javascript" src="${basePath}/js/util.js"></script>
	<script type="text/javascript">
	var zTree = null;
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
	zNodes[0] = { id:0, name:"根目录", open:true};
<c:forEach items="${list}" var="module">
	zNodes[i] = {id:${module.id},pId:${module.parentId==null?0:module.parentId}, name:"${module.name}",u:"${module.url}",funs:"${module.functions}",priority:"${module.priority}",display:"${module.display}",level:"${module.level}",remark:"${module.remark}",click:"put(${module.id})"};
	i++;
</c:forEach>
	$(document).ready(function(){
		zTree = $.fn.zTree.init($("#treeD"), setting, zNodes);
	});
	if ("${result}" == "SUCCESS")
		alert("操作成功");
	function check() {
		if (get("module.name").value == "") {
			alert("模块名称不能为空");
			get("module.name").focus();
			return false;
		}
		return true;
	}
	function put(id) {
		var node = zTree.getNodeByParam("id",id,null);
		get("module.id").value = node.id;
		get("module.parentId").value = node.pId;
		get("module.name").value = node.name;
		get("module.url").value = node.u;
		get("module.functions").value =node.funs;
		get("module.priority").value = node.priority;
		var display = document.getElementsByName("module.display");
		for (var i  = 0; i < display.length; i++) {
			if (display[i].value == node.display) {
				display[i].checked = true;
			}
		}
		get("module.level").value = node.level;
		get("module.remark").value = node.remark;
	}
	function add() {
		if (!check())
			return false;
		get("myForm").action = "module_save.do";
		get("myForm").submit();
	}
	function update() {
		if (get("module.id").value == "") {
			alert("请先选择要修改的信息");
			return false;
		}
		if (!check())
			return false;
		get("myForm").action = "module_update.do";
		get("myForm").submit();
	}
	function del() {
		if (get("module.id").value == "") {
			alert("请先选择要删除的信息");
			return false;
		}
		if (confirm("确定要删除信息吗？")) {
			get("myForm").action = "module_delete.do";
			get("myForm").submit();
		}
	}
	</script>
</body>
</html>