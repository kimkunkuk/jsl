<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common_header.jsp" %>
<%@ include file="../common_menu_admin.jsp" %>
<script>
	function goSearch(){
		pro.method="post";
		pro.action="Product";
		pro.submit();
	}
</script>
<style>
	.view_img{
		width:100px;
		height:100px;
	}
</style>
<div id="b_right">
			<p class="n_title">
				PRODUCT
			</p>
			<div class="record_group record_group_left">
				<p><i class="fa-solid fa-bell"></i> 총게시글<span> ${t_totalcount} </span>건</p>
			</div>
			<form name="pro">	
			<p class="select_box select_box_right">
			<input type="hidden" name="t_nowPage">
				<select name="t_level" >
					<option value="">레벨선택</option>
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="C">C</option>
				</select>
				<select name="t_select" class="sel_box">
					<option value="title" <c:if test="${t_select eq 'title'}"> selected </c:if> >제품명</option>
					<option value="no" <c:if test="${t_select eq 'no'}"> selected </c:if> >제품번호</option>
				</select>
				<input type="text" name="t_search" value="${t_search}" class="sel_text">

				<button type="button" onclick="goSearch()" class="sel_button"><i class="fa fa-search"></i> SEARCH</button>
			</p>
			</form>				
			
			<table class="boardList">
				<colgroup>
					<col width="5%">
					<col width="10%">
					<col width="30%">
					<col width="10%">
					<col width="35%">
					<col width="5%">
					<col width="5%">
				</colgroup>
				<thead>
					<tr>
						<th>No</th>
						<th>제품번호</th>
						<th>제품명</th>
						<th>가격</th>
						<th>사진</th>
						<th>레벨</th>
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
						<td class="t_left"><a href="javascript:goView('${arr.getNo()}')">${arr.getNo()}</a></td>
						<td>${arr.getTitle()}</td>
						<td>${arr.getPrice()}</td>
						<td>
							<img src="attach/product/${arr.getAttach()}"/ class="view_img">
						</td>
						<td>${arr.getP_level()}</td>
						<td>${arr.getHit()}</td>
					</tr>
				</c:forEach>	
				</tbody>
			</table>
			
			<div class="paging">
				${t_paging}
				<a href="javascript:goProduct('writeForm')" class="write">글쓰기</a>
			</div>
		</div>	
	</div>

<%@ include file="../common_footer.jsp" %>