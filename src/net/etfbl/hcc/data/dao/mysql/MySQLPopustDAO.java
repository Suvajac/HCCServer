package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		if(kodPopusta<=0)
			return false;
		String query = "update racun set IdPopusta=?  where IdRacuna=?";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, kodPopusta);
			ps.setInt(2, gost.getRacun().getIdRacuna());

			retVal = ps.executeUpdate() == 1;
		} catch(MySQLIntegrityConstraintViolationException e){
			//System.out.println("Pogresan kod popusta je unesen!");
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
	public boolean popustIskoristi(int kodPopusta) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		if(kodPopusta<=0)
			return false;
		String query = "update popust set Aktivan=0  where KodPopusta=?";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, kodPopusta);

			retVal = ps.executeUpdate() == 1;
		} catch(MySQLIntegrityConstraintViolationException e){
			//System.out.println("Pogresan kod popusta je unesen!");
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

		String query = "delete from popust "
				+ "WHERE KodPopusta=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, popust.getKodPopusta());

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
	public ArrayList<Popust> getPopuste() {
		ArrayList<Popust> retVal = new ArrayList<Popust>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * from popust ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Popust(rs.getInt(1),rs.getDouble(2),rs.getBoolean(3)));
		} catch (SQLException e) {
			e.printStackTrace();
			DBUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtilities.getInstance().close(ps, rs);
		}
		return retVal;
	}

	@Override
	public boolean provjeriPopust(int kodPopusta) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select * from popust where KodPopusta=? and Aktivan=1 ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, kodPopusta);
			rs = ps.executeQuery();

			if (rs.next())
				retVal=true;
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
