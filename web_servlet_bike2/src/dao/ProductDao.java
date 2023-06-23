package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.NewsDto;
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
				"(select no, title, attach, hit, p_level, price, to_char(reg_date,'yy/MM/dd hh24:mi:ss' )as reg_date from bike_이주형_product\r\n" + 
				"where "+select+" like '%"+search+"%' and  p_level like '%"+level+"%' order by reg_date desc)tbl)\r\n" + 
				"where rnum >= "+start+" and rnum <= "+end+"";
		//System.out.println(query);
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
	
	//게시글 뷰
	public ProductDto getProductView(String no) {
		ProductDto dto = new ProductDto();
		String query = "select p.no, p.title, p.content, p.attach, to_char(p.reg_date,'yyyy/MM/dd hh24:mi:ss' )as reg_date,\r\n" + 
				"p.price, p.hit, p.p_size, p.id, p.p_level, m.name \r\n" + 
				"from bike_이주형_product p, bike_이주형_member m\r\n" + 
				"where p.id = m.id and no = '"+no+"'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getNString("content");
				String attach = rs.getNString("attach");
				String reg_date = rs.getNString("reg_date");
				String price = rs.getString("price");
				int hit = rs.getInt("hit");
				String p_size = rs.getString("p_size");
				String id = rs.getNString("id");
				String p_level = rs.getString("p_level");
				String name  = rs.getNString("name");

				dto = new ProductDto(no, title, content, attach, reg_date, price, p_size, id, p_level, name, hit);
				
			}
		}catch(SQLException e) {
			System.out.println("getProductView(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//조회수 증가
	public void getHitCount(String no) {
		String query="update bike_이주형_product\r\n" + 
				"set hit = hit+1\r\n" + 
				"where no = '"+no+"'";
	try {
		con = DBConnection.getConnection();
		ps  = con.prepareStatement(query);
		int result = ps.executeUpdate();
		if(result != 1) System.out.println("bike product 조회수 오류");
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		DBConnection.closeDB(con, ps, rs);
	}
		
	}
	
	//다음글 이전글
		public ProductDto getPreTitle(String no) {
			ProductDto dto = new ProductDto();
			String query ="select  a.no, b.title\r\n" + 
						"from (select max(no) as no from bike_이주형_product where no < '"+no+"') a, bike_이주형_product b\r\n" + 
						"where a.no = b.no";
			
			try {
				con = DBConnection.getConnection();
				ps  = con.prepareStatement(query);
				rs  = ps.executeQuery();
				if(rs.next()) {
					String NO = rs.getNString("no");
					String title = rs.getNString("title");
					
					dto = new ProductDto(NO, title);
				}
			}catch(SQLException e) {
				System.out.println("getPreTitle() "+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return dto;
		}
		
		public ProductDto getNextTitle(String no) {
			ProductDto dto = new ProductDto();
			String query ="select  a.no, b.title\r\n" + 
						"from (select min(no) as no from bike_이주형_product where no > '"+no+"') a, bike_이주형_product b\r\n" + 
						"where a.no = b.no";
			
			try {
				con = DBConnection.getConnection();
				ps  = con.prepareStatement(query);
				rs  = ps.executeQuery();
				if(rs.next()) {
					String NO = rs.getNString("no");
					String title = rs.getNString("title");
					
					dto = new ProductDto(NO, title);
				}
			}catch(SQLException e) {
				System.out.println("getNextTitle() "+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return dto;
		}

		//수정
		public int getUpdate(ProductDto dto) {
			int result = 0;
			String query = "update bike_이주형_product\r\n" + 
					"set title = '"+dto.getTitle()+"',\r\n" + 
					"content = '"+dto.getContent()+"',\r\n" + 
					"attach = '"+dto.getAttach()+"',\r\n" + 
					"price = '"+dto.getPrice()+"',\r\n" + 
					"p_size = '"+dto.getP_size()+"',\r\n" + 
					"p_level = '"+dto.getP_level()+"'\r\n" + 
					//"reg_date = to_date('"+dto.getReg_date()+"','yyyy-MM-dd hh24:mi:ss')\r\n" + 
					"where no = '"+dto.getNo()+"'";
			try {
				con = DBConnection.getConnection();
				ps  = con.prepareStatement(query);
				result = ps.executeUpdate();
			}catch(SQLException e) {
				System.out.println("getUpdate(): "+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return result;
		}
		
		//삭제
		public int getDelete(String no) {
			int result = 0;
			String query = "delete from bike_이주형_product\r\n" + 
						"where no = '"+no+"'";
			
			try {
				con = DBConnection.getConnection();
				ps  = con.prepareStatement(query);
				result = ps.executeUpdate();
			}catch(SQLException e) {
				System.out.println("getDelete(): "+query);
				e.printStackTrace();
			}finally {
				DBConnection.closeDB(con, ps, rs);
			}
			
			return result;
		}
}
