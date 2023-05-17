package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.AdminDto;
import dto.FaqDto;

public class AdminDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//암호화
	public String encryptSHA256(String value) throws NoSuchAlgorithmException{
        String encryptData ="";
         
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        sha.update(value.getBytes());
 
        byte[] digest = sha.digest();
        for (int i=0; i<digest.length; i++) {
            encryptData += Integer.toHexString(digest[i] &0xFF).toUpperCase();
        }
         
        return encryptData;
    }
	
	//아이디 체크
	public String checkId(String id, String pw) {
		String name = "";
		String query = "select name from home_이주형_member\r\n" + 
					"where id='"+id+"' and\r\n" + 
					"password='"+pw+"'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				name = rs.getNString("name");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return name;
	}
	
	//게시물 갯수 조회
			public int getTotalCount(String select, String search) {
				int result = 0;
				String query = "select count(*) as count from home_이주형_member \r\n" + 
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
			public ArrayList<AdminDto> getMemberListPage(String select, String search, int start, int end){
				ArrayList<AdminDto> arr = new ArrayList<AdminDto>();
				String query = "select * from\r\n" + 
							"(select rownum rnum , tbl.* from\r\n" + 
							"(select id, name, mobile, email, to_char(reg_date, 'yyyy-MM-dd hh24:mi:ss') as reg_date, acount from home_이주형_member \r\n" + 
							"where "+select+" like '%"+search+"%'" +  
							"order by reg_date desc) tbl)\r\n" + 
							"where rnum >= "+start+" and rnum <= "+end+"";
				
				try {
					con = DBConnection.getConnection();
					ps  = con.prepareStatement(query);
					rs = ps.executeQuery();
					while(rs.next()) {
						String id = rs.getNString("id");
						String name = rs.getNString("name");
						String mobile = rs.getNString("mobile");
						String email = rs.getNString("email");
						String reg_date = rs.getNString("reg_date");
						String acount = rs.getNString("acount");
						
						AdminDto dto = new AdminDto(id, name, mobile, email, reg_date, acount);
						arr.add(dto);
					}
				}catch(SQLException e) {
					System.out.println("getMemberListPage(): "+query);
					e.printStackTrace();
				}finally {
					DBConnection.closeDB(con, ps, rs);
				}
				
				return arr;
			}		
			
	//회원 리스트 조회
	public ArrayList<AdminDto> getMemberList(String select, String search){
		ArrayList<AdminDto> arr = new ArrayList<AdminDto>();
		String query = "select id, name, mobile, email, to_char(reg_date, 'yyyy-MM-dd hh24:mi:ss') as reg_date, acount from home_이주형_member\r\n"+
					"where "+select+" like '%"+search+"%'" + 
					"order by reg_date desc";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getNString("id");
				String name = rs.getNString("name");
				String mobile = rs.getNString("mobile");
				String email = rs.getNString("email");
				String reg_date = rs.getNString("reg_date");
				String acount = rs.getNString("acount");
				
				AdminDto dto = new AdminDto(id, name, mobile, email, reg_date, acount);
				arr.add(dto);
			}
			
		}catch(SQLException e) {
			System.out.println("getMemberList(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}
	
	//회원정보 조회
	public AdminDto getMemberView(String id) {
		AdminDto dto = new AdminDto();
		String query = "select id, name, mobile, email, password, to_char(reg_date,'yyyy-MM-dd hh24:mi:ss') as reg_date,\r\n" + 
					"job, tell_1, tell_2, tell_3, to_char(login_date,'yyyy-MM-dd hh24:mi:ss') as login_date, pwlen, acount,\r\n" + 
					"to_char(acount_del_date,'yyyy-MM-dd hh24:mi:ss') as acount_del_date\r\n" + 
					"from home_이주형_member\r\n" + 
					"where id = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				String name = rs.getNString("name");
				String mobile = rs.getNString("mobile");
				String email = rs.getNString("email");
				String reg_date = rs.getNString("reg_date");
				String pw = rs.getNString("password");
				String job = rs.getNString("job");
				String tell_1 = rs.getNString("tell_1");
				String tell_2 = rs.getNString("tell_2");
				String tell_3 = rs.getNString("tell_3");
				String login_date = rs.getNString("login_date");
				int pwlen = rs.getInt("pwlen");
				String acount = rs.getNString("acount");
				String del_date = rs.getNString("acount_del_date");
				
				dto = new AdminDto(id, name, mobile, email, reg_date, pw, tell_1, tell_2, tell_3, job, login_date, pwlen, acount, del_date);
				
			}
		}catch(SQLException e) {
			System.out.println("getMemberView(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
}
