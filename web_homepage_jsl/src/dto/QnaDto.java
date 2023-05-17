package dto;

public class QnaDto {
	private String no, title, content, reg_id, reg_date, qna_date, reg_name, answer;
	private int hit;
	
	
	
	public QnaDto() {
		super();
	}


	//저장용
	public QnaDto(String no, String title, String content, String reg_id, String reg_date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
	}
	
	
	//리스트 전체 조회용
	public QnaDto(String no, String title, String reg_name, String reg_date, int hit, String answer) {
		super();
		this.no = no;
		this.title = title;
		this.reg_date = reg_date;
		this.reg_name = reg_name;
		this.answer = answer;
		this.hit = hit;
	}

	//상세보기 용
	public QnaDto(String title, String reg_name, String content, String reg_date, String qna_date,  int hit, String reg_id ,String answer
			) {
		super();
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
		this.qna_date = qna_date;
		this.reg_name = reg_name;
		this.answer = answer;
		this.reg_id = reg_id;
		this.hit = hit;
	}
	
	//이전글 다음글용
	public QnaDto(String no, String title) {
		super();
		this.no = no;
		this.title = title;
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
	public String getReg_id() {
		return reg_id;
	}
	public String getReg_date() {
		return reg_date;
	}
	public String getQna_date() {
		return qna_date;
	}
	public String getReg_name() {
		return reg_name;
	}
	public String getAnswer() {
		return answer;
	}
	public int getHit() {
		return hit;
	}
	
	
	
}
