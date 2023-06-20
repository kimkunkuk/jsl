package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.MemberDto;

public class AdminDao {
	Connection 		  con = null;
	PreparedStatement ps  = null;
	ResultSet 		  rs  = null;
	
	//멤버 리스트 조회
	public ArrayList<MemberDto> getMemberList(String select, String search, int start, int end){
		ArrayList<MemberDto> arr = new ArrayList<MemberDto>();
		String query = "select * from\r\n" + 
				"(select rownum as rnum, tbl.* from \r\n" + 
				"(select id, name, area, mobile_1, mobile_2, mobile_3,\r\n" + 
				"to_char(reg_date,'yyyy-MM-dd hh24:mi:ss') as reg_date, \r\n" + 
				"to_char(login_date,'yyyy-MM-dd hh24:mi:ss') as login_date,\r\n" + 
				"account from bike_이주형_member\r\n" + 
				"where "+select+" like '%"+search+"%'\r\n" + 
				"order by reg_date desc)tbl)\r\n" + 
				"where rnum >= "+start+" and rnum <= "+end+"";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getNString("id");
				String name = rs.getNString("name");
				String area = rs.getNString("area");
				String mobile_1 = rs.getNString("mobile_1");
				String mobile_2 = rs.getNString("mobile_2");
				String mobile_3 = rs.getNString("mobile_3");
				String reg_date = rs.getNString("reg_date");
				String login_date = rs.getNString("login_date");
				String account = rs.getNString("account");
				
				MemberDto dto = new MemberDto(id, name, area, mobile_1, mobile_2, mobile_3, reg_date, login_date, account);
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
	
	//게시글 갯수 조회
	public int getTotalCount(String select, String search) {
		int result = 0;
		String query = "select count(*) as count from bike_이주형_member\r\n" + 
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
	

}
