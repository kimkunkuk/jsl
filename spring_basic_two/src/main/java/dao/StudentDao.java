package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.StudentDto;
import oracle.sql.ARRAY;

public class StudentDao {
Connection con = null;
PreparedStatement ps = null;
ResultSet rs = null;
	
	//등록
	public int studentSave(StudentDto dto){
		int result = 0;
		String query = "insert into h_이주형_student\r\n" + 
					"values('"+dto.getSyear()+"','"+dto.getSclass()+"','"+dto.getSno()+"','"+dto.getName()+"',\r\n" + 
					""+dto.getKor()+","+dto.getEng()+","+dto.getMat()+","+dto.getTotal()+","+dto.getAve()+",'"+dto.getGrade()+"')";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("studentSave(): "+query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	//중복체크 1
	public int getCount1(String sno, String syear, String sclass) {
		int result = 0;
		String query="select count(*) as count from h_이주형_student\r\n" + 
					"where sno = '"+sno+"' and sclass = '"+sclass+"' and syear='"+syear+"'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				int count = rs.getInt("count");
				result = count;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	//중복체크2
	public int getCount2(String name, int ikor, int ieng, int imat) {
		int result = 0;
		String query = "select count(*) as count \r\n" + 
					"from h_이주형_student\r\n" + 
					"where name = '"+name+"' and kor = "+ikor+" and eng = "+ieng+" and mat = "+imat+"";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				int count = rs.getInt("count");
				result = count;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	//조회
	public ArrayList<StudentDto> getStudentList(String gubun1, String gubun2, String search){
		ArrayList<StudentDto> arr = new ArrayList<StudentDto>();
		String query="";
		if(gubun1.equals("all")) {
			query="select * from h_이주형_student\r\n" + 
					  //"where "+gubun2+" like '%"+search+"%'  \r\n" + 
					  "order by syear, sclass, to_number(sno)";
		}else {
			query="select * from h_이주형_student\r\n" + 
					  "where syear ='"+gubun1+"' and "+gubun2+" like '%"+search+"%'  \r\n" + 
					  "order by syear, sclass, to_number(sno)";
		}
		  
//		  String query= "select * from h_이주형_student\r\n";  
//		  if(!gubun1.equals("all")) {
//		  query = query + "where syear ='"+gubun1+"'";
//		  }
//		  query = query + "order by syear, sclass, to_number(sno)";
		  
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				StudentDto dto = new StudentDto(rs.getNString("syear"), rs.getNString("sclass"), rs.getNString("sno"),
												rs.getNString("name"), rs.getInt("kor"), rs.getInt("eng"),rs.getInt("mat"),
												rs.getInt("total"), rs.getDouble("ave"),rs.getNString("grade")	);
				
				arr.add(dto);
			}
		}catch(SQLException e) {
			System.out.println("getStudentList() "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return arr;
	}
	
	//뷰
	public StudentDto getView(String sno, String syear, String sclass) {
		StudentDto dto = new StudentDto();
		String query ="select * from h_이주형_student\r\n" + 
					"where sno = '"+sno+"' and syear ='"+syear+"' and sclass='"+sclass+"'";
		System.out.println(query);
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				dto = new StudentDto(rs.getNString("syear"), rs.getNString("sclass"), rs.getNString("sno"),
									 rs.getNString("name"), rs.getInt("kor"), rs.getInt("eng"),rs.getInt("mat"),
									 rs.getInt("total"), rs.getDouble("ave"),rs.getNString("grade")	);
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//수정
	public int studentUpdate(StudentDto dto) {
		int result = 0;
		String query= "update h_이주형_student\r\n" + 
				"set syear = '"+dto.getSyear()+"',\r\n" + 
				"sclass = '"+dto.getSclass()+"',\r\n" + 
				"sno = '"+dto.getSno()+"',\r\n" + 
				"name = '"+dto.getName()+"',\r\n" + 
				"kor = "+dto.getKor()+",\r\n" + 
				"eng = "+dto.getEng()+",\r\n" + 
				"mat = "+dto.getMat()+",\r\n" + 
				"total = "+dto.getTotal()+",\r\n" + 
				"ave = "+dto.getAve()+"\r\n" + 
				"where syear = '"+dto.getOsyear()+"' and sclass = '"+dto.getOsclass()+"' and sno = '"+dto.getOsno()+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("studentUpdate() "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
			
		return result;
	}
	
	//삭제
	public int studentDelete(String sno, String syear, String sclass) {
		int result = 0;
		String query="delete from h_이주형_student\r\n" + 
					"where syear ='"+syear+"' and sclass = '"+sclass+"' and sno = '"+sno+"'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("studentDelte(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}
	
	//반조회
	public ArrayList<StudentDto> getClassList(String gubun1, String gubun, String gubun2, String search){
		ArrayList<StudentDto> arr = new ArrayList<StudentDto>();
		String query = "select * from h_이주형_student\r\n" + 
					"where syear = '"+gubun1+"' and sclass = '"+gubun+"' and "+gubun2+" like '%"+search+"%'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				StudentDto dto = new StudentDto(rs.getNString("syear"), rs.getNString("sclass"), rs.getNString("sno"),
												rs.getNString("name"), rs.getInt("kor"), rs.getInt("eng"),rs.getInt("mat"),
												rs.getInt("total"), rs.getDouble("ave")	,rs.getNString("grade")					);
				
				arr.add(dto);
			}
		}catch(SQLException e) {
			System.out.println("getStudentList() "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return arr;
	}
}
