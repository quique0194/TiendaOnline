package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Download;
import pe.edu.ucsp.oms.repository.DownloadDao;


@Repository
public class JdbcDownloadDao extends JdbcGenericDao<Download ,Long> implements DownloadDao{

	
	private final DownloadMapper mapper = new DownloadMapper();
	
	@Override
	public void update(Download download) {
		String sql = "UPDATE " + getTableName() + " SET date = ? , id_content=?, id_user = ?  WHERE id = ?";
		jdbcTemplate.update(sql,download.getDate(), download.getIdContent(), download.getIdUser(), download.getId());
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
		.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}

	@Override
	protected RowMapper<Download> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return Download.TABLE_NAME;
	}

	@Override
	public List<Download> filterByDate(String date) {
		String sql = "SELECT * FROM " + getTableName() + " WHERE date LIKE :date";
	    SqlParameterSource namedParameters = new MapSqlParameterSource("date",date);
	    return jdbcTemplate.query(sql, getRowMapper(), namedParameters);
	}

	@Override
	public List<Download> filterByUser(Long idUser) {
		String sql = "SELECT * FROM " + getTableName() + " WHERE id_user= ?";
	    return jdbcTemplate.query(sql, getRowMapper(), idUser);
	}
	

	public static class DownloadMapper implements RowMapper<Download> {
		public Download mapRow(ResultSet rs, int rowNum) throws SQLException {
			Download download = new Download();
			download.setId(rs.getLong("id"));
			download.setDate(rs.getString("date"));
			download.setIdContent(rs.getLong("id_content"));
			download.setIdUser(rs.getLong("id_user"));
			return download;
		}
	}

}
