package net.etfbl.hcc.data.dao.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.OglasDAO;
import net.etfbl.hcc.model.Oglas;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLOglasDAO implements OglasDAO {

	public MySQLOglasDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Oglas> getOglasi() {
		ArrayList<Oglas> retVal = new ArrayList<Oglas>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * from oglas ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Oglas(rs.getInt(1),rs.getString(3),rs.getTimestamp(2).toLocalDateTime()));
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
	public int dodaj(Oglas oglas) {
		int retVal = 0;
		Connection conn = null;
		CallableStatement proc = null;

		try {
			conn = ConnectionPool.getInstance().checkOut();
			proc = conn.prepareCall("{ call insert_into_oglas (?, ?, ? , ?) }");
			proc.registerOutParameter(4, Types.INTEGER);

			proc.setInt(1, oglas.getIdOglasa());
			if(oglas.getDatum()!=null){
				java.sql.Timestamp date=java.sql.Timestamp.valueOf(oglas.getDatum());
            	proc.setTimestamp(2,date);
            }else proc.setTimestamp(2,null);
			proc.setString(3, oglas.getPoruka());
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

	@Override
	public boolean obrisi(Oglas oglas) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM oglas "
				+ "WHERE IdOglasa=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, oglas.getIdOglasa());

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
