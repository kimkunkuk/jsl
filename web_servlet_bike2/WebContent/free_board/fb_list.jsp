<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<%@ include file="../common_menu.jsp" %>		
<script>
	function goSearch(){
		notice.method="post";
		notice.action="FreeBoard";
		notice.submit();
	}
	function goPage(pageNumber){
		notice.t_nowPage.value = pageNumber;
		notice.method="post";
		notice.action="Notice";
		notice.submit();
	}
	function goWriteFrom(){
		noti.t_gubun.value="writeForm";
		noti.method="post";
		noti.action="FreeBoard";
		noti.submit();
	}
	function goView(no){
		noti.t_gubun.value="view";
		noti.t_no.value= no;
		noti.method="post";
		noti.action="FreeBoard";
		noti.submit();
	}
</script>
<form name="noti">
	<input type="hidden" name="t_gubun">
	<input type="hidden" name="t_no">
</form>
		<div id="b_right">
			<p class="n_title">
				FREE_BOARD
			</p>
			<form name="notice">
			<div class="record_group record_group_left">
				<p><i class="fa-solid fa-bell"></i> 총게시글<span>${t_totalCount}</span>건</p>
			</div>		
			<p class="select_box select_box_right">
			<input type="hidden" name="t_nowPage">
				<select name="t_select" class="sel_box">
					<option value="f.title" <c:if test="${t_select eq 'f.title'}"> selected </c:if> >Title</option>
					<option value="m.name" <c:if test="${t_select eq 'm.name'}"> selected </c:if> >Name</option>
				</select>
				<input type="text" name="t_search" value="${t_search }" class="sel_text">

				<button type="button" onclick="goSearch()" class="sel_button"><i class="fa fa-search"></i> SEARCH</button>
			</p>			
			</form>
			<table class="boardList">
				<colgroup>
					<col width="5%">
					<col width="60%">
					<col width="10%">
					<col width="19%">
					<col width="6%">
				</colgroup>
				<thead>
					<tr>
						<th>No</th>
						<th>Title</th>
						<th>Reg Name</th>
						<th>Reg Date</th>
						<th>Hit</th>
					</tr>
				</thead>
				<tbody>
				
				<c:set var="number" value="${t_order}"></c:set>
				<c:forEach items="${t_arr}" var="arr">
					<tr>
						<td>
							${number}
							<c:set var="number" value="${number-1}"></c:set>
						</td>
						<td class="t_left"><a href="javascript:goView('${arr.getNo()}')">${arr.getTitle()}</a></td>
						<td>${arr.getName() }</td>
						<td>${arr.getReg_date() }</td>
						<td>${arr.getHit() }</td>
					</tr>
				</c:forEach>		
				</tbody>
			</table>
			
			<div class="paging">
				${t_paging}
			
			<!-- 
				<a href=""><i class="fa fa-angle-double-left"></i></a>
				<a href=""><i class="fa fa-angle-left"></i></a>
				<a href="" class="active">1</a>
				<a href="">2</a>
				<a href="">3</a>
				<a href="">4</a>
				<a href="">5</a>
				<a href=""><i class="fa fa-angle-right"></i></a>
				<a href=""><i class="fa fa-angle-double-right"></i></a>
				
				세션은 한번 셋어트리뷰트 해놓으면 jsp 에서 모든곳에서 샤용가능
			-->
			
				<a href="javascript:goWriteFrom()" class="write">글쓰기</a>
			
			</div>
		</div>	
	</div>
<%@ include file="../common_footer.jsp" %>






