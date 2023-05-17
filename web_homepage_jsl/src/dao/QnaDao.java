package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.QnaDto;


public class QnaDao {
Connection con 		 = null;
PreparedStatement ps = null;
ResultSet rs 		 = null;

	//게시글 번호생성
	public String getMaxNo() {
		String no = "";
		String query ="select nvl(max(no), 'N000') as no\r\n" + 
					"from home_이주형_qna";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				no = rs.getNString("no");
				no = no.substring(1);
				int iNo = Integer.parseInt(no);
				iNo++;
				
				DecimalFormat df = new DecimalFormat("N000");
				no = df.format(iNo);
			}
		}catch(SQLException e) {
			System.out.println("getMaxNo(): "+query);
			e.printStackTrace();
		}finally {
			
		}
		return no;
	}
	
	//저장
	public int getQnaSave(QnaDto dto) {
		int result = 0;
		String query="insert into home_이주형_qna\r\n" + 
					"(no, title, content, reg_id, reg_date)\r\n" + 
					"values\r\n" + 
					"('"+dto.getNo()+"','"+dto.getTitle()+"','"+dto.getContent()+"',"
							+ "'"+dto.getReg_id()+"',to_date('"+dto.getReg_date()+"','yyyy-MM-dd hh24:mi:ss'))";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("getQnaSave(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//게시물 갯수 조회
	public int getTotalCount(String select, String search) {
		int result = 0;
		String query = "select count(*) as count from home_이주형_qna q\r\n" + 
					"where "+select+" like '%"+search+"%'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
			}
		}catch(SQLException e) {
			System.out.println("getTotalCount(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//페이징 조회
	public ArrayList<QnaDto> getQnaListPage(String select, String search, int start, int end){
		ArrayList<QnaDto> arr = new ArrayList<QnaDto>();
		String query = "select * from\r\n" + 
					"(select rownum rnum , tbl.* from\r\n" + 
					"(select q.no, q.title, q.answer, m.name, to_char(q.reg_date, 'yy/MM/dd') as reg_date, q.hit\r\n" + 
					"from home_이주형_qna q, home_이주형_member m\r\n" + 
					"where q.reg_id = m.id and "+select+" like '%"+search+"%'\r\n" + 
					"order by q.no desc) tbl)\r\n" + 
					"where rnum >= "+start+" and rnum <= "+end+"";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String no = rs.getNString("no");
				String title = rs.getNString("title");
				String name = rs.getNString("name");
				String reg_date = rs.getNString("reg_date");
				int hit = rs.getInt("hit");
				String answer = rs.getNString("answer");
				
				QnaDto dto = new QnaDto(no, title, name, reg_date, hit, answer);
				arr.add(dto);
			}
		}catch(SQLException e) {
			System.out.println("getQnaListPage(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}
	
	//전체리스트 조회
	public ArrayList<QnaDto> getQnaList(String select, String search){
		ArrayList<QnaDto> arr = new ArrayList<QnaDto>();
		String query ="select q.no, q.title, m.name, to_char(q.reg_date,'yyyy-MM-dd')as reg_date, q.hit, q.answer\r\n" + 
					"from home_이주형_qna q, home_이주형_member m\r\n" + 
					"where q.reg_id = m.id and "+select+" like '%"+search+"%'"+
					"order by no desc";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String no = rs.getNString("no");
				String title = rs.getNString("title");
				String name = rs.getNString("name");
				String reg_date = rs.getNString("reg_date");
				int hit = rs.getInt("hit");
				String answer = rs.getNString("answer");
				
				QnaDto dto = new QnaDto(no, title, name, reg_date, hit, answer);
				arr.add(dto);
				System.out.println(query);
			}
		}catch(SQLException e) {
			System.out.println("getQnaSave(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return arr;
	}
	
	//상세보기 뷰
	public QnaDto getQnaView(String no) {
		QnaDto dto = new QnaDto();
		String query = "select q.title, m.name, q.content,\r\n" + 
					"to_char(q.reg_date,'yy/MM/dd hh24:mi:ss') as reg_date, \r\n" + 
					"to_char(q.qna_date,'yy/MM/dd hh24:mi:ss') as qna_date,\r\n" + 
					"q.reg_id, q.hit, q.answer\r\n" + 
					"from home_이주형_qna q , home_이주형_member m\r\n" + 
					"where q.reg_id = m.id and q.no = '"+no+"'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
			//System.out.println(query);	
				String title = rs.getNString("title");
				String name = rs.getNString("name");
				String content = rs.getNString("content");
				String reg_date = rs.getNString("reg_date");
				String qna_date = rs.getNString("qna_date");
				int hit = rs.getInt("hit");
				String reg_id = rs.getNString("reg_id");
				String answer = rs.getNString("answer");
				
				dto = new QnaDto(title, name, content, reg_date, qna_date, hit, reg_id, answer);
				
			}
		}catch(SQLException e) {
			System.out.println("getQnaView(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//조회수 증가
	public void getHit(String no) {
		String query="update home_이주형_qna\r\n" + 
					"set hit = hit+1\r\n" + 
					"where no ='"+no+"'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			int result = ps.executeUpdate();
			if(result != 1) System.out.println("QnA 조회수 오류!!!");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
	}
	
	//이전글 다음글
	public QnaDto getPreView(String no) {
		QnaDto dto = new QnaDto();
		String query = "select a.no, b.title\r\n" + 
					"from(select max(no) as no from home_이주형_qna where no < '"+no+"') a, home_이주형_qna b\r\n" + 
					"where a.no = b.no";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				String NO = rs.getNString("no");
				String title = rs.getNString("title");
				
				dto = new QnaDto(NO, title);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	public QnaDto getNextView(String no) {
		QnaDto dto = new QnaDto();
		String query = "select a.no, b.title\r\n" + 
					"from(select min(no) as no from home_이주형_qna where no > '"+no+"') a, home_이주형_qna b\r\n" + 
					"where a.no = b.no";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				String NO = rs.getNString("no");
				String title = rs.getNString("title");
				
				dto = new QnaDto(NO, title);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dto;
	}
	
	//답변저장
	public int getSaveAnswer(String no, String answer, String qna_date) {
		int result = 0;
		String query ="update home_이주형_qna\r\n" + 
					"set answer = '"+answer+"',\r\n" + 
					"qna_date = to_date('"+qna_date+"', 'yyyy-MM-dd hh24:mi;ss')\r\n" + 
					"where no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("getSaveAnswer(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//큐앤에이 수정 
	public int getQnaUpdate(String title, String content, String no) {
		int result = 0;
		String query = "update home_이주형_qna\r\n" + 
					"set title = '"+title+" ',\r\n" + 
					"content = '"+content+"'\r\n" + 
					"where no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("getQnaUpdate(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//큐앤에이 삭제
	public int getQnaDelete(String no) {
		int result = 0;
		String query = "delete from home_이주형_qna\r\n" + 
					"where no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("getQnaDelete(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
	
	//답변삭제
	public int getAnswerDelete(String no) {
		int result = 0;
		String query = "update home_이주형_qna\r\n" + 
					"set answer = null,\r\n" + 
					"qna_date = null\r\n" + 
					"where no = '"+no+"'";
		
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("getAnswerDelete(): "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}
}
