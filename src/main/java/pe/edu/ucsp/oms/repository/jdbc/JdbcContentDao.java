package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


import pe.edu.ucsp.oms.domain.Content;
import pe.edu.ucsp.oms.repository.ContentDao;


@Repository
public class JdbcContentDao extends JdbcGenericDao<Content, Long> implements ContentDao {

	private final ContentMapper mapper = new ContentMapper();
	
	@Override
	public void update(Content content) {
		String sql = "UPDATE " + getTableName() + " SET content=?, " +
				"name=?, autor=?, description=?, price=?, size=?, times_download=?, " +
				"id_category=?, id_promo=?, id_type_file=? WHERE id=?";
		jdbcTemplate.update(sql, content.getContent(), 
				content.getName(), content.getAutor(), content.getDescription(), 
				content.getPrice(), content.getSize(), content.getTimesDownload(),
				content.getIdCategory(), content.getIdPromo(),
				content.getIdTypeFile(), content.getId());
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
		.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}

	@Override
	protected RowMapper<Content> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return Content.TABLE_NAME;
	}
	
	public final class ContentMapper implements RowMapper<Content> {
		public Content mapRow(ResultSet rs, int rowNum) throws SQLException {
			Content content = new Content();
			content.setId(rs.getLong("id"));
			content.setContent(rs.getString("content"));			
			content.setName(rs.getString("name"));
			content.setAutor(rs.getString("autor"));
			content.setDescription(rs.getString("description"));
			content.setPrice(rs.getFloat("price"));
			content.setSize(rs.getInt("size"));
			content.setTimesDownload(rs.getInt("times_download"));
			content.setIdCategory(rs.getLong("id_category"));
			content.setIdPromo(rs.getInt("id_promo"));
			content.setIdTypeFile(rs.getLong("id_type_file"));
			return content;
		}
	}

}
