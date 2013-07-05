package pe.edu.ucsp.oms.domain;

public class Promo implements BaseEntity<Integer>{
		
	private static final long serialVersionUID = 1L;
	public final static String TABLE_NAME = "Promos";
	
	private Integer id;
	private String startDate;
	private String endDate;
	private Integer percent; 
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public Integer getPercent() {
		return percent;
	}
	
	public void setPercent(Integer percent) {
		this.percent = percent;
	}
}
