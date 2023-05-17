<%@page import="common.CommonUtil"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*" %>    
<%@ include file="../common_session.jsp" %>
<%@ include file="../common_header.jsp" %>	

<%
	FaqDao dao = new FaqDao();
	String select = request.getParameter("t_select");
	String search = request.getParameter("t_search");
	if(select == null){
		select = "f.title";
		search = "";
	}
	
	/* paging 설정 start*/
	int totalCount = dao.getTotalCount(select,search);
	int list_setup_count = 10;  //한페이지당 출력 행수 
	int pageNumber_count = 3;  //한페이지당 출력 페이지 갯수
	
	String nowPage = request.getParameter("t_nowPage");
	int current_page = 0; // 현재페이지 번호
	int total_page = 0;    // 전체 페이지 수
	
	if(nowPage == null || nowPage.equals("")) current_page = 1; 
	else current_page = Integer.parseInt(nowPage);
	
	total_page = totalCount / list_setup_count;  // 몫 : 2
	int rest = 	totalCount % list_setup_count;   // 나머지:1
	if(rest !=0) total_page = total_page + 1;     // 3
	
	int start = (current_page -1) * list_setup_count + 1;
	int end   = current_page * list_setup_count;
	/* paging 설정 end*/	
	
	//ArrayList<FaqDto> dtos = dao.getFaqList(select, search);
	ArrayList<FaqDto> dtos = dao.getFaqListPage(select, search, start, end);
	
%>
<script>
	function goUpdate(no){
		noo.t_no.value = no;
		noo.method="post";
		noo.action="faq_update.jsp";
		noo.submit();
	}
	function goDelete(no){
		var tf = confirm("정말삭제하실건가요?");
		if(tf){
			noo.t_no.value = no;
			noo.method="post";
			noo.action="db_faq_delete.jsp";
			noo.submit();
		}
	}
	function goPage(pageNumber){
		pageForm.t_nowPage.value = pageNumber;
		pageForm.method="post";
		pageForm.action="faq_list.jsp";
		pageForm.submit();
	}
</script>
<form name="noo">
	<input type="hidden" name="t_no">
</form>
<form name="pageForm">
	<input type="hidden" name="t_nowPage">
</form>
	<!-- sub contents -->
	<%@ include file = "../common_subcontent.jsp" %>
	<div class="container">
	  <div class="search_wrap">
		<div class="record_group">
			<p>총게시글<span><%=totalCount %></span>건</p>
		</div>
		<div class="search_group">
			<form name="faq" >
				<select name="t_select" class="select">
					<option value="f.title" <%if(select.equals("f.title")) out.print("selected"); %>>제목</option>
					<option value="f.content" <%if(select.equals("f.content")) out.print("selected"); %>>내용</option>
				</select>
				<input type="text" name="t_search" class="search_word" value=<%=search %>>
				<button class="btn_search"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
			</form>
		</div>
	  </div> <!-- search end -->
	  <div class="bord_list">
	  	<table class="bord_table">
	  		<colgroup>
				<col width="5%">
				<col width="35%">
				<col width="10%">
				<col width="20%">
				<col width="30%">
			</colgroup>
		  	<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th></th>
			</tr>
		</table>
		<%for(int k=0; k<dtos.size(); k++) {%>
		<div class="faq-group">
					
				<div class="accordion">
					<table class="table">
						<colgroup>
							<col width="10%">
							<col width="*">
							<col width="15%">
							<col width="20%">
						</colgroup>
						
						<tr>
							<td><%=totalCount-((current_page-1)*list_setup_count+k) %></td>
							<td><%=dtos.get(k).getTitle() %></td>
							<td><%=dtos.get(k).getReg_name() %></td>
							<td><%=dtos.get(k).getReg_date() %></td>
						</tr>
					</table>
				</div>
				
				<div class="panel">
					<div style="width:1050px; word-break:break-all; word-wrap:break-word;"><%=dtos.get(k).getContent() %></div>
					<%if(sessionId.equals("manager")) {%>
						<div class="faq-button">
							<input type="button" value ="수정" onClick="goUpdate('<%=dtos.get(k).getNo() %>')" style='cursor:pointer'>
							<input type="button" value ="삭제" onClick="goDelete('<%=dtos.get(k).getNo() %>')" style='cursor:pointer'>
						</div>
					<%} %>
				</div>
							
				
		</div>
		<%} %>
		
		<script>
			$(function() {
/*			
				$( '.accordion' ).click( function() {
				//$(".accordion").on("click",function() {	
					//$(".panel").slideUp();
					//$(this).next().slideToggle();
					//$(this).next().slideDown();
					$(".panel").not($(this).next().slideToggle()).slideUp();
					//$(this).next().slideDown();
					

				} );
		*/			
			
				$(".accordion").on("click",function() {
					$(".panel").not($(this).next().slideToggle()).slideUp();
					$(".accordion").not($(this)).removeClass("active");
					$(this).toggleClass("active");
				});
		
			});
		</script>

		<div class="paging">
			<%
				String paging = CommonUtil.pageListPost(current_page, total_page, pageNumber_count);
				out.print(paging);
			%>
			
			<%if(sessionLevel.equals("top")) {%>
			<a href="faq_write.jsp" class="btn_write">글쓰기</a>
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









