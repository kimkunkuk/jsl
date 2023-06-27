<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<script>
	function goMenu(val){
		var menuName= "";
		if(val == "admin"){
			menuName = "Admin";
		}else if(val == "product"){
			menuName = "Product";
		}
		menu.t_gubun.value = val;
		menu.method="post";
		menu.action= menuName;
		menu.submit();
	}
</script>
<form name="menu">
	<input type="hidden" name="t_gubun">
</form>
<div id="b_left">
			<P>PRODUCT</P>
			<ul>
				<li>
				<a href="Admin">
					
					<span class="fnt"><i class="fas fa-apple-alt"></i></span> 
					
					PRODUCT LIST
				</a>
				</li>
				
			</ul>
</div>