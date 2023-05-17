<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common_session.jsp" %>
<%@ page import = "dao.*,dto.*,java.util.ArrayList" %>    
<%@ include file = "../common_header.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
	PdsDao dao = new PdsDao();
	
	String select = request.getParameter("t_select");
	String search = request.getParameter("t_search");
	if(select == null){
		select = "p.title";
		search = "";
	}
	ArrayList<PdsDto> dtos = dao.getPdsList(select, search);
%>
<script>
	function goSearch(){
		myform.method="post";
		myform.action="pds.jsp";
		myform.submit();
	}
	function goView(no){
		//alert("d");
		view.t_no.value = no;
		view.method="post";
		view.action="pds_view.jsp";
		view.submit();
	}
</script>	
<form name="view">
	<input type="hidden" name="t_no">
</form>
<%@ include file = "../common_subcontent.jsp" %>

	<div class="container">
	  <div class="search_wrap">
		<div class="record_group">
			<p>총게시글<span><%=dtos.size() %></span>건</p>
		</div>
		<div class="search_group">
			<form name="myform" >
				<select name="t_select" class="select">
					<option value="p.title" <%if(select.equals("p.title")) out.print("selected"); %>>제목</option>
					<option value="p.content" <%if(select.equals("p.content")) out.print("selected"); %>>내용</option>
				</select>
				<input type="text" name="t_search" class="search_word" value="<%=search%>">
				<button class="btn_search" onclick="goSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
			</form>
		</div>
	  </div> <!-- search end -->
	  <div class="bord_list">
		<table class="bord_table" summary="이표는 번호,제목,글쓴이,날자,조회수로 구성되어 있습니다">
			<caption class="sr-only">공지사항 리스트</caption>
			<colgroup>
				<col width="10%">
				<col width="*">
				<col width="10%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>첨부파일</th>
					<th>글쓴이</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
			<%for(PdsDto dto : dtos){ %>
				<tr>
					<td><%=dto.getNo() %></td>
					<td class="title"><a href="javascript:goView('<%=dto.getNo() %>')"><%=dto.getTitle() %></a></td>
					<td><%if(dto.getAttach() != null) out.print("<img src='../images/file.png'>");%></td>
					<td><%=dto.getReg_name() %></td>
					<td><%=dto.getReg_date() %></td>
					<td><%=dto.getHit() %></td>
				</tr>
				<%} %>
			</tbody>
		</table>
		<div class="paging">
			<a href=""><i class="fa  fa-angle-double-left"></i></a>
			<a href=""><i class="fa fa-angle-left"></i></a>
			<a href="" class="active">1</a>
			<a href="">2</a>
			<a href="">3</a>
			<a href="">4</a>
			<a href="">5</a>
			<a href=""><i class="fa fa-angle-right"></i></a>
			<a href=""><i class="fa  fa-angle-double-right"></i></a>
			<%if(sessionLevel.equals("top")){ %>
				<a href="pds_write.jsp" class="btn_write">글쓰기</a>
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









