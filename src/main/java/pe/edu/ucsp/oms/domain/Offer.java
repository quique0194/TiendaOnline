package pe.edu.ucsp.oms.domain;

import java.util.Date;
import java.util.List;


public class Offer implements BaseEntity<Integer> {
	private Integer id;

	private Date start;

	private Date end;

	private String name;

	private List<MediaContent> products;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getName() {
	  return name;
  }

	public void setName(String name) {
	  this.name = name;
  }

	public List<MediaContent> getProducts() {
	  return products;
  }

	public void setProducts(List<MediaContent> products) {
	  this.products = products;
  }
}
