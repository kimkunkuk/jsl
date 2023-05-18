<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String sessionName = (String)session.getAttribute("sessionName");
	if(sessionName == null) sessionName = "";
	
	String sessionId = (String)session.getAttribute("sessionId");
	if(sessionId == null) sessionId = "";
%>