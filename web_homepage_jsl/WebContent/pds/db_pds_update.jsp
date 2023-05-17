<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*,common.*"%>
<%@ include file = "../common_session.jsp" %>
<%
	PdsDao dao = new PdsDao();
	request.setCharacterEncoding("utf-8");
	
	String pdsDir = CommonUtil.getFile_dir_pds();
	int maxSize = 1024 * 1024 * 10;
	MultipartRequest mpr = new MultipartRequest(request, pdsDir, maxSize, "utf-8", new DefaultFileRenamePolicy());
	String no = mpr.getParameter("t_no");
	String title = mpr.getParameter("t_title");
	title = title.replaceAll("'", "''");
	String content = mpr.getParameter("t_content");
	content = content.replaceAll("'", "''");
	String attach = mpr.getFilesystemName("t_attach");
	if(attach == null) attach = "";
	
	String deleteAttach = mpr.getParameter("t_deleteFile");
	String oriAttach = mpr.getParameter("t_ori_attach");
	if(oriAttach == null) oriAttach = "";
	String saveAttachName = "";
	if(deleteAttach != null){
		File file = new File(pdsDir, deleteAttach);
		boolean tf = file.delete();
		if(!tf){
			System.out.print("자료실 첨부파일삭제:" + tf);
		}
	}else {
		saveAttachName = oriAttach;
	}
	
	if(!attach.equals("")){
		if(!oriAttach.equals("")){
			File file = new File(pdsDir, oriAttach);
			boolean tf = file.delete();
		}
		saveAttachName = attach;
		}
	
	PdsDto dto = new PdsDto(no, title, content, saveAttachName);
	int result = dao.getPdsUpdate(dto);
	String msg = "수정되었습니다.";
	if(result != 1) msg = "수정실패!";
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	alert("<%=msg%>");
	location.href="pds.jsp";
</script>
</head>
<body>

</body>
</html>