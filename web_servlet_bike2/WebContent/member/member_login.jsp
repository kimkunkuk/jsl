<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common_header.jsp" %>
<script>
	function goPw(){
		goLogin.t_pw.focus();
	}
	function memberLogin(){
		if(checkValue(goLogin.t_id,"id입력!")) return;
		if(goLogin.t_pw.value == ""){
			
			return;
		}
		goLogin.t_gubun.value="goLogin";
		goLogin.method="post";
		goLogin.action="Member";
		goLogin.submit();
	}
	
</script>
		<div id="b_left">
			<P>MEMBER</P>
			<ul>
				<li><a href="javascript:goWork('memberLogin')"><span class="fnt"><i class="fas fa-apple-alt"></i></span>LOGIN</a></li>
				<li><a href="javascript:goWork('memberPasswordFind')">ID / PASSWORD</a></li>
				<li><a href="javascript:goWork('memberJoin')"> JOIN</a></li>
			</ul>
		</div>
		
		<div id="b_right">
			<p class="n_title">
				MEMBER LOGIN
			</p>
		
			<div class="login">
				<div class="member_boxL">
					<h2>LOGIN</h2>
					<div class="login_form">
						<form name="goLogin">
							<input type="hidden" name="t_gubun" value="login">
							<div class="fl_clear"><label for="mbrId">아이디</label><input name="t_id" id="mbrId" type="text" onkeypress="if( event.keyCode==13 ){goPw()}"></div>
							<div class="fl_clear"><label for="scrtNo">비밀번호</label><input name="t_pw" id="scrtNo" type="password" onkeypress="if( event.keyCode==13 ){memberLogin()}"></div>
							
							<a class="btn_login btn_Blue" href="javascript:memberLogin()">로그인</a>
							
							
							
						</form>
					</div>
				   
				</div>		
			</div>

		</div>	
	</div>
<%@ include file="../common_footer.jsp" %>






