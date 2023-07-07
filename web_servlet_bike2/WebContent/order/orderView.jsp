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
	function goMemInfo(id){
		pro.t_id.value=id;
		pro.t_gubun.value="view";
		pro.method="post";
		pro.action="Admin"
		pro.submit();
	}
	function goDelete(no){
		var state = '<c:out value="${t_dto.getState()}"/>';
		if(state == "배송중" || state == "배송완료" ){
			alert("상품배송이 시작되어 주문취소가 불가능 합니다. 상담사에게 연락해주세요.");
			return;
		}
		pro.t_no.value=no;
		pro.t_gubun.value="delete";
		pro.method="post";
		pro.action="Order";
		pro.submit();
	}
	
</script>
<style>
	.view_img{
		width:150px;
		height:150px;
		border: 1px solid black;
	}
</style>
<p class="n_title">주문 정보</p>
<div id="b_center">
			
			<form name="pro">
			<input type="hidden" name="t_id">
			<input type="hidden" name="t_gubun">
			<input type="hidden" name="t_no">
			<input type="hidden" name="t_up">
			<table class="boardForm">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="10%">
					<col width="40%">
				</colgroup>
				<tbody>
					<tr>
						<th>판매번호</th>
						<td colspan="3">${t_dto.getS_no()}</td>
					</tr>
					<tr>
						<th>제품명</th>
						<td>${t_dto.getTitle()}</td>
						<th>제품번호</th>
						<td>${t_dto.getP_no()}</td>
					</tr>
					<tr>
						<th>구매자 정보</th>
						<td><a href="javascript:goMemInfo('${t_dto.getId()}')">${t_dto.getName()}/${t_dto.getId()}</a></td>
						<th>구매자 번호</th>
						<td>
						${t_dto.getMobile_1()}-
						${t_dto.getMobile_2()}-
						${t_dto.getMobile_3()}
						</td>
					</tr>		
					<tr>
						<th>제품사진</th>
						<td>
						<img src="attach/product/${t_dto.getAttach()}"/ class="view_img">;
						</td>
						<th>Price</th>
						<td><fmt:formatNumber value="${t_dto.getPrice()}" pattern="#,###"/></td>
					</tr>
					<tr>
						<th>주소</th>
						<td colspan="3">${t_dto.getAddress()}</td>
					</tr>
					<tr>
						<th>결제방법</th>
						<td>${t_dto.getPay()}</td>
						<th>주문일자</th>
						<td>${t_dto.getReg_date()}</td>
					</tr>
					<tr>
						<th>진행상황</th>
						<td colspan="3">
						${t_dto.getState()}
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
			<c:if test="${t_dto.getState() ne '주문취소'}">
				<a href="javascript:goDelete('${t_dto.getS_no()}')" class="butt">주문취소</a>
			</c:if>	
				<a href="Order" class="butt">List</a>
			</div>	
		</div>	
	</div>
<%@ include file="../common_footer.jsp" %>