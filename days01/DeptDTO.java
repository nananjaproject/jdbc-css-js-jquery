package days01;

public class DeptDTO {
	
	private int deptno;
	private String dname;
	private String loc;
	
	
	// 생성자
	public DeptDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeptDTO(int deptno, String dname, String loc) {		// default 생성자 생성.
		super();
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}
	
	// getter, setter 생성			Alt + Shift + S
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}

	@Override
	public String toString() {
		return "DeptDTO [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "]";
	}
	
	
	
	
	

}
