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
					window.location.href = "news_delete.do?news.id="+id;
				}
			}
			function query(){
				var form = get("myForm");
				form.action = "news_query.do";
				form.submit();
		    }
		</script>
	</head>
	<body>
		<div class="body-box">
			<div class="rhead">
				<div class="rpos">当前位置： ${currentPosition}</div>
				<form class="ropt" method="post" action="news_add.do">
					<input type="submit" value=" 添 加 "/>
				</form>
				<div class="clear"></div>
			</div>
			<form name="myForm" action="news_list.do" method="post">
				<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
					<thead class="pn-lthead">
						<tr>
							<th>标题</th>
							<th>时间</th>
							<th>发布人</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody class="pn-ltbody">
						<c:forEach items="${list}" var="news">
							<tr onmouseover="this.className='pn-lhover'" onmouseout="this.className=''" align="center">
								<td title="${news.title}"><div class="w150">${news.title}</div></td>
								<td>${news.createTime}</td>
								<td>${news.username}</td>
								<td class="pn-lopt"><a href="news_edit.do?news.id=${news.id}" class="pn-loperator">修改</a>┆<a 
									href="javascript:del(${news.id});" class="pn-loperator">删除</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<jsp:include page="../include/pager.jsp"/>
			</form>
		</div>
	</body>
</html>