<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<%@ include file="../common_menu.jsp" %>
<style>
	.hide {display:none;}  
    .show {display:table-row;} 
    .bor 	{border: none;} 
    .item {
    	
    }
    .record_group_left {
	    width: 100%;
	    padding-top: 10px;
	    margin-bottom: 20px;
	}
	#top{
		width: 100%;
		display:flex;
		padding:10px 0;
		justify-content: space-around;
		border-top:1px solid #848484;
		border-bottom:1px solid #848484;
	}
    .boardList .item { 
    	display:flex;
    	justify-content: space-around;
    	cursor:pointer;
		
		border-bottom:1px solid #e0e0e0;
		padding:8px 0;
	}
	.content{
		white-space:pre-wrap;
		padding:20px;
	}
</style>
<script>
	function goWrite(){
		con.t_gubun.value="write";
		con.method="post";
		con.action="Etc";
		con.submit();
	}
	function goComment(no){
		
		$.ajax({
			type : "POST",
			url : "DownHit",
			async: false, 
			data: "t_no="+no,
			dataType : "text", //순간적으로 띄운 브라우저 에 글씨 받아오겠다.
			error : function(){
				alert('통신실패!!!!!');
			},
			success : function(data){
				var result = $.trim(data);
				con.down.value = result;
				
			}
		});	
		
	}
	
	
	$(function(){  
        var article = (".recruit .show");  
        $(".recruit .item ").click(function() { 
            var myArticle =$(this).next("div");  
            if($(myArticle).hasClass('hide')) {  
                $(article).removeClass('show').addClass('hide');  
                $(myArticle).removeClass('hide').addClass('show');  
            }  
            else {  
                $(myArticle).addClass('hide').removeClass('show');  
            }  
        });
        
    });  
</script>

<form name="con">
	<input type="hidden" name="t_gubun">
	<input type="hidden" name="t_no">
</form>
<div id="b_right">
			<p class="n_title">
				ETC
			</p>
			<div class="record_group record_group_left">
				<p><i class="fa-solid fa-bell"></i> 총게시글<span>${t_totalCount}</span>건</p>
			</div>
			<div id="top">
				<div style="margin-left:100px;">[ TITLE ]</div>
				<div>[ Writer ]</div>
				<div>[ Date ]</div>
			</div>			
			<div class="boardList recruit">
				<c:forEach items="${t_arr}" var="arr">
					<div class="item" onClick="content()">
						<div><a href="#">${arr.getTitle()}</a></div>
						<div>${arr.getReg_name()}</div>
						<div>${arr.getReg_date()}</div>
					</div>
					<div class="hide">
						<span>Content</span>
						<div class="content">
							${arr.getContent()}
						</div>
						<input type="text" name="t_coment" style="width:600px; height:60px;">
						<div>
						<a href="javascript:goComment('${arr.getNo()}')" style="padding: 5px 10px; background: #D8D8D8;">댓글작성</a>
						</div>
						
					</div>
				</c:forEach>	
			
			<div class="paging">
				${t_paging}
				<a href="javascript:goWrite()" class="write">글쓰기</a>
			</div>
		</div>	
	</div>

<%@ include file="../common_footer.jsp" %>