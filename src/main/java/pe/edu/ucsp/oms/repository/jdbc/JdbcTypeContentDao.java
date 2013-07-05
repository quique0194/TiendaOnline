package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.TypeContent;
import pe.edu.ucsp.oms.repository.TypeContentDao;

@Repository
public class JdbcTypeContentDao extends JdbcGenericDao<TypeContent, Long> implements TypeContentDao{

	private final TypeContentMapper mapper = new TypeContentMapper();
	
	@Override
	public void update(TypeContent tc) {
		String sql = "UPDATE " + getTableName() + " SET type_content=? " +
				"WHERE id=?";
		jdbcTemplate.update(sql, tc.getTypeContent(), tc.getId());
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
		.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}

	@Override
	protected RowMapper<TypeContent> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return TypeContent.TABLE_NAME;
	}

	public final class TypeContentMapper implements RowMapper<TypeContent>{

		@Override
		public TypeContent mapRow(ResultSet rs, int rowNum) throws SQLException {
			TypeContent tc = new TypeContent();
			tc.setId(rs.getLong("id"));
			tc.setTypeContent(rs.getString("type_content"));
			return tc;
		}
		
	}
}
