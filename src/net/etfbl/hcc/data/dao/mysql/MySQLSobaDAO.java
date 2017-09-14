package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.SobaDAO;
import net.etfbl.hcc.model.Soba;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLSobaDAO implements SobaDAO {

	public MySQLSobaDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Soba getSobu(int brojKreveta) {
			Soba retVal = null;
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			String query = "SELECT * FROM soba where BrSobe not in "
					+ "(SELECT BrSobe FROM registracija where DatumDo > curdate()) and BrKreveta=?";

			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setInt(1, brojKreveta);
				rs = ps.executeQuery();

				if (rs.next())
					retVal = new Soba(rs.getInt(1), rs.getInt(2),
							rs.getInt(3),rs.getDouble(4));

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
	public Soba getSobuSaBrojem(int broj) {
		Soba retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM soba where BrSobe=?";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, broj);
			rs = ps.executeQuery();

			if (rs.next())
				retVal = new Soba(rs.getInt(1), rs.getInt(2),
						rs.getInt(3),rs.getDouble(4));

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
	public ArrayList<Soba> getSlobodneSobe() {
		ArrayList<Soba> retVal = new ArrayList<Soba>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * from soba where BrSobe not in(select BrSobe from registracija where DatumDo > curdate())  ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Soba(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDouble(4)));
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
