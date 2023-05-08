package dto;

public class SnackDto {
	private String p_code, p_name, m_code, m_name, sprice;
	private int price;
	
	//뷰
	public SnackDto() {
		super();
	}

	//제조사 
	public SnackDto(String m_code, String m_name) {
		super();
		this.m_code = m_code;
		this.m_name = m_name;
	}
	
	//등록
	public SnackDto(String p_code, String p_name, int price, String m_code) {
		super();
		this.p_code = p_code;
		this.p_name = p_name;
		this.m_code = m_code;
		this.price = price;
	}
	//수정
	
	//목록조회
	public SnackDto(String p_code, String p_name, String m_name, String sprice) {
		super();
		this.p_code = p_code;
		this.p_name = p_name;
		this.m_name = m_name;
		this.sprice = sprice;
	}
	
	//상세조회
	public SnackDto(String p_code, String p_name, String m_name, String sprice, String m_code) {
		super();
		this.p_code = p_code;
		this.p_name = p_name;
		this.m_name = m_name;
		this.sprice = sprice;
		this.m_code = m_code;
	}
	
	public String getP_code() {
		return p_code;
	}

	public String getP_name() {
		return p_name;
	}
	public String getM_code() {
		return m_code;
	}
	public String getM_name() {
		return m_name;
	}
	public int getPrice() {
		return price;
	}
	public String getSprice() {
		return sprice;
	}

	
	
	
	
	
}
