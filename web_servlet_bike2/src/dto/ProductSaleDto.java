package dto;

public class ProductSaleDto {
	private String s_no, p_no, id, state, address, pay, price, reg_date;

	
	public ProductSaleDto() {
		super();
	}
	
	
	//저장용
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
	
	
}
