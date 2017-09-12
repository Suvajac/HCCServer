package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.UtisakDAO;
import net.etfbl.hcc.model.Utisak;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLUtisakDAO implements UtisakDAO {

	public MySQLUtisakDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dodaj(Utisak utisak) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "insert into utisak(Tekst,Datum,Username) values "
				+ "(?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, utisak.getTekst());
			if(utisak.getDatum()!=null){
				java.sql.Timestamp date=java.sql.Timestamp.valueOf(utisak.getDatum());
            	ps.setTimestamp(2,date);
            }else ps.setTimestamp(2,null);
			ps.setString(3, utisak.getKorisnik().getUsername());

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
	public ArrayList<Utisak> getUtisci() {
		ArrayList<Utisak> retVal = new ArrayList<Utisak>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * from utisak ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Utisak(rs.getInt(1),rs.getString(2),rs.getTimestamp(3).toLocalDateTime(),
						new MySQLGostDAO().getKorisnik(rs.getString(4))));
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
	public boolean obrisi(Utisak utisak) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM utisak "
				+ "WHERE IdUtiska=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, utisak.getIdUtiska());

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
