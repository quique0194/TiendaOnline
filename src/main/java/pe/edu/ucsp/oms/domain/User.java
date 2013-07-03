package pe.edu.ucsp.oms.domain;

public class User extends Person implements BaseEntity<Long> {

	
	private static final long serialVersionUID = 1L;


	public final static String TABLE_NAME = "Users";

	
	private Double balance;
	private boolean state;
	private int points;
	private Content[] downloads;
	
	
	public User() {
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Content[] getDownloads() {
		return downloads;
	}

	public void setDownloads(Content[] downloads) {
		this.downloads = downloads;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
}
