package pe.edu.ucsp.oms.domain;

public class VoucherUser implements BaseEntity<Long>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String TABLE_NAME = "Voucher_user";
	
	private Long idUser;
	private long idVoucher;
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdVoucher() {
		return idVoucher;
	}

	public void setIdVoucher(Long idVoucher) {
		this.idVoucher = idVoucher;
	}
	

}
