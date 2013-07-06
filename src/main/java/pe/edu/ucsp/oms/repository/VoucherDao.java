package pe.edu.ucsp.oms.repository;

import pe.edu.ucsp.oms.domain.Voucher;

public interface VoucherDao extends GenericDao<Voucher , Long>{

	Voucher findByPoints (int points);
	
}
