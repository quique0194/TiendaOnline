package pe.edu.ucsp.oms.repository;

import java.util.List;

import pe.edu.ucsp.oms.domain.User;

public interface UserDao extends GenericDao<User, Long> {
	User findByEmail(String email);
	Long findIdByUsername(String username);

	List<User> filterByEmail(String email);
	void updateBalance(User user);
	
	boolean existsUser(String username, String password);
}
