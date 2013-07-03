package pe.edu.ucsp.oms.repository;

import java.util.List;

import pe.edu.ucsp.oms.domain.Voucher;

public interface VoucherDao extends GenericDao<Voucher , Long>{

	Voucher findByPoints (int points);
	
}
