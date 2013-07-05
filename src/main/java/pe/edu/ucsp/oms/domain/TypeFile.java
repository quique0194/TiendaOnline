package pe.edu.ucsp.oms.domain;

public class TypeFile implements BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME = "Type_file";
	
	private Long id;
	private String extension;
	private String mime;
	private Long idTypeContent;
	
	@Override
	public String toString() {
		String str="";
		str+="Id: "+Long.toString(id)+"\n";
		str+="Extension: "+getExtension()+"\n";
		str+="Mime: "+getMime()+"\n";
		str+="idTypeContent: "+Long.toString(getIdTypeContent())+"\n";
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

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public Long getIdTypeContent() {
		return idTypeContent;
	}

	public void setIdTypeContent(Long idTypeContent) {
		this.idTypeContent = idTypeContent;
	}


	
	
}
