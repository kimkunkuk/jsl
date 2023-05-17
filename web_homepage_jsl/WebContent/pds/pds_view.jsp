<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*" %>
<%@ include file = "../common_session.jsp" %>
<%
	PdsDao dao = new PdsDao();
	request.setCharacterEncoding("utf-8");
	String no = request.getParameter("t_no");
	
	dao.setHitCount(no);
	
	
	PdsDto dto  = dao.getPdsView(no);
	String attachName = "";
	if(dto.getAttach() != null) attachName = dto.getAttach();
	
	PdsDto predto = dao.getPreTitle(no);
	PdsDto nextdto = dao.getNextTitle(no);
	
%>
<%@ include file = "../common_header.jsp" %>	
<%@ include file = "../common_subcontent.jsp" %>	
<script>
	function goView(no){
		pds.t_no.value = no;
		pds.method="post";
		pds.action="pds_view.jsp";
		pds.submit();
	}
	function goUpdateForm(){
		pds.method="post";
		pds.action="pds_update.jsp";
		pds.submit();
	}
	function goDelete(no){
		var tf = confirm("삭제할껴???");
		if(tf){
			pds.method="post";
			pds.action="db_pds_delete.jsp";
			pds.submit();
		}
	}
</script>
<form name="pds">
	<input type="hidden" name="t_no" value="<%=no%>">
	<input type="hidden" name="t_attach" value="<%=attachName%>">
</form>
	<div class="container">
		<div class="board_view">
			<h2><%=dto.getTitle() %></h2>
			<p class="info"><span class="user"><%=dto.getReg_name() %></span> | <%=dto.getReg_date() %> | <i class="fa fa-eye"></i> <%=dto.getHit() %></p>
			<div class="board_pds">
			첨부파일 : <a href="../common/filedown.jsp?t_fileDir=pds&t_fileName=<%=dto.getAttach()%>"><%if(dto.getAttach() != null) {%><%=dto.getAttach() %><%} else out.print("없음"); %></a>
			</div>
			<div class="board_body">
				<textarea readonly style="font-family: 'Noto Sans KR', sans-serif; font-weight:300; font-size:16px;"><%=dto.getContent() %></textarea>
			</div>
			<div class="prev_next">
				
				<%if(predto.getNo() != null) {
					String tlTitle = predto.getTitle();
					if(tlTitle.length()>15){
						tlTitle = tlTitle.substring(0, 15)+"...";
					}
				%>
				<a href="javascript:goView('<%=predto.getNo() %>')" class="btn_prev"><i class="fa fa-angle-left"></i>
				<span class="prev_wrap">
					<strong>이전글</strong><span><%=tlTitle %></span>
				</span>
				</a>
				<%} %>
				
				<div class="btn_3wrap">
					<a href="pds.jsp">목록</a>
				<%if(sessionLevel.equals("top")) {%>
					<a href="javascript:goUpdateForm()">수정</a> 
					<a href="javascript:goDelete()">삭제</a>
				<%} %>
				</div>
				
				<%if(nextdto.getNo() != null) {
					String trTitle = nextdto.getTitle();
					if(trTitle.length()>15){
						trTitle = trTitle.substring(0, 15)+"...";
					}	
				%>
				<a href="javascript:goView('<%=nextdto.getNo() %>')" class="btn_next">
				<span class="next_wrap">
					<strong>다음글</strong><span><%=trTitle %></span>
				</span>
				<i class="fa fa-angle-right"></i>
				</a>
				<%} %>
				
			</div>
		</div>
	</div>

	<!-- end contents -->
	
	<script>
		$(function() {
			$(".location  .dropdown > a").on("click",function(e) {
				e.preventDefault();
				if($(this).next().is(":visible")) {
					$(".location  .dropdown > a").next().hide();
				} else {
					$(".location  .dropdown > a").next().hide();
					$(this).next().show();
				}
			});
		});
	</script>
	

<%@ include file = "../common_footer.jsp" %>

 </body>
</html>









