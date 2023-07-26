package days03.board;

import java.sql.Date;

public class BoardDTO {
	
	private int seq;
	private String writer;
	private String pwd;
	private String email;
	private String title;
	private Date writedate;
	private int readed;
	private int tag;
	private String content;
	
	
	public BoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BoardDTO(int seq, String writer, String email, String title, Date writedate, int readed) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.email = email;
		this.title = title;
		this.writedate = writedate;
		this.readed = readed;

	}


	public BoardDTO(int seq, String writer, String pwd, String email, String title, Date writedate, int readed, int tag,
			String content) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.pwd = pwd;
		this.content = content;
		this.email = email;
		this.title = title;
		this.writedate = writedate;
		this.readed = readed;
		this.tag = tag;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Date getWritedate() {
		return writedate;
	}


	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}


	public int getReaded() {
		return readed;
	}


	public void setReaded(int readed) {
		this.readed = readed;
	}


	public int getTag() {
		return tag;
	}


	public void setTag(int tag) {
		this.tag = tag;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return "BoardDTO [seq=" + seq + ", writer=" + writer + ", pwd=" + pwd + ", email=" + email + ", title=" + title
				+ ", writedate=" + writedate + ", readed=" + readed + ", tag=" + tag + ", content=" + content + "]";
	}
	 
	
	
	
	

}
