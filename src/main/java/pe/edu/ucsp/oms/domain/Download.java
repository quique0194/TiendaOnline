package pe.edu.ucsp.oms.domain;

public class Download implements BaseEntity<Long>{

	
	private static final long serialVersionUID = 1L;
	public final static String TABLE_NAME = "Downloads";

	
	private Long id;
	private String date;
	private Long idUser;
	private Long idContent;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
