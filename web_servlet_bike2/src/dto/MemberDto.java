package dto;

public class MemberDto {
	private String id, name, pw, area, address, mobile_1, mobile_2, mobile_3, gender, hobby_t, hobby_r, hobby_s, 
				   reg_date;
	
	private int pwlen;
	
	

	public MemberDto(String id, String name, String pw, String area, String address, String mobile_1, String mobile_2,
			String mobile_3, String gender, String hobby_t, String hobby_r, String hobby_s, String reg_date,
			int pwlen) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.area = area;
		this.address = address;
		this.mobile_1 = mobile_1;
		this.mobile_2 = mobile_2;
		this.mobile_3 = mobile_3;
		this.gender = gender;
		this.hobby_t = hobby_t;
		this.hobby_r = hobby_r;
		this.hobby_s = hobby_s;
		this.reg_date = reg_date;
		this.pwlen = pwlen;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPw() {
		return pw;
	}

	public String getArea() {
		return area;
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

	public String getGender() {
		return gender;
	}

	public String getHobby_t() {
		return hobby_t;
	}

	public String getHobby_r() {
		return hobby_r;
	}

	public String getHobby_s() {
		return hobby_s;
	}

	public String getReg_date() {
		return reg_date;
	}

	public int getPwlen() {
		return pwlen;
	}
	
	
}
