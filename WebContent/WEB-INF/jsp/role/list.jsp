<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>list</title>
<link type="text/css" rel="stylesheet" href="${basePath}/css/admin.css"/>
<script type="text/javascript" src="${basePath}/js/util.js"></script>
<script language="javascript">
	function del(id){
		if(confirm("确认要删除吗？")){
			window.location.href = "role_delete.do?role.id="+id;
		}
	}
</script>
</head> 
<body>
<div class="body-box">
<div class="rhead">
<div class="rpos">当前位置： ${currentPosition}</div>
<form class="ropt" method="post" action="role_add.do">
	<input type="submit" value=" 添 加 "/>
</form>
<div class="clear"></div>
</div>
<form id="myForm" action="role_list.do" method="post">
<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
	<thead class="pn-lthead">
		<tr>
			<th>编号</th>
			<th>角色名称</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${list}" var="item">
			<tr onmouseover="this.className='pn-lhover'" onmouseout="this.className=''" align="center">
				<td>${item.id}</td>
				<td>${item.name}</td>
				<td>${item.remark}</td>
				<td class="pn-lopt"><a href="role_edit.do?role.id=${item.id}" class="pn-loperator">修改</a>
				┆ <a href="javascript:del(${item.id});" class="pn-loperator">删除</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="../include/pager.jsp"/>
</form>
</div>
</body>
</html>