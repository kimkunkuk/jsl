<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String sessionName = (String)session.getAttribute("sessionName");
	
	//out.print("name: "+name);
%>    
<%@ include file = "../common_header.jsp" %>

	<section id="main_visual">
		<div class="visual_wrap">
			<h2 class="sr-only">메인 비쥬얼</h2>
			<ul>
				<!-- <li><img src="images/main01.jpg" alt="학생단체관람" class="w100"></li> -->
				<li class="one"></li>
			</ul>
			<div class="visual_inner">
				<p class="title">국가와 청년실업자를 을 위한<br/>
				<strong>취업선두</strong> 중심기관</p>
				<p class="txt">지식, 기술, 태도를 공유하고 가치를 창출하는 역동적인 정보처리 중심기관으로<br/>산학연 공통체와 함게 성장하며 국가 혁신 성장의 밑거름이 되겠습니다</p>
			</div>
		</div>
	</section>
	<section class="news_group">
		<div class="news_tit">
			<p class="sub_tit">배움의 즐거움이 있는곳</p>
			<h2>JSL COLEGE 소식</h2>
			<p class="text">JSL인재개발원 다양한 소식을 확인 하실 수 있습니다</p>
			<a href="" class="btn-border">READ MORE</a>
		</div>
		<ul class="news_list">
			<li>
				<a href="">
					<img src="./images/news01.jpg" alt="식품경진대회 우수상 사진" class="w100">
					<strong>식품경진대회 우수상 수상</strong>
					<p>2017년 식품안전분야 공공데이터 활용 경진대회 우수상을 또 수상하였습니다.</p>
					<span>2018.09.20</span>
					<div class="over">
						<strong>식품경진대회 우수상 수상</strong>
						<p>2017년 식품안전분야 공공데이터 활용 경진대회 우수상을 또 수상하였습니다.</p>
						<span>2018.09.20</span>
					</div>
				</a>
			</li>
			<li>
				<a href="">
					<img src="./images/news02.jpg" alt="식품경진대회 우수상 사진" class="w100">
					<strong>식품경진대회 우수상 수상</strong>
					<p>2017년 식품안전분야 공공데이터 활용 경진대회 우수상을 또 수상하였습니다.</p>
					<span>2018.09.20</span>
					<div class="over">
						<strong>식품경진대회 우수상 수상</strong>
						<p>2017년 식품안전분야 공공데이터 활용 경진대회 우수상을 또 수상하였습니다.</p>
						<span>2018.09.20</span>
					</div>
				</a>
			</li>
			<li class="news_end">
				<a href="">
					<img src="./images/news03.jpg" alt="식품경진대회 우수상 사진" class="w100">
					<strong>식품경진대회 우수상 수상</strong>
					<p>2017년 식품안전분야 공공데이터 활용 경진대회 우수상을 또 수상하였습니다.</p>
					<span>2018.09.20</span>
					<div class="over">
						<strong>식품경진대회 우수상 수상</strong>
						<p>2017년 식품안전분야 공공데이터 활용 경진대회 우수상을 또 수상하였습니다.</p>
						<span>2018.09.20</span>
					</div>
				</a>
			</li>
		</ul>
	</section>
	<section class="main_content">
		<h2 class="sr-only">주요서비스</h2>
		<div class="main_left">
			<div class="box">
				<div class="txt_relative">
					<h3>간행물</h3>
					<p>지식정보인프라지<br>05호</p>
					<a href="" class="btn-border">READ MORE</a>
				</div>
				<div class="img_transition">
				</div>
			</div>
			<div class="box gallery">
				<div class="txt_relative">
					<h3>간행물</h3>
					<p>지식정보인프라지<br>05호</p>
					<a href="" class="btn-border">READ MORE</a>
				</div>
				<div class="img_transition">
				</div>
			</div>
		</div>
		<div class="main_center">
			<div class="box3">
				<h3 class="sr-only">알림마당</h3>
				<nav>
					<ul>
						<li class="active"><a href="#tabview1">공지사항</a></li>
						<li><a href="#tabview2">졸업후기</a></li>
					</ul>
				</nav>
				<div class="noticelist clearfix active taball" id="tabview1">
					<div class="recently">
						<p class="title">
							<a class="title" href="">빅데이터응용SW개발 양성과정 모집</a>
							<span class="date">2018-09-27</span>
						</p>
						<p class="text">
							빅데이터처리, JAVA, JSP, PHP, DB을 이용한 응용소프트웨어 개발자를 모집중입니다...
						</p>
					</div>
					<ul>
						<li><a href="">HCI기반 UI/UX전문가양성과종 훈련생 모집</a><span class="date">201-09-27</span></li>
						<li><a href="">품질경영전문가 훈련생 모집</a><span class="date">201-09-27</span></li>
						<li><a href="">대전디자인경진대회 공지</a><span class="date">201-09-27</span></li>
						<li><a href="">국비전액무료교육생 모집</a><span class="date">201-09-27</span></li>
					</ul>
				</div>
				<div class="graduation  clearfix taball" id="tabview2">
					<div class="recently">
						<p class="title">
							<a class="title" href="">내가 포기하지 않으면 실패은 없다</a>
							<span class="date">2018-09-27</span>
						</p>
						<p class="text">
							이런 저런 핑게를 대며 나는 20년간 나를 포기하면 살았다. 시간이 흐른 뒤에 ...
						</p>
					</div>
					<ul>
						<li><a href="">즐거움이 가득한 배움의 즐거움을 알다</a><span class="date">201-09-27</span></li>
						<li><a href="">하자, 해보자, 할 수없다</a><span class="date">201-09-27</span></li>
						<li><a href="">무엇 때문에 오늘도 다시 시작하는가</a><span class="date">201-09-27</span></li>
						<li><a href="">남에게도 있는 시간과 노력을 투자하면</a><span class="date">201-09-27</span></li>
					</ul>
				</div>
			</div>
			<div class="box4">
			</div>
			<div class="box5">
			</div>
		</div>
		<div class="main_right">
		</div>
	</section>

	<section class="other">
		<h2 class="sr-only">기타서비스</h2>
		<div class="container clearfix">
			<div class="other_left">
				<h3 class="sr-only">소제목을 입력</h3>
				<div class="title_box">
					<em>small title insert</em>
					<p class="title">제목을<strong>입력하세요</strong></p>
					<p class="text">1980년 회사내용을 입력하세요</p>
					<a href="" class="btn-border white">READ MORE</a>
				</div>
				<div class="content_box">
				</div>
			</div>
			<div class="other_right">
				<h3 class="sr-only">소제목을 입력</h3>
				<div class="title_box">
					<em>small title insert</em>
					<p class="title">제목을<strong>입력하세요</strong></p>
					<p class="text">1980년 회사내용을 입력하세요</p>
					<ul>
						<li>목록 내용을 입력</li>
						<li>목록 내용을 입력</li>
						<li>목록 내용을 입력</li>
					</ul>
				</div>
				<div class="content_box">
				</div>
			</div>
		</div>
	</section>


<script>
  	$(function() {
		$(".taball").hide();
		$("#tabview1").show();
		$(".main_center .box3 nav ul li a").click(function(e) {
		//$(".main_center .box3 nav ul li").click(function(e) {
			e.preventDefault();
			// nav ul li a(공지,졸업)을 클릭했을때
			$(".main_center .box3 nav ul li").removeClass("active");
			// nav ul li 태그에 선언된 active라는 클래스이름을 삭제한다
			//$(this).parent().addClass("active");
			// 공지, 졸업둘중에 하나클릭을 했다면 현재 클릭한 자기자신위에 부모에게 active 클래스가 추가되어 active css가 실행된다
			//$(this).addClass("active");
			$(this).parent().addClass("active");
			$(".taball").hide();
			$($(this).attr("href")).show();
			//var num = $(this).index();
			//$(".taball").hide().eq(num).show();
			});
		});
  </script>

  <div class="right_quick">
	<button class="btn btn-open" type="button"></button>
  </div>

<%@ include file = "../common_footer.jsp" %>
	

 </body>
</html>









