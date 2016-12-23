package id.ac.its.depandi.dynamic_srs.core;

public class SRS {
	
	private int srs_id;
	private int user_id;
	private String srs_name;
	
	public int getSrs_id() {
		return srs_id;
	}
	
	public void setSrs_id(int srs_id) {
		this.srs_id = srs_id;
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String getSrs_name() {
		return srs_name;
	}
	public String getUser_name() {
		return srs_name;
	}
	
	public void setSrs_name(String srs_name) {
		this.srs_name = srs_name;
	}
	
	public SRS(int user_id, String srs_name){
		super();
		this.user_id = user_id;
		this.srs_name = srs_name;
	}

	@Override
	public String toString() {
		return "SRS [srs_id=" + srs_id + ", user_id=" + user_id + ", srs_name=" + srs_name + "]";
	}

}
