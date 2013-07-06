package pe.edu.ucsp.oms.domain;

public class Content implements BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "Contents";
	
	private Long id;
	private String content;
	private String name;
	private String autor;
	private String description;
	private float price;
	private int size;
	private int timesDownload;
	private Long idCategory;
	private Long idPromo;	
	private Long idTypeFile;	
	

	@Override
	public String toString() {
		String str="";
		str+="ID: "+Long.toString(id)+"\n";
		str+="Name: "+getName()+"\n";
		str+="Autor: "+getAutor()+"\n";
		str+="Description: "+getDescription()+"\n";
		str+="Price: "+Float.toString(getPrice())+"\n";
		return str;
	}


	@Override
	public Long getId() {
		return id;
	}


	@Override
	public void setId(Long id) {
		this.id=id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public int getTimesDownload() {
		return timesDownload;
	}


	public void setTimesDownload(int timesDownload) {
		this.timesDownload = timesDownload;
	}


	public Long getIdCategory() {
		return idCategory;
	}


	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}


	public Long getIdPromo() {
		return idPromo;
	}


	public void setIdPromo(Long idPromo) {
		this.idPromo = idPromo;
	}


	public Long getIdTypeFile() {
		return idTypeFile;
	}


	public void setIdTypeFile(Long idTypeFile) {
		this.idTypeFile = idTypeFile;
	}








	
	
}
