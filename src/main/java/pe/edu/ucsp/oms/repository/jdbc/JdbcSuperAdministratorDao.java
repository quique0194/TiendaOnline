package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.SuperAdministrator;
import pe.edu.ucsp.oms.repository.SuperAdministratorDao;

@Repository
public class JdbcSuperAdministratorDao extends JdbcGenericDao<SuperAdministrator, Long> implements SuperAdministratorDao {
	
	private final SuperAdministratorMapper mapper = new SuperAdministratorMapper();
	protected PasswordEncoder encoder = new Md5PasswordEncoder();
	
	@Override
	public boolean existsSuperAdministrator(String username, String password) {
		String sql = "SELECT * FROM " + getTableName() + " WHERE username = ? and password = ?";
        return jdbcTemplate.queryForRowSet(sql, username, password).next();        
	}
	
	@Override
	public void update(final SuperAdministrator sadmi) {
		String sql = "UPDATE " + getTableName() + " SET first_name = ?, last_name = ?, email = ?, username = ? WHERE id = ?";
		jdbcTemplate.update(sql, sadmi.getFirstName(), sadmi.getLastName(), sadmi.getEmail(), sadmi.getUsername(), sadmi.getId());
	}
	
	@Override
	public void save(SuperAdministrator sadmi) {
		sadmi.setPassword(encoder.encodePassword(sadmi.getPassword(), null));
	  super.save(sadmi);
	}
	
	@Override
	public SuperAdministrator findByUsername(String username) {
    String sql = "SELECT * FROM " + getTableName() + " WHERE username = ?";
    SqlParameterSource namedParameters = new MapSqlParameterSource("username", username);
    return jdbcTemplate.queryForObject(sql, getRowMapper(), namedParameters);
	}
	
	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}
	
	@Override
	protected RowMapper<SuperAdministrator> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return SuperAdministrator.TABLE_NAME;
	}

	public static class SuperAdministratorMapper implements RowMapper<SuperAdministrator> {
		public SuperAdministrator mapRow(ResultSet rs, int rowNum) throws SQLException {
			SuperAdministrator sadmi = new SuperAdministrator();
			sadmi.setId(rs.getLong("id"));
			sadmi.setUsername(rs.getString("username"));
			sadmi.setPassword(rs.getString("password"));
			sadmi.setFirstName(rs.getString("first_name"));
			sadmi.setLastName(rs.getString("last_name"));
			sadmi.setEmail(rs.getString("email"));
			return sadmi;
		}
	}

	@Override
	public Long findIdByUsername(String username) {
		String sql = "SELECT * FROM " + getTableName() + " WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, getRowMapper(), username).getId();
		
	}
}
