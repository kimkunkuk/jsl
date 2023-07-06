<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>

 <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
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
	function showCard(){
		$('#cash').hide();
		$('#card').show();
	}
	function showCash(){
		$('#cash').show();
		$('#card').hide();
	}
	var request = new XMLhttpRequest();
	function Onazi(){
		/*
		pro.t_name.value = pro.o_name.value;
		pro.t_address.value = pro.o_address.value;
		pro.t_mobile_1.value = pro.o_mobile_1.value;
		pro.t_mobile_2.value = pro.o_mobile_2.value;
		pro.t_mobile_3.value = pro.o_mobile_3.value;
		*/
		
		$.ajax({
			type : "POST",
			url : "SaleCheck",
			async: true, 
			data: JSON.stringify(test);
			dataType : "JSON", //순간적으로 띄운 브라우저 에 글씨 받아오겠다.
			error : function(){
				alert('통신실패!!!!!');
			},
			success : function(data){
				return pro.t_name.value = data;
				
			}
		});	
		
		
	}
	function New(){
		pro.reset();
	}
</script>
<style>
	.view_img{
		width:150px;
		height:150px;
		border: 1px solid black;
	}
</style>
<p class="n_title">구매상품</p>
<div id="b_center">
			
			<form name="pro">
			<input type="hidden" name="t_p_no" value="${t_dto.getNo()}">
			<input type="hidden" name="t_price" value="${t_dto.getPrice()}">
			<input type="hidden" name="t_id" value="${t_dto.getId()}">
			<input type="hidden" name="t_gubun">
			<input type="hidden" name="t_up">
			<table class="boardForm">
				<colgroup>
					<col width="30%">
					<col width="30%">
					<col width="20%">
					<col width="20%">
				</colgroup>
				<thead>
					<tr>
						<th>사진</th>
						<th>제품명</th>
						<th>가격</th>
						<th>주문일</th>
					</tr>
				</thead>
				<tbody>
					<tr >
						<td style="text-align:center;">
						<img src="attach/product/${t_dto.getAttach()}"/ class="view_img" >
						</td>
						<td style="text-align:center;">${t_dto.getTitle()}</td>
						<td style="text-align:center;"><fmt:formatNumber value="${t_dto.getPrice()}" pattern="#,###"/></td>
						<td style="text-align:center;">오늘</td>
					</tr>	
			</table>
			<div style="border-bottom:0.8px solid black; padding: 30px; margin-bottom: 50px;"></div>
			<p class="n_title">주문자 정보</p>
			<input type="radio" onChange="Onazi()" value="onazi" name="t_input"> 회원정보와 동일&nbsp;&nbsp;
			<input type="radio" onChange="New()" value="new" name="t_input"> 새로 입력
			
			<table class="boardForm">
				<tr>
					<th>이름</th>
					<td colspan="3"><input type="text" value="" name="t_name" size="7"></td>
				</tr>
				<tr>
					<th>주소</th>
					<td colspan="3"><input type="text" value="" name="t_address" class="input600"></td>
				</tr>
				<tr>
					<th>휴대전화</th>
					<td colspan="3">
						<input type="text" name="t_mobile_1" value="" size="2"> -
						<input type="text" name="t_mobile_2" value="" size="3"> -
						<input type="text" name="t_mobile_3" value="" size="3">
					</td>
				</tr>
				<tr style="display:none;">
					<th>이름</th>
					<td colspan="3"><input type="text" value="${t_dto.getName()}" name="o_name" size="7"></td>
				</tr>
				<tr style="display:none;">
					<th>주소</th>
					<td colspan="3"><input type="text" value="${t_dto.getAddress()}" name="o_address" class="input600"></td>
				</tr>
				<tr style="display:none;">
					<th>휴대전화</th>
					<td colspan="3">
						<input type="text" name="o_mobile_1" value="${t_dto.getMobile_1()}" size="2"> -
						<input type="text" name="o_mobile_2" value="${t_dto.getMobile_2()}" size="3"> -
						<input type="text" name="o_mobile_3" value="${t_dto.getMobile_3()}" size="3">
					</td>
				</tr>
				<tr>
					<th>결제방법</th>
					<td colspan="3">
						<input type="radio" onChange="showCard()" value="카드" name="t_pay" class="middleCheck" /> 카드&nbsp;&nbsp;        
						<input type="radio" onChange="showCash()" value="계좌이체" name="t_pay" class="middleCheck" /> 계좌이체     
					</td>
				</tr>
				
				<tr id="card" style="display:none;">
					<th>카드번호</th>
					<td colspan="3">
						<input type="text" name="t_card_1" size="3"> -
						<input type="text" name="t_card_2" size="3"> -
						<input type="text" name="t_card_3" size="3"> -
						<input type="text" name="t_card_4" size="3">
					</td>
				</tr>
				<tr id="cash" style="display:none;">
					<th>계좌번호</th>
					<td colspan="3">
						대전은행: 123-1122-0155-11 JSL인재
					</td>
				</tr>
			</table>
			</form>
			<div class="buttonGroup">
			<c:if test="${!empty sessionId }">
				<a href="javascript:goSale()" class="butt">주문하기</a>
			</c:if>	
				<a href="Product" class="butt">List</a>
			</div>	
		</div>	
	</div>
<%@ include file="../common_footer.jsp" %>