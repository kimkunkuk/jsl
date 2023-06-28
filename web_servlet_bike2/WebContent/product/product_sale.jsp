<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>


<c:if test="${empty sessionId}">
	<script>
		alert("로그인 하세요.");
		location.href="Member?t_gubun=memberLogin";
	</script>
</c:if>
<script>
	function goSale(){
		if(checkValue(pro.t_address,"주소입력!")) return;
		if(checkValue(pro.t_pay,"결제방법 선택!")) return;
		
		pro.method="post";
		pro.action="Product?t_gubun=saleSave";
		pro.submit();
	}
</script>
<style>
	.view_img{
		width:350px;
		height:350px;
		border: 1px solid black;
	}
</style>
<p class="n_title">구매</p>
<div id="b_center">
			
			<form name="pro">
			<input type="hidden" name="t_p_no" value="${t_dto.getNo()}">
			<input type="hidden" name="t_price" value="${t_dto.getPrice()}">
			<table class="boardForm">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="10%">
					<col width="40%">
				</colgroup>
				<tbody>
					<tr>
						<th>제품명</th>
						<td colspan="3">${t_dto.getTitle()}</td>
					</tr>	
					<tr>
						<th>사진</th>
						<td colspan="3">
						<img src="attach/product/${t_dto.getAttach()}"/ class="view_img">;
						</td>
					</tr>
					<style>
						.viewContent{
							white-space:pre-wrap;
						}
					</style>
					<tr>
						<th>Price</th>
						<td  colspan="3"><fmt:formatNumber value="${t_dto.getPrice()}" pattern="#,###"/></td>
					</tr>
					<tr>
						<th>주소</th>
						<td colspan="3"><input type="text" name="t_address" class="input600"></td>
					</tr>
					<tr>
					  <th>결제방법</th>
					  <td colspan="3">
						  <input type="radio" value="카드" name="t_pay" class="middleCheck" /> 카드&nbsp;&nbsp;        
						  <input type="radio" value="계좌이체" name="t_pay" class="middleCheck" /> 계좌이체     
					  </td>
					</tr>
<style>
	#preview-image{
		border:1px solid gray;
		width:500px;
		height:300px;
		margin:0 0 10px 50px;
		display:none;
	}
</style>	
			</table>
			</form>
			<div class="buttonGroup">
				<a href="javascript:goSale()" class="butt">주문하기</a>
				<a href="Product" class="butt">List</a>
			</div>	
		</div>	
	</div>
<%@ include file="../common_footer.jsp" %>