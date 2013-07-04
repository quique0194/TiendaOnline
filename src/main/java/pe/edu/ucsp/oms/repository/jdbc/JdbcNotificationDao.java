package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Notification;
import pe.edu.ucsp.oms.repository.NotificationDao;

@Repository
public class JdbcNotificationDao extends JdbcGenericDao<Notification, Long> implements NotificationDao{

	private final NotificationMapper mapper = new NotificationMapper();
	
	@Override
	public void update(Notification notification) {
		String sql = "UPDATE " + getTableName() + " SET detail=?," +
				"id_user=? WHERE id=?";
		jdbcTemplate.update(sql, notification.getDetail(),
				notification.getIdUser(), notification.getId());
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
		.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}

	@Override
	protected RowMapper<Notification> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return Notification.TABLE_NAME;
	}
	
	public final class NotificationMapper implements RowMapper<Notification>{
		
		@Override
		public Notification mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			Notification notification=new Notification();
			notification.setId(rs.getLong("id"));
			notification.setDetail(rs.getString("detail"));
			notification.setIdUser(rs.getLong("id_user"));
			return notification;
		}
		
	}

}
