package dto;

public class FreeDto {
	private String no, title, content, attach, reg_id, reg_date, update_date, name;
	private int hit, dw_hit;
	
	
	public FreeDto() {
		super();
	}

	//리스트 용
	public FreeDto(String no, String title, String reg_date, String name, int hit) {
		super();
		this.no = no;
		this.title = title;
		this.reg_date = reg_date;
		this.name = name;
		this.hit = hit;
	}

	//글 저장용
	public FreeDto(String no, String title, String content, String attach, String reg_date, String reg_id) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
	}
	
	//글 상세보기용
	public FreeDto(String title, String content, String attach, String reg_date, String update_date, 
			int hit, int dw_hit, String name, String reg_id, String no) {
		super();
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.reg_date = reg_date;
		this.update_date = update_date;
		this.name = name;
		this.hit = hit;
		this.dw_hit = dw_hit;
		this.reg_id = reg_id;
		this.no = no;
	}
	
	//수정용
	public FreeDto(String no, String title, String content, String attach, String update_date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.update_date = update_date;
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
	public String getReg_date() {
		return reg_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public String getName() {
		return name;
	}
	public int getHit() {
		return hit;
	}
	public int getDw_hit() {
		return dw_hit;
	}
	
	
}
