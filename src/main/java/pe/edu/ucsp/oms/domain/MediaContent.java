package pe.edu.ucsp.oms.domain;

public class MediaContent implements BaseEntity<Long> {

	public static final String TABLE_NAME = "producto";

	private int id;

	private MediaType type;

	private String name;

	private String author;

	private String description;

	private Double price;

	private Category category;

	/**
	 * File size in bytes.
	 */
	private Long size;

	private byte[] content;

	/**
	 * Representa la oferta actual de un producto. {@code null} en caso no tenga
	 * promocion.
	 */
	private Offer currentOffer;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Offer getCurrentOffer() {
		return currentOffer;
	}

	public void setCurrentOffer(Offer currentOffer) {
		this.currentOffer = currentOffer;
	}

	public MediaType getType() {
		return type;
	}

	public void setType(MediaType type) {
		this.type = type;
	}
}
