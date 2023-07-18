package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.FreeDto;
import dto.NoticeDto;

public class FreeDao {
	Connection 		  con = null;
	PreparedStatement ps = null;
	ResultSet 		  rs = null;
	
	//게시글 번호 생성
		public String getMaxNo(){
			String no = "";
			String query = "select nvl(max(no),'N000') as no\r\n" + 
						"from bike_이주형_freeboard";
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

	//게시글 저장	
		public int getFreeSave(FreeDto dto) {
			int result = 0;
			String query = "insert into bike_이주형_freeboard\r\n" + 
					"(no, title, content, attach, reg_id, reg_date)\r\n" + 
					"values('"+dto.getNo()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"', '"+dto.getAttach()+"', '"+dto.getReg_id()+"', "
							+ "to_date('"+dto.getReg_date()+"', 'yyyy-MM-dd hh24:mi:ss'))";
			
			try {
				con = DBConnection.getConnection();
				ps  = con.prepareStatement(query);
				result = ps.executeUpdate();
			}catch(SQLException e) {
				System.out.println("getFreeSave(): "+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return result;
		}
		
	//프리보드 리스트
		public ArrayList<FreeDto> getFreeList(String select, String search, int start, int end) {
			ArrayList<FreeDto> arr = new ArrayList<>();
			String query = "select * from\r\n" + 
					"(select rownum as rnum, tbl.* from\r\n" + 
					"(select f.no, f.title, to_char(f.reg_date,'yy-MM-dd') as reg_date, f.hit, m.name\r\n" + 
					"from bike_이주형_freeboard f, bike_이주형_member m\r\n" + 
					"where f.reg_id = m.id and "+select+" like '%"+search+"%'\r\n" + 
					"order by f.no desc)tbl)\r\n" + 
					"where rnum >= "+start+" and rnum <= "+end+"";

			try {
				con = DBConnection.getConnection();
				ps  = con.prepareStatement(query);
				rs	= ps.executeQuery();
				while(rs.next()) {
					String no = rs.getNString("no");
					String title = rs.getString("title");
					String reg_date = rs.getString("reg_date");
					int hit = rs.getInt("hit");
					String name = rs.getString("name");
					
					FreeDto dto = new FreeDto(no, title, reg_date, name, hit);
					
					arr.add(dto);
					
				}
			}catch(SQLException e) {
				System.out.println("getFreeList(): "+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return arr;
		}
		
		//게시글 갯수 조회
		public int getTotalCount(String select, String search) {
			int result = 0;
			String query = "select count(*) as count from bike_이주형_freeboard f\r\n" + 
						"where "+select+" like '%"+search+"%'";
			
			try {
				con = DBConnection.getConnection();
				ps  = con.prepareStatement(query);
				rs  = ps.executeQuery();
				if(rs.next()) {
					result = rs.getInt("count");
				}
			}catch(SQLException e) {
				System.out.println(query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return result;
		}
		
		//조회수 증가
		public void getHitCount(String no) {
			String query="update bike_이주형_freeboard\r\n" + 
					"set hit = hit+1\r\n" + 
					"where no = '"+no+"'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			int result = ps.executeUpdate();
			if(result != 1) System.out.println("bike freeboard 조회수 오류");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
			
		}

		//프리보드 상세보기
		public FreeDto getFreeView(String no) {
			FreeDto dto = new FreeDto();
			String query = "select f.title, f.content, f.attach, \r\n" + 
					"to_char(f.reg_date,'yyyy-MM-dd hh24:mi:ss') as reg_date,\r\n" + 
					"to_char(f.update_date,'yyyy-MM-dd hh24:mi:ss') as update_date,\r\n" + 
					"f.hit, f.download_hit, m.name\r\n" + 
					"from bike_이주형_freeboard f, bike_이주형_member m\r\n" + 
					"where f.reg_id =  m.id and f.no = '"+no+"'";
			
			try {
				con = DBConnection.getConnection();
				ps  = con.prepareStatement(query);
				rs	= ps.executeQuery();
				if(rs.next()) {
					String title = rs.getString(1);
					String content = rs.getString(2);
					String attach = rs.getString(3);
					String reg_date = rs.getString(4);
					String update_date = rs.getString(5);
					int hit = rs.getInt(6);
					int dw_hit = rs.getInt(7);
					String name = rs.getString(8);
					
					dto = new FreeDto(title, content, attach, reg_date, update_date, hit, dw_hit, name);
					
				}
			}catch(SQLException e) {
				System.out.println("getFreeList(): "+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return dto;
		}
}
