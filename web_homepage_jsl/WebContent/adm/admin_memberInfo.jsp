<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dto.*,dao.*" %>    
<%
	AdminDao dao = new AdminDao();
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("t_id");
	AdminDto dto = dao.getMemberView(id);
%>
<%@ include file = "../common_header.jsp" %>

  <script src="../js/jquery-3.3.1.min.js"></script>
  <script>
  	function updateForm(){
  		location.href="admin_member_update.jsp";
  	}
  	function deleteForm(){
  		location.href="admin_member_delete.jsp";
  	}
  </script>

 </head>
 <body>

	<div class="sub_title">
		<h2>회원정보</h2>
		<div class="container">
		  <div class="location">
			<ul>
				<li class="btn_home">
					<a href="index.html"><i class="fa fa-home btn_plus"></i></a>
				</li>
				<li class="dropdown">
					<a href="">커뮤니티<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="gratings.html">공지사항</a>
						<a href="allclass.html">학과및모집안내</a>
						<a href="portfolio.html">포트폴리오</a>
						<a href="online.html">온라인접수</a>
						<a href="notice.html">커뮤니티</a>
					</div>
				</li>
				<li class="dropdown">
					<a href="">공지사항<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="notice.html">공지사항</a>
						<a href="qa.html">질문과답변</a>
						<a href="faq.html">FAQ</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
		<div class="con_title">
            <h1><%=dto.getId() %></h1>
            <%if(dto.getAcount().equals("n")) {%>
         	<p style="font-size:20px; color:red;">탈퇴한 회원</p>
         	<%} %>
        </div>
		<div class="join_write col_989">
                <div class="list_con">
                    <ul class="icon_type1">
                        <li>회원정보는 개인정보 취급방침에 따라 안전하게 보호되며 회원님의 명백한 동의 없이 공개 또는 제3자에게 제공되지 않습니다.</li>
                    </ul>
                </div>
            <table class="table_write02" summary="회원가입을 위한 이름, 아이디, 비밀번호, 비밀번호확인, 소속, 유선전화번호, 휴대전화번호, 이메일, 주소, 본인확인질문, 본인확인답, 주활용사이트, 알림여부 정보 입력">
                <caption>회원가입을 위한 정보입력표</caption>
                <colgroup>
                    <col width="160px">
                    <col width="auto">
                </colgroup>
                <tbody id="joinDataBody">
                    <tr>
                        <th><label for="name">이름</label></th>
                        <td>
                           <%=dto.getName() %>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="id">아이디<span class="must"></span></label></th>
                        <td>
                            <%=dto.getId() %>
						</td>
                    </tr>
                    <tr>
                        <th>비밀번호<span class="must"><b></b></span></th>
                        <td>
                            <%for(int k=0; k< dto.getPwlen(); k++) out.print("*");%>
                        </td>
                    </tr>
                    <tr>
                        <th>소속<span class="must"><b></b></span></th>
                        <td>
                            <%=dto.getJob() %>
                        </td>
                    </tr>
                    <tr>
                        <th>유선전화</th>
                        <td>
                         <% //if (dto.getTell_1() != null) {
                        		//out.print(dto.getTell_1());
                        	//}
                        	%>
                         
                        <%if(dto.getTell_1() != null) {%>
                           <%=dto.getTell_1() %> -  <%=dto.getTell_2() %> -  <%=dto.getTell_3() %>
                        <%} else %> 정보없음 
                        </td>
                    </tr>
                    <tr>
                        <th>휴대전화<span class="must"></th>
                        <td>
                          	<%=dto.getMobile().substring(0, 3) %> -
                          	<%=dto.getMobile().substring(3, 7) %> -
                          	<%=dto.getMobile().substring(7) %>
                        </td>
                    </tr> 
                    <tr>
                        <th><label for="email">이메일</label></th>
                        <td>
                            <%=dto.getEmail() %>
                        </td>
                    </tr>
                    <tr>
                        <th>회원가입일</th>
                        <td>
                            <%=dto.getReg_date()  %>
                        </td>
                    </tr>
                    <tr>
                        <th>최근접속일</th>
                        <td>
                        <%if(dto.getLogin_date() != null) {%>
                            <%=dto.getLogin_date()  %>
                        <%} else %> 최근 접속일 없음
                        </td>
                    </tr>
                    <%if(dto.getAcount().equals("n")) {%>
                    <tr style="color:red;">
                        <th>회원탈퇴일</th>
                        <td>
                            <%=dto.getDel_date() %>
                        </td>
                    </tr>
                    <%} %>
            </table>
        </div>
	</div>
	<!-- end contents -->
	
	<div class="btnArea Acenter pt60 pb100">
        <a href="javascript:history.go(-1);" class="btn_round btn_large btn_BlueGray w180"><b>목록</b></a>
        
    </div>
	
	
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
	

<%@ include file = "../common_footer.jsp" %>

 </body>
</html>









    