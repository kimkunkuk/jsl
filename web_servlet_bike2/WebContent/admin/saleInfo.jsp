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
		admin.t_gubun.value = "view";-
		admin.t_no.value = no;
		admin.method="post";
		admin.action="SaleList";
		admin.submit();
	}
	
	
</script>
<form name="admin">
	<input type="hidden" name="t_no">
	<input type="hidden" name="t_gubun">
	<input type="hidden" name="t_date" value="${arr.getReg_date()}">
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
<style>
	.hide {display:none;}  
    .show {display:table-row;}  
    .item td {cursor:pointer;}
</style>
 <script type="text/javascript">  

        $(function(){  
            var article = (".recruit .show");  
            $(".recruit .item  td").click(function() { 
                var myArticle =$(this).parents().next("tr");  
                if($(myArticle).hasClass('hide')) {  
                    $(article).removeClass('show').addClass('hide');  
                    $(myArticle).removeClass('hide').addClass('show');  
                }  
                else {  
                    $(myArticle).addClass('hide').removeClass('show');  
                }  
            });
            
        });  
		function content(){
			/*
			(select reg_date , sum(price) as price from
(select to_char(reg_date,'yy-MM-dd') as reg_date, price as price
from bike_이주형_product_sale where state != '주문취소' and s_no like '%2307%' ) 
group by reg_date) order by price desc
			*/
			$.ajax({
				type : "POST",
				url : "SaleContent",
				data: "t_date="+admin.t_date.value,
				dataType : "text", //순간적으로 띄운 브라우저 에 글씨 받아오겠다.
				error : function(){
					alert('통신실패!!!!!');
				},
				success : function(data){
					var result = $.trim(data);
					
					join.t_idcheck.value = result;
					
				}
			});	
			
		}
    </script>  	
   
			<table class="boardList recruit" >
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
					
					<tr class="item" onClick="content()">
						<td>${arr.getReg_date()}</td>
						<td>${arr.getCount()}</td>
						<td><fmt:formatNumber value="${arr.getPrice()}" pattern="#,###"/>원</td>
					</tr>
					
					<tr class="hide">
						<td></td>
						<td>숨김</td>
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