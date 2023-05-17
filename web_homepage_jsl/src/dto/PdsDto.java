package dto;

public class PdsDto {
	private String no, title, content, attach, reg_id, reg_name, reg_date;
	private int hit;
	
	public PdsDto() {
		super();
	}

	//게시글저장용
	public PdsDto(String no, String title, String content, String attach, String reg_id, String reg_date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
	}
	
	//게시글 리스트, 뷰 조회용
	public PdsDto(String no, String title, String attach, String content, String reg_name, String reg_date, int hit) {
		super();
		this.no = no;
		this.title = title;
		this.attach = attach;
		this.content = content;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
		this.hit = hit;
	}
	
	//이전글, 다음글용
	public PdsDto(String no, String title) {
		super();
		this.no = no;
		this.title = title;
	}
	
	//수정용
	public PdsDto(String no, String title, String content, String attach) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
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
	public String getAttach() {
		return attach;
	}
	public String getReg_id() {
		return reg_id;
	}
	public String getReg_name() {
		return reg_name;
	}
	public String getReg_date() {
		return reg_date;
	}
	public int getHit() {
		return hit;
	}
	
	
	
}
