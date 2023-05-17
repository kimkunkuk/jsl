package dto;

public class NewsDto {
	private String no, title, content, reg_id, reg_date, update_date , reg_name;
	private int hit;
	
	
	public NewsDto() {
		super();
	}

	//게시글저장용
	public NewsDto(String no, String title, String content, String reg_id, String reg_date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
	}
	
	//뉴스 전체리스트용
	public NewsDto(String no, String title, String reg_date, String reg_name, int hit) {
		super();
		this.no = no;
		this.title = title;
		this.reg_date = reg_date;
		this.reg_name = reg_name;
		this.hit = hit;
	}
	
	//상세조회용
	public NewsDto(String no, String title, String content, String reg_date, String update_date, String reg_name,
			int hit) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
		this.update_date = update_date;
		this.reg_name = reg_name;
		this.hit = hit;
	}
	
	//수정용
	public NewsDto(String no, String title, String content, String update_date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.update_date = update_date;
	}
	
	//이전글 다음글용
	public NewsDto(String no, String title) {
		super();
		this.no = no;
		this.title = title;
	}

	public String getNo() {
		return no;
	}

	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getReg_id() {
		return reg_id;
	}
	public String getReg_date() {
		return reg_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public String getReg_name() {
		return reg_name;
	}
	public int getHit() {
		return hit;
	}
	
	
}
