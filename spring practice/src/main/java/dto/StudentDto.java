package dto;

public class StudentDto {
	private String syear, sclass, sno, name, osyear, osclass, osno, grade;
	private int kor, eng, mat, total;
	private double ave;
	//등록 생성자
	public StudentDto(String syear, String sclass, String sno, String name, int kor, int eng, int mat, int total, double ave, String grade) {
		super();
		this.syear = syear;
		this.sclass = sclass;
		this.sno = sno;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.total = total;
		this.ave = ave;
		this.grade = grade;
	}
	
	//뷰
	public StudentDto() {
		// TODO Auto-generated constructor stub
	}
	
	//수정
	public StudentDto(String syear, String sclass, String sno, String name, String osyear, String osclass, String osno,
			int kor, int eng, int mat, int total, double ave) {
		super();
		this.syear = syear;
		this.sclass = sclass;
		this.sno = sno;
		this.name = name;
		this.osyear = osyear;
		this.osclass = osclass;
		this.osno = osno;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.total = total;
		this.ave = ave;
	}
	
	public String getSyear() {
		return syear;
	}
	public String getOsyear() {
		return osyear;
	}
	public String getOsclass() {
		return osclass;
	}
	public String getOsno() {
		return osno;
	}
	public String getSclass() {
		return sclass;
	}
	public String getSno() {
		return sno;
	}
	public String getName() {
		return name;
	}
	public int getKor() {
		return kor;
	}
	public int getEng() {
		return eng;
	}
	public int getMat() {
		return mat;
	}
	public int getTotal() {
		return total;
	}
	public double getAve() {
		return ave;
	}
	public String getGrade() {
		return grade;
	}
}
