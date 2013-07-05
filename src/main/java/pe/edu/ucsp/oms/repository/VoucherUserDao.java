package pe.edu.ucsp.oms.repository;

import java.util.List;

import pe.edu.ucsp.oms.domain.VoucherUser;

public interface VoucherUserDao extends GenericDao<VoucherUser, Long>{

	List<VoucherUser> filterByIdUser(Long idUser);
	List<VoucherUser> filterByIdContent(Long idCategory);

}
