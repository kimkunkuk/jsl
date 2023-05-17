<%@page import="common.CommonUtil"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.*,dto.*" %>
<%
	PdsDao dao = new PdsDao();
	request.setCharacterEncoding("utf-8");
	
	String no = request.getParameter("t_no");
	String attach = request.getParameter("t_attach");
	
	int result = dao.getPdsDelete(no);
	String msg = "삭제되었습니다.";
	if(result == 1){
		if(!attach.equals("")){
			File file = new File(CommonUtil.getFile_dir_pds(),attach);
			boolean tf = file.delete();
			if(!tf) System.out.print("공지사학 게시글 삭제 첨부파일 삭제 오류");
		}
	}
	if(result != 1) msg = "삭제실패!";
	
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