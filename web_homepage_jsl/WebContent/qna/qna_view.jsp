<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common_session.jsp" %>
<%@ page import = "dao.*,dto.*" %>
<%
	request.setCharacterEncoding("utf-8");
	QnaDao dao = new QnaDao();
	String no = request.getParameter("t_no");
	
	dao.getHit(no);
	QnaDto dto = dao.getQnaView(no);
	QnaDto preDto  = dao.getPreView(no);
	QnaDto nextDto = dao.getNextView(no);
	
	
	
%>
<%@ include file = "../common_header.jsp" %>
<script>
	function goView(no){
		view.t_no.value = no;
		view.method="post";
		view.action="qna_view.jsp";
		view.submit();
	}
	function goAnswer(){
		//alert("d");
		if(checkValue(view.t_answer,"내용입력!")) return;
		view.method="post";
		view.action="db_qna_answer.jsp";
		view.submit();
	}
	function goUpdate(){
		view.method="post";
		view.action="qna_update.jsp";
		view.submit();
	}
	function goAnswerDel(){
		view.method="post";
		view.action="db_asnswer_delete.jsp";
		view.submit();
	}
	function goDelete(){
		view.method="post";
		view.action="db_qna_delete.jsp";
		view.submit();
	}
</script>
<form name="view">
	<input type="hidden" name="t_no" value="<%=no%>">

	<!-- sub contents -->
	<div class="sub_title">
		<h2>질문답변</h2>
		<div class="container">
		  <div class="location">
			<ul>
				<li class="btn_home">
					<a href="index.html"><i class="fa fa-home btn_plus"></i></a>
				</li>
				<li class="dropdown">
					<a href="">커뮤니티<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="gratings.html">공지사항</a>
						<a href="allclass.html">학과및모집안내</a>
						<a href="portfolio.html">포트폴리오</a>
						<a href="online.html">온라인접수</a>
						<a href="notice.html">커뮤니티</a>
					</div>
				</li>
				<li class="dropdown">
					<a href="">질문답변<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="gratings.html">공지사항</a>
						<a href="gratings.html">질문답변</a>
						<a href="gratings.html">취업실적</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>
	
	<div class="container">
		<div class="board_view">
			<h2><%=dto.getTitle() %></h2>
			<p class="info"><span class="user"><%=dto.getReg_name() %></span> | <%=dto.getReg_date() %> | 
			<i class="fa fa-eye"></i> <%=dto.getHit() %></p>
			<div class="board_body">
			<p style="font-weight:bold">질문</p> 
				<textarea readonly style="outline-color: #fff;" ><%=dto.getContent() %></textarea>
			
			<p style="font-weight:bold">답변</p> 
			<%if(dto.getAnswer() == null && !sessionLevel.equals("top")) out.print("관리자의 답변을 기다려주세요.");%> 
			<%if(dto.getAnswer() != null || sessionLevel.equals("top")) {%>	
				
				<%if(dto.getQna_date() != null) { %><p style="font-weight:bold">답변 작성일 <%=dto.getQna_date() %></p><%} %>
				<textarea <%if(!sessionLevel.equals("top")) out.print("readonly"); %> name="t_answer" style="border: 2px solid black"><%if(dto.getAnswer() != null) out.print(dto.getAnswer());%></textarea>
			<%} %>	
			
			<%if(sessionLevel.equals("top")) { %>
				<br>	
				<input type="button" value="답변저장" class="btn_ok" onClick="goAnswer()" style='cursor:pointer'>
				<input type="button" value="답변삭제" class="btn_ok" onClick="goAnswerDel()" style='cursor:pointer'>
			<%} %>	
			
	</form>
<script type="text/javascript">
//<![CDATA[
$(document).ready(function(){
	$(".answerButt").toggle(function(){
		$(".answerArea").slideDown(500);	
	}, function(){
		$(".answerArea").slideUp(500);
	})
});
//]]>
</script>
<style>
	.answerArea{display:none} 
	.btn_3wrap span {
		padding: 12px 18px;
		display: inline-block;
		border: 1px solid #878787;
	}
	.answerArea .textArea_H120{
		padding:5px;
		width:700px;
		height:120px;
	}	
	.answerArea .saveButt{
		float:right;
		padding: 12px 18px;
		display: inline-block;
		border: 1px solid #878787;
	}	
</style>
				
			<!-- 답변 -->
			<form name="answer">
				<div class="answerArea">
					<input type="hidden" name="t_no" value="">
					<textarea name="t_answer" class="textArea_H120"></textarea>
					<a href="javascript:goAnswerSave()" class="saveButt">Answer Save</a>
				</div>
			</form>					
			</div>
			
			<div class="prev_next">
				<%if(preDto.getNo() != null) {
					String preTitle = preDto.getTitle();
					if(preTitle.length() > 15) {
						preTitle = preTitle.substring(0, 15)+"...";
					}
				%>
				<a href="javascript:goView('<%=preDto.getNo() %>')" class="btn_prev"><i class="fa fa-angle-left"></i>
				<span class="prev_wrap">
					<strong><%=preDto.getNo() %></strong><span><%=preTitle %></span>
				</span>
				</a>
				<%} %>
				
				<div class="btn_3wrap">
					<a href="qna_list.jsp">목록</a>
				<%if(sessionId.equals(dto.getReg_id()) && dto.getAnswer() == null || sessionLevel.equals("top")) { %> 
					<a href="javascript:goUpdate()">수정</a> 
					<a href="javascript:goDelete()">삭제</a> 
				<%} %>	
					<!-- <a href="qna_answer.jsp">답변</a>  -->
				</div>
				
				<%if(nextDto.getNo() != null) { 
					String nextTitle = nextDto.getTitle();
					if(nextTitle.length() > 15) {
						nextTitle = nextTitle.substring(0, 15)+"...";
					}
				%>
				<a href="javascript:goView('<%=nextDto.getNo() %>')" class="btn_next">
				<span class="next_wrap">
					<strong><%=nextDto.getNo() %></strong><span><%=nextTitle %></span>
				</span>
				<i class="fa fa-angle-right"></i></a>
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









