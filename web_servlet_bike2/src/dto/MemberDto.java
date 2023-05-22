package dto;

public class MemberDto {
	private String id, name, pw, area, address, mobile_1, mobile_2, mobile_3, gender, hobby_t, hobby_r, hobby_s, 
				   reg_date, update_date, login_date, account_del_date, memberlevel, account;
	
	private int pwlen;
	
	
	
	public MemberDto() {
		super();
	}

	
	
	//회원 수정용
	public MemberDto(String id, String name, String area, String address, String mobile_1, String mobile_2,
			String mobile_3, String gender, String hobby_t, String hobby_r, String hobby_s, String update_date) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.address = address;
		this.mobile_1 = mobile_1;
		this.mobile_2 = mobile_2;
		this.mobile_3 = mobile_3;
		this.gender = gender;
		this.hobby_t = hobby_t;
		this.hobby_r = hobby_r;
		this.hobby_s = hobby_s;
		this.update_date = update_date;
	}




	//회원가입용
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
	
	
	//멤버 인포용
	public MemberDto(String id, String name, String pw, String area, String address, String mobile_1, String mobile_2,
			String mobile_3, String gender, String hobby_t, String hobby_r, String hobby_s, String reg_date,
			String update_date, String login_date, String account_del_date, String memberlevel, String account, int pwlen) {
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
		this.update_date = update_date;
		this.login_date = login_date;
		this.account_del_date = account_del_date;
		this.memberlevel = memberlevel;
		this.account = account;
		this.pwlen = pwlen;
		
	}

	//로그인용
	public MemberDto(String name, String memberlevel) {
		super();
		this.name = name;
		this.memberlevel = memberlevel;
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

	public String getUpdate_date() {
		return update_date;
	}

	public String getLogin_date() {
		return login_date;
	}

	public String getMemberlevel() {
		return memberlevel;
	}
	
	public String getAccount() {
		return account;
	}
	
	public String getAccount_del_date() {
		return account_del_date;
	}
}
