package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.MemberDto;

public class Memberdao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//멤버등록
	public int memberSave(MemberDto dto){
		int result = 0;
		String query="insert into h_이주형_member\r\n" + 
					"(id, name, age, reg_date)\r\n" + 
					"values\r\n" + 
					"('"+dto.getId()+"','"+dto.getName()+"',"+dto.getAge()+",'"+dto.getReg_date()+"')";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("memberSave(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	//멤버목록조회
	public ArrayList<MemberDto> getMemberList(String gubun, String search){
		ArrayList<MemberDto> dtos = new ArrayList<>();

//select id, name, age, to_char(reg_date, 'yyyy-MM-dd')as reg_date from h_이주형_member
//order by reg_date desc
		
		String query="select id, name, age, to_char(reg_date, 'yyyy-MM-dd')as reg_date from h_이주형_member\r\n" + 
					" where "+gubun+" like '%"+search+"%'"+
					" order by reg_date desc";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getNString("id");
				String name = rs.getNString("name");
				int age = rs.getInt("age");
				String reg_date = rs.getNString("reg_date");
				
				MemberDto dto = new MemberDto(id, name, age, reg_date);
				dtos.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		
		return dtos;
		
	}
	// 멤버뷰 조회
	public MemberDto getMemberView(String id) {
		MemberDto dto = new MemberDto();
		String query = "select name, age, to_char(reg_date, 'yyyy-MM-dd') as reg_date from h_이주형_member\r\n" + 
					"where id = '"+id+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				String name = rs.getNString("name");
				int age = rs.getInt("age");
				String reg_date = rs.getNString("reg_date");
				
				dto = new MemberDto(id, name, age, reg_date);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	//멤버 수정
	public int memberUpdate(MemberDto dto) {
		int result = 0;
		String query ="update h_이주형_member\r\n" + 
					"set name = '"+dto.getName()+"',\r\n" + 
					"age="+dto.getAge()+",\r\n" + 
					"reg_date='"+dto.getReg_date()+"'\r\n" + 
					"where id = '"+dto.getId()+"'";
		
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
	//멤버 삭제
	public int getDelete(String id) {
		int result = 0;
		String query ="delete from h_이주형_member\r\n" + 
					"where id ='"+id+"'";
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