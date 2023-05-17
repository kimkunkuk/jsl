package dto;

public class AdminDto {
	private String id, name, mobile, email, reg_date, pw, tell_1, tell_2, tell_3, job, login_date, acount, del_date;
	private int pwlen;
	
	public AdminDto() {
		super();
	}
	
	//전체 회원리스트 조회용
	public AdminDto(String id, String name, String mobile, String email, String reg_date, String acount) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.reg_date = reg_date;
		this.acount = acount;
	}

	//멤버 조회용
	public AdminDto(String id, String name, String mobile, String email, String reg_date, String pw, String tell_1,
			String tell_2, String tell_3, String job, String login_date, int pwlen, String acount, String del_date) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.reg_date = reg_date;
		this.pw = pw;
		this.tell_1 = tell_1;
		this.tell_2 = tell_2;
		this.tell_3 = tell_3;
		this.job = job;
		this.login_date = login_date;
		this.pwlen = pwlen;
		this.acount = acount;
		this.del_date = del_date;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public String getReg_date() {
		return reg_date;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getTell_1() {
		return tell_1;
	}

	public void setTell_1(String tell_1) {
		this.tell_1 = tell_1;
	}

	public String getTell_2() {
		return tell_2;
	}

	public void setTell_2(String tell_2) {
		this.tell_2 = tell_2;
	}

	public String getTell_3() {
		return tell_3;
	}

	public void setTell_3(String tell_3) {
		this.tell_3 = tell_3;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getLogin_date() {
		return login_date;
	}

	public int getPwlen() {
		return pwlen;
	}

	public String getAcount() {
		return acount;
	}

	public String getDel_date() {
		return del_date;
	}
	
	
}
