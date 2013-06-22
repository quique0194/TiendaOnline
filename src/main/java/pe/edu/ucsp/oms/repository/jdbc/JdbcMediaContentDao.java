package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.MediaContent;
import pe.edu.ucsp.oms.repository.MediaContentDao;

@Repository
public class JdbcMediaContentDao extends JdbcGenericDao<MediaContent, Long> implements
MediaContentDao {

	private final MediaContentMapper mapper = new MediaContentMapper();
	protected PasswordEncoder encoder = new Md5PasswordEncoder();

	@Override
	public void update(MediaContent mediaContent) {
		String sql = "UPDATE " + getTableName() + " SET name = ? WHERE id = ?";
		jdbcTemplate.update(sql, mediaContent.getName(), mediaContent.getId());
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}

	@Override
	protected RowMapper<MediaContent> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return MediaContent.TABLE_NAME;
	}

	public final class MediaContentMapper implements RowMapper<MediaContent> {
		public MediaContent mapRow(ResultSet rs, int rowNum) throws SQLException {
			MediaContent mediaContent = new MediaContent();
			mediaContent.setId(rs.getLong("id"));
			mediaContent.setName(rs.getString("name"));
			return mediaContent;
		}
	}
}
