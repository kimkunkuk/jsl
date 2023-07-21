<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<%@ include file="../common_menu.jsp" %>
<style>
	.hide {display:none;}  
    .show {} 
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
	
	$(document).ready(function(){
		CommentList();
	});
	
	function CommentList(){
	
		$.ajax({
				
				type : 'POST',
				url : 'CommentList',
				dataType : 'text',
				error : function(){
					alert('통신 실패');
				},
				success : function(data){
					data = JSON.parse(data);
					console.log(data);
					
					var tb = "<div class='com-group'>";
					for(var i=0; i<data.t_arr.length; i++){
						var jsob = JSON.parse(JSON.stringify(data.t_arr[i]));
						var no = jsob.no;
						var title = jsob.title;
						var group_no = jsob.group_no;
						var name = jsob.name;
						var date = jsob.date;
						if(no == group_no){
							tb+= "글번호:"+no;
							tb+= "그룹번호:"+group_no;
							tb+= "작성자:"+name;
							tb+= "댓글내용:"+title;
							tb+= "작성일:"+date;
						}
					}
					tb += "</div>";
					$(".com-group").html(tb);
					console.log(tb);
				}
			});
		}
	
	function goComment(no){
		var k = "#"+no;
		console.log($(k).val());
		
		$.ajax({
			
			type : 'POST',
			url : 'SaveComment',
			dataType : 'text',
			data : 't_no='+no+'&t_comment='+$(k).val(),
			error : function(){
				alert('통신 실패');
			},
			success : function(){
				$(k).val("");
				CommentList();
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
					<div class="item">
						<div><a href="#">${arr.getTitle()}</a></div>
						<div>${arr.getReg_name()}</div>
						<div>${arr.getReg_date()}</div>
					</div>
					
					<div class="hide">
						<span>Content</span>
						<div class="content">
							${arr.getContent()}
						</div>
						<form name="${arr.getNo()}">
							<input type="text" name="t_comment" style="width:600px; height:60px;" id="${arr.getNo()}">
						</form>
						<c:if test="${sessionLevel eq 'member' or sessinLevel eq 'admin' }"><div>
						<a href="javascript:goComment('${arr.getNo()}')" style="padding: 5px 10px; background: #D8D8D8;">댓글작성</a>
						</div></c:if>
						<div class="com-group">
						
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