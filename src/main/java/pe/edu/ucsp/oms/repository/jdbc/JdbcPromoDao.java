package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Promo;
import pe.edu.ucsp.oms.repository.PromoDao;

@Repository
public class JdbcPromoDao extends JdbcGenericDao<Promo, Long> implements PromoDao{
	
	private final PromoMapper mapper = new PromoMapper();
	protected PasswordEncoder encoder = new Md5PasswordEncoder();
	
	@Override
	public void update(final Promo promo) {
		String sql = "UPDATE " + getTableName() + " SET start_date = ?,end_date = ?,percent = ? WHERE id = ?";
		jdbcTemplate.update(sql, promo.getStartDate(), 
				promo.getEndDate(), promo.getPercent(), promo.getId());
	}
	
	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}
	@Override
	protected String getTableName() {
		return Promo.TABLE_NAME;
	}
	
	public static class PromoMapper implements RowMapper<Promo> {
		public Promo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Promo promo = new Promo();
			promo.setId(rs.getLong("id"));
			promo.setStartDate(rs.getString("startDate"));
			promo.setEndDate(rs.getString("endDate"));
			promo.setPercent(rs.getInt("percent"));
			return promo;
		}
	}

	@Override
	protected RowMapper<Promo> getRowMapper() {
		return mapper;
	}

}
