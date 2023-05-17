package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.FaqDto;
import dto.QnaDto;

public class FaqDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//게시글번호 생성
	public String getMaxNo() {
		String no = "";
		String query = "select nvl(max(no) ,'N000') as no from home_이주형_faq";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				no = rs.getNString("no");
				no = no.substring(1); 
				int Ino = Integer.parseInt(no);
				Ino ++;
				
				DecimalFormat df = new DecimalFormat("F000");
				no = df.format(Ino);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return no;
	}
	
	//게시글 저장
	public int getFaqSave(FaqDto dto) {
		int result = 0;
		String query = "insert into home_이주형_faq\r\n" + 
					"(no, title, content, reg_id, reg_date)\r\n" + 
					"values\r\n" + 
					"('"+dto.getNo()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"', "
							+ "'"+dto.getReg_id()+"', to_date('"+dto.getReg_date()+"', 'yyyy-MM-dd hh24:mi:ss'))";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("getFaqSave(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//게시물 갯수 조회
		public int getTotalCount(String select, String search) {
			int result = 0;
			String query = "select count(*) as count from home_이주형_faq f\r\n" + 
						"where "+select+" like '%"+search+"%'";
			
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
	
	//페이징 조회
		public ArrayList<FaqDto> getFaqListPage(String select, String search, int start, int end){
			ArrayList<FaqDto> arr = new ArrayList<FaqDto>();
			String query = "select * from\r\n" + 
						"(select rownum rnum , tbl.* from\r\n" + 
						"(select f.no, f.title, f.content, m.name, to_char(f.reg_date, 'yyyy-MM-dd hh24:mi:ss') as reg_date \r\n" + 
						"from home_이주형_faq f, home_이주형_member m\r\n" + 
						"where f.reg_id = m.id and "+select+" like '%"+search+"%'\r\n" + 
						"order by f.no desc) tbl)\r\n" + 
						"where rnum >= "+start+" and rnum <= "+end+"";
			
			try {
				con = DBConnection.getConnection();
				ps  = con.prepareStatement(query);
				rs = ps.executeQuery();
				while(rs.next()) {
					String no = rs.getNString("no");
					String title = rs.getNString("title");
					String content = rs.getNString("content");
					String name = rs.getNString("name");
					String reg_date = rs.getNString("reg_date");
					
					FaqDto dto = new FaqDto(no, title, content, reg_date, name, "");
					arr.add(dto);
					
				}
			}catch(SQLException e) {
				System.out.println("getFaqListPage(): "+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return arr;
		}
		
	//게시글 목록
	public ArrayList<FaqDto> getFaqList(String select, String search){
		ArrayList<FaqDto> arr = new ArrayList<FaqDto>();
		String query="select  f.no, f.title, f.content, m.name, to_char(f.reg_date, 'yyyy-MM-dd hh24:mi:ss') as reg_date\r\n" + 
					"from home_이주형_faq f , home_이주형_member m\r\n" + 
					"where f.reg_id = m.id and "+select+" like '%"+search+"%' "
					+ "order by no desc";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String no = rs.getNString("no");
				String title = rs.getNString("title");
				String content = rs.getNString("content");
				String name = rs.getNString("name");
				String reg_date = rs.getNString("reg_date");
				
				FaqDto dto = new FaqDto(no, title, content, reg_date, name, "");
				arr.add(dto);
				
			}
		}catch(SQLException e) {
			System.out.println("getFaqList(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}
	
	//수정할때 글 정보 가져오기
	public FaqDto getView(String no) {
		FaqDto dto = new FaqDto();
		String query = "select  f.no, f.title, f.content, m.name, \r\n" + 
					"to_char(f.reg_date, 'yyyy-MM-dd hh24:mi:ss') as reg_date\r\n" + 
					"from home_이주형_faq f , home_이주형_member m\r\n" + 
					"where f.reg_id = m.id and f.no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String title = rs.getNString("title");
				String content = rs.getNString("content");
				String name = rs.getNString("name");
				String reg_date = rs.getNString("reg_date");
				
				dto = new FaqDto(no, title, content, reg_date, name, "");
				
			}
		}catch(SQLException e) {
			System.out.println("getFaqList(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//수정
	public int getFaqUpdate(String no, String title, String content) {
		int result = 0;
		String query = "update home_이주형_faq\r\n" + 
					"set title = '"+title+"',\r\n" + 
					"content = '"+content+"'\r\n" + 
					"where no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("getFaqUpdate(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//삭제
	public int getFaqDelete(String no) {
		int result = 0;
		String query = "delete from home_이주형_faq\r\n" + 
					"where no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("getFaqDelete(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
}
