package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.SportskaOpremaDAO;
import net.etfbl.hcc.model.SportskaOprema;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLSportskaOpremaDAO implements SportskaOpremaDAO {

	public MySQLSportskaOpremaDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<SportskaOprema> getOprema() {
		ArrayList<SportskaOprema> retVal = new ArrayList<SportskaOprema>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * from sportskaoprema ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new SportskaOprema(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
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
