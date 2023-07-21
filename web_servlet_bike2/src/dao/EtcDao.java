package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.EtcDto;
import dto.FreeDto;
import dto.NoticeDto;

public class EtcDao {
	Connection 		  con = null;
	PreparedStatement ps = null;
	ResultSet 		  rs = null;
	
	
	
	//게시글 총 갯수
	public int getTotalCount(String select, String search) {
		int count = 0;
		String query = "select count(*) as count from home_이주형_notice n\r\n" + 
				"where "+select+" like '%"+search+"%'";
	
	try {
		con = DBConnection.getConnection();
		ps  = con.prepareStatement(query);
		rs  = ps.executeQuery();
		if(rs.next()) {
			count = rs.getInt("count");
		}
	}catch(SQLException e) {
		System.out.println(query);
		e.printStackTrace();
	}finally {
		DBConnection.closeDB(con, ps, rs);
	}
	
		return count;
	}


	//게시글 번호 생성
			public String getMaxNo(){
				String no = "";
				String query = "select nvl(max(no),'N000') as no\r\n" + 
							"from bike_이주형_etc";
				try {
					con = DBConnection.getConnection();
					ps  = con.prepareStatement(query);
					rs  = ps.executeQuery();
					if(rs.next()) {
						no = rs.getNString("no"); // N001
						no = no.substring(1); // 001
						int n = Integer.parseInt(no); // 1
						n++; // 2
						
						DecimalFormat df = new DecimalFormat("N000");
						no = df.format(n); // N002
					}
				}catch(SQLException e) {
					System.out.println("getMaxNo(): "+query);
					e.printStackTrace();
				}finally {
					DBConnection.closeDB(con, ps, rs);
				}
				
				return no;
			}

			//저장
			public int getSave(EtcDto dto) {
				int result = 0;
				String query = "insert into bike_이주형_etc\r\n" + 
						"(no, group_no, depth, title, content, reg_id, reg_date)\r\n" + 
						"values('"+dto.getNo()+"', '"+dto.getGroup_no()+"', '"+dto.getDepth()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"',\r\n" + 
						"'"+dto.getReg_id()+"',to_date('"+dto.getReg_date()+"','yyyy-MM-dd hh24:mi:ss'))";
				//System.out.println(query);
				try {
					con = DBConnection.getConnection();
					ps  = con.prepareStatement(query);
					result = ps.executeUpdate();
				}catch(SQLException e) {
					System.out.println("getSave(): "+query);
					e.printStackTrace();
				}finally {
					DBConnection.closeDB(con, ps, rs);
				}
				
				return result;
			}

			//리스트
			public ArrayList<EtcDto> getEtcList() {
				ArrayList<EtcDto> arr = new ArrayList<>();
				String query = "select e.no, e.group_no, e.depth, e.title, e.content, e.reg_id, \r\n" + 
						"to_char(e.reg_date,'yy-MM-dd hh:mi:ss')as reg_date, m.name\r\n" + 
						"from bike_이주형_etc e, bike_이주형_member m\r\n" + 
						"where e.reg_id = m.id order by e.no desc ";
				
				try {
					con = DBConnection.getConnection();
					ps  = con.prepareStatement(query);
					rs	= ps.executeQuery();
					while(rs.next()) {
						String no 		= rs.getNString("no");
						String group_no = rs.getString("group_no");
						int    depth 	= rs.getInt("depth");
						String title 	= rs.getString("title");
						String content  = rs.getString("content");
						String reg_id 	= rs.getString("reg_id");
						String reg_date = rs.getString("reg_date");
						String name 	= rs.getString("name");
						
						EtcDto dto = new EtcDto(no, group_no, title, content, reg_id, reg_date, name, depth);
						
						arr.add(dto);
						
					}
				}catch(SQLException e) {
					System.out.println("getEtcList(): "+query);
					e.printStackTrace();
				}finally {
					DBConnection.closeDB(con, ps, rs);
				}
				
				return arr;
			}

			//댓글 리스트
			public ArrayList<EtcDto> getComment() {
				ArrayList<EtcDto> arr = new ArrayList<>();
				String query = "select e.no, e.group_no, e.title, to_char(e.reg_date,'yy-MM-dd hh:mi:ss') as reg_date, m.name\r\n" + 
						"from bike_이주형_etc e, bike_이주형_member m\r\n" + 
						"where e.reg_id = m.id order by e.no desc";
				
				try {
					con = DBConnection.getConnection();
					ps  = con.prepareStatement(query);
					rs	= ps.executeQuery();
					while(rs.next()) {
						String no = rs.getString("no");
						String group_no = rs.getNString("group_no");
						String title = rs.getString("title");
						String reg_date = rs.getString("reg_date");
						String name = rs.getString("name");
						
						EtcDto dto = new EtcDto(no, title, group_no, name, reg_date );
						
						arr.add(dto);
					}
				}catch(SQLException e) {
					System.out.println("getComment(): "+query);
					e.printStackTrace();
				}finally {
					DBConnection.closeDB(con, ps, rs);
				}
				
				return arr;
			}
}
