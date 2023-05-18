package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DBConnection;
import dto.MemberDto;

public class MemberDao {
	Connection 		  con = null;
	PreparedStatement ps  = null;
	ResultSet 		  rs  = null;
	
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
	
	//회원등록
	public int memberSave(MemberDto dto) {
		int result = 0;
		String query = "insert into bike_이주형_member\r\n" + 
				"(id, name, password, area, address, mobile_1, mobile_2, mobile_3, gender,\r\n" + 
				"hobby_travel, hobby_reading, hobby_sports, pwlen, reg_date)\r\n" + 
				"values('"+dto.getId()+"','"+dto.getName()+"','"+dto.getPw()+"', '"+dto.getArea()+"', "
						+ "'"+dto.getAddress()+"', '"+dto.getMobile_1()+"', '"+dto.getMobile_2()+"', '"+dto.getMobile_3()+"', '"+dto.getGender()+"',\r\n" + 
				"'"+dto.getHobby_t()+"', '"+dto.getHobby_r()+"', '"+dto.getHobby_s()+"', '"+dto.getPwlen()+"', to_date('"+dto.getReg_date()+"','yyyy-MM-dd hh24:mi:ss'))";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	 //ID 중복검사
    public int checkId(String id) {
    	int result = 0;
    	String query = "select count(*) as count from bike_이주형_member\r\n" + 
    			 	"where id ='"+id+"'";
    	try {
    		con = DBConnection.getConnection();
    		ps  = con.prepareStatement(query);
    		rs  = ps.executeQuery();
    		if(rs.next()) {
    			int count = rs.getInt("count");
    			result = count;
    		}
    	}catch(SQLException e) {
    		System.out.println("오류 getCheckPassword(): "+query);
    		e.printStackTrace();
    	}finally {
    		DBConnection.closeDB(con, ps, rs);
    	}
    	return result;
    }
    
    //로그인
    public MemberDto checkLogin(String id, String pw) {
    	//MemberDto dto = new MemberDto();
    	MemberDto dto = null;
    	String query = "select name, memberlevel from bike_이주형_member\r\n" + 
    				"where id='"+id+"' and password = '"+pw+"'";
    	
    	try {
    		con = DBConnection.getConnection();
    		ps  = con.prepareStatement(query);
    		rs  = ps.executeQuery();
    		if(rs.next()) {
    			String name = rs.getNString("name");
    			String level = rs.getString("memberlevel");
    			dto = new MemberDto(name, level);
    			
    		}
    	}catch(SQLException e) {
    		System.out.println("checkLogin(): "+query);
    		e.printStackTrace();
    	}finally {
    		DBConnection.closeDB(con, ps, rs);
    	}
    	
    	return dto;
    }
    
    //계정 유무(탈퇴확인)
    public String checkAcount(String id) {
    	String result = "";
    	String query = "select acount from bike_이주형_member\r\n" + 
    				"where id = '"+id+"'";
    	
    	try {
    		con = DBConnection.getConnection();
    		ps  = con.prepareStatement(query);
    		rs  = ps.executeQuery();
    		if(rs.next()) {
    			result = rs.getNString("acount");
    		}
    	}catch(SQLException e) {
    		System.out.println("checkAcount(): "+query);
    		e.printStackTrace();
    	}finally {
    		DBConnection.closeDB(con, ps, rs);
    	}
    	
    	return result;
    }
    
}
