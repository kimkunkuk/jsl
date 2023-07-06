package dto;

public class ProductDto {
	private String no, title, content, attach, reg_date, price, p_size, id, p_level, name, address, mobile_1, mobile_2, mobile_3;
	private int hit, count;
	
	
	public ProductDto() {
		super();
	}
	
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
	
	//인덱스 프로덕트용
	public ProductDto(String no, String title, String attach, String p_level, String price) {
		super();
		this.no = no;
		this.title = title;
		this.attach = attach;
		this.p_level = p_level;
		this.price = price;
	}
	
	//게시글 상세보기 용
	public ProductDto(String no, String title, String content, String attach, String reg_date, String price,
			String p_size, String id, String p_level, String name, int hit, String address,
			String mobile_1, String mobile_2, String mobile_3) {
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
		this.name = name;
		this.hit = hit;
		this.address = address;
		this.mobile_1 = mobile_1;
		this.mobile_2 = mobile_2;
		this.mobile_3 = mobile_3;
	}
	
	//이전글, 다음글 용
	public ProductDto(String no, String title) {
		super();
		this.no = no;
		this.title = title;
	}

	//수정용
	public ProductDto(String no, String title, String content, String attach, String reg_date, String price,
			String p_size, String p_level) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.reg_date = reg_date;
		this.price = price;
		this.p_size = p_size;
		this.p_level = p_level;
	}

	//물건구매시 회원정보용
	public ProductDto(String id, String name, String address, String mobile_1, String mobile_2, String mobile_3) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.mobile_1 = mobile_1;
		this.mobile_2 = mobile_2;
		this.mobile_3 = mobile_3;
	}
	
	//판매현황 리스트용
	public ProductDto(String reg_date, String price, int count) {
		super();
		this.reg_date = reg_date;
		this.price = price;
		this.count = count;
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
	public String getName() {
		return name;
	}
	public int getHit() {
		return hit;
	}
	public String getAddress() {
		return address;
	}

	public String getMobile_1() {
		return mobile_1;
	}

	public String getMobile_2() {
		return mobile_2;
	}

	public String getMobile_3() {
		return mobile_3;
	}

	public int getCount() {
		return count;
	}
	
	
}
