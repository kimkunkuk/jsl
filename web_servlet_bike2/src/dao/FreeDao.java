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
		public ArrayList<FreeDto> getFreeList() {
			ArrayList<FreeDto> arr = new ArrayList<>();
			String query = "select f.title, to_char(f.reg_date,'yy-MM-dd') as reg_date, f.hit, m.name\r\n" + 
						"from bike_이주형_freeboard f, bike_이주형_member m\r\n" + 
						"where f.reg_id = m.id";
			
			try {
				con = DBConnection.getConnection();
				ps  = con.prepareStatement(query);
				rs	= ps.executeQuery();
				while(rs.next()) {
					String title = rs.getString("title");
					String reg_date = rs.getString("reg_date");
					int hit = rs.getInt("hit");
					String name = rs.getString("name");
					
					FreeDto dto = new FreeDto(title, reg_date, name, hit);
					
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
}
