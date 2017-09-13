package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.OpremaSportUslugaDAO;
import net.etfbl.hcc.model.SportUsluga;
import net.etfbl.hcc.model.SportskaOprema;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLOpremaSportUslugaDAO implements OpremaSportUslugaDAO {

	public MySQLOpremaSportUslugaDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<SportskaOprema> getOprema(SportUsluga usluga) {
		ArrayList<SportskaOprema> retVal = new ArrayList<SportskaOprema>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select * from sportskaoprema natural join opremasportusluga where IdUsluge=? ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, usluga.getIdUsluge());
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new SportskaOprema(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getString(4)));
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
	public boolean setOprema(SportUsluga usluga) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "insert into opremasportusluga(IdSportskeOpreme,IdUsluge) values (?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			for(SportskaOprema so:usluga.getListaOpreme()){
				ps.setInt(1, so.getIdProizvoda());
				ps.setInt(2, usluga.getIdUsluge());
				retVal = ps.executeUpdate() == 1;
				if(!retVal)
					return false;
			}
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
