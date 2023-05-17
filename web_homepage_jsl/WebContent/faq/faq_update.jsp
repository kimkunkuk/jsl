<%@page import="dto.FaqDto,dao.*,common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_session.jsp" %>    
<%@ include file="../common_header.jsp" %>
<%
	if(!sessionLevel.equals("top")){	
%>
<script>
	alert("관리자화면 입니다.");
	location.href="/web_homepage_jsl/index.jsp";
</script>
<%} %>
<%
	FaqDao dao = new FaqDao();
	String no = request.getParameter("t_no");
	FaqDto dto = dao.getView(no);
	String update_date = CommonUtil.getTodayTime();
%>
<script>
	function goSave(){
		faq.method="post";
		faq.action="db_faq_update.jsp";
		faq.submit();
	}
</script>	
	<!-- sub contents -->
	<div class="sub_title">
		<h2>자주하는 질문(FAQ)</h2>
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
					<a href="">자주하는질문<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="notice.html">공지사항</a>
						<a href="qa.html">질문과답변</a>
						<a href="faq.html">자주하는질문</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
	  <div class="write_wrap">
	  <h2 class="sr-only">글쓰기</h2>
	  <form name="faq" method="post" action="faq_insert.html" onsubmit="return check()">
	  <!-- action을 처리하기전에 check()사용자 함수를 실행하고 되돌아 와라-->
	  <input type="hidden" name="t_no" value="<%=no%>">
			<table class="bord_table">
				<caption class="sr-only">자주하는 질문 입력 표</caption>
				<colgroup>
					<col width="20%">
					<col width="*">
					<col width="20%">
					<col width="*">
				</colgroup>
				<tbody>
					
					<tr class="first">
						<th>질문</th>
						<td colspan="3"><input type="text" name="t_title" value="<%=dto.getTitle() %>"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3"><textarea name="t_content"><%=dto.getContent() %></textarea></td>
					</tr>
					<tr>
						<th>등록일</th>
						<td><%=dto.getReg_date() %></td>
						<th>수정일</th>
						<td><%=update_date %></td>
					</tr>
				</tbody>
			</table>
			<div class="btn_wrap">
				<input type="button" value="수정" class="btn_ok" style='cursor:pointer' onClick="goSave()">&nbsp;&nbsp;
				<input type="button" value="목록" class="btn_list" style='cursor:pointer' onClick="location.href='faq_list.jsp';">
			</div>
		</form>
	  </div>
	  
	</div>
	<!-- end contents -->
	<script>
		function check() {
			
			if(faq.title.value=="") {
				alert("제목을 입력");
				faq.title.focus();
				return false;
			}
			if(faq.contents.value=="") {
				alert("내용을 입력");
				faq.contents.focus();
				return false;
			}
			return true;
		}
	</script>
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