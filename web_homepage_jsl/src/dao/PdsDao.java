package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.NoticeDto;
import dto.PdsDto;

public class PdsDao {
	Connection 		  con = null;
	PreparedStatement ps = null;
	ResultSet 		  rs = null;
	
	//게시글번호 생성
	public String getMaxNo() {
		String no = "";
		String query = "select nvl(max(no),'N000') as no from home_이주형_pds";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				no = rs.getNString("no"); // n000
				no = no.substring(1); //000
				int ino = Integer.parseInt(no);
				ino++;
				
				DecimalFormat df = new DecimalFormat("N000");
				no = df.format(ino);
			}
		}catch(SQLException e) {
			System.out.println("getMaxNo(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return no;
	}
	
	//게시글 저장
	public int PdsSave(PdsDto dto) {
		int result = 0;
		String query = "insert into home_이주형_pds\r\n" + 
					"(no, title, content, attach, reg_id, reg_date)\r\n" + 
					"values\r\n" + 
					"('"+dto.getNo()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"', '"+dto.getAttach()+"', '"+dto.getReg_id()+"', \r\n" + 
					"to_date('"+dto.getReg_date()+"','yyyy-MM-dd hh24:mi:ss') )";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("PdsSave(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//게시글 리스트 조회
	public ArrayList<PdsDto> getPdsList(String select, String search){
		ArrayList<PdsDto> arr = new ArrayList<PdsDto>();
		String query = "select p.no, p.title, p.attach, m.name, to_char(p.reg_date,'yy/MM/dd') as reg_date, p.hit\r\n" + 
					"from home_이주형_pds p, home_이주형_member m\r\n" + 
					"where p.reg_id = m.id and "+select+" like '%"+search+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String no = rs.getNString("no");
				String title = rs.getNString("title");
				String attach = rs.getNString("attach");
				String name = rs.getNString("name");
				String reg_date = rs.getNString("reg_date");
				int hit = rs.getInt("hit");
				
				PdsDto dto = new PdsDto(no, title, attach, "", name, reg_date, hit);
				arr.add(dto);
				
			}
		}catch(SQLException e) {
			System.out.println("getPdsList(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}
	
	//게시글 뷰 
	public PdsDto getPdsView(String no) {
		PdsDto dto = new PdsDto();
		String query = "select p.no, p.title, p.content, p.attach, m.name, \r\n" + 
					"to_char(p.reg_date,'yy/MM/dd hh:mi:ss') as reg_date, p.hit \r\n" + 
					"from home_이주형_pds p, home_이주형_member m\r\n" + 
					"where p.reg_id = m.id and p.no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				no = rs.getNString("no");
				String title = rs.getNString("title");
				String content = rs.getNString("content");
				String attach = rs.getNString("attach");
				String name = rs.getNString("name");
				String reg_date = rs.getNString("reg_date");
				int hit = rs.getInt("hit");
				
				dto = new PdsDto(no, title, attach, content, name, reg_date, hit);
				
			}
		}catch(SQLException e) {
			System.out.println("getPdsList(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//조회수
	public void setHitCount(String no) {
		String query = "update home_이주형_pds\r\n" + 
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
		public PdsDto getPreTitle(String no) {
			PdsDto dto = new PdsDto();
			String query = "select a.no, b.title from\r\n" + 
						"(select max(no) as no from home_이주형_pds\r\n" + 
						"where no < '"+no+"') a , home_이주형_pds b\r\n" + 
						"where a.no = b.no";
			
			try {
				con = DBConnection.getConnection();
				ps  = con.prepareStatement(query);
				rs	= ps.executeQuery();
				if(rs.next()) {
					String No = rs.getNString("no");
					String title = rs.getNString("title");
					dto = new PdsDto(No, title);
				}
			}catch(SQLException e) {
				System.out.println("getNoticeList(): "+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return dto;
		}
		
		public PdsDto getNextTitle(String no) {
			PdsDto dto = new PdsDto();
			String query = "select a.no , b.title from \r\n" + 
						"(select min(no) as no from home_이주형_Pds\r\n" + 
						"where no > '"+no+"') a, home_이주형_Pds b\r\n" + 
						"where a.no = b.no";
			try {
				con = DBConnection.getConnection();
				ps  = con.prepareStatement(query);
				rs	= ps.executeQuery();
				if(rs.next()) {
					String No = rs.getNString("no");
					String title = rs.getNString("title");
					dto = new PdsDto(No, title);
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
	public int getPdsUpdate(PdsDto dto) {
		int result = 0;
		String query = "update home_이주형_pds\r\n" + 
					"set title = '"+dto.getTitle()+"',\r\n" + 
					"content = '"+dto.getContent()+"',\r\n" + 
					"attach = '"+dto.getAttach()+"'\r\n" + 
					"where no = '"+dto.getNo()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("getPdsUpdate(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//삭제
	public int getPdsDelete(String no) {
		int result = 0;
		String query="delete from home_이주형_pds\r\n" + 
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
