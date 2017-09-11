package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
				retVal.add(new Oglas(rs.getInt(1),rs.getString(3),new Date(rs.getDate(2).getTime())));
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
	public boolean dodaj(Oglas oglas) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "insert into oglas(IdOglasa,Datum,Poruka) values "
				+ "(?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, oglas.getIdOglasa());
			if(oglas.getDatum()!=null){
            	java.sql.Date date=new java.sql.Date(oglas.getDatum().getTime());
            	ps.setDate(2,date);
            }else ps.setDate(2,null);
			ps.setString(3, oglas.getPoruka());

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
