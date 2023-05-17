<%@ page pageEncoding="UTF-8"%>
<%
	String sessionLevel = (String)session.getAttribute("sessionLevel");
	if(sessionLevel == null) sessionLevel = "";
	
	String sessionName = (String)session.getAttribute("sessionName");
	if(sessionName == null) sessionName = "";
	
	String sessionId = (String)session.getAttribute("sessionId");
	if(sessionId == null) sessionId = "";
%>