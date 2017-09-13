package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.UslugaRestoranaDAO;
import net.etfbl.hcc.model.UslugaRestorana;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLUslugaRestoranaDAO implements UslugaRestoranaDAO {

	public MySQLUslugaRestoranaDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dodaj(UslugaRestorana usluga) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "call insert_into_uslugarestorana "
				+ "(?, ?, ? , ? , ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, usluga.getIdUsluge());
			ps.setString(2, usluga.getNaziv());
			ps.setDouble(3, usluga.getCijena());
			ps.setInt(4, usluga.getBrojStolica());
			ps.setString(5, usluga.getVrijeme());

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
	public boolean obrisi(UslugaRestorana usluga) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM usluga "
				+ "WHERE IdUsluge=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, usluga.getIdUsluge());

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
