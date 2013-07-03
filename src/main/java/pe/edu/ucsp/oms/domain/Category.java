package pe.edu.ucsp.oms.domain;

public class Category implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String TABLE_NAME = "Categories";
	
	private Long id;

	private String name;

	private Long idParent;
	
	public Long getIdParent(){
		return idParent;
	}
	
	public void setIdParent(Long idParent){
		this.idParent = idParent;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	
}
