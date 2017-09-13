package net.etfbl.hcc.data.dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.ProizvodDAO;
import net.etfbl.hcc.model.Proizvod;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLProizvodDAO implements ProizvodDAO {

	public MySQLProizvodDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int dodaj(Proizvod proizvod) {
		int retVal = 0;
		Connection conn = null;
		CallableStatement proc = null;

		try {
			conn = ConnectionPool.getInstance().checkOut();
			proc = conn.prepareCall("{ call insert_into_proizvod (?, ?, ? , ? , ?) }");
			proc.registerOutParameter(5, Types.INTEGER);

			proc.setInt(1, proizvod.getIdProizvoda());
			proc.setString(2, proizvod.getNaziv());
			proc.setDouble(3, proizvod.getCijena());
			proc.setString(4, proizvod.getTip());
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
	public boolean obrisi(Proizvod proizvod) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM proizvod "
				+ "WHERE IdProizvoda=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, proizvod.getIdProizvoda());

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
	public ArrayList<Proizvod> getProizvodi() {
		ArrayList<Proizvod> retVal = new ArrayList<Proizvod>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * from proizvod ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Proizvod(rs.getInt(1),rs.getString(4),rs.getString(2),rs.getDouble(3)));
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
