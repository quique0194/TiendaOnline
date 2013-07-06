package pe.edu.ucsp.oms.domain;

public class Promo implements BaseEntity<Long>{
		
	private static final long serialVersionUID = 1L;
	public final static String TABLE_NAME = "Promos";
	
	private Long id;
	private String startDate;
	private String endDate;
	private Integer percent; 
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
