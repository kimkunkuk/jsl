<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<script>
	function goMemberList(){
		if(checkValue(admin.id,"아이디를 입력하세요.")) return;
		if(checkValue(admin.pw,"비밀번호를 입력하세요.")) return;
		
		admin.method="post";
		admin.action="db_admin_login.jsp";
		admin.submit();
	}
</script>
	<!-- sub contents -->
	<%@ include file = "../common_subcontent.jsp" %>

	<div class="bg_admim">
		<div class="container">
			<div class="grap">
				<form name="admin" method="post" action="">
					<fieldset>
						<legend class="sr-only">관리자로그인</legend>
						<label for="id" class="sr-only">아이디입력</label><input type="text" name="t_id" placeholder="아이디를 입력하세요" id="id">
						<label for="pw" class="sr-only">패스워드입력</label><input type="password" name="t_pw" placeholder="패스워드를 입력하세요" id="pw">
						<a href="javascript:goMemberList()" onClick="admin_check();" class="btn_admin">로그인</a>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<!-- end contents -->
	<script>
		$(function() {
			$(".location  .dropdown > a").on("click",function(e) {
				e.preventDefault();
				if($(this).next().is(":visible")) {
					$(".location  .dropdown > a").next().hide();
				} else {
					$(".location  .dropdown > a").next().hide();
					$(this).next().show();
				}
			});
		});
	</script>
	

	<%@ include file="../common_footer.jsp" %>

 </body>
</html>









