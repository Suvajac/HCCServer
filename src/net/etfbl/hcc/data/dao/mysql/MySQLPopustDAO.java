package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.PopustDAO;
import net.etfbl.hcc.model.Gost;
import net.etfbl.hcc.model.Popust;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLPopustDAO implements PopustDAO {

	public MySQLPopustDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Popust potvrdiPopust(String kodPopusta,Gost gost) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean dodaj(Popust popust) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "insert into popust(KodPopusta,Procenat,Aktivan) values "
				+ "(?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, popust.getKodPopusta());
			ps.setDouble(2, popust.getProcenat());
			ps.setBoolean(3, true);

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			DBUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtilities.getInstance().close(ps);
		}
		return retVal;
	}

	@Override
	public boolean obrisi(Popust popust) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE popust SET "
				+ "Aktivan=? "
				+ "WHERE KodPopusta=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setBoolean(1, false);
			ps.setInt(2, popust.getKodPopusta());

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			DBUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtilities.getInstance().close(ps);
		}
		return retVal;
	}

}
