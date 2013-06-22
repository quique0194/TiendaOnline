package pe.edu.ucsp.oms.domain;

public class Category implements BaseEntity<Integer> {

	private Integer id;

	private Category parent;

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
}
