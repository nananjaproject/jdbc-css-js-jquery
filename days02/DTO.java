package days02;

public class DTO {
	private int deptno;
	private String ename;
	private String job;
	
	

	public DTO(int deptno, String ename, String job) {
		super();
		this.deptno = deptno;
		this.ename = ename;
		this.job = job;
	}
	public DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	@Override
	public String toString() {
		return "DTO [deptno=" + deptno + ", ename=" + ename + ", job=" + job + "]";
	}
	
}
