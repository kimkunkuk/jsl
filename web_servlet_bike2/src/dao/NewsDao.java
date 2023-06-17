package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.NewsDto;

public class NewsDao {
Connection 		  con = null;
PreparedStatement ps  = null;
ResultSet 		  rs  = null;
	
	//게시글 번호 생성
	public String getMaxNo() {
		String no = "";
		String query = "select nvl(max(no),'N000') as no\r\n" + 
					"from home_이주형_news";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				no = rs.getNString("no");
				no = no.substring(1);
				int n = Integer.parseInt(no);
				n++;
				
				DecimalFormat df = new DecimalFormat("N000");
				no = df.format(n);
				
			}
		}catch(SQLException e){
			System.out.println("getMaxNo():"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return no;
	}

	//뉴스 등록
	public int getNewsSave(NewsDto dto) {
		int result = 0;
		String query = "insert into home_이주형_news\r\n" + 
					"(no,title,content,attach,reg_id,reg_date)\r\n" + 
					"values\r\n" + 
					"('"+dto.getNo()+"','"+dto.getTitle()+"','"+dto.getContent()+"','"+dto.getAttach()+"','"+dto.getReg_id()+"',to_date('"+dto.getReg_date()+"', 'yyyy-MM-dd hh24:mi:ss'))";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("getNewsSave(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//뉴스 갯수 조회
	public int getTotalCount(String select, String search) {
		int result = 0;
		String query="select count(*) as count from home_이주형_news a\r\n" + 
					"where a.title like '%%'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
			}
		}catch(SQLException e) {
			System.out.println("getTotalCount(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//뉴스 리스트 페이징 조회 
	public ArrayList<NewsDto> getNewsListPage(String select, String search, int start, int end){
		ArrayList<NewsDto> arr = new ArrayList<NewsDto>();
		String query = "select * from\r\n" + 
				"(select rownum rnum, tbl.* from\r\n" + 
				"(select a.no, a.title, m.name, to_char(a.reg_date,'yy/MM/dd' )as reg_date, a.hit \r\n" + 
				"from home_이주형_news a, home_이주형_member m \r\n" + 
				"where a.reg_id = m.id and "+select+" like '%"+search+"%'\r\n" + 
				"order by a.no desc) tbl)\r\n" + 
				"where rnum >="+start+" and rnum <="+end+"";
			try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String no = rs.getNString("no");
				String title = rs.getString("title");
				String name  = rs.getNString("name");
				String reg_date = rs.getNString("reg_date");
				int hit = rs.getInt("hit");
				
				NewsDto dto = new NewsDto(no, title, reg_date, name, hit);
				arr.add(dto);
			}
		}catch(SQLException e) {
			System.out.println("getNewsListPage(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}
	
	//뉴스 리스트
	public ArrayList<NewsDto> getNewsList(String select, String search){
		ArrayList<NewsDto> arr = new ArrayList<NewsDto>();
		String query = "select a.no, a.title, b.name, to_char(a.reg_date,'yy-MM-dd') as reg_date, a.hit \r\n" + 
					"from home_이주형_news a, home_이주형_member b\r\n" + 
					"where a.reg_id = b.id and "+select+" like '%"+search+"%'\r\n" + 
					"order by a.no desc";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String no = rs.getNString("no");
				String title = rs.getString("title");
				String name  = rs.getNString("name");
				String reg_date = rs.getNString("reg_date");
				int hit = rs.getInt("hit");
				
				NewsDto dto = new NewsDto(no, title, reg_date, name, hit);
				arr.add(dto);
			}
		}catch(SQLException e) {
			System.out.println("getNewsList(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}
	
	//뉴스 상세보기
	public NewsDto getView(String no) {
		NewsDto dto = new NewsDto();
		String query = "select a.no, a.title, a.content, a.attach, b.name, \r\n" + 
					"to_char(a.reg_date,'yy/MM/dd hh:mi:ss') as reg_date, a.hit, \r\n" + 
					"to_char(a.update_date,'yy/MM/dd hh:mi:ss') as update_date\r\n" + 
					"from home_이주형_news a, home_이주형_member b\r\n" + 
					"where a.reg_id = b.id and no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String NO = rs.getNString("no");
				String title = rs.getString("title");
				String content = rs.getNString("content");
				String attach = rs.getNString("attach");
				String name  = rs.getNString("name");
				String reg_date = rs.getNString("reg_date");
				int hit = rs.getInt("hit");
				String update_date = rs.getNString("update_date");
				
				dto = new NewsDto(NO, title, content, attach, reg_date, update_date, name, hit );
				
			}
		}catch(SQLException e) {
			System.out.println("getNewsList(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//조회수 
	public void getHitCount(String no) {
		String query="update home_이주형_news\r\n" + 
					"set hit = hit+1\r\n" + 
					"where no = '"+no+"'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			int result = ps.executeUpdate();
			if(result != 1) System.out.println("뉴스조회수 오류");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
	}
	
	//수정
	public int getNewsUpdate(NewsDto dto) {
		int result = 0;
		String query="update home_이주형_news\r\n" + 
				"set title = '"+dto.getTitle()+"',\r\n" + 
				"content = '"+dto.getContent()+"',\r\n" +
				"attach = '"+dto.getAttach()+"',\r\n" +
				"update_date = to_date('"+dto.getUpdate_date()+"', 'yyyy-MM-dd hh24:mi:ss')\r\n" + 
				"where no = '"+dto.getNo()+"'";
		System.out.println(query);
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("getNewsUpdate() "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//삭제
	public int getNewsDelete(String no) {
		int result = 0;
		String query="delete from home_이주형_news\r\n" + 
					"where no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("getNewsUpdate() "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//다음글 이전글
	public NewsDto getPreTitle(String no) {
		NewsDto dto = new NewsDto();
		String query ="select  a.no, b.title\r\n" + 
					"from (select max(no) as no from home_이주형_news where no < '"+no+"') a, home_이주형_news b\r\n" + 
					"where a.no = b.no";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				String NO = rs.getNString("no");
				String title = rs.getNString("title");
				
				dto = new NewsDto(NO, title);
			}
		}catch(SQLException e) {
			System.out.println("getNewsUpdate() "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	public NewsDto getNextTitle(String no) {
		NewsDto dto = new NewsDto();
		String query ="select  a.no, b.title\r\n" + 
					"from (select min(no) as no from home_이주형_news where no > '"+no+"') a, home_이주형_news b\r\n" + 
					"where a.no = b.no";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				String NO = rs.getNString("no");
				String title = rs.getNString("title");
				
				dto = new NewsDto(NO, title);
			}
		}catch(SQLException e) {
			System.out.println("getNewsUpdate() "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
}
