<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common_header.jsp" %>

<c:choose>
<c:when test="${sessionLevel eq 'admin' }"><%@ include file="../common_menu_admin.jsp" %></c:when>
<c:otherwise><%@ include file="../common_menu_product.jsp" %></c:otherwise>
</c:choose>
<script>
	function goSearch(){
		pro.method="post";
		pro.action="Product";
		pro.submit();
	}
	function goPage(pageNumber){
		pro.t_nowPage.value = pageNumber;
		pro.method="post";
		pro.action="Product";
		pro.submit();
	}
	function goView(no){
		view.t_no.value = no;
		view.t_gubun.value = "view";
		view.method="post";
		view.action="Product";
		view.submit();
	}
</script>
<style>
	.view_img{
		width:100px;
		height:100px;
	}
</style>
<form name="view">
	<input type="hidden" name="t_no">
	<input type="hidden" name="t_gubun">
</form>

<div id="b_right">
			<p class="n_title">
				PRODUCT
			</p>
			<div class="record_group record_group_left">
				<p><i class="fa-solid fa-bell"></i> 총게시글<span> ${t_totalcount} </span>건</p>
			</div>
			<form name="pro">	
			<input type="hidden" name="t_nowPage">
			<p class="select_box select_box_right">
			<c:if test="${sessionLevel eq 'admin'}">
				<select name="t_level" class="sel_box">
					<option value="">레벨선택</option>
					<option value="A" <c:if test="${t_level eq 'A'}"> selected </c:if> >A</option>
					<option value="B" <c:if test="${t_level eq 'B'}"> selected </c:if> >B</option>
					<option value="C" <c:if test="${t_level eq 'C'}"> selected </c:if> >C</option>
				</select>
			</c:if>	
				<select name="t_select" class="sel_box">
					<option value="title" <c:if test="${t_select eq 'title'}"> selected </c:if> >제품명</option>
					<option value="no" <c:if test="${t_select eq 'no'}"> selected </c:if> >제품번호</option>
				</select>
				<input type="text" name="t_search" value="${t_search}" class="sel_text">

				<button type="button" onclick="goSearch()" class="sel_button"><i class="fa fa-search"></i> SEARCH</button>
			</p>
			</form>				
			
			<table class="boardList">
			<c:choose>
			<c:when test="${sessionLevel eq 'admin'}">
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
			</c:when>
				
			<c:otherwise>
				<colgroup>
					<col width="5%">
					<col width="10%">
					<col width="30%">
					<col width="10%">
					<col width="35%">
					<col width="10%">
				</colgroup>
				<thead>
					<tr>
						<th>No</th>
						<th>제품번호</th>
						<th>제품명</th>
						<th>가격</th>
						<th>사진</th>
						<th>Hit</th>
					</tr>
				</thead>
			</c:otherwise>
			</c:choose>
				<tbody>
				<c:set var="number" value="${t_order}"></c:set>
				<c:forEach items="${t_arr}" var="arr">
					<tr>
						<td>
							${number}
							<c:set var="number" value="${number-1}"></c:set>
						</td>
						<td class="t_left"><a href="javascript:goView('${arr.getNo()}')">${arr.getNo()}</a></td>
						<td><a href="javascript:goView('${arr.getNo()}')">${arr.getTitle()}</a></td>
						<td><fmt:formatNumber value="${arr.getPrice()}" pattern="#,###"/></td>
						<td>
							<img src="attach/product/${arr.getAttach()}"/ class="view_img">
						</td>
						<c:if test="${sessionLevel eq 'admin'}">
						<td>${arr.getP_level()}</td>
						</c:if>
						<td>${arr.getHit()}</td>
					</tr>
				</c:forEach>	
				</tbody>
			</table>
			
			<div class="paging">
				${t_paging}
				<c:if test="${sessionLevel eq 'admin'}">
				<a href="javascript:goProduct('writeForm')" class="write">글쓰기</a>
				</c:if>
			</div>
		</div>	
	</div>

<%@ include file="../common_footer.jsp" %>