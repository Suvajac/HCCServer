package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.StavkaDAO;
import net.etfbl.hcc.model.Racun;
import net.etfbl.hcc.model.Stavka;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLStavkaDAO implements StavkaDAO {

	public MySQLStavkaDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dodaj(Stavka stavka, Racun racun) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "insert into stavka(IdStavke,Cijena,Datum,IdRacuna,IdUsluge) values "
				+ "(?, ?, ?, ? , ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, stavka.getIdStavke());
			ps.setDouble(2, stavka.getCijena());
			if(stavka.getDatum()!=null){
            	java.sql.Date date=new java.sql.Date(stavka.getDatum().getTime());
            	ps.setDate(3,date);
            }else ps.setDate(3,null);
			ps.setInt(4, racun.getIdRacuna());
			ps.setInt(5, stavka.getUsluga().getIdUsluge());

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
	public ArrayList<Stavka> getStavke(Racun racun) {
		// ne treba jer imas gosta staic koji ima racun koji ima stavke
		return null;
	}

}
