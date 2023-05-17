<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*" %>
<%
	String no = request.getParameter("t_no");
	NoticeDao dao = new NoticeDao();
	
	dao.setHitCount(no);
	
	NoticeDto dto = dao.getNoticeView(no);
	NoticeDto tldto = dao.getLeftTitle(no); // 이전글
	NoticeDto trdto = dao.getRightTitle(no); // 다음글
	
%>
<%@ include file = "../common_session.jsp" %>
<%@ include file = "../common_header.jsp" %>
<script>
	function goView(no){
		notice.t_no.value = no;
		notice.method="post";
		notice.action="notice_view.jsp";
		notice.submit();
	}
	function goUpdateForm(){
		//notice.t_no.value = no;
		notice.method="post";
		notice.action="notice_update.jsp";
		notice.submit();
	}
	function goDelete(no){
		var result = confirm("삭제하시겠습니까???");
		if(result){
			notice.t_no.value = no;
			notice.method="post";
			notice.action="db_notice_delete.jsp";
			notice.submit();
		}
	}
</script>
<form name="notice">
	<input type="hidden" name="t_no" value="<%=no%>">
</form>
<div class="sub_title">
		<h2><a href="/web_homepage_jsl/notice/notice.jsp">공지사항</a></h2>
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
			<h2>[공지] <%=dto.getTitle() %></h2>
			<p class="info"><span class="user"> <%=dto.getReg_name() %></span> |  <%=dto.getReg_date() %> | <i class="fa fa-eye"></i>  <%=dto.getHit() %></p>
			<div class="board_body">
				<textarea readonly style="font-family: 'Noto Sans KR', sans-serif; font-weight:300; font-size:16px;"><%=dto.getContent() %></textarea>
				
			</div>
			<div class="prev_next">
			<%
				if(tldto.getTitle() != null){ 
					String tlTitle = tldto.getTitle();
					if(tlTitle.length()>15){
						tlTitle = tlTitle.substring(0, 15)+"...";
					}
			%>
			<!-- 
				<a href="notice_view.jsp?t_no=<%=tldto.getNo() %>" class="btn_prev"><i class="fa fa-angle-left"></i>
					<span class="prev_wrap">
						<strong>이전글</strong><span><%=tlTitle %> </span>
					</span>
				</a>
			 -->	
			 	<a href="javascript:goView('<%=tldto.getNo() %>')" class="btn_prev"><i class="fa fa-angle-left"></i>
					<span class="prev_wrap">
						<strong>이전글</strong><span><%=tlTitle %> </span>
					</span>
				</a>
			<%} %>
			<script>
				
			</script>
				<div class="btn_3wrap">
					<a href="notice.jsp">목록</a> 
					<%if(sessionLevel.equals("top")) {%>
					<a href="javascript:goUpdateForm()">수정</a> 
					<a href="javascript:goDelete('<%=no%>')">삭제</a>
					<%} %>
				</div>
			<%
				if(trdto.getTitle() != null){ 
					String trTitle = trdto.getTitle();
					if(trTitle.length()>15){
						trTitle = trTitle.substring(0, 15)+"...";
					}	
					
			%>
				<a href="javascript:goView('<%=trdto.getNo() %>')" class="btn_next">
					<span class="next_wrap">
						<strong>다음글</strong><span><%=trTitle%></span>
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