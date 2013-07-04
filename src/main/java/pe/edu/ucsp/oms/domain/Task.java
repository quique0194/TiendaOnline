package pe.edu.ucsp.oms.domain;

public class Task implements BaseEntity<Long>{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	public final static String TABLE_NAME = "Tasks";
	private Long id;
	private String task;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;
		
	}
	
	public String getTask() {
		return task;
	}
	
	public void setTask(String task) {
		this.task = task;
	}
	
}
