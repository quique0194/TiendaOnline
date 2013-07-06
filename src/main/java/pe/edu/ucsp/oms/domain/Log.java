package pe.edu.ucsp.oms.domain;

public class Log implements BaseEntity<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static String TABLE_NAME = "Logs_administrator";
	
	private Long date;
	
	private String detail;
	
	private Long id_administrator;
	
	private Long id_task;
	
	public Long getDate() {
		return date;
	}


	public void setDate(Long date) {
		this.date = date;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public Long getId_administrator() {
		return id_administrator;
	}


	public void setId_administrator(Long id_administrator) {
		this.id_administrator = id_administrator;
	}


	public Long getId_task() {
		return id_task;
	}


	public void setId_task(Long id_task) {
		this.id_task = id_task;
	}
	
	@Override
	public Long getId() {
		return null;
	}

	@Override
	public void setId(Long id) {
	}
}
