package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.RacunDAO;
import net.etfbl.hcc.model.Korisnik;
import net.etfbl.hcc.model.Popust;
import net.etfbl.hcc.model.Racun;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLRacunDAO implements RacunDAO {

	public MySQLRacunDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean kreiraj(Racun racun) {
		// ne treba radi se kad kreiras Gosta
		return false;
	}

	@Override
	public boolean azuriraj(Racun racun) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE racun SET "
				+ "Placen = true "
				+ "WHERE IdRacuna=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, racun.getIdRacuna());

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
	public Racun getRacun(Korisnik korisnik) {
		// ne treba imas u gostu koji je static
		return null;
	}

	@Override
	public boolean dodajPopust(Popust popust,Racun racun) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE racun SET "
				+ "IdPopusta = ? "
				+ "WHERE IdRacuna=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, popust.getKodPopusta());
			ps.setInt(2, racun.getIdRacuna());

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
