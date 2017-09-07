package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.GostDAO;
import net.etfbl.hcc.model.Korisnik;
import net.etfbl.hcc.model.Popust;
import net.etfbl.hcc.model.Racun;
import net.etfbl.hcc.model.Recepcionar;
import net.etfbl.hcc.model.Soba;
import net.etfbl.hcc.model.Stavka;
import net.etfbl.hcc.model.Usluga;
import net.etfbl.hcc.model.Gost;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLGostDAO implements GostDAO {

	public MySQLGostDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Gost getKorisnik(String username) {
		Gost retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement psStavke = null;
		ResultSet rsStavke = null;

		String query = "SELECT *FROM korisnik NATURAL JOIN gost natural join registracija natural join soba "
				+ "natural join racun inner join popust on IdPopusta=KodPopusta "
				+ "WHERE username= ?";
		String queryStavke="SELECT * FROM stavka natural join usluga where IdRacuna = ? ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			psStavke=conn.prepareStatement(queryStavke);

			ps.setString(1, username);

			rs = ps.executeQuery();

			if (rs.next()){
				retVal = new Gost(rs.getString(3), rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7));
				retVal.setSoba(new Soba(rs.getInt(2),rs.getInt(10),rs.getInt(11),rs.getDouble(12)));
				Racun r=new Racun(rs.getInt(1),rs.getBoolean(13),new Popust(rs.getInt(14),rs.getDouble(16),rs.getBoolean(17)),null);


				psStavke.setInt(1, r.getIdRacuna());
				rsStavke = psStavke.executeQuery();
				ArrayList<Stavka> retStavke=new ArrayList<Stavka>();
				while(rsStavke.next()){
					Usluga u=new Usluga(rsStavke.getInt(1),rsStavke.getString(6),null);
					Stavka s=new  Stavka(rsStavke.getInt(2),rsStavke.getDouble(3),rsStavke.getDate(4),u);
					u.setStavka(s);
					retStavke.add(s);
				}
				r.setStavke(retStavke);
				retVal.setRacun(r);
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
	public boolean dodaj(Gost gost,java.sql.Date date,java.sql.Date date2) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "call insert_into_gost "
				+ "(?, ?, ?, ?, ? , ?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, gost.getUsername());
			ps.setString(2, gost.getIme());
			ps.setString(3, gost.getPrezime());
			ps.setString(4, gost.getBrojTelefona());
			ps.setString(5, gost.getLozinkaHash());
			ps.setInt(6, gost.getSoba().getBrSobe());
			ps.setDate(7, date);
			ps.setDate(8, date2);

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
	public boolean azuriraj(Gost gost) {
		boolean retVal = false;
		/*Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE predmet SET "
				+ "NazivPredmeta=?, "
				+ "ECTS=?, "
				+ "NazivFakulteta=? "
				+ "WHERE IdPredmeta=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, predmet.getNazivPredmeta());
			ps.setByte(2, predmet.getEcts());
			ps.setString(3, predmet.getFakultet().getNazivFakulteta());
			ps.setInt(4, predmet.getIdPredmeta());

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			DBUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtilities.getInstance().close(ps);
		}*/
		return retVal;
	}

}
