package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.WellnessTerminDAO;
import net.etfbl.hcc.model.Termin;
import net.etfbl.hcc.model.WellnessTermin;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLWellnessTerminDAO implements WellnessTerminDAO {

	public MySQLWellnessTerminDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean provjeriTermin(Termin termin) {
		boolean retVal = true;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select * from wellnesstermin natural join termin natural join vrijemetermina "
				+ "where Vrijeme = ? and Datum = ? ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
			java.sql.Time timeValue=null;
			try {
				timeValue = new java.sql.Time(sdf.parse(termin.getVrijeme()).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps.setTime(1, timeValue);

			java.sql.Date date=new java.sql.Date(termin.getDatum().getTime());
        	ps.setDate(2,date);

			rs = ps.executeQuery();

			if (rs.next())
				retVal = false;

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
	public boolean dodaj(WellnessTermin termin) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "call insert_into_wellnesstermin "
				+ "(?, ?, ?) ";
		try {
			System.out.println("ter: "+termin.getIdTermina()+" "+termin.getVrijeme()+" "+termin.getDatum());
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, termin.getIdTermina());
			if(termin.getDatum()!=null){
            	java.sql.Date date=new java.sql.Date(termin.getDatum().getTime());
            	ps.setDate(2,date);
            }else ps.setDate(2,null);
			if(termin.getVrijeme()!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
				java.sql.Time timeValue=null;
				try {
					timeValue = new java.sql.Time(sdf.parse(termin.getVrijeme()).getTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(timeValue);
				ps.setTime(3, timeValue);
			}else ps.setTime(3, null);

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
	public boolean obrisi(WellnessTermin termin) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM termin "
				+ "WHERE IdTermina=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, termin.getIdTermina());

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
