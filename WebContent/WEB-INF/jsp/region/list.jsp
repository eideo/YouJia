<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>list</title>
		<link type="text/css" rel="stylesheet" href="${basePath}/css/admin.css"/>
		<script type="text/javascript" src="${basePath}/js/util.js"></script>
		<script language="javascript">
			function del(id){
				if(confirm("确认要删除吗？")){
					window.location.href = "region_delete.do?user.id="+id;
				}
			}
			function query(){
				var form = get("myForm");
				form.action = "region_query.do";
				form.submit();
		    }
		</script>
	</head>
	<body>
		<div class="body-box">
			<div class="rhead">
				<div class="rpos">当前位置： ${currentPosition}</div>
				<form class="ropt" method="post" action="region_add.do">
					<input type="submit" value=" 添 加 "/>
				</form>				
				<div class="clear"></div>
			</div>
			<form name="myForm" action="region_list.do" method="post">
				<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
				<tr>
				  <td class="pn-flabel pn-flabel-h" style="text-align:left">&nbsp;&nbsp;状态:
				    <select name="state">
				  		<option value="">请选择</option>
				  		<option value="0" <c:if test="${state eq '0'}">selected</c:if>>未开始</option>
				  		<option value="1" <c:if test="${state eq '1'}">selected</c:if>>海选</option>
				  		<option value="2" <c:if test="${state eq '2'}">selected</c:if>>决赛</option>
				  		<option value="3" <c:if test="${state eq '3'}">selected</c:if>>已结束</option>
				  	</select>
				    &nbsp;<input type="button" value=" 查 询 " onclick="query()"/>
				  </td>
				</tr>
			</table>
				<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
					<thead class="pn-lthead">
						<tr>
							<th>ID</th>
							<th>参赛地区</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody class="pn-ltbody">
						<c:forEach items="${list}" var="region">
							<tr onmouseover="this.className='pn-lhover'" onmouseout="this.className=''" align="center">
								<td title="${region.id}"><div class="w150">${region.id}</div></td>
								<td>${region.name}</td>
								<td>${region.startTime}</td>
								<td>${region.endTime}</td>
								<td>
									<c:if test="${region.state == 0}">未开始</c:if>
									<c:if test="${region.state == 1}">海选</c:if>
									<c:if test="${region.state == 2}">决赛</c:if>
									<c:if test="${region.state == 3}">已结束</c:if>
								</td>
								<td class="pn-lopt"><a href="region_edit.do?region.id=${region.id}" class="pn-loperator">修改</a>┆<a 
									href="javascript:del(${region.id});" class="pn-loperator">删除</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<jsp:include page="../include/pager.jsp"/>
			</form>
		</div>
	</body>
</html>