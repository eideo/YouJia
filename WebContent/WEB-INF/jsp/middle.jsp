<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>middle</title>
<link href="${basePath}/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var LEFT_MENU_VIEW=0;
	function leftmenu_open()
	{
		LEFT_MENU_VIEW=0;
		leftmenu_ctrl();
	}
	function leftmenu_ctrl()
	{
		var frame = parent.document.getElementById("mainFrame");
	   if(LEFT_MENU_VIEW==0)
	   {
	      frame.cols="200,9,*";
	      LEFT_MENU_VIEW=1;
	      document.getElementById("myarrow").src ="${basePath}/image/arrow_l.gif";
	   }
	   else
	   {
	      frame.cols="0,9,*";
	      LEFT_MENU_VIEW=0;
	      document.getElementById("myarrow").src="${basePath}/image/arrow_r.gif";
	   }
	}
	function setPointer(theRow, thePointerColor)
	{
	    if (typeof(theRow.style) == 'undefined' || typeof(theRow.cells) == 'undefined')
	    {
	        return false;
	    }
	
	    var row_cells_cnt=theRow.cells.length;
	    for (var c = 0; c < row_cells_cnt; c++)
	    {
	        theRow.cells[c].bgColor = thePointerColor;
	    }
	
	    return true;
	}
</script>
<style type="text/css">
div {
	position: absolute;
	top: 40%;
}
</style>
</head>
<body leftmargin="0" onLoad="leftmenu_ctrl();" background="${basePath}/image/spliter.jpg">
<div>
<TABLE cellspacing="0" width="100%" height="100%" cellpadding="0" align="center" >
   <TR>
     <TD>
       <TABLE cellspacing="0" width="100%" height="50" bgcolor="#EEEEEE" border="1" bordercolorlight="#000000" bordercolordark="#FFFFFF" cellpadding="0">
         <TR onClick="leftmenu_ctrl()" onMouseOver="setPointer(this, '#FFF0BD')" onMouseOut="setPointer(this, '#EEEEEE')">
           <TD style="cursor:pointer">
           <img id="myarrow" src="${basePath}/image/arrow_l.gif"></TD>
         </TR>
       </TABLE>
     </TD>
   </TR>
 </TABLE>
</div>
</body>
</html>