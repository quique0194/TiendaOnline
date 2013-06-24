package pe.edu.ucsp.oms.domain;

public class Content implements BaseEntity<Integer>{
	public static final String TABLE_NAME = "Contents";
	
	private Integer id;
	private String name;
	private String autor;
	private String description;
	private float price;
	private int size;
	private int vecesDescargado;
	private String category;
	private Promo promo;
	private String contentType;
	private String extension;
	private String mime;	
	
	private String content;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id=id;
		
	}
	
}
