<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title></title>
<link type="text/css" rel="stylesheet" href="${basePath}/css/admin.css"/>
<script type="text/javascript" src="${basePath}/js/util.js"></script>
<script type="text/javascript">
function changeAction(str){
	var uploadFile = document.getElementById("upload");
	var curform = document.getElementById("subform");
	curform.enctype="";
	curform.action = "data_" + str + ".do";
	curform.method = "post";
	if(str == 'reload'){
		var fileName = uploadFile.value;
		if(fileName == null || fileName == ""){
			alert("文件路径不能为空");
			return false;
		}
		var tmp = fileName.substr(fileName.length-4, fileName.length);
		if(tmp != ".sql"){
			alert("只能上传.sql文件");
			return false;
		}
		curform.enctype = "multipart/form-data";
		if(!confirm("确认要恢复数据库？")){
			return false;
		}
	}
	curform.submit();
}
</script>

</head>
<body>
<div class="body-box">
<div class="rhead">
	<div class="rpos">当前位置： ${currentPosition}</div>
	<div class="clear"></div>
</div>
<form id="subform" method="post" action="dba_reload.do" enctype="multipart/form-data">
<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">备份数据库：</td>
		<td width="80%" class="pn-fcontent"><input style="width:100px" type="button" value="备份" onclick="changeAction('backup');"/></td>
	</tr>
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h"></td>
		<td width="80%" class="pn-fcontent"></td>
	</tr>
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h">恢复数据库：</td>
		<td width="80%" class="pn-fcontent">恢复文件路径：<input type="file" name="upload" id="upload"><font color="red">${message }</font></td>
	</tr>
	<tr>
		<td width="20%" class="pn-flabel pn-flabel-h"></td>
		<td width="80%" class="pn-fcontent">
		<input style="width:100px" type="button" value="恢复" onclick="changeAction('reload');">
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>