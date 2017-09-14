package net.etfbl.hcc.data.dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.RacunDAO;
import net.etfbl.hcc.model.Korisnik;
import net.etfbl.hcc.model.Popust;
import net.etfbl.hcc.model.Racun;
import net.etfbl.hcc.model.Stavka;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLRacunDAO implements RacunDAO {

	public MySQLRacunDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Racun kreiraj() {
		Racun retVal = new Racun();
		int id=0;
		Connection conn = null;
		CallableStatement proc = null;

		try {
			conn = ConnectionPool.getInstance().checkOut();
			proc = conn.prepareCall("call insert_into_racun (?) ");
			proc.registerOutParameter(1, Types.INTEGER);
			proc.execute();
			id = proc.getInt(1);
			retVal.setIdRacuna(id);
			retVal.setPlacen(false);
			retVal.setStavke(new ArrayList<Stavka>());

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
