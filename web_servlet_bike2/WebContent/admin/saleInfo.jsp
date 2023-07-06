<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>    
<%@ include file="../common_menu_admin.jsp" %>
<script>
	var level ='<c:out value="${sessionLevel}"/>'
	if(level != 'admin'){
		alert("관리자 화면입니다.");
		location.href="Index";
	}
	function goSearch(){
		adm.method="post";
		adm.action="SaleList";
		adm.submit();
	}
	function goPage(pageNumber){
		adm.t_nowPage.value = pageNumber;
		adm.method="post";
		adm.action="SaleList";
		adm.submit();
	}
	function goView(no){
		admin.t_gubun.value = "view";
		admin.t_no.value = no;
		admin.method="post";
		admin.action="SaleList";
		admin.submit();
	}
	
	$(".allclick").click(function(e){
		e.preventDefault();
		$("#disableDiv").css("display","block");
		$("#b_menu_all").slideDown(500);
		$("#b_menu_all").css("z-index","999");
	});
</script>
<form name="admin">
	<input type="hidden" name="t_no">
	<input type="hidden" name="t_gubun">
</form>	
		<div id="b_right">
			<p class="n_title">
				판매현황
			</p>
			<form name="adm">			
			<p class="select_box select_box_right">
			<input type="hidden" name="t_nowPage">
				<select name="t_state" class="sel_box">
					<option value="">진행상황</option>
					<option value="입금확인중"  <c:if test="${t_display_count eq '입금확인중' }">selected</c:if> >입금확인</option>
					<option value="배송준비중" <c:if test="${t_display_count eq '배송준비중' }">selected</c:if> >배송준비</option>
					<option value="배송중" <c:if test="${t_display_count eq '배송중' }">selected</c:if> >배송중</option>
					<option value="배송완료" <c:if test="${t_display_count eq '배송완료' }">selected</c:if> >배송완료</option>
				</select>
				<select name="t_select" class="sel_box">
					<option value="s_no" <c:if test="${t_select eq 's_no'}">selected</c:if> >판매번호</option>
					<option value="id" <c:if test="${t_select eq 'id'}">selected</c:if> >구매자</option>
				</select>
				<input type="text" name="t_search" value="${t_search}" class="sel_text">
			
				<button type="button" onClick="goSearch()" class="sel_button"><i class="fa fa-search"></i> SEARCH</button>
			</p>			
			</form>
			<table class="boardList">
				<colgroup>
					<col width="33%">
					<col width="33%">
					<col width="33%">
				</colgroup>
				<thead>
					<tr>
						<th>월</th>
						<th>판매건수</th>
						<th>총매출</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${t_arr}" var="arr">
					<tr>
						<td><a href="#">${arr.getReg_date()}</a></td>
						<td>${arr.getCount()}</td>
						<td><fmt:formatNumber value="${arr.getPrice()}" pattern="#,###"/>원</td>
					</tr>
				</c:forEach>		
				</tbody>
			</table>
			
			<div class="paging">
				${t_paging}
			</div>
		</div>	
	</div>
	<%@include file="../common_footer.jsp" %>