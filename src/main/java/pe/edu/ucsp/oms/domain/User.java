package pe.edu.ucsp.oms.domain;


/**
 * 
 * @author jneyra
 *
 */
public class User implements BaseEntity<Long> {

	public final static String TABLE_NAME = "Users";

	private Long id;

	private String username;

	private String firstName;
	
	private String lastName;

	private String password;

	private Role role;

	private String email;

	private Double amount;

	public User() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "{id: " +  getId()+ ", firstName: " + firstName + ", lastName: " + lastName + ", email: " + email + "}";
	}

	public String getEmail() {
	  return email;
  }

	public void setEmail(String email) {
	  this.email = email;
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

	public String getUsername() {
	  return username;
  }

	public void setUsername(String userName) {
	  this.username = userName;
  }

	public String getPassword() {
	  return password;
  }

	public void setPassword(String password) {
	  this.password = password;
  }

	public Role getRole() {
	  return role;
  }

	public void setRole(Role role) {
	  this.role = role;
  }

	public Double getAmount() {
	  return amount;
  }

	public void setAmount(Double amount) {
	  this.amount = amount;
  }
}
