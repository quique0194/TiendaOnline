package pe.edu.ucsp.oms.domain;

public class MediaType implements BaseEntity<Integer> {

	private Integer id;
	private String extension;
	private String mimeType;
	private String typeContent;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
}
