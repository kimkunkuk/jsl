package dto;

public class FreeDto {
	private String no, title, content, attach, reg_id, reg_date, update_date, name;
	private int hit, dw_hit;
	
	
	//리스트 용
	public FreeDto(String title, String reg_date, String name, int hit) {
		super();
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
