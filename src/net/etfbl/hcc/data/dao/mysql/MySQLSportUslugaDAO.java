package net.etfbl.hcc.data.dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.SportUslugaDAO;
import net.etfbl.hcc.model.SportUsluga;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLSportUslugaDAO implements SportUslugaDAO {

	public MySQLSportUslugaDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int dodaj(SportUsluga usluga) {
		int retVal = 0;
		Connection conn = null;
		CallableStatement proc = null;

		try {
			conn = ConnectionPool.getInstance().checkOut();
			proc = conn.prepareCall("{ call insert_into_sportusluga (?, ?, ? , ? , ?) }");
			proc.registerOutParameter(5, Types.INTEGER);

			proc.setInt(1, usluga.getIdUsluge());
			proc.setString(2, usluga.getNaziv());
			proc.setDouble(3, usluga.getCijena());
			proc.setInt(4, usluga.getSportTermin().getIdTermina());

			proc.execute();
			retVal = proc.getInt(5);

		} catch (SQLException e) {
			e.printStackTrace();
			DBUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtilities.getInstance().close(proc);
		}
		return retVal;
	}

	@Override
	public boolean obrisi(SportUsluga usluga) {
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
