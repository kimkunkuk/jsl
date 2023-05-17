<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*" %>
<%@ include file="../common_session.jsp" %>
<%@ include file="../common_header.jsp" %>
<%
	NewsDao dao = new NewsDao();
	request.setCharacterEncoding("utf-8");
	String no = request.getParameter("t_no");
	
	dao.getHitCount(no);
	
	NewsDto dto = dao.getView(no);
	NewsDto predto  = dao.getPreTitle(no);
	NewsDto nextdto	= dao.getNextTitle(no);
	
%>	
<script>
	function goView(no){
		news.t_no.value = no;
		news.method="post";
		news.action="news_view.jsp";
		news.submit();
	}
	function goUpdate(){
		news.method="post";
		news.action="news_update.jsp";
		news.submit();
	}
	function goDelete(){
		var result = confirm("삭제하시겠습니까???");
		if(result){
			news.method="post";
			news.action="db_news_delete.jsp";
			news.submit();
		}
	}
</script>
<form name="news">
	<input type="hidden" name="t_no" value=<%=no %>>
</form>
	<!-- sub contents -->
	<div class="sub_title">
		<h2><a href="news.jsp">뉴스</a></h2>
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
			<br> <%if(dto.getUpdate_date() != null){ %> 글 수정날짜 <%=dto.getUpdate_date()%> <%} %></p>   
			<p class="info"><i class="fa fa-eye"></i> <%=dto.getHit() %></p>
			<div class="board_body">
				<textarea readonly style="font-family: 'Noto Sans KR', sans-serif; font-weight:300; font-size:16px;"><%=dto.getContent() %></textarea>
			</div>
			<div class="prev_next">
			<%if(predto.getNo() != null) { 
				String preTitle = predto.getTitle();
				if(preTitle.length()>15) {
					preTitle = preTitle.substring(0,15)+"...";
				}
			%>
				<a href="javascript:goView('<%=predto.getNo() %>')" class="btn_prev"><i class="fa fa-angle-left"></i>
				<span class="prev_wrap">
				
					<strong>이전글</strong><span><%=preTitle %></span>
				</span>
				</a>
			<%} %>	
			
				<div class="btn_3wrap">
					<a href="news.jsp">목록</a>
					<%if(sessionLevel.equals("top")){ %> 
					<a href="javascript:goUpdate()">수정</a> 
					<a href="javascript:goDelete()">삭제</a>
					<%} %>
				</div>
				
			<%if(nextdto.getNo() != null) { 
				String nextTitle = nextdto.getTitle();
				if(nextTitle.length() > 15){
					nextTitle = nextTitle.substring(0, 15)+"...";
				}
			%>
				<a href="javascript:goView('<%=nextdto.getNo() %>')" class="btn_next">
				<span class="next_wrap">
					<strong>다음글</strong><span><%=nextTitle %></span>
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
	

<%@ include file="../common_footer.jsp" %>

 </body>
</html>









