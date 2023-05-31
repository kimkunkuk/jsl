<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<%@ include file="../common_menu.jsp" %>
<div id="b_right">
			<p class="n_title">
				ETC
			</p>
			<div class="record_group record_group_left">
				<p><i class="fa-solid fa-bell"></i> 총게시글<span> 4 </span>건</p>
			</div>			
			<p class="select_box select_box_right">
				<select name="t_select" class="sel_box">
					<option value="" selected >Title</option>
					<option value=""  >Content</option>
				</select>
				<input type="text" name="" value="" class="sel_text">

				<button type="button"  class="sel_button"><i class="fa fa-search"></i> SEARCH</button>
			</p>			
			
			<table class="boardList">
				<colgroup>
					<col width="5%">
					<col width="60%">
					<col width="5%">
					<col width="10%">
					<col width="14%">
					<col width="6%">
				</colgroup>
				<thead>
					<tr>
						<th>No</th>
						<th>Title</th>
						<th>Attach</th>
						<th>Reg Name</th>
						<th>Reg Date</th>
						<th>Hit</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>9</td>
						<td class="t_left"><a href="notice_view.html">구매 절차 과정 안내 드립니다.</a></td>
						<td><img src="../images/clip.png"></td>
						<td>관리자</td>
						<td>2020-07-28</td>
						<td>412</td>
					</tr>	
					<tr>
						<td>8</td>
						<td class="t_center">구매 절차 과정 안내 드립니다.</td>
						<td></td>
						<td>관리자</td>
						<td>2020-07-28</td>
						<td>412</td>
					</tr>	
					<tr>
						<td>7</td>
						<td class="t_center">구매 절차 과정 안내 드립니다.</td>
						<td><img src="../images/clip.png"></td>
						<td>관리자</td>
						<td>2020-07-28</td>
						<td>412</td>
					</tr>	
					<tr>
						<td>6</td>
						<td class="t_center">구매 절차 과정 안내 드립니다.</td>
						<td><img src="../images/clip.png"></td>
						<td>관리자</td>
						<td>2020-07-28</td>
						<td>412</td>
					</tr>	
					<tr>
						<td>5</td>
						<td class="t_center">구매 절차 과정 안내 드립니다.</td>
						<td></td>
						<td>관리자</td>
						<td>2020-07-28</td>
						<td>412</td>
					</tr>	
					<tr>
						<td>4</td>
						<td class="t_center">구매 절차 과정 안내 드립니다.</td>
						<td></td>
						<td>관리자</td>
						<td>2020-07-28</td>
						<td>412</td>
					</tr>	
					<tr>
						<td>3</td>
						<td class="t_center">구매 절차 과정 안내 드립니다.</td>
						<td></td>
						<td>관리자</td>
						<td>2020-07-28</td>
						<td>412</td>
					</tr>	
					<tr>
						<td>2</td>
						<td class="t_center">구매 절차 과정 안내 드립니다.</td>
						<td></td>
						<td>관리자</td>
						<td>2020-07-28</td>
						<td>412</td>
					</tr>	
					<tr>
						<td>1</td>
						<td class="t_center">구매 절차 과정 안내 드립니다.</td>
						<td></td>
						<td>관리자</td>
						<td>2020-07-28</td>
						<td>412</td>
					</tr>	
					<tr>
						<td>0</td>
						<td class="t_center">구매 절차 과정 안내 드립니다.</td>
						<td></td>
						<td>관리자</td>
						<td>2020-07-28</td>
						<td>412</td>
					</tr>	
				</tbody>
			</table>
			
			<div class="paging">
				<a href=""><i class="fa fa-angle-double-left"></i></a>
				<a href=""><i class="fa fa-angle-left"></i></a>
				<a href="" class="active">1</a>
				<a href="">2</a>
				<a href="">3</a>
				<a href="">4</a>
				<a href="">5</a>
				<a href=""><i class="fa fa-angle-right"></i></a>
				<a href=""><i class="fa fa-angle-double-right"></i></a>
				<a href="notice_write.html" class="write">글쓰기</a>
			</div>
		</div>	
	</div>

<%@ include file="../common_footer.jsp" %>