<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%
	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String now = sdf.format(new Date());
	response.reset();
	response.setHeader("Content-Disposition", "attachment;filename="
			+ now + "_backup.sql");
	response.setContentType("application/x-download; charset=utf-8");
%>
${outSqlStr }