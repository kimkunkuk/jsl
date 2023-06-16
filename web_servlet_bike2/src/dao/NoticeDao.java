package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.MemberDto;
import dto.NoticeDto;

public class NoticeDao {
Connection 		  con = null;
PreparedStatement ps = null;
ResultSet 		  rs = null;

	//게시글 번호 생성
	public String getMaxNo(){
		String no = "";
		String query = "select nvl(max(no),'N000') as no\r\n" + 
					"from home_이주형_notice";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				no = rs.getNString("no"); // N001
				no = no.substring(1); // 001
				int n = Integer.parseInt(no); // 1
				n++; // 2
				
				DecimalFormat df = new DecimalFormat("N000");
				no = df.format(n); // N002
			}
		}catch(SQLException e) {
			System.out.println("getMaxNo(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return no;
	}
	
	//등록
	public int noticeSave(NoticeDto dto) {
		int result = 0;
		String query = "insert into home_이주형_notice\r\n" + 
					"(no, title, content, attach, reg_id, reg_date)\r\n" + 
					"values\r\n" + 
					"('"+dto.getNo()+"','"+dto.getTitle()+"','"+dto.getContent()+"',"
							+ "'"+dto.getAttach()+"','"+dto.getReg_id()+"',to_date('"+dto.getReg_date()+"','yyyy-MM-dd hh24:mi:ss'))";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("noticeSave(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	//게시글 갯수 조회
	public int getTotalCount(String select, String search) {
		int result = 0;
		String query = "select count(*) as count from home_이주형_notice n\r\n" + 
					"where "+select+" like '%"+search+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	
	//조회
	/*public ArrayList<NoticeDto> getNoticeList(String select, String search){
		ArrayList<NoticeDto> arr = new ArrayList<NoticeDto>();
		String query = "select n.no, n.title, m.name, to_char(n.reg_date,'yy-MM-dd' )as reg_date, n.hit \r\n" + 
					"from home_이주형_notice n, home_이주형_member m \r\n" + 
					"where n.reg_id = m.id and "+select+" like '%"+search+"%'\r\n" + 
					"order by n.no desc";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs	= ps.executeQuery();
			while(rs.next()) {
				String no 	 	= rs.getNString("no");
				String title 	= rs.getNString("title");
				String name  	= rs.getNString("name");
				String reg_date = rs.getNString("reg_date");
				int    hit 	 	= rs.getInt("hit");
				NoticeDto dto = new NoticeDto(no, title, name, reg_date, hit);
				
				arr.add(dto);
				
			}
		}catch(SQLException e) {
			System.out.println("getNoticeList(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}*/
	
	//페이징 조회
	public ArrayList<NoticeDto> getNoticeListPage(String select, String search, int start, int end){
		ArrayList<NoticeDto> arr = new ArrayList<NoticeDto>();
		String query = "select * from\r\n" + 
				"(select rownum as rnum, tbl.* from \r\n" + 
				"(select n.no, n.title, n.attach, m.name, to_char(n.reg_date,'yy/MM/dd' )as reg_date, n.hit \r\n" + 
				"from home_이주형_notice n, home_이주형_member m \r\n" + 
				"where n.reg_id = m.id and "+select+" like '%"+search+"%'\r\n" + 
				"order by n.no desc ) tbl)\r\n" + 
				"where rnum >= "+start+" and rnum <="+end+"";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs	= ps.executeQuery();
			while(rs.next()) {
				String attach	= rs.getNString("attach");
				String no 	 	= rs.getNString("no");
				String title 	= rs.getNString("title");
				String name  	= rs.getNString("name");
				String reg_date = rs.getNString("reg_date");
				int    hit 	 	= rs.getInt("hit");
				NoticeDto dto = new NoticeDto(no, title, name, reg_date, hit, attach);
				
				arr.add(dto);
				
			}
		}catch(SQLException e) {
			System.out.println("getNoticeList(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}
	
	//상세조회
	public NoticeDto getNoticeView(String no) {
		NoticeDto dto = new NoticeDto();
		String query = "select n.no, n.title, n.content, n.attach, m.name, to_char(n.reg_date,'yy/MM/dd hh:mi:ss' )as reg_date, n.hit \r\n" + 
					"from home_이주형_notice n, home_이주형_member m \r\n" + 
					"where n.reg_id = m.id and n.no = '"+no+"'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs	= ps.executeQuery();
			while(rs.next()) {
				String title 	= rs.getNString("title");
				String content  = rs.getNString("content");
				String attach	= rs.getNString("attach");
				String name  	= rs.getNString("name");
				String reg_date = rs.getNString("reg_date");
				int    hit 	 	= rs.getInt("hit");
				dto = new NoticeDto(no, title, content, attach, name, reg_date, hit);
				
			}
		}catch(SQLException e) {
			System.out.println("getNoticeList(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//조회수증가
	public void setHitCount(String no){
		String query="update home_이주형_notice\r\n" + 
				"set hit = hit+1\r\n" + 
				"where no = '"+no+"'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			int result = ps.executeUpdate();
			if(result == 0) System.out.println("공지사항 조회증가수 오류");
		}catch(SQLException e) {
			System.out.println("setHitCount(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
	}
	
	//이전글, 다음글
	public NoticeDto getLeftTitle(String no) {
		NoticeDto dto = new NoticeDto();
		String query = "select a.no , b.title from \r\n" + 
					"(select max(no) as no from home_이주형_notice\r\n" + 
					"where no < '"+no+"') a, home_이주형_notice b\r\n" + 
					"where a.no = b.no";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs	= ps.executeQuery();
			if(rs.next()) {
				String No = rs.getNString("no");
				String title = rs.getNString("title");
				dto = new NoticeDto(No, title);
			}
		}catch(SQLException e) {
			System.out.println("getNoticeList(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	public NoticeDto getRightTitle(String no) {
		NoticeDto dto = new NoticeDto();
		String query = "select a.no , b.title from \r\n" + 
					"(select min(no) as no from home_이주형_notice\r\n" + 
					"where no > '"+no+"') a, home_이주형_notice b\r\n" + 
					"where a.no = b.no";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs	= ps.executeQuery();
			if(rs.next()) {
				String No = rs.getNString("no");
				String title = rs.getNString("title");
				dto = new NoticeDto(No, title);
			}
		}catch(SQLException e) {
			System.out.println("getNoticeList(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//수정
	public int getUpdate(NoticeDto dto) {
		int result = 0;
		String query = "update home_이주형_notice\r\n" + 
					"set title = '"+dto.getTitle()+"',\r\n" + 
					"content = '"+dto.getContent()+"',\r\n" + 
					"attach = '"+dto.getAttach()+"'\r\n" + 
					"where no = '"+dto.getNo()+"'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//삭제
	public int getDelete(String no) {
		int result = 0;
		String query="delete from home_이주형_notice\r\n" + 
					"where no = '"+no+"'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
		
	}
	
}
