<%@ page pageEncoding="UTF-8"%>
<%
	String url = request.getServletPath();  // /news/news.jsp
	String title = "";
	if(url.contains("news")) title = "뉴스";
	else if(url.contains("notice")) title = "공지사항";
	else if(url.contains("faq")) title = "자주묻는질문";
	else if(url.contains("qna")) title = "질문과답변";
	else if(url.contains("admin")) title = "관리자";
	else if(url.contains("pds")) title = "자료실";
	
	String subsessionLevel = (String)session.getAttribute("sessionLevel");
	if(subsessionLevel == null) subsessionLevel = "";
%>
<div class="sub_title">
		<h2><%=title %></h2>
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
					<a href="/web_homepage_jsl<%=url%>"><%=title %><i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="/web_homepage_jsl/notice/notice.jsp">공지사항</a>
						<a href="/web_homepage_jsl/news/news.jsp">뉴스</a>
						<a href="/web_homepage_jsl/qna/qna_list.jsp">질문과답변</a>
						<a href="/web_homepage_jsl/faq/faq_list.jsp">FAQ</a>
						<a href="/web_homepage_jsl/pds/pds.jsp">자료실</a>
						<%if(subsessionLevel.equals("top")) {%>
								<a href="/web_homepage_jsl/adm/admin_member_list.jsp">관리자</a>
						<%} %>	
					</div>
				</li>
			</ul>
		  </div>
		</div>
	</div>