<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>

<link type="text/css" rel="stylesheet" href="${basePath}/css/jquery.css"/>
<script type="text/javascript" src="${basePath}/js/jquery/jquery-1.3.2.min.js" charset="UTF-8"></script> 
<script type="text/javascript" src="${basePath}/js/jquery/jquery.validate.js" charset="UTF-8"></script> 
<script type="text/javascript" src="${basePath}/js/jquery/messages_cn.js" charset="UTF-8"></script> 	
<script type="text/javascript">	

$(document).ready(function() {
	$("#myForm").validate({			
		success: function(label) {					
			label.html("&nbsp;").addClass("checked");
		}
	});
		
	jQuery.validator.addMethod("decimal", function(value, element) {
		   var decimal = /^-?\d+(\.\d+)?$/;
		   return this.optional(element) || (decimal.test(value));
	}, $.validator.format("请正确输入金额!"));	
		
});		
</script>