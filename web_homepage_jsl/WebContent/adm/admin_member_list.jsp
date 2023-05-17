<%@page import="common.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.*,dto.*,java.util.ArrayList" %>
<%@ include file = "../common_session.jsp" %>
<%
	if(!sessionLevel.equals("top")){
%>
<script>
	alert("관리자 화면입니다.");
	location.href="../index.jsp";
</script>
<%} %>
<%
	AdminDao dao = new AdminDao();
	request.setCharacterEncoding("UTF-8");
	String select = request.getParameter("t_select");
	String search = request.getParameter("t_search");
	
	if(select == null) {
		select = "id"; 
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
	
	//ArrayList<AdminDto> dtos = dao.getMemberList(select, search);
	ArrayList<AdminDto> dtos = dao.getMemberListPage(select, search, start, end);
	
%>

<%@ include file = "../common_header.jsp" %>
<script>
	function goSearch(){
		notice.method="post";
		notice.action="admin_member_list.jsp";
		notice.submit();
	}
	function goView(id){
		//alert(no);
		view.t_id.value = id;
		view.method="post";
		view.action="admin_memberInfo.jsp";
		view.submit();
	}
	function goPage(pageNumber){
		pageForm.t_nowPage.value = pageNumber;
		pageForm.method="post";
		pageForm.action="admin_member_list.jsp";
		pageForm.submit();
	}
</script>
<form name="view">
	<input type="hidden" name="t_id" >
</form>
<form name="pageForm">
	<input type="hidden" name="t_nowPage">
</form>
	<%@ include file = "../common_subcontent.jsp" %>

	<div class="container">
	  <div class="search_wrap">
		<div class="record_group">
			<p>총회원수<span>&nbsp;<%=totalCount %><?=$count?></span>명</p>
		</div>
		<div class="search_group">
			<form name="notice">
				<select name="t_select" class="select">
					<option value="id" <%if(select.equals("id")) out.print("selected"); %>>ID</option>
					<option value="name" <%if(select.equals("name")) out.print("selected"); %>>성명</option>
				</select>
				<input type="text" name="t_search" class="search_word" value=<%=search %>>
				<button class="btn_search" onclick="goSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
			</form>
		</div>
	  </div> <!-- search end -->
	  <div class="bord_list">
		<table class="bord_table" summary="이표는 번호,제목,글쓴이,날자,조회수로 구성되어 있습니다">
			<caption class="sr-only">공지사항 리스트</caption>
			<colgroup>
				<col width="15%">
				<col width="15%">
				<col width="20%">
				<col width="20%">
				<col width="20%">
				<col width="20%">
			</colgroup>
			<thead>
				<tr>
					<th>아이디</th>
					<th>성명</th>
					<th>연락처</th>
					<th>이메일</th>
					<th>회원가입일</th>
					<th>계정유무</th>
				</tr>
			</thead>
			<tbody>
			<%for(int k=0; k<dtos.size(); k++){ %>
				<tr>
					<td><a href="javascript:goView('<%=dtos.get(k).getId() %>')"><%=dtos.get(k).getId() %></a></td>
					<td><a href="javascript:goView('<%=dtos.get(k).getId() %>')"><%=dtos.get(k).getName() %></a></td>
					<td><%=dtos.get(k).getMobile().substring(0, 3) %>-
						<%=dtos.get(k).getMobile().substring(3, 7) %>-
						<%=dtos.get(k).getMobile().substring(7) %>
					</td>
					<td><%=dtos.get(k).getEmail() %></td>
					<td><%=dtos.get(k).getReg_date() %></td>
					<td><%=dtos.get(k).getAcount() %></td>
				</tr>
			<%} %>
			</tbody>
		</table>
		<div class="paging">
			<%
				String paging = CommonUtil.pageListPost(current_page, total_page, pageNumber_count);
				out.print(paging);
			%>
			
			
			
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









