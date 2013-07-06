package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Log;
import pe.edu.ucsp.oms.repository.LogDao;
@Repository
public class JdbcLogDao extends JdbcGenericDao<Log, Long> implements LogDao{
	
	private final LogAdministratorMapper mapper = new LogAdministratorMapper();
	
	@Override
	public void update(final Log log) {
		String sql = "UPDATE " + getTableName() + " SET date = ?, detail= ?, id_administrator= ?, id_task= ?";
		jdbcTemplate.update(sql, log.getDate(), log.getDetail(), log.getId_administrator(), log.getId_task());
	}
	
	@Override
	public List<Log> filterByAdministrator(Long id_administrator) {
		String sql = "SELECT * FROM " + getTableName() + " WHERE id_administrator= ?";
		return jdbcTemplate.query(sql, getRowMapper(), id_administrator);
	}
	
	@Override
	public List<Log> filterByTask(Long id_task) {
		String sql = "SELECT * FROM " + getTableName() + " WHERE id_task =id_task";
	    SqlParameterSource namedParameters = new MapSqlParameterSource("id_task", id_task);
	    return jdbcTemplate.query(sql, getRowMapper(), namedParameters);
	}	

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}

	@Override
	protected RowMapper<Log> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return Log.TABLE_NAME;
	}

	public static class LogAdministratorMapper implements RowMapper<Log> {
		public Log mapRow(ResultSet rs, int rowNum) throws SQLException {
			Log log = new Log();
			log.setDate(rs.getLong("date"));
			log.setDetail(rs.getString("detail"));
			log.setId_administrator(rs.getLong("id_administrator"));
			log.setId_task(rs.getLong("id_task"));
			return log;
		}
	}
}
