package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Task;
import pe.edu.ucsp.oms.repository.TaskDao;



@Repository
public class JdbcTaskDao extends JdbcGenericDao<Task, Long> implements TaskDao {
	private final TaskMapper mapper = new TaskMapper();
	protected PasswordEncoder encoder = new Md5PasswordEncoder();
	
	@Override
	public void update(final Task task) {
		String sql = "UPDATE " + getTableName() + " SET task = ? WHERE id = ?";
		jdbcTemplate.update(sql, task.getTask(), task.getId());
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}

	@Override
	protected RowMapper<Task> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return Task.TABLE_NAME;
	}

	public static class TaskMapper implements RowMapper<Task> {
		public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
			Task task = new Task();
			task.setId(rs.getLong("id"));
			task.setTask(rs.getString("task"));
			return task;
		}
	}
}
