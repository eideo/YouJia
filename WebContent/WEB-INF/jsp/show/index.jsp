<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>排行榜</title>
	<link rel="stylesheet" href="css/show.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="css/common.css" type="text/css" media="screen" />
</head>
<body>
	<div id="container">
		<div id="nav">
			<input id="nav1" type="image" src="images/nav1.gif" onclick="location.href='${basePath}/index.do'"/>
			<input id="nav2" type="image" src="images/nav2.gif" onclick="register()" />
			<input id="nav3" type="image" src="images/nav3_sel.gif" onclick="location.href='${basePath}/rank.do'"/>
			<input id="nav4" type="image" src="images/nav4.gif" onclick="location.href='${basePath}/previous.do'"/>
			<input id="nav5" type="image" src="images/nav5.gif" onclick="location.href='${basePath}/reward.do'"/>		
		</div>
		<div id="show_main">
			<div id="show_left">
				<div id="show_nav">
					<input type="image" src="images/bj.gif" id="rid4" onclick="getUsers(this,4);return false;"/>
					<input type="image" src="images/gz_sel.gif"  id="rid10" onclick="getUsers(this,10);return false;"/>
					<input type="image" src="images/sh.gif" id="rid1" onclick="getUsers(this,1);return false;"/>
					<input type="image" src="images/wh.gif" id="rid11" onclick="getUsers(this,11);return false;"/>
					<input type="image" src="images/nj.gif" id="rid5" onclick="getUsers(this,5);return false;"/>
					<input type="image" src="images/sz.gif" id="rid12" onclick="getUsers(this,12);return false;"/>
					<input type="image" src="images/cq.gif" id="rid8" onclick="getUsers(this,8);return false;"/>
					<input type="image" src="images/xa.gif" id="rid7" onclick="getUsers(this,7);return false;"/>
					<input type="image" src="images/cd.gif" id="rid9" onclick="getUsers(this,9);return false;"/>
					<input type="image" src="images/tj.gif" id="rid6" onclick="getUsers(this,6);return false;"/>
					<input type="image" src="images/sy.gif" id="rid3" onclick="getUsers(this,3);return false;"/>
					<input type="image" src="images/hz.gif" id="rid2" onclick="getUsers(this,2);return false;"/>																																										
				</div>
				<div id="showers"></div>
			</div>
			<div id="show_right">
				<div id="shower_detail" style="display:none">
					<img src="" id="shower_pic" />
					<div class="shower_re" style="margin-top:3px">
						<span class="v_n" id="v_n2"></span>
						<div class="shower_vote1">
							<div class="v_l"></div>
							<div class="v_m" id="v_m2"></div>
							<div class="v_r"></div>
						</div>
						<img src="images/heartme.gif" onclick="javascript:vote1()" title="投我一票" />
						
					</div>
					<div class="shower_a"><span id="v_a2"></span><img src="images/comment.gif" id="log" title="说点什么？" onclick="javascript:checkState()" /></div>
					<div class="shower_a" style="color:#e5004f;font-size:14px;font-weight:bold">“我的魅力优主张”：</div>
					<div id="shower_bio"></div>
					<img src="images/comment_t.gif" />
					<div id="comment_list">
					</div>					
				</div>
			</div>
		</div>
		<div style="clear:both"><input type="hidden" name="regionId" value="6" /></div>
		<div class="copyright"></div>
	</div>
	<jsp:include page="../include/js.jsp"></jsp:include>
	<script type="text/javascript">
		var nowRegionId=${regionId};
		var nowId;
		var tmpUserId = ${nowUserId};
		var preSel = null;
		$(document).ready(function(){
			if(nowRegionId >0){
				getUsers($("#rid" + nowRegionId),nowRegionId);
			}else{
				getUsers($("#rid10"),10);
			}
						
		});
		function getUsers(obj,regionId){
			nowRegionId = regionId;
			$('#showers').html('<div style="margin-top:100px;font-size:20px;">正在加载参赛数据，请稍候...</div>');
			$.get('show/entities.do?regionId='+regionId+'&r='+Math.random(), function(data) {
					if(preSel != null){
						$(preSel).attr("src",$(preSel).attr("src").replace("_sel",""));
					}
					$(obj).attr("src",$(obj).attr("src").replace("_sel","").replace(".gif","_sel.gif"));
					preSel = obj;
				  	$('#showers').html();
				  	$('#showers').html(data);
				  	if(tmpUserId > 0){
				  		getUserDetail($("#img_user_" + tmpUserId),regionId,tmpUserId);
				  		tmpUserId = 0;
				  	}
			});
		}
		function getUsers2(pageNo){
			$('#showers').html('<div style="margin-top:100px;font-size:20px;">正在加载参赛数据，请稍候...</div>');
			$.get('show/entities.do?pn='+pageNo+'&regionId='+nowRegionId+'&r='+Math.random(), function(data) {
			  	$('#showers').html();
			  	$('#showers').html(data);
			});
		}		
		function vote1(){
			vote(nowRegionId,nowId);
		}
		function checkState(){
			$.getJSON('voter/checkState.do', function(data) {
				comment(data.name);
			});	
		}
		function comment(nn){
			var m = '<div><div id="info_comments"><div id="info_nn_2">昵 称</div><div id="info_nn2_input"><input type="text" id="comment_name" value="'+nn+'" /></div><div id="info_comm">评论</div><div id="info_comment_area"><textarea id="comment_con"></textarea></div><img src="images/info_confirm.gif" id="info_confirm" onclick="commentSubmit()" /><img src="images/info_close.gif" id="info_close" alt="关闭" onclick="closeOverlay(\'comment_overlay\')" /></div></div>';
			var $comment_overlay = $(m)
		    .attr('id', 'comment_overlay_modal3')
		    .css({
			    zIndex: 3000,
			    opacity: 1,
			    position: 'absolute',
			    top: '40%',
			    left: '45%'
		    });
			$(this).overlay({
				id:"comment_overlay",
			    effect: 'fade',
			    opacity: 0.8,
			    closeOnClick: true,
			    onShow: function() {
			        $('body').append($comment_overlay);
			        },
			    onHide: function() {
			        $comment_overlay.remove();
			        }
			});					
		}
		function commentSubmit(){
			if($("#comment_name").val() == ''){
				alert("昵称不能为空");
				return false;
			}			
			if($("#comment_con").val() == ''){
				alert("评论内容不能为空");
				return false;
			}
			if($("#comment_con").val().length > 40){
				alert("评论内容长度不能超过40个字符");
				return false;
			}			
			$.post('voter/comment.do','c=' + encodeURIComponent($("#comment_con").val()) + '&uid='+ nowId + "&n=" + encodeURIComponent($("#comment_name").val()) ,function(data) {
					if(data.state == 2){
						checkState();
					}else{
						alert(data.message);
						if(data.state == 0){
							closeOverlay('comment_overlay');
							getUserComments(nowId);
						}
					}
				},"json");	
		}
		function getUserComments(id,pageNo){
			$.post("voter/comments.do","u="+id+"&pn="+pageNo,function(data){
				$('#comment_list').html(data);
			});
		}
		function getPicture(id,pic){
			var pagerBtn = "";
			$.getJSON("show/userPicture.do?u="+id + "&r=" + nowRegionId,function(data){
				if(data.pre){
					pagerBtn = "<img src='images/previous.gif' style='position:absolute;left:12px;bottom:13px;cursor:pointer' onclick='getPicture("+data.pre.id+",\""+data.pre.pic+"\")' />";
				}
				if(data.next){
					pagerBtn = pagerBtn + "<img src='images/next.gif' style='position:absolute;right:13px;bottom:13px;cursor:pointer' onclick='getPicture("+data.next.id+",\""+data.next.pic+"\")' />";
				}
				$("#pagerbtn").html(pagerBtn);
			});
		//	$("#user_big_img").hide();
			$("#user_big_img").attr("src","${basePath}" + pic.replace("small","big"));
			//var picHeight = $("#user_big_img").height();
			//var picWidth = $("#user_big_img").width();
		//	var screenH = $(document.body).height();
			//if(picHeight > (screenH - 100)){
		//		$("#user_big_img").attr("height",(screenH - 100));
		//		$("#user_big_img").attr("width",picWidth * ((screenH - 100) / picHeight));
		//	}
		//	$("#user_big_img").show();
		}
		function getUserDetail(obj,regionId,id){
			nowRegionId = regionId;
			nowId = id;
			$("#shower_pic").attr("src",$(obj).attr("src").replace("small","medium"));
			$("#v_n2").html($("#v_n_" + id).html());
			$("#v_m2").html($("#v_m_" + id).html());
			$("#v_a2").html($("#v_a_" + id).html());
			$("#v_v2").attr("class",$("#v_v_" + id).attr("class"));	
			$("#shower_bio").html($("#v_b_" + id).html());
			getUserComments(id,1);
			$("#shower_detail").show();

			$.getJSON("show/userPicture.do?u="+id + "&r=" + nowRegionId,function(data){
				var pagerBtn = "";
				if(data.pre){
					pagerBtn = "<img src='images/previous.gif' style='position:absolute;left:12px;bottom:13px;cursor:pointer' onclick='getPicture("+data.pre.id+",\""+data.pre.pic+"\")' />";
				}
				if(data.next){
					pagerBtn = pagerBtn + "<img src='images/next.gif' style='position:absolute;right:13px;bottom:13px;cursor:pointer' onclick='getPicture("+data.next.id+",\""+data.next.pic+"\")' />";
				}
				var m = "<div><div id='pic_close' onclick='closeOverlay(\"user_overlay\")'></div><div id='user_big_div'><img id='user_big_img' src='"+$(obj).attr("src").replace("small","big")+ "' /></div><div id='pagerbtn'>"+pagerBtn+"</div></div>";
				var screenH = $(document.body).height();
				var screenW = $(document.body).width();
				var $user_overlay = $(m)
			    .attr('id', 'user_overlay_modal3')
			    .css({
				    zIndex: 3000,
				    opacity: 1,
				    position: 'absolute',
				    top: $(document).scrollTop() + 100 + "px",
				    left: ($(document.body).width() - 446) / 2 + "px" 
			    });
				$(this).overlay({
					id:"user_overlay",
				    effect: 'fade',
				    opacity: 0.8,
				    closeOnClick: true,
				    onShow: function() {
				        $('body').append($user_overlay);
						//var picHeight = $("#user_big_img").height();
						//var picWidth = $("#user_big_img").width();
						
				        $("#user_overlay_modal3").attr("width",$(document.body).height());
				    },
				    onHide: function() {
				        $user_overlay.remove();
				        }
				});
			});
			
		}
	</script>
</body>
</html>