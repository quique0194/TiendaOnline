package pe.edu.ucsp.oms.domain;


public class Administrator extends Person implements BaseEntity<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String TABLE_NAME = "Administrator";

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;		
	}

	

}