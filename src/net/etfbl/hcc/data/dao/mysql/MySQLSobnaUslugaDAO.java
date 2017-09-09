package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.SobnaUslugaDAO;
import net.etfbl.hcc.model.SobnaUsluga;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLSobnaUslugaDAO implements SobnaUslugaDAO {

	public MySQLSobnaUslugaDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dodaj(SobnaUsluga usluga) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "call insert_into_sobnausluga "
				+ "(?, ?, ? , ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, usluga.getIdUsluge());
			ps.setString(2, usluga.getNaziv());
			ps.setDouble(3, usluga.getCijena());
			ps.setString(4, usluga.getTip());

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
	public boolean obrisi(SobnaUsluga usluga) {
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
