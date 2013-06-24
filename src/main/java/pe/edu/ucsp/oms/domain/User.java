package pe.edu.ucsp.oms.domain;

public class User implements BaseEntity<Integer> {

	public final static String TABLE_NAME = "Users";

	private Integer id;

	private String username;
	private String password;
	private String firstName;	
	private String lastName;
	private String email;
	private Double balance;
	private int points;
	private Content[] downloads;
	
	public User() {
	}

	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id=id;	
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
}
