package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import pe.edu.ucsp.oms.domain.Content;
import pe.edu.ucsp.oms.repository.ContentDao;



public class JdbcContentDao extends JdbcGenericDao<Content, Integer> implements ContentDao {

	private final ContentMapper mapper = new ContentMapper();
	
	@Override
	public void update(Content content) {
		String sql = "UPDATE " + getTableName() + " SET name=? autor=? description=? price=? size=? times_download=? id_category=? id_promo=? WHERE id=?";
		jdbcTemplate.update(sql, content.getName(), content.getAutor(), content.getPrice(), 
				content.getSize(), content.getTimesDownload(), content.getCategory().getId(), 
				content.getPromo().getId(), content.getId());
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected RowMapper<Content> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return Content.TABLE_NAME;
	}
	
	// Arreglar detalles
	public final class ContentMapper implements RowMapper<Content> {
		public Content mapRow(ResultSet rs, int rowNum) throws SQLException {
			Content content = new Content();
			content.setId(rs.getInt("id"));
			content.setName(rs.getString("name"));
			return content;
		}
	}

}
