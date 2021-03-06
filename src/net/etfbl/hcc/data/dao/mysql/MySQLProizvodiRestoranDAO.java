package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.ProizvodiRestoranDAO;
import net.etfbl.hcc.model.Proizvod;
import net.etfbl.hcc.model.UslugaRestorana;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLProizvodiRestoranDAO implements ProizvodiRestoranDAO {

	public MySQLProizvodiRestoranDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Proizvod> getProizvodi(UslugaRestorana usluga) {
		ArrayList<Proizvod> retVal = new ArrayList<Proizvod>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select * from proizvod natural join proizvodirestoran where IdUslugaRestorana=? ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, usluga.getIdUsluge());
			rs = ps.executeQuery();
			int loop=0;
			while (rs.next()){
				loop=rs.getInt(6);
				for(int i=0;i<loop;i++)
					retVal.add(new Proizvod(rs.getInt(1),rs.getString(4),rs.getString(2),rs.getDouble(3)));
			}
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
	public boolean setProizvodi(UslugaRestorana usluga) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "call insert_into_proizvodirestoran(?,?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			if(usluga.getListaProizvoda().size()>0)
				for(Proizvod p:usluga.getListaProizvoda()){
					ps.setInt(1, p.getIdProizvoda());
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
