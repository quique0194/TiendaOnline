package pe.edu.ucsp.oms.domain;


public class Puntuation implements BaseEntity<Long>{
	private static final long serialVersionUID = 1L;
	public final static String TABLE_NAME = "Puntuations";
	
	private Long idContent;
	private Long idUser;
	private Integer val; 
	

	public Long getIdContent() {
		return idContent;
	}

	public void setIdContent(Long idContent) {
		this.idContent = idContent;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	public Integer getVal() {
		return val;
	}
	
	public void setVal(Integer val) {
		this.val = val;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}
}
