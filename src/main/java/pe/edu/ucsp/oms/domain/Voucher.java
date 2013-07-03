package pe.edu.ucsp.oms.domain;

public class Voucher implements BaseEntity<Long> {

	private Long id;

	private int points;

	private short discount;
	
	private static final long serialVersionUID = 1L;

	public static String TABLE_NAME = "Vouchers";

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getPoints(){
		return points;
	}
	
	public void setPoints(int points){
		this.points = points;
	}

	public short getDiscount(){
		return discount;
	}
	
	public void setDiscount(short discount){
		this.discount = discount;
	}
}
