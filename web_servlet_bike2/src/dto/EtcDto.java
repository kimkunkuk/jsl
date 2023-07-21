package dto;

public class EtcDto {
	private String no, group_no, title, content, reg_name, reg_id, reg_date;
	private int depth;
	
	
	public EtcDto() {
		super();
	}
	
	
	//게시글저장용
	public EtcDto(String no, String group_no,  int depth, String title, String content, String reg_id, String reg_date) {
		super();
		this.no = no;
		this.group_no = group_no;
		this.title = title;
		this.content = content;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
		this.depth = depth;
	}

	
	//리스트용
	public EtcDto(String no, String group_no, String title, String content,  String reg_id,
			String reg_date, String reg_name, int depth) {
		super();
		this.no = no;
		this.group_no = group_no;
		this.title = title;
		this.content = content;
		this.reg_name = reg_name;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
		this.depth = depth;
	}
	
	
	//댓글용
	public EtcDto(String no, String title, String group_no, String reg_name, String reg_date) {
		super();
		this.no = no;
		this.title = title;
		this.group_no = group_no;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
	}


	public String getNo() {
		return no;
	}
	public String getGroup_no() {
		return group_no;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getReg_name() {
		return reg_name;
	}
	public String getReg_id() {
		return reg_id;
	}
	public String getReg_date() {
		return reg_date;
	}
	public int getDepth() {
		return depth;
	}
	
	
}
