<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>首页</title>
	<link rel="stylesheet" href="css/home.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="css/common.css" type="text/css" media="screen" />
	<script type="text/javascript" src="ck.js?57" charset="utf-8"></script>
	<script type="text/javascript">
		var nowRegionId = null;
	</script>
</head>
<body>
	<div id="container">
		<div id="nav">
			<input id="nav1" type="image" src="images/nav1_sel.gif" onclick="location.href='${basePath}/index.do'"/>
			<input id="nav2" type="image" src="images/nav2.gif" onclick="register()" />
			<input id="nav3" type="image" src="images/nav3.gif" onclick="location.href='${basePath}/rank.do'"/>
			<input id="nav4" type="image" src="images/nav4.gif" onclick="location.href='${basePath}/previous.do'"/>
			<input id="nav5" type="image" src="images/nav5.gif" onclick="location.href='${basePath}/reward.do'"/>		
		</div>
		<div>
			<img src="images/ban.gif" />
		</div>
		<div id="activity">
			<div id="activity_remark">
				<div class="media">
					<script type="text/javascript">C.K({f:"${basePath}/static/Final_A.mp4",e:"1"},"tmedia",295,235,{wmode:"transparent"});</script>
				</div>
				<img src="images/activity_intro.jpg" style="margin-top:3px">
				<div style="width:277px;margin:4px 0 0 4px;line-height:17px"><span style="margin-left:20px">《优家画报》是目前中国销量领先的高端现代女性周刊，独创“修身、齐家、游天下”一报三册内容，唯一一本提供“一站式”阅读和优雅生活全面解决方案的刊物。</span><br/><span style="margin-left:20px">《优家画报》巡展作为《优家画报》的年度重点活动，已成功举办三届，成为同类媒体中规模最大、最具影响力的活动，今年将在原有基础上，将在全国12个城市进行巡展，为优女性提供更多关爱身心的新鲜资讯，并通过舞台环节选出“最具魅力Modern Lady”。</span></div>
				<img src="images/detail.jpg" style="cursor:pointer" id="detail" onclick="location.href='intro.do'" />
			</div>			
			<div id="activity_pictures">
				<img src="images/s_page1.jpg" />
				<img src="images/s_page2.jpg" />
			</div>
		</div>
		<div id="rank_list">
			<img src="images/rank.gif" />
			<div style="">
			<c:forEach var="region" items="${regions}" varStatus="rinfo">
				<c:choose>
					<c:when test="${rinfo.index == 0}"><span class="span_sel" onclick="showRegionDetail(this,${region.id})" id="fcp">${region.name}</span><script>nowRegionId = ${region.id}</script></c:when>
					<c:otherwise><span class="span_unsel" onclick="showRegionDetail(this,${region.id})">${region.name}</span></c:otherwise>
				</c:choose>
			</c:forEach>
			</div>
		</div>
		<div id="rank_pic">
			
		</div>
		<div id="main">
			<img src="images/act_map.gif" style="position:absolute;left:10px;top:18px" />
			<div id="activities">
				<c:forEach var="region" items="${regions}" varStatus="rinfo">
					<c:choose>
						<c:when test="${ region.state > 0  && region.startTime < curDate}"><div class="circle d${rinfo.index+1} cp${rinfo.index+1}" onmouseover="tooltip.show('开始时间：<fmt:formatDate value="${region.startTime}" pattern="yyyy-MM-dd"/><br/>结束时间：<fmt:formatDate value="${region.endTime}" pattern="yyyy-MM-dd"/>${region.remark}');" onmouseout="tooltip.hide()">${region.name}</div></c:when>
						<c:otherwise><div class="circle cc${rinfo.index+1} cp${rinfo.index+1}" onmouseover="tooltip.show('开始时间：<fmt:formatDate value="${region.startTime}" pattern="yyyy-MM-dd"/><br/>结束时间：<fmt:formatDate value="${region.endTime}" pattern="yyyy-MM-dd"/>${region.remark}');" onmouseout="tooltip.hide()">${region.name}</div></c:otherwise>
					</c:choose>
				</c:forEach>
				<span style="position:absolute;left:95px;top:75px;color:#fff">（首站）</span>
				<span style="position:absolute;left:180px;top:355px;color:#fff">（最后一站）</span>
			</div>
			<div id="news">
				<img src="images/news.gif" />
				<c:forEach items="${lastestNews}" var="n">
				<div class="news_item"><span></span><a href="news.do?id=${n.id}" class="link">${n.title}</a></div>
				</c:forEach>
			</div>
			<div id="prouser"></div>
			<img src="images/tuan.gif" class="tuan" />
			<img src="images/p1.gif" class="p1" onmouseover="mouover('pp1')" onmouseout="mouout('pp1')"/>
			<img src="images/p2.gif" class="p2" onmouseover="mouover('pp2')" onmouseout="mouout('pp2')"/>
			<img src="images/p3.gif" class="p3" onmouseover="mouover('pp3')" onmouseout="mouout('pp3')"/>
			<img src="images/p4.gif" class="p4" onmouseover="mouover('pp4')" onmouseout="mouout('pp4')"/>
			<img src="images/p5.gif" class="p5" onmouseover="mouover('pp5')" onmouseout="mouout('pp5')"/>
			<img src="images/pp1.gif" id="pp1" class="pp1"/>
			<img src="images/pp2.gif" id="pp2" class="pp2"/>
			<img src="images/pp3.gif" id="pp3" class="pp3"/>
			<img src="images/pp4.gif" id="pp4" class="pp4"/>
			<img src="images/pp5.gif" id="pp5" class="pp5"/>
		</div>
		<div id="frlink"><img src="images/lo1.gif" class="lo1" /><a href="http://www.watsons.com.cn/" target="_blank"><img src="images/lo2.gif" class="lo2"/></a><a href="http://www.tatashoes.com.cn" target="_blank"><img src="images/lo3.gif" class="lo3" /></a><a href="http://www.modernweekly.com" target="_blank"><img src="images/lo4.gif" class="lo4"/></a><a href="http://www.ctf.com.cn/index.asp#cn,gb,home" target="_blank"><img src="images/lo5.gif" class="lo5"/></a><a href="http://www.goelia.com.cn" target="_blank"><img src="images/lo6.gif" class="lo6"/></a><a href="http://www.modernmedia.com.cn/event/2012imagazine/index.html" target="_blank"><img src="images/lo7.gif" class="lo7"/></a><a href="http://www.eau-thermale-avene.cn" target="_blank"><img src="images/lo8.gif" class="lo8"/></a><a href="http://www.xjthealthylife.cn/" target="_blank"><img src="images/lo9.gif" class="lo9"/></a><a href="http://weibo.com/moderntv" target="_blank"><img src="images/lo10.gif" class="lo10"/></a><a href="https://www.airasiago.com" target="_blank"><img src="images/lo14.gif" class="lo14"/></a><a href="http://www.metroer.com" target="_blank"><img src="images/lo12.gif" class="lo12"/></a><img src="images/lo13.gif" class="lo13"/><a href="http://www.airasia.com/cn" target="_blank"><img src="images/lo11.gif" class="lo11"/></a><a href="http://club.cmpd.cn:99" target="_blank"><img src="images/lo15.gif" class="lo15"/></a></div>
		<div id="copyright">
			<img src="images/copyright.gif" style="margin-bottom:5px;" />
		</div>
	</div>
	<div><img src="images/flower.gif" id="flower" /></div>
	<jsp:include page="../include/js.jsp"></jsp:include>
	<script src="js/tip.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/jquery/jquery.cycle.all.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var preSel = null;
		function showRegionDetail(obj,regionId){
			$('#rank_pic').html('<div style="margin-top:60px;font-size:20px;color:#fff">正在加载参赛数据，请稍候。。。</div>');
			$.get('users.do?r='+regionId+'&ar='+Math.random(), function(data) {
			  	$('#rank_pic').html();
			  	$('#rank_pic').html(data);
			});
			if(obj == null){
				preSel = $("#fcp");
			}else{
				if(preSel != null){
					$(preSel).removeClass("span_sel").addClass("span_unsel");
				}
				$(obj).removeClass("span_unsel").addClass("span_sel");
				preSel = obj;
			}
		}
		function mouover(id){
			$("#" + id).show();
		}
		function mouout(id){
			$("#" + id).hide();
		}
		$(document).ready(function(){
			showRegionDetail(null,nowRegionId);
			$(".news_item").mouseover(function(){
				$(this).removeClass("news_item");
				$(this).addClass("news_item_select");
			}).mouseout(function(){
				$(this).removeClass("news_item_select");
				$(this).addClass("news_item");
			});
			$("#flower").mouseover(function(){flower.stop();}).mouseout(function(){flower.start();}).click(function(){
				var m = "<div><div id='pic_close' onclick='closeOverlay(\"flower_overlay\")'></div><img src='images/big_flower.gif' style='width:672px;height:714px' /></div>"
				var $user_overlay = $(m)
			    .attr('id', 'flower_overlay_modal3')
			    .css({
				    zIndex: 3000,
				    opacity: 1,
				    position: 'absolute',
				    top: '5%',
				    left: '20%'
			    });
				$(this).overlay({
					id:"flower_overlay",
				    effect: 'fade',
				    opacity: 0.8,
				    closeOnClick: true,
				    onShow: function() {
				        $('body').append($user_overlay);
				        },
				    onHide: function() {
				        $user_overlay.remove();
				        }
				});
			});
			flower.start();
			$('#activity_pictures').cycle({ 
				fx:      'custom', 
					cssBefore: {  
						left: 115,  
						top:  115,  
						width: 0,  
						height: 0,  
						opacity: 1, 
						display: 'block' 
					}, 
					animOut: {  
						opacity: 0  
					}, 
					animIn: {  
						left: 0,  
						top: 0,  
						width: 652,  
						height: 440  
					}, 
					cssAfter: {  
						zIndex: 0 
					}, 
					delay: -3000 
			});			
		});
		
	</script>
</body>
</html>