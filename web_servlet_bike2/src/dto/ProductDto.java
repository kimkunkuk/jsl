package dto;

public class ProductDto {
	private String no, title, content, attach, reg_date, price, p_size, id, p_level;
	private int hit;
	
	
	//저장용
	public ProductDto(String no, String title, String content, String attach, String reg_date, String price,
			String p_size, String id, String p_level) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.reg_date = reg_date;
		this.price = price;
		this.p_size = p_size;
		this.id = id;
		this.p_level = p_level;
	}
	
	//프로덕트 게시글 리스트용
	public ProductDto(String no, String title, String attach, int hit, String p_level, String price) {
		super();
		this.no = no;
		this.title = title;
		this.attach = attach;
		this.hit = hit;
		this.p_level = p_level;
		this.price = price;
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
	public String getReg_date() {
		return reg_date;
	}
	public String getPrice() {
		return price;
	}
	public String getP_size() {
		return p_size;
	}
	public String getId() {
		return id;
	}
	public String getP_level() {
		return p_level;
	}
	public int getHit() {
		return hit;
	}
	
	
}
