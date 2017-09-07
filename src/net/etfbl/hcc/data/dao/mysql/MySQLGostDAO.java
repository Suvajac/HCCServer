package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.KorisnikDAO;
import net.etfbl.hcc.model.Korisnik;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLGostDAO implements KorisnikDAO {

	public MySQLGostDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Korisnik getKorisnik(String username) {
		Korisnik retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT *"
				+ "FROM korisnik "
				+ "NATURAL JOIN gost "
				+ "WHERE username=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next())
				retVal = new Korisnik(rs.getString(1), rs.getString(2),
						rs.getString(3),rs.getString(4),rs.getString(5));
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
	public boolean dodaj(Korisnik korisnik) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean azuriraj(Korisnik korisnik) {
		// TODO Auto-generated method stub
		return false;
	}

}
