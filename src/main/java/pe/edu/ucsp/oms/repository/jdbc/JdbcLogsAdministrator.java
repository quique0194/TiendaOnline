package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Administrator;
import pe.edu.ucsp.oms.domain.LogAdministrator;
import pe.edu.ucsp.oms.domain.Task;
import pe.edu.ucsp.oms.repository.AdministratorDao;
import pe.edu.ucsp.oms.repository.LogAdministratorDao;
import pe.edu.ucsp.oms.repository.jdbc.JdbcAdministratorDao.AdministratorMapper;

@Repository
public class JdbcLogsAdministrator extends JdbcGenericDao<LogAdministrator, Long> implements LogAdministratorDao{
	
	private final LogAdministratorMapper mapper = new LogAdministratorMapper();
	
	@Override
	public void update(final LogAdministrator log) {
		String sql = "UPDATE " + getTableName() + " SET date = ?, detail= ?, id_administrator= ?, id_task= ?";
		jdbcTemplate.update(sql, log.getDate(), log.getDetail(), log.getId_administrator(), log.getId_task());
	}

	@Override
	public List<Administrator> filterByAdministrator(int id_admi) {
		return null;
		/*SELECT admi.username , task.date, task.detail
		FROM Administrator AS admi, Logs_administrator AS task
		WHERE admi.id= task.id_administrator and admi.id=2;*/

		/*String sql = "SELECT * FROM " + getTableName() + " WHERE id_admi =id_admi";
	    SqlParameterSource namedParameters = new MapSqlParameterSource("id_admi", id_admi);
	    return jdbcTemplate.query(sql, getRowMapper(), namedParameters);*/
	}

	@Override
	public List<Task> filterByTask(int id_task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}

	@Override
	protected RowMapper<LogAdministrator> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return LogAdministrator.TABLE_NAME;
	}

	public static class LogAdministratorMapper implements RowMapper<LogAdministrator> {
		public LogAdministrator mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogAdministrator log = new LogAdministrator();
			log.setDate(rs.getLong("date"));
			log.setDetail(rs.getString("detail"));
			log.setId_administrator(rs.getLong("id_administrator"));
			log.setId_task(rs.getLong("id_task"));
			return log;
		}
	}
}
