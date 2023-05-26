package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import common.DBConnection;
import dto.MemberDto;

public class MemberDao {
	Connection 		  con = null;
	PreparedStatement ps  = null;
	ResultSet 		  rs  = null;
	
	
	
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
    public String checkAccount(String id) {
    	String result = "";
    	String query = "select account from bike_이주형_member\r\n" + 
    				"where id = '"+id+"'";
    	
    	try {
    		con = DBConnection.getConnection();
    		ps  = con.prepareStatement(query);
    		rs  = ps.executeQuery();
    		if(rs.next()) {
    			result = rs.getNString("account");
    		}
    	}catch(SQLException e) {
    		System.out.println("checkAccount(): "+query);
    		e.printStackTrace();
    	}finally {
    		DBConnection.closeDB(con, ps, rs);
    	}
    	
    	return result;
    }
    
    //로그인 데이트
    public int setLoginTime(String id, String date) {
    	int result = 0;
    	String query = "update bike_이주형_member\r\n" + 
    			"set Login_date = to_date('"+date+"','yyyy-MM-dd hh24:mi:ss')\r\n" + 
    			"where id = '"+id+"'";
    	
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
    
    //멤버 인포 뷰
	public MemberDto getMemberInfo(String id) {
		MemberDto dto = null;
		String query = "select id, name, password, area, address, mobile_1, mobile_2, mobile_3, gender,\r\n" + 
				"hobby_travel, hobby_reading, hobby_sports, pwlen, \r\n" + 
				"to_char(reg_date,'yyyy-MM-dd hh24:mi:ss') as reg_date, \r\n" + 
				"to_char(update_date,'yyyy-MM-dd hh24:mi:ss') as update_date,\r\n" + 
				"to_char(login_date,'yyyy-MM-dd hh24:mi:ss') as login_date,\r\n" + 
				"to_char(account_del_date,'yyyy-MM-dd hh24:mi:ss') as account_del_date, \r\n" + 
				"memberlevel, account\r\n" + 
				"from bike_이주형_member \r\n" + 
				"where id = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				String name = rs.getNString("name");
				String area = rs.getNString("area");
				String pw = rs.getNString("password");
				String address = rs.getNString("address");
				String mobile_1 = rs.getNString("mobile_1");
				String mobile_2 = rs.getNString("mobile_2");
				String mobile_3 = rs.getNString("mobile_3");
				String gender = rs.getNString("gender");
				String hobby_t = rs.getNString("hobby_travel");
				String hobby_r = rs.getNString("hobby_reading");
				String hobby_s = rs.getNString("hobby_sports");
				int pwlen = rs.getInt("pwlen");
				String reg_date = rs.getNString("reg_date");
				String update_date = rs.getNString("update_date");
				String login_date = rs.getNString("login_date");
				String account_del_date = rs.getNString("account_del_date");
				String memberlevel = rs.getNString("memberlevel");
				String account = rs.getNString("account");
				
				dto = new MemberDto(id, name, pw, area, address, 
						mobile_1, mobile_2, mobile_3, gender, hobby_t, hobby_r, hobby_s, 
						reg_date, update_date, login_date, account_del_date, memberlevel, account, pwlen);
				
			}
		}catch(SQLException e){
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
	
		return dto;
	}
	
	//비밀번호 체크
	public int getPasswordCheck(String id, String pw) {
		int result = 0;
		String query = "select count(*) as count \r\n" + 
				"from bike_이주형_member\r\n" + 
				"where id='"+id+"' and password='"+pw+"'";
		
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
	
	//회원정보 수정
	public int memberUpdate(MemberDto dto) {
		int result = 0;
		String query = "update bike_이주형_member\r\n" + 
				"set name = '"+dto.getName()+"',\r\n" + 
				"area = '"+dto.getArea()+"',\r\n" + 
				"address = '"+dto.getAddress()+"',\r\n" + 
				"mobile_1 = '"+dto.getMobile_1()+"',\r\n" + 
				"mobile_2 = '"+dto.getMobile_2()+"',\r\n" + 
				"mobile_3 = '"+dto.getMobile_3()+"',\r\n" + 
				"gender = '"+dto.getGender()+"',\r\n" + 
				"hobby_travel = '"+dto.getHobby_t()+"',\r\n" + 
				"hobby_reading = '"+dto.getHobby_r()+"',\r\n" + 
				"hobby_sports = '"+dto.getHobby_s()+"',\r\n" + 
				"update_date = to_date('"+dto.getUpdate_date()+"','yyyy-MM-dd hh24:mi:ss')\r\n" + 
				"where id = '"+dto.getId()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("멤버수정"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	//회원탈퇴
	public int memberDelete(String id, String delDate) {
		int result = 0;
		String query = "update bike_이주형_member\r\n" + 
				"set account = 'n', account_del_date = to_date('"+delDate+"','yyyy-MM-dd hh24:mi:ss')  \r\n" + 
				"where id = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("회원탈퇴 memberDelete(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	//비밀번호 찾기
	public String getCheckMember(String id, String mobile_1, String mobile_2, String mobile_3, String email) {
		String name = "";
		String query = "select name from bike_이주형_member\r\n" + 
				"where id = '"+id+"' \r\n" + 
				"and mobile_1 = '"+mobile_1+"'\r\n" + 
				"and mobile_2 = '"+mobile_2+"'\r\n" + 
				"and mobile_3 = '"+mobile_3+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs	= ps.executeQuery();
			if(rs.next()) {
				name = rs.getString("name");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return name;
	}
	
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
	
	/*	새로운 비밀번호 생성*/
	public String getNewPassword(int pwLength) {
        StringBuffer temp =new StringBuffer();
        Random rnd = new Random();
        
        for(int i=0;i<pwLength;i++)
        {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
            case 0:
                // a-z
                temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                break;
            case 1:
                // A-Z
                temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                break;
            case 2:
                // 0-9
                temp.append((rnd.nextInt(10)));
                break;
            }
//            System.out.println("pw :"+temp.toString());	
        }
        return temp.toString();		
	}
	
	//메일 비밀번호 초기화, 비밀번호 수정
	public int setMemberPassword(String id, String newPassword, int pwlen) {
		int result = 0;
		String query = "update bike_이주형_member\r\n" + 
					"set password = '"+newPassword+"'\r\n" + 
					" ,pwlen = '"+pwlen+"'"+
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
	
	
}
