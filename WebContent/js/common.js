function check(regionId,userId){
	var reglog = '<div><div id="info"><input type="hidden" id="tmp_rid" value="'+regionId+'" /><input type="hidden" id="tmp_uid" value="'+userId+'"/><img id="info_title" src="images/info_title.gif" /><div id="info_nn">昵 称</div><div id="info_nn_input"><input type="text" id="voter_name" /></div><div id="info_phone">电 话</div><div id="info_phone_input"><input type="text" id="voter_phone" /></div><img src="images/info_confirm.gif" id="info_confirm" onclick="voterSubmit()" /><img src="images/info_close.gif" id="info_close" alt="关闭" onclick="closeOverlay(\'modal_overlay\')" /></div>';
	var $modal = $(reglog)
    .attr('id', 'modal')
    .css({
	    zIndex: 3000,
	    opacity: 1,
	    position: 'absolute',
	    top: '30%',
	    left: '40%'
    });
	$(this).overlay({
		id:"modal_overlay",
	    effect: 'fade',
	    opacity: 0.8,
	    closeOnClick: true,
	    onShow: function() {
	        $('body').append($modal);
	        FauxPlaceholder();
	        },
	    onHide: function() {
	        $modal.remove();
	        }
	});
}
function FauxPlaceholder() {
	if(!ElementSupportAttribute('input','placeholder')) {
		$("input[placeholder]").each(function() {
			var $input = $(this);
			$input.after('<input id="'+$input.attr('id')+'-faux" style="display:none;" type="text" value="' + $input.attr('placeholder') + '" />');
			var $faux = $('#'+$input.attr('id')+'-faux');

			$faux.show().attr('class', $input.attr('class')).attr('style', $input.attr('style'));
			$input.hide();

			$faux.focus(function() {
				$faux.hide();
				$input.show().focus();
			});

			$input.blur(function() {
				if($input.val() === '') {
					$input.hide();
					$faux.show();
				}
			});
		});
	}
}
function ElementSupportAttribute(elm, attr) {
	var test = document.createElement(elm);
	return attr in test;
}

function funPlaceholder(element) {
    var placeholder = '';
    if (element && !("placeholder" in document.createElement("input")) && (placeholder = element.getAttribute("placeholder"))) {
        element.onfocus = function() {
            if (this.value === placeholder) {
                this.value = "";
            }
            this.style.color = '';
        };
        element.onblur = function() {
            if (this.value === "") {
                this.value = placeholder;
                this.style.color = 'graytext';    
            }
        };
        
        //样式初始化

        if (element.value === "") {
            element.value = placeholder;
            element.style.color = 'graytext';    
        }
    }
};

function reload(obj){
	obj.src = "randomCode.do?time="+new Date().getTime();
}
//p=图片连接
//t=标题
//z=内容连接
function share(p,t,z){
		var s = screen;
		var d = document;
		var e = encodeURIComponent;
		var r = '';
		var l = '';
		var c = 'utf-8';
		var f ='http://v.t.sina.com.cn/share/share.php?appkey=realappkey';
		var u = z;
		var pt=['&url=',e(u),'&title=',e(t||d.title),'&source=',e(r),'&sourceUrl=',e(l),'&content=',c||'gb2312','&pic=',e(p||'')].join('');
		
		var a = function(){
			window.open([f,pt].join(''),'mb',['toolbar=0,status=0,resizable=1,width=440,height=430,left=',(s.width-440)/2,',top=',(s.height-430)/2].join(''));
			//if(!)
			//	u.href=[f,pt].join('');
		};
		if(/Firefox/.test(navigator.userAgent))
			setTimeout(a,0);
		else
			a();
}
function registerShare(){
	share('', '#摩登·优女性2012《优家画报》关爱之旅#我已在http://www.uplus.cn成功报名，说不定我就是下一位《优家画报》封面之星！如果你也想享受东南亚风情之旅，快来报名参加！参与投票的你还有机会获得200元丰富礼品（全国共50份）！支持优女性，支持#最具魅力Modern Lady#', '');
}
function commonShare(){
	share('','#摩登·优女性2012《优家画报》关爱之旅#我已在http://www.uplus.cn成功投票，支持“最具魅力Modern Lady”！凡参与投票均有机会获取200元丰富礼品（全国共50份）！支持优女性，支持#最具魅力Modern Lady#','');
}
function voterReg(){
	var voterreg = '<div><div id="vote_reg"><div id="vote_header"><img src="images/vote_close.gif" class="vote_close" alt="关闭" onclick="closeOverlay(\'modal2_overlay\')" /></div><div style="background:#1c1c1e"><div id="vote_main"><div class="vote_input"><input type="text" class="vote_input_text" placeholder="昵称" name="voter_name" id="voter_name" /></div><div class="vote_input"><input type="text" class="vote_input_text" placeholder="电子邮箱" id="voter_email" name="voter_email" /></div><div class="vote_input"><input type="password" placeholder="密码" class="vote_input_text" value="" id="voter_password" name="voter_password" /></div><div class="vote_verify"><input type="text" class="vote_verify_text" placeholder="验证码" name="check_code" id="check_code" /><img title="看不清楚点击图片" width="50" height="20" src="randomCode.do" onclick="reload(this);" style="cursor: pointer;margin-left:20px" /></div><div class="vote_submit" onclick="voterSubmit()"></div></div></div><div id="vote_footer"></div></div></div>';
	var $modal2 = $(voterreg)
    .attr('id', 'modal2')
    .css({
	    zIndex: 3000,
	    opacity: 1,
	    position: 'absolute',
	    top: '30%',
	    left: '40%'
    });
	$(this).overlay({
		id:"modal2_overlay",
	    effect: 'fade',
	    opacity: 0,
	    closeOnClick: true,
	    onShow: function() {
	        	$('body').append($modal2);
	        	FauxPlaceholder();
	        },
	    onHide: function() {
	        $modal2.remove();
	        }
	});	
}
function voterSubmit(){
	if($("#voter_name").val() == ''){
		alert("请输入昵称");
		return false;
	}
	if($("#voter_phone").val()==''){
		alert("请输入电话");
		return false;
	}
	$.post('voter/register.do',"n=" + encodeURIComponent($("#voter_name").val()) + "&p=" +$("#voter_phone").val() + "&rid=" + $("#tmp_rid").val() + "&uid=" + $("#tmp_uid").val(), function(data) {
		if(data.nuid){
			try{
				$("#v_m_" + $("#tmp_uid").val()).html(parseInt($("#v_m_" + $("#tmp_uid").val()).html()) + 1);
				$("#v_m2_" + $("#tmp_uid").val()).html(parseInt($("#v_m2_" + $("#tmp_uid").val()).html()) + 1);
			}catch(e){}			
			$("#modal").html('<div id="info_complete"><img src="images/info_success.gif" id="info_success" style="cursor:pointer" onclick="closeOverlay(\'modal_overlay\')" /><img src="images/info_close.gif" id="info_close" onclick="closeOverlay(\'modal_overlay\')" /></div>');
		}else if(data.state == 0){
			alert(data.message);
		}else{
			alert(data.message);
		}
		},"json");
}
function voterLogin(){
	
	if($("#ve").val()==''){
		alert("请输入电子邮箱");
		return false;
	}
	if($("#vp").val() == ''){
		alert("密码不能为空");
		return false;
	}
	if($("#cc").val() == ''){
		alert("验证码不能为空");
		return false;
	}	
	$.post('voter/login.do',"e=" + $("#ve").val() + "&p=" +$("#vp").val() + "&c="+$("#cc").val(), function(data) {
		if(data.state == 0){
			location.reload();
		}else{
			alert(data.message);
		}
		},"json");	
}
function closeOverlay(id){
	$("#" +id).click();
}
function register(){
	$.getJSON("show/getRegions.do",function(data){
		var regions = "";
		for ( var i = 0; i < data.length; i++) {
			regions += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
		}
		//var reg = '<div><div id="reg_box"><div id="reg_box_header"><img src="images/reg_close.gif" class="reg_close" alt="关闭" onclick="closeOverlay(\'modal3_overlay\')" /></div><div id="reg_box_main"><div id="reg_box_main_c"><div class="reg_box_c"><div style="margin-right:25px">我的邮箱</div><div class="reg_box_input"><input type="text" /></div></div><div class="reg_box_c" style="padding-top:17px"><div style="margin-right:25px">创建密码</div><div class="reg_box_input"><input type="text" /></div></div><div class="reg_box_c" style="padding-top:15px"><div style="margin-right:50px">昵称</div><div class="reg_box_input"><input type="text" /></div></div><div class="reg_box_c" style="padding-top:14px"><div style="margin-right:27px">参赛地区</div><div class="reg_box_select"><select>'+regions+'</select></div></div><div class="reg_box_c" style="padding-top:12px"><div style="margin-right:20px">姓名(真实)</div><div class="reg_box_input"><input type="text" /></div></div><div class="reg_box_c" style="padding-top:12px"><div style="margin-right:52px">年龄</div><div class="reg_box_input"><input type="text" /></div></div><div class="reg_box_c" style="padding-top:16px"><div style="margin-right:29px">联系电话</div><div class="reg_box_input"><input type="text" /></div></div><div class="reg_box_c" style="padding-top:17px"><div style="margin-right:29px">手机号码</div><div class="reg_box_input"><input type="text" /></div></div><div class="reg_box_c" style="padding-top:17px"><div style="margin-right:29px">一句宣言</div><div class="reg_box_input2"><input type="text" /></div></div><div class="reg_box_c" style="padding-top:20px"><div style="margin-right:6px">上传个人相片</div><img src="images/reg_up.gif" class="reg_up" /></div><div class="reg_box_c" style="padding-top:12px"><div style="margin-right:35px">验 证 码</div><div class="reg_box_verify"><input type="text" id="check_code" maxlength="4" /></div><img title="看不清楚点击图片" width="50" height="20" src="randomCode.do" onclick="reload(this);" style="cursor: pointer;margin-left:20px" /></div></div><img src="images/reg_submit.gif" class="reg_submit" alt="提交" /></div><div id="reg_box_footer"></div></div></div>';
		var reg = '<div><input type="hidden" id="upic"><div id="reg_box"><div id="reg_box_header"><img src="images/reg_close.gif" class="reg_close" alt="关闭" onclick="closeOverlay(\'modal3_overlay\')" /></div><div id="reg_box_main"><div id="reg_box_main_c"><div class="reg_box_c"><div style="margin-right:44px">我的邮箱</div><div class="reg_box_input"><input type="text" id="email" /></div><div class="reg_box_c"><div style="margin-right:69px">密码</div><div class="reg_box_input"><input type="password" id="password"></div></div></div><div class="reg_box_c"><div style="margin-right:69px">昵称</div><div class="reg_box_input"><input type="text" id="nickname" /></div></div><div class="reg_box_c"><div style="margin-right:46px">参赛地区</div><div class="reg_box_select"><select id="region_id">'+regions+'</select></div></div><div class="reg_box_c"><div style="margin-right:39px">姓名(真实)</div><div class="reg_box_input"><input type="text" id="real_name" /></div></div><div class="reg_box_c"><div style="margin-right:71px">年龄</div><div class="reg_box_input"><input type="text" id="age" /></div></div><div class="reg_box_c"><div style="margin-right:48px">联系电话</div><div class="reg_box_input"><input type="text" id="telephone" /></div></div><div class="reg_box_c"><div style="margin-right:48px">手机号码</div><div class="reg_box_input"><input type="text" id="mobile" /></div></div><div class="reg_box_c2"><div style="margin-right:11px">我的魅力优主张<br/>（100字以内）</div><div class="reg_box_area"><textarea id="bio" placeholder="（你认为最具魅力的Modern Lady需要具备什么条件？）"></textarea></div></div><div class="reg_box_c" style="position:relative"><div style="margin-right:25px">上传个人照片<br/>（限制1M）</div><img src="images/pic1.gif" id="reg_pic" style="border:1px solid #d9cd69" /><div id="up_file_btn"></div></div><div class="reg_box_c"><div style="margin-right:54px">验 证 码</div><div class="reg_box_verify"><input type="text" id="check_code" maxlength="4" /></div><img title="看不清楚点击图片" width="50" height="20" src="randomCode.do" onclick="reload(this);" style="cursor: pointer;margin-left:20px" /></div></div><img src="images/reg_submit.gif" class="reg_submit" alt="提交" onclick="userRegister()" /></div><div id="reg_box_footer"></div></div></div>';
		var $modal3 = $(reg)
	    .attr('id', 'modal3')
	    .css({
		    zIndex: 3000,
		    opacity: 1,
		    position: 'absolute',
		    top: '10%',
		    left: '25%'
	    });
		$(this).overlay({
			id:"modal3_overlay",
		    effect: 'fade',
		    opacity: 0.8,
		    closeOnClick: true,
		    onShow: function() {
		        $('body').append($modal3);
		        FauxPlaceholder();
		        new AjaxUpload(document.getElementById('up_file_btn'),{
		            action: 'show/upload.do',
		            name: 'qqfile',
		            responseType:"JSON",
		            onSubmit : function(file, ext){
	                    if (ext && /^(jpg|png|jpeg)$/.test(ext)){
	                    } else {
	                    	alert("文件类型不支持，只支持jpg,jpeg,png");
	                        return false;
	                    }
		            },
		            onChange:function(){
		            	if(document.getElementById("qqfile").files && document.getElementById("qqfile").files[0].size > 1024 * 1024){
		            		alert("图片大小不能超过1M");
		            		return false;
		            	}
		            },           
		            onComplete: uploadComplete
		        });		        
		        
		    },
		    onHide: function() {
		        $modal3.remove();
		        }
		});
 
	});
	
}
function uploadComplete(fileName, responseJSON){
	var small = responseJSON.pic.replace("big","small");
	$("#upic").val(small);
	$("#reg_pic").attr("src",small);
	var medium = responseJSON.pic.replace("big","medium");
	var picdiv = '<div id="pic_confirm" ><div id="pic_confirm_h"></div><div id="pic_confirm_m"><img src="images/confirm_btn.gif" id="btn_c" onclick="closeOverlay(\'pic_confirm_overlay\')" /><img id="pic_small" src="'+small+'" /><img id="pic_medium" src="'+medium+'" /></div><div id="pic_confirm_f"></div></div>';
	var $modal2 = $(picdiv)
    .attr('id', 'pic_confirm_modal')
    .css({
	    zIndex: 3000,
	    opacity: 1,
	    position: 'absolute',
	    top: '20%',
	    left: '25%'
    });
	$(this).overlay({
		id:"pic_confirm_overlay",
	    effect: 'fade',
	    opacity: 0,
	    closeOnClick: true,
	    onShow: function() {
	        	$('body').append($modal2);
	        },
	    onHide: function() {
	        $modal2.remove();
	        }
	});
}
function userRegister(){
	if($("#email").val() == ''){
		alert("电子邮箱不能为空!");
		return false;
	}
	if(!(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test($("#email").val()))){
		alert("电子邮箱格式不正确!");
		return false;
	}
	if($("#password").val() == ''){
		alert("密码不能为空!");
		return false;
	}	
	if($("#password").val().length > 20){
		alert("密码长度不能超过16个字符!");
		return false;
	}	
	if($("#nickname").val() == ''){
		alert("昵称不能为空!");
		return false;
	}
	if($("#nickname").val().length>10){
		alert("昵称长度不能超过10个字符!");
		return false;
	}
	if($("#region_id").val() == ''){
		alert("请选择参赛地区!");
		return false;
	}
	if($("#real_name").val() == ''){
		alert("真实姓名不能为空!");
		return false;
	}
	if($("#real_name").val().length>10){
		alert("真实姓名长度不能超过10个字符!");
		return false;
	}
	if($("#age").val()==''){
		alert("年龄不能为空!");
		return false;
	}
	if(!(/^\d+$/.test($("#age").val()))){
		alert("年龄只能用数字!");
		return false;
	}
	if(parseInt($("#age").val())>100){
		alert("年龄太大了!");
		return false;
	}	
	if($("#telephone").val() == '' && $("#mobile").val() == ''){
		alert("联系电话和手机号码不能同时为空!");
		return false;
	}
	if($("#telephone").val().length > 20){
		alert("联系电话长度不正确!");
		return false;
	}
	if($("#mobile").val().length > 20){
		alert("手机号码长度不正确!");
		return false;
	}
	if($("#bio").val().length>100){
		alert("宣言长度不能超过100个字符!");
		return false;
	}
	if($("#check_code").val()==''){
		alert("验证码不能为空!");
		return false;
	}
	if($("#upic").val() == ''){
		alert("请上传个人照片!");
		return false;
	}
	$.post('show/register.do',"e=" + encodeURIComponent($("#email").val()) + "&p=" + $("#password").val() + "&nn=" +encodeURIComponent($("#nickname").val()) + "&r=" +$("#region_id").val() + "&te=" + $("#telephone").val() + "&m=" + $("#mobile").val() + "&b=" + encodeURIComponent($("#bio").val()) + "&n=" + encodeURIComponent($("#real_name").val()) + "&pic=" + $("#upic").val() + "&a=" + $("#age").val() + "&c="+$("#check_code").val(), function(data) {
		if(data.state == 0){
			alert(data.message);
			registerShare();
			location.href='rank.do';
		}else{
			alert(data.message);
		}
		},"json");
}
function vote(region_id,user_id){
	$.getJSON('voter/vote.do?rid=' + region_id + "&uid="+ user_id, function(data) {
		if(data.state == 2){
			check(region_id,user_id);
		}else{
			alert(data.message);
			if(data.state == 0){
				try{
					$("#v_m_" + user_id).html(parseInt($("#v_m_" + user_id).html()) + 1);
					$("#v_m2_" + user_id).html(parseInt($("#v_m2_" + user_id).html()) + 1);
				}catch(e){}
				
			}			
		}
	});
}