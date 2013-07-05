package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Category;
import pe.edu.ucsp.oms.domain.User;

import pe.edu.ucsp.oms.repository.CategoryDao;


@Repository
public class JdbcCategoryDao extends JdbcGenericDao<Category, Long> implements CategoryDao {

	private final CategoryMapper mapper = new CategoryMapper();
	protected PasswordEncoder encoder = new Md5PasswordEncoder();
	
	@Override
	public void update(Category category) {
		String sql = "UPDATE " + getTableName() + " SET name = ? , id_father = ? WHERE id = ? ";
		jdbcTemplate.update(sql, category.getName() , category.getIdFather() , category.getId());
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
		.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}
	
	@Override
	public Category findParent(Long id) {
	    String sql = "SELECT * FROM Categories WHERE id = (select id_father from Categories  where id = ?)";
        return jdbcTemplate.queryForObject(sql, getRowMapper(), id);
	}

	
	@Override
	protected RowMapper<Category> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return Category.TABLE_NAME;
	}
	
	public final class CategoryMapper implements RowMapper<Category> {
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category category = new Category();
			category.setId(rs.getLong("id"));
			category.setName(rs.getString("name"));
			category.setIdFather(rs.getLong("id_father"));
			return category;
		}
	}

	@Override
	public Category findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> filterByParent(String parent) {
		// TODO Auto-generated method stub
		return null;
	}

}