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
					window.location.href = "comment_delete.do?comment.id="+id;
				}
			}
			function query(){
				var form = get("myForm");
				form.action = "comment_query.do";
				form.submit();
		    }
		</script>
	</head>
	<body>
		<div class="body-box">
			<div class="rhead">
				<div class="rpos">当前位置： ${currentPosition}</div>
				<div class="clear"></div>
			</div>
			<form name="myForm" action="comment_list.do" method="post">
				<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
				<tr>
				  <td class="pn-flabel pn-flabel-h" style="text-align:left">&nbsp;&nbsp;姓名:
				    <input type="text" name="name" value="${name}"/>
                    &nbsp;电子邮箱:<input type="text" name="email" value="${email}"/>
				    &nbsp;<input type="button" value=" 查 询 " onclick="query()"/>
				  </td>
				</tr>
			</table>
				<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
					<thead class="pn-lthead">
						<tr>
							<th>评论人</th>
							<th>电子邮箱</th>
							<th>评论时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody class="pn-ltbody">
						<c:forEach items="${list}" var="comment">
							<tr onmouseover="this.className='pn-lhover'" onmouseout="this.className=''" align="center">
								<td title="${comment.name}"><div class="w150">${comment.name}</div></td>
								<td>${comment.email}</td>
								<td>${comment.createTime}</td>
								<td class="pn-lopt"><a 
									href="javascript:del(${comment.id});" class="pn-loperator">删除</a>┆<a 
									href="comment_view.do?comment.id=${comment.id}" class="pn-loperator">查看</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<jsp:include page="../include/pager.jsp"/>
			</form>
		</div>
	</body>
</html>