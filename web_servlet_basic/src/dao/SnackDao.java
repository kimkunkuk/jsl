package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.SnackDto;

public class SnackDao {
Connection con = null;
PreparedStatement ps = null;
ResultSet rs = null;
	//등록할때 옵션 회사목록 
	public ArrayList<SnackDto> getCompanyList(){
		ArrayList<SnackDto> arr = new ArrayList<>();
		String query = "select m_code, m_name\r\n" + 
					"from commonsnack\r\n" + 
					"order by m_name";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String m_code = rs.getNString("m_code");
				String m_name = rs.getNString("m_name");
				
				SnackDto dto = new SnackDto(m_code, m_name);
				arr.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}
	
	//등록
	public int snackSave(SnackDto dto) {
		int result = 0;
		String query = "insert into h_이주형_snack\r\n" + 
					"values ('"+dto.getP_code()+"', '"+dto.getP_name()+"', '"+dto.getM_code()+"', "+dto.getPrice()+")";
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
	
	//조회 , 검색
	public ArrayList<SnackDto> getSnackList(String gubun, String search){
		ArrayList<SnackDto> arr = new ArrayList<>();
		String query = "select s.p_code, s.p_name, c.m_name, to_char(s.price,'999,999') as price\r\n" + 
					"from h_이주형_snack s, commonsnack c \r\n" + 
					"where s.m_code = c.m_code and "+gubun+" like '%"+search+"%'\r\n" + 
					"order by p_code";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String p_code = rs.getNString("p_code");
				String p_name = rs.getNString("p_name");
				String m_name = rs.getNString("m_name");
				String price = rs.getNString("price");
				SnackDto dto = new SnackDto(p_code, p_name, m_name, price);
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
	
	//뷰
	public SnackDto getSnackView(String code) {
		SnackDto dto = new SnackDto();
		String query="select s.p_code, s.p_name, c.m_code, c.m_name, to_char(s.price, '999,999') as price\r\n" + 
					"from h_이주형_snack s, commonsnack c\r\n" + 
					"where s.m_code = c.m_code and p_code = '"+code+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				String p_code = rs.getNString("p_code");
				String p_name = rs.getNString("p_name");
				String m_name = rs.getNString("m_name");
				String sprice = rs.getNString("price");
				String m_code = rs.getNString("m_code");
				dto = new SnackDto(p_code, p_name, m_name, sprice, m_code);
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
		
	}
	
	//수정
	public int getSnackUpdate(SnackDto dto) {
		int result = 0;
		String query = "update h_이주형_snack\r\n" + 
					"set p_name = '"+dto.getP_name()+"',\r\n" + 
					"m_code = '"+dto.getM_code()+"',\r\n" + 
					"price = "+dto.getPrice()+"\r\n" + 
					"where p_code ='"+dto.getP_code()+"'";
		System.out.println(query);
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
	
	//삭제
	public int getDelete(String code) {
		int result = 0;
		String query="delete from h_이주형_snack\r\n" + 
					"where p_code ='"+code+"'";
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
	
	//등록 중복체크
	public int snakcSaveCount(String code) {
		int result = 0;
		String query = "select count(*) as count\r\n" + 
					"from h_이주형_snack\r\n" + 
					"where p_code ='"+code+"'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				int count = rs.getInt("count");
				result = count;
			};
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
		return result;
	}
}
