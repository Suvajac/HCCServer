package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.ObavjestenjeDAO;
import net.etfbl.hcc.model.Obavjestenje;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLObavjestenjeDAO implements ObavjestenjeDAO {

	public MySQLObavjestenjeDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Obavjestenje> getObavjestenja() {
		ArrayList<Obavjestenje> retVal = new ArrayList<Obavjestenje>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * from obavjestenje ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Obavjestenje(rs.getInt(1),rs.getString(2),rs.getTimestamp(3).toLocalDateTime()));
		} catch (SQLException e) {
			e.printStackTrace();
			DBUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtilities.getInstance().close(ps, rs);
		}
		return retVal;
	}

}
