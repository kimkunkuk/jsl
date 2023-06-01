package dto;

public class EtcDto {
	public String no, title, content, reg_date, update_date, reg_name, attach;
	public int hit;
	
	//목록조회용
	public EtcDto(String no, String title, String reg_name, String reg_date, int hit, String attach) {
		super();
		this.no = no;
		this.title = title;
		this.attach = attach;
		this.reg_date = reg_date;
		this.reg_name = reg_name;
		this.hit = hit;
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
	public String getAttach() {
		return attach;
	}
	
	
}
