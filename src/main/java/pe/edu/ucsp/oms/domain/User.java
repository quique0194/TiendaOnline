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
	private int puntos;
	Content[] descargas;
	
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
	
}
