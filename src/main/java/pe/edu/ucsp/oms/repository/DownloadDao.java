package pe.edu.ucsp.oms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Download;

@Repository
public interface DownloadDao extends GenericDao<Download ,Long> {

	List<Download> filterByDate(String date );
	List<Download> filterByUser(Long idUser);

}
