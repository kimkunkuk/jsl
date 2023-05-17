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
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//로그인
	public String checkLogin(String id, String password) {
		String name = "";
		String query = "select name from home_이주형_member\r\n" + 
					"where id='"+id+"' and\r\n" + 
					"password='"+password+"'";
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
	
	//회원가입
	public int memberJoin(MemberDto dto) {
		int result = 0;
		String query ="insert into home_이주형_member\r\n" + 
					"(id, name, password, job, tell_1, tell_2, tell_3, mobile, email, reg_date, pwlen)\r\n" + 
					"values('"+dto.getId()+"','"+dto.getName()+"','"+dto.getPassword()+"','"+dto.getJob()+"',"
							+ "'"+dto.getTell_1()+"','"+dto.getTell_2()+"','"+dto.getTell_3()+"',"
							+ "'"+dto.getMobile()+"','"+dto.getEmail()+"',to_date('"+dto.getReg_date()+"','yyyy-MM-dd hh24:mi:ss'),'"+dto.getPwlen()+"')";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("memberJion(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	// 암호화
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
    
    //멤버뷰
    public MemberDto memberView(String id) {
    	MemberDto dto = null;
    	String query="select id, name, job,\r\n" + 
    				"tell_1, tell_2, tell_3,\r\n" + 
    				"mobile, email, to_char(reg_date,'yyyy-MM-dd') as reg_date\r\n" + 
    				"from home_이주형_member\r\n" + 
    				"where id = '"+id+"'";
    	try {
    		con = DBConnection.getConnection();
    		ps  = con.prepareStatement(query);
    		rs  = ps.executeQuery();
    		if(rs.next()) {
    			dto = new MemberDto(rs.getNString("id"), rs.getNString("name"), "", rs.getNString("job"), 
							rs.getNString("tell_1"), rs.getNString("tell_2"), rs.getNString("tell_3"),
							rs.getNString("mobile"), rs.getNString("email"), rs.getNString("reg_date"));
    		}
    	}catch(SQLException e) {
    		System.out.println(query);
    		e.printStackTrace();
    	}finally {
    		DBConnection.closeDB(con, ps, rs);
    	}
    	
    	return dto;
    }
    
    //수정,삭제 할때 비밀번호 확인 
    public int getCheckPassword(String id, String pw) {
    	int result = 0;
    	String query ="select count(*) as count from home_이주형_member\r\n" + 
    				"where id = '"+id+"' and password = '"+pw+"'";
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
    
    // 수정
    public int getMemberUpdate(MemberDto dto) {
    	int result = 0;
    	String query = "update home_이주형_member\r\n" + 
    				"set name='"+dto.getName()+"', \r\n" + 
    				"job='"+dto.getJob()+"', \r\n" + 
    				"tell_1='"+dto.getTell_1()+"', \r\n" + 
    				"tell_2='"+dto.getTell_2()+"', \r\n" + 
    				"tell_3='"+dto.getTell_3()+"',\r\n" + 
    				"mobile='"+dto.getMobile()+"',\r\n" + 
    				"email='"+dto.getEmail()+"'\r\n" + 
    				"where id ='"+dto.getId()+"'";
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
    public int getMemberDelete(String id, String date) {
    	int result = 0;
    	String query= "update home_이주형_member\r\n" + 
    				"set acount = 'n',\r\n" + 
    				"acount_del_date = to_date('"+date+"','yyyy-MM-dd hh24:mi:ss')\r\n" + 
    				"where id = '"+id+"'";
    	
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
    
    //ID 중복검사
    public int checkId(String id) {
    	int result = 0;
    	String query = "select count(*) as count from home_이주형_member\r\n" + 
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
    
    //로그인 날짜
    public void getLogin_date(String login_date, String id) {
    	String query = "update home_이주형_member\r\n" + 
    				"set login_date = to_date('"+login_date+"', 'yyyy-MM-dd hh24:mi:ss')\r\n" + 
    				"where id = '"+id+"'";
    	try {
    		con = DBConnection.getConnection();
    		ps  = con.prepareStatement(query);
    		int result = ps.executeUpdate();
    		if(result != 1) System.out.println("getLogin_date(): "+query);
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		DBConnection.closeDB(con, ps, rs);
    	}
    }
    
    //계정 유무(탈퇴확인)
    public String checkAcount(String id) {
    	String result = "";
    	String query = "select acount from home_이주형_member\r\n" + 
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
