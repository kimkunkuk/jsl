<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*,common.*" %>
<%@ include file = "../common_session.jsp" %>    
<%
	request.setCharacterEncoding("utf-8");
	PdsDao dao = new PdsDao();
	
	String pdsDir = CommonUtil.getFile_dir_pds();
	int maxSize = 1024 * 1024 * 10;
	MultipartRequest mpr = new MultipartRequest(request, pdsDir, maxSize, "utf-8", new DefaultFileRenamePolicy());
	
	String no = dao.getMaxNo();
	String title = mpr.getParameter("t_title");
	title = title.replaceAll("\'", "\''");
	String content = mpr.getParameter("t_content");
	content = content.replaceAll("\'", "\''");
	String attach =	mpr.getFilesystemName("t_attach");
	if(attach == null) attach = "";
	String reg_id = sessionId;
	String reg_date = CommonUtil.getTodayTime();
	
	PdsDto dto = new PdsDto(no, title, content, attach, reg_id, reg_date);
	int result = dao.PdsSave(dto);
	
	String msg="등록실패!!!";
	if(result == 1) msg="등록성공!!!";
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	alert("<%=msg%>");
	<%if(result == 1){%>
		location.href="pds.jsp";
	<%}else%> window.history.back();
</script>
</head>
<body>

</body>
</html>