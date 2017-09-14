package net.etfbl.hcc.data.dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.ObavjestenjeDAO;
import net.etfbl.hcc.model.Obavjestenje;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLObavjestenjeDAO implements ObavjestenjeDAO {

	public MySQLObavjestenjeDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Obavjestenje> getObavjestenja() {
		ArrayList<Obavjestenje> retVal = new ArrayList<Obavjestenje>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * from obavjestenje ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Obavjestenje(rs.getInt(1),rs.getString(2),rs.getTimestamp(3).toLocalDateTime(),rs.getBoolean(4)));
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
	public boolean procitajObavjestenje(Obavjestenje obavjestenje) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "update obavjestenje set Procitano=1, Datum=? where IdObavjestenja=?";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setTimestamp(1, Timestamp.valueOf(obavjestenje.getDatum()));
			ps.setInt(2, obavjestenje.getIdObavjestenje());

			retVal = ps.executeUpdate() == 1;
		} catch(MySQLIntegrityConstraintViolationException e){
			System.out.println("Uneseno obavjestenje ne postoji");
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
	public int dodaj(Obavjestenje obavjestenje) {
		int retVal = 0;
		Connection conn = null;
		CallableStatement proc = null;

		try {
			conn = ConnectionPool.getInstance().checkOut();
			proc = conn.prepareCall(" call insert_into_obavjestenje (?, ?, ? , ? ) ");
			proc.registerOutParameter(4, Types.INTEGER);

			proc.setInt(1, obavjestenje.getIdObavjestenje());
			proc.setString(2, obavjestenje.getTekst());
			proc.setTimestamp(3, java.sql.Timestamp.valueOf(obavjestenje.getDatum()));

			proc.execute();
			retVal = proc.getInt(4);

		} catch (SQLException e) {
			e.printStackTrace();
			DBUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtilities.getInstance().close(proc);
		}
		return retVal;
	}

}
