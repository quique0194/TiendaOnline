package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Voucher;
import pe.edu.ucsp.oms.repository.VoucherDao;

@Repository
public class JdbcVoucherDao extends JdbcGenericDao<Voucher, Long> implements VoucherDao{

	private final VoucherMapper mapper = new VoucherMapper(); 
	@Override
	public void update(Voucher voucher) {
		System.out.println("entra al Voucher DAO");
		String sql = "UPDATE " + getTableName() + " SET points = ? , discount = ? WHERE id = ? ";
		jdbcTemplate.update(sql, voucher.getPoints() , voucher.getDiscount() , voucher.getId());
	}
	

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
		.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}

	@Override
	protected RowMapper<Voucher> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return Voucher.TABLE_NAME;
	}

	public final class VoucherMapper implements RowMapper<Voucher> {
		public Voucher mapRow(ResultSet rs , int rowNum ) throws SQLException{
			Voucher voucher = new Voucher();
			voucher.setId(rs.getLong("id"));
			voucher.setPoints(rs.getInt("points"));
			voucher.setDiscount(rs.getShort("discount"));
			return voucher;
		}
	}
	@Override
	public Voucher findByPoints(int points) {
		// TODO Auto-generated method stub
		return null;
	}

}
