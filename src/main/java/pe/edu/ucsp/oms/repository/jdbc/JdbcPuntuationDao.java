package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import pe.edu.ucsp.oms.domain.Puntuation;
import pe.edu.ucsp.oms.repository.PuntuationDao;

public class JdbcPuntuationDao extends JdbcGenericDao<Puntuation, Long> implements PuntuationDao{
	
	private final PuntuationMapper mapper = new PuntuationMapper();
	protected PasswordEncoder encoder = new Md5PasswordEncoder();
	
	@Override
	public void update(final Puntuation puntuation) {
		String sql = "UPDATE " + getTableName() + " SET val = ? WHERE id_content = ? AND id_user = ?";
		jdbcTemplate.update(sql, puntuation.getVal(), puntuation.getIdContent(), puntuation.getIdUser());
	}
	@Override
	protected String getTableName() {
		return Puntuation.TABLE_NAME;
	}
	
	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}
	
	public static class PuntuationMapper implements RowMapper<Puntuation> {
		public Puntuation mapRow(ResultSet rs, int rowNum) throws SQLException {
			Puntuation puntuation = new Puntuation();
			puntuation.setIdContent(rs.getLong("id_content"));
			puntuation.setIdUser(rs.getLong("id_user"));
			puntuation.setVal(rs.getInt("val"));
			return puntuation;
		}
	}
	
	@Override
	protected RowMapper<Puntuation> getRowMapper() {
		return mapper;
	}
}
