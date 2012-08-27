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
					window.location.href = "user_delete.do?user.id="+id;
				}
			}
			function query(){
				var form = get("myForm");
				form.action = "user_query.do";
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
			<form name="myForm" action="user_list.do" method="post">
				<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
				<tr>
				  <td class="pn-flabel pn-flabel-h" style="text-align:left">&nbsp;&nbsp;昵称:
				    <input type="text" name="username" value="${username}"/>
                    &nbsp;是否有效:
				    <select name="valid">
				  		<option value="">请选择</option>
				  		<option value="0" <c:if test="${valid eq '0'}">selected</c:if>>未审核</option>
				  		<option value="1" <c:if test="${valid eq '1'}">selected</c:if>>已审核</option>
				  	</select>
				    &nbsp;<input type="button" value=" 查 询 " onclick="query()"/>
				  </td>
				</tr>
			</table>
				<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
					<thead class="pn-lthead">
						<tr>
							<th>昵称 </th>
							<th>电子邮箱</th>
							<th>年龄</th>
							<th>参赛地区</th>
							<th>联系电话</th>
							<th>手机号码</th>
							<th>海选票数</th>
							<th>决赛票数</th>
							<th>是否有效</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody class="pn-ltbody">
						<c:forEach items="${list}" var="user">
							<tr onmouseover="this.className='pn-lhover'" onmouseout="this.className=''" align="center">
								<td title="${user.nickname}"><div class="w150">${user.nickname}</div></td>
								<td>${user.email}</td>
								<td>${user.age}</td>
								<td>${user.regionName}</td>
								<td>${user.telephone}</td>
								<td>${user.mobile}</td>
								<td>${user.rank1}</td>
								<td>${user.rank2}</td>
								<td>
									<c:choose>
										<c:when test="${user.valid}">已审核</c:when>
										<c:otherwise>未审核</c:otherwise>
									</c:choose>
								</td>
								<td class="pn-lopt"><a href="user_edit.do?user.id=${user.id}" class="pn-loperator">修改</a>┆<a 
									href="javascript:del(${user.id});" class="pn-loperator">删除</a>┆<a 
									href="user_view.do?user.id=${user.id}" class="pn-loperator">查看</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<jsp:include page="../include/pager.jsp"/>
			</form>
		</div>
	</body>
</html>