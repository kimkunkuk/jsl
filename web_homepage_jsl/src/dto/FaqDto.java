package dto;

public class FaqDto {
	private String no, title, content, reg_id, reg_date, reg_name;

	
	
	public FaqDto() {
		super();
	}


	//저장용
	public FaqDto(String no, String title, String content, String reg_id, String reg_date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
	}
	
	
	//게시글 리스트 조회용
	public FaqDto(String no, String title, String content, String reg_date, String reg_name, String reg_id) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
		this.reg_name = reg_name;
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

	public String getReg_name() {
		return reg_name;
	}
	
	
}
