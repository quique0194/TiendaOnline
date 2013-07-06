package pe.edu.ucsp.oms.repository;

import java.util.List;

import pe.edu.ucsp.oms.domain.Category;

public interface CategoryDao extends GenericDao<Category, Long> {

	Category findByName(String name);
	
	List<Category> filterByParent(Category father);
	List<Category> filterByIdParent(Long idFather);

	// Retorna una lista con todas las Categias Padres
	List<Category> findParents(Long id);
	
	Category findParent(Long id);
	
	void deleteCategory(Long id);
	
	
}
