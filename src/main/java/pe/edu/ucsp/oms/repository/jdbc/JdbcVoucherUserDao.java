package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import pe.edu.ucsp.oms.domain.VoucherUser;
import pe.edu.ucsp.oms.repository.VoucherUserDao;

public class JdbcVoucherUserDao extends JdbcGenericDao<VoucherUser, Long> implements VoucherUserDao{

private final PaymentMapper mapper = new PaymentMapper();
	
	@Override
	public void update(VoucherUser voucher_user) {
			
	}

	@Override
	public List<VoucherUser> filterByIdUser(Long idUser) {
		String sql = "SELECT * FROM " + getTableName() + " WHERE id_user LIKE :idUser";
	    SqlParameterSource namedParameters = new MapSqlParameterSource("id_user",idUser);
	    return jdbcTemplate.query(sql, getRowMapper(), namedParameters);
	}

	@Override
	public List<VoucherUser> filterByIdContent(Long idVoucher) {
		String sql = "SELECT * FROM " + getTableName() + " WHERE id_voucher LIKE :idVoucher";
	    SqlParameterSource namedParameters = new MapSqlParameterSource("id_voucher",idVoucher);
	    return jdbcTemplate.query(sql, getRowMapper(), namedParameters);
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
		.withTableName(getTableName());
	}

	@Override
	protected RowMapper<VoucherUser> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return VoucherUser.TABLE_NAME;
	}
	
	
	public static class PaymentMapper implements RowMapper<VoucherUser> {
		public VoucherUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			VoucherUser voucher_user = new VoucherUser();
			voucher_user.setIdVoucher(rs.getLong("id_content"));
			voucher_user.setIdUser(rs.getLong("id_user"));
			return voucher_user;
		}
	}

}
