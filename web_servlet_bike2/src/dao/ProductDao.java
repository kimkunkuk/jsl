package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.ProductDto;

public class ProductDao {
Connection con = null;
PreparedStatement ps = null;
ResultSet rs = null;

	//게시글 번호 생성
	public String getMaxNo() {
		String no = "";
		String query = "select nvl(max(no),'P000') as no\r\n" + 
					"from bike_이주형_product";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				no = rs.getNString("no");
				no = no.substring(1);
				int n = Integer.parseInt(no);
				n++;
				
				DecimalFormat df = new DecimalFormat("P000");
				no = df.format(n);
				
			}
		}catch(SQLException e){
			System.out.println("getMaxNo():"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return no;
	}

	//프로덕트 게시글 저장
	public int getProductSave(ProductDto dto) {
		int result = 0;
		String query = "insert into bike_이주형_product\r\n" + 
					"(no, title, content, attach, reg_date, price, p_size, id, p_level)\r\n" + 
					"values('"+dto.getNo()+"','"+dto.getTitle()+"','"+dto.getContent()+"','"+dto.getAttach()+"',"
							+ "to_date('"+dto.getReg_date()+"', 'yyyy-MM-dd hh24:mi:ss'),\r\n" + 
					"'"+dto.getPrice()+"','"+dto.getP_size()+"','"+dto.getId()+"','"+dto.getP_level()+"')";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("getProductSave(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//프로덕트 게시글 리스트
	public ArrayList<ProductDto> getProductList(String select, String search, String level, int start, int end){
		ArrayList<ProductDto> arr = new ArrayList<>();
		String query = "select * from\r\n" + 
				"(select rownum rnum, tbl.* from \r\n" + 
				"(select no, title, attach, hit, p_level, price from bike_이주형_product\r\n" + 
				"where "+select+" like '%"+search+"%' and  p_level like '%"+level+"%')tbl)\r\n" + 
				"where rnum >= "+start+" and rnum <= "+end+"";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String no = rs.getString("no");
				String title = rs.getString("title");
				String attach = rs.getString("attach");
				int hit = rs.getInt("hit");
				String p_level = rs.getString("p_level");
				String price = rs.getNString("price");
				
				ProductDto dto = new ProductDto(no, title, attach, hit, p_level, price);
				
				arr.add(dto);
				
			}
		}catch(SQLException e) {
			System.out.println("getProductList(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}
	
	//프로덕트 게시글 전체수
	public int getTotalCount(String select, String search, String level) {
		int result = 0;
		String query = "select count(*) as count from bike_이주형_product\r\n" + 
					"where "+select+" like '%"+search+"%' and p_level like '%"+level+"%'";
		
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
