package dto;

public class ProductSaleDto {
	private String s_no, p_no, id, state, address, pay, price, reg_date, 
				   name, mobile_1, mobile_2, mobile_3, title, attach, card_1, card_2, card_3, card_4;

	
	public ProductSaleDto() {
		super();
	}
	
	//판매 리스트용
	public ProductSaleDto(String s_no, String p_no, String id, String state, String address, String pay, String price,
			String reg_date) {
		super();
		this.s_no = s_no;
		this.p_no = p_no;
		this.id = id;
		this.state = state;
		this.address = address;
		this.pay = pay;
		this.price = price;
		this.reg_date = reg_date;
	}

	//저장용
	public ProductSaleDto(String s_no, String p_no, String id, String state, String address, String pay, String price,
			String reg_date, String name, String mobile_1, String mobile_2, String mobile_3, 
			String card_1, String card_2, String card_3, String card_4) {
		super();
		this.s_no = s_no;
		this.p_no = p_no;
		this.id = id;
		this.state = state;
		this.address = address;
		this.pay = pay;
		this.price = price;
		this.reg_date = reg_date;
		this.name = name;
		this.mobile_1 = mobile_1;
		this.mobile_2 = mobile_2;
		this.mobile_3 = mobile_3;
		this.card_1 = card_1;
		this.card_2 = card_2;
		this.card_3 = card_3;
		this.card_4 = card_4;
	}

	
	//주문정보 상세보기용
	public ProductSaleDto(String s_no, String p_no, String id, String state, String address, String pay, String price,
			String reg_date, String name, String mobile_1, String mobile_2, String mobile_3, String title,
			String attach) {
		super();
		this.s_no = s_no;
		this.p_no = p_no;
		this.id = id;
		this.state = state;
		this.address = address;
		this.pay = pay;
		this.price = price;
		this.reg_date = reg_date;
		this.name = name;
		this.mobile_1 = mobile_1;
		this.mobile_2 = mobile_2;
		this.mobile_3 = mobile_3;
		this.title = title;
		this.attach = attach;
	}
	
	//개인 오더리스트 용
	public ProductSaleDto(String s_no, String state, String price, String reg_date, String title, String attach) {
		super();
		this.s_no = s_no;
		this.state = state;
		this.price = price;
		this.reg_date = reg_date;
		this.title = title;
		this.attach = attach;
	}
	
	
	public String getS_no() {
		return s_no;
	}

	public String getP_no() {
		return p_no;
	}

	public String getId() {
		return id;
	}

	public String getState() {
		return state;
	}

	public String getAddress() {
		return address;
	}

	public String getPay() {
		return pay;
	}

	public String getPrice() {
		return price;
	}

	public String getReg_date() {
		return reg_date;
	}


	public String getName() {
		return name;
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


	public String getTitle() {
		return title;
	}


	public String getAttach() {
		return attach;
	}


	public String getCard_1() {
		return card_1;
	}


	public String getCard_2() {
		return card_2;
	}


	public String getCard_3() {
		return card_3;
	}


	public String getCard_4() {
		return card_4;
	}
	
	
}
