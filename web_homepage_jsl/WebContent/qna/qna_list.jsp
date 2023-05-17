<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,dto.*,java.util.ArrayList,common.*" %>
<%@ include file="../common_session.jsp" %>
<%
	QnaDao dao = new QnaDao();
	request.setCharacterEncoding("utf-8");
	String select = request.getParameter("t_select");
	String search = request.getParameter("t_search");
	String t_line = request.getParameter("t_line");
	if(t_line == null){
		t_line = "10";
	}
	int line = Integer.parseInt(t_line);
	
	if(select == null){
		select = "q.title";
		search = "";
	}
	
	/* paging 설정 start*/
	int totalCount = dao.getTotalCount(select,search);
	int list_setup_count = line;  //한페이지당 출력 행수 
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
	
	//ArrayList<QnaDto> dtos = dao.getQnaList(select, search);
	ArrayList<QnaDto> dtos = dao.getQnaListPage(select, search, start, end);
	
%>	
<%@ include file="../common_header.jsp" %>
<script>
	function goView(no){
		view.t_no.value = no;
		view.method="post";
		view.action="qna_view.jsp";
		view.submit();
	}
	function goPage(pageNumber){
		pageForm.t_nowPage.value = pageNumber;
		pageForm.method="post";
		pageForm.action="qna_list.jsp";
		pageForm.submit();
	}
	function changeLine(){
		qna.method="post";
		qna.action="qna_list.jsp";
		qna.submit();
	}
</script>
<form name="view">
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
			<form name="qna" action="">
				<select name="t_line" class="select" style="width:100px;" onchange="changeLine()">
					<option value="5" <%if(line == 5) out.print("selected"); %>>5줄 보기</option>
					<option value="10" <%if(line == 10) out.print("selected"); %>>10줄 보기</option>
				</select>
				<select name="t_select" class="select">
					<option value="q.title" <%if(select.equals("q.title")) out.print("selected"); %>>제목</option>
					<option value="q.content" <%if(select.equals("q.content")) out.print("selected"); %>>내용</option>
				</select>
				<input type="text" name="t_search" value="<%=search %>" class="search_word">
				<button class="btn_search"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
			</form>
		</div>
	  </div> <!-- search end -->
	  <div class="bord_list">
		<table class="bord_table" summary="이표는 번호,제목,답변상태, 작성자, 작성일, 조회수로 구성되어 있습니다">
			<caption class="sr-only">질문과 답변 리스트</caption>
			<colgroup>
				<col width="10%">
				<col width="*">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>답변상태</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<%
					int listNumber, n =0;
					for(int k=0; k<dtos.size(); k++) {
					listNumber = totalCount - (start+n)+1;
				%>
				<tr>
					<td><%=listNumber %></td>
					<td class="title"><a href="javascript:goView('<%=dtos.get(k).getNo() %>')"><%=dtos.get(k).getTitle() %></a></td>
					<%if(dtos.get(k).getAnswer() == null) {%><td><span class="waiting">답변대기</span></td><%} %> 
					<%if(dtos.get(k).getAnswer() != null) {%><td><span class="complet">답변완료</span></td><%} %>
					<td><%=dtos.get(k).getReg_name() %></td>
					<td><%=dtos.get(k).getReg_date() %></td>
					<td><%=dtos.get(k).getHit() %></td>
				</tr>
				<%n++;} %>
				
					<!--<td><span  class="complet">답변완료</span></td>-->
					
			</tbody>
		</table>
		<div class="paging">
			<%
				String paging = CommonUtil.pageListPost(current_page, total_page, pageNumber_count);
				out.print(paging);
			%>
			
			<%if(sessionLevel.equals("member") || sessionLevel.equals("top")) {%>
				<a href="qna_write.jsp" class="btn_write">글쓰기</a>
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









