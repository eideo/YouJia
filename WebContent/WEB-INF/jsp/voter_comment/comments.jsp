<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:forEach items="${comments}" var="comment" varStatus="idx">
<div class="comment_voter">
	<div class="comment_voter_info${idx.index % 2}">
		<img src="images/p.gif" alt="">
		<span>${comment.name}</span>
	</div>
	<div class="comment_content${idx.index % 2}">
		<div class="c_h"></div>
		<div class="c_c">
			${comment.comment}
		</div>
		<div class="c_f"></div>
	</div>
</div>
</c:forEach>
<script type="text/javascript">
var cid =${userId};
function gotoPage(pageNo) {
	getUserComments(cid,pageNo);
}
</script>
<jsp:include page="../include/comment_pager.jsp"/>
