package pe.edu.ucsp.oms.domain;

public class Payment implements BaseEntity<Long>{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String TABLE_NAME = "Payments";
	
	private Long idUser;
	private Long idContent;
	
	public Payment(){
	}

	@Override
	public Long getId() {
		return null;
	}

	@Override
	public void setId(Long id) {
		
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdContent() {
		return idContent;
	}

	public void setIdContent(Long idContent) {
		this.idContent = idContent;
	}

}
