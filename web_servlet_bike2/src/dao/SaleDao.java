package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnection;
import dto.ProductSaleDto;

public class SaleDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//판매 리스트
	public ArrayList<ProductSaleDto> getSaleList(String select, String search, String tstate){
		ArrayList<ProductSaleDto> arr = new ArrayList<>();
		String query = "select s_no, p_no, id, state, address, \r\n" + 
				"pay, price, to_char(reg_date,'yyyy-MM-dd hh24:mi:ss')as reg_date\r\n" + 
				"from bike_이주형_product_sale\r\n" + 
				"where "+select+" like '%"+search+"%' and state like '%"+tstate+"%'\r\n" + 
				"order by s_no desc";
		//System.out.println(query);
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String s_no = rs.getString("s_no");
				String p_no = rs.getString("p_no");
				String id = rs.getString("id");
				String state = rs.getString("state");
				String address = rs.getString("address");
				String pay = rs.getString("pay");
				String price = rs.getString("price");
				String reg_date = rs.getString("reg_date");
				
				ProductSaleDto dto = new ProductSaleDto(s_no, p_no, id, state, address, pay, price, reg_date);
				arr.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}

	//전체개수
	public int getTotalCount(String select, String search, String state) {
		int result = 0;
		String query = "select count(*) as count from bike_이주형_product_sale\r\n" + 
					"where "+select+" like '%"+search+"%' and state like '%"+state+"%'";
		//System.out.println(query);
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
	
	//개인멤버 오더리스트 전체갯수
	public int getTotalOrderCount(String select, String search, String id) {
		int result = 0;
		String query = "select count(*) as count \r\n" + 
				"from bike_이주형_product_sale s, bike_이주형_product p\r\n" + 
				"where s.p_no = p.no and "+select+" like '%"+search+"%' and s.id = '"+id+"' ";
		//System.out.println(query);
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
			}
		}catch(SQLException e) {
			System.out.println("getTotalOrderCount(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
		
	}
	//주문정보 상세보기
	public ProductSaleDto getSaleView(String no) {
		ProductSaleDto dto = new ProductSaleDto();
		String query="select s.s_no, s.p_no, s.id, s.state, s.address, s.pay, s.price, s.reg_date,\r\n" + 
				"m.name, m.mobile_1, m.mobile_2, m.mobile_3, p.title, p.attach\r\n" + 
				"from bike_이주형_product_sale s, bike_이주형_member m, bike_이주형_product p\r\n" + 
				"where s.id = m.id and s.p_no = p.no and s.s_no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				String s_no = rs.getString("s_no");
				String p_no = rs.getString("p_no");
				String id = rs.getString("id");
				String state = rs.getString("state");
				String address = rs.getString("address");
				String pay = rs.getString("pay");
				String price = rs.getString("price");
				String reg_date = rs.getString("reg_date");
				String name = rs.getString("name");
				String mobile_1 = rs.getString("mobile_1");
				String mobile_2 = rs.getString("mobile_2");
				String mobile_3 = rs.getString("mobile_3");
				String title = rs.getString("title");
				String attach = rs.getString("attach");
				
				dto = new ProductSaleDto(s_no, p_no, id, state, address, pay, price, reg_date, name, mobile_1, mobile_2, mobile_3, title, attach);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}

	//주문정보 진행상황 수정
	public int getStateUpdate(String no, String state) {
		int result = 0;
		String query = "update bike_이주형_product_sale\r\n" + 
				"set state = '"+state+"'\r\n" + 
				"where s_no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e){
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//개인회원 오더리스트
	public ArrayList<ProductSaleDto> getOrderList(String id, String select, String search){
		ArrayList<ProductSaleDto> arr = new ArrayList<>();
		String query = "select s.s_no, s.state, s.price, to_char(s.reg_date,'yy-MM-dd') as reg_date, \r\n" + 
				"p.title, p.attach\r\n" + 
				"from bike_이주형_product_sale s, bike_이주형_product p\r\n" + 
				"where s.p_no = p.no and s.id='"+id+"' and p.title like '%"+search+"%'";
		//System.out.println(query);
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String no = rs.getString("s_no");
				String state = rs.getString("state");
				String price = rs.getString("price");
				String reg_date = rs.getString("reg_date");
				String title = rs.getString("title");
				String attach = rs.getString("attach");
				
				ProductSaleDto dto = new ProductSaleDto(no, state, price, reg_date, title, attach);
				
				arr.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}
	
	//주문취소
	public int getSaleCancel(String no) {
		int result = 0;
		String query = "update bike_이주형_product_sale\r\n" + 
				"set state = '주문취소'\r\n" + 
				"where s_no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e){
			System.out.println(query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
}
