package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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
	public boolean potvrdiPopust(int kodPopusta,Gost gost) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "update racun set IdPopusta=?  where IdRacuna=?";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, kodPopusta);
			ps.setInt(2, gost.getRacun().getIdRacuna());

			retVal = ps.executeUpdate() == 1;
		} catch(MySQLIntegrityConstraintViolationException e){
			System.out.println("Pogresan kod popusta je unesen!");
		} catch (SQLException e) {
			e.printStackTrace();
			//DBUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtilities.getInstance().close(ps);
		}
		return retVal;
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
