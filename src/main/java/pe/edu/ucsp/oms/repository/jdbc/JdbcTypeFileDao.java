package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.TypeFile;
import pe.edu.ucsp.oms.repository.TypeFileDao;

@Repository
public class JdbcTypeFileDao extends JdbcGenericDao<TypeFile, Long> implements TypeFileDao{

	private final TypeFileMapper mapper = new TypeFileMapper();
	
	@Override
	public void update(TypeFile tf) {
		String sql = "UPDATE " + getTableName() + " SET extension=?," +
				"mime=?, id_typecontent=? WHERE id=?";
		jdbcTemplate.update(sql, tf.getExtension(),
				tf.getMime(),tf.getIdTypecontent(), tf.getId());
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
		.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}

	@Override
	protected RowMapper<TypeFile> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return TypeFile.TABLE_NAME;
	}

	public final class TypeFileMapper implements RowMapper<TypeFile>{

		@Override
		public TypeFile mapRow(ResultSet rs, int rowNum) throws SQLException {
			TypeFile tf=new TypeFile();
			tf.setId(rs.getLong("id"));
			tf.setExtension(rs.getString("extension"));
			tf.setMime(rs.getString("mime"));
			tf.setIdTypecontent(rs.getLong("id_typecontent"));
			return tf;
		}
		
	}
}
