<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:forEach items="${entities}" var="entry">
<div class="shower">
	<div class="shower_pic">
		<img src="${basePath}${entry.pic1}" title="点击查看详细信息" id="img_user_${entry.id}" onclick="getUserDetail(this,${entry.regionId},${entry.id})" />
	</div>
	<div class="shower_re">
		<span class="v_n" id="v_n_${entry.id}">${entry.nickname}</span>
		<div class="shower_vote">
			<div class="v_l"></div>
			<div class="v_m" id="v_m_${entry.id}">${entry.rank1}</div>
			<div class="v_r"></div>
		</div>		
	</div>
	<div class="shower_a"><span id="v_a_${entry.id}">${entry.age}岁</span><img style="position:absolute;right:73px;cursor:pointer" src="images/sina.gif" onclick="commonShare()" /><img style="position:absolute;right:0;top:2px;cursor:pointer" src="images/heartme.gif" onclick="javascript:vote(${entry.regionId},${entry.id})" title="投我一票" /></div>
	
	<div id="v_b_${entry.id}" style="display:none">${entry.bio1}</div>
</div>
</c:forEach>
<c:if test="${fn:length(entities) == 0}">
<div style="margin-top:100px;font-size:20px;">暂时没人参加比赛</div>
</c:if>
<jsp:include page="../include/user_pager.jsp"/>