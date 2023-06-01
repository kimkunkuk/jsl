package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.EtcDto;
import dto.NoticeDto;

public class EtcDao {
	Connection 		  con = null;
	PreparedStatement ps = null;
	ResultSet 		  rs = null;
	
	//etc list조회
	public ArrayList<EtcDto> getEtcList(String select, String search, int start, int end){
		ArrayList<EtcDto> arr = new ArrayList<>();
		String query = "select * from\r\n" + 
				"(select rownum as rnum, tbl.* from \r\n" + 
				"(select n.no, n.title, n.attach, m.name, to_char(n.reg_date,'yy/MM/dd' )as reg_date, n.hit\r\n" + 
				"from home_이주형_notice n , home_이주형_member m\r\n" + 
				"where n.reg_id = m.id and "+select+" like '%"+search+"%'\r\n" + 
				"order by no desc)tbl)\r\n" + 
				"where rnum >= "+start+" and rnum <= "+end+"";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String attach	= rs.getNString("attach");
				String no 	 	= rs.getNString("no");
				String title 	= rs.getNString("title");
				String name  	= rs.getNString("name");
				String reg_date = rs.getNString("reg_date");
				int    hit 	 	= rs.getInt("hit");
				
				EtcDto dto = new EtcDto(no, title, name, reg_date, hit, attach);
				
				arr.add(dto);
				
			}
		}catch(SQLException e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}
	
	//게시글 총 갯수
	public int getTotalCount(String select, String search) {
		int count = 0;
		String query = "select count(*) as count from home_이주형_notice n\r\n" + 
				"where "+select+" like '%"+search+"%'";
	
	try {
		con = DBConnection.getConnection();
		ps  = con.prepareStatement(query);
		rs  = ps.executeQuery();
		if(rs.next()) {
			count = rs.getInt("count");
		}
	}catch(SQLException e) {
		System.out.println(query);
		e.printStackTrace();
	}finally {
		DBConnection.closeDB(con, ps, rs);
	}
	
		return count;
	}
}
