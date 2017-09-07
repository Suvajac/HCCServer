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
		PreparedStatement psPopust = null;
		ResultSet rsPopust = null;

		String query = "SELECT *FROM korisnik NATURAL JOIN gost natural join registracija natural join soba "
				+ "natural join racun "
				+ "WHERE username= ?";
		String queryStavke="SELECT * FROM stavka natural join usluga where IdRacuna = ? ";
		String queryPopust="SELECT * FROM popust where KodPopusta = ? ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			psStavke=conn.prepareStatement(queryStavke);
			psPopust=conn.prepareStatement(queryPopust);

			ps.setString(1, username);

			rs = ps.executeQuery();

			if (rs.next()){
				retVal = new Gost(rs.getString(3), rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7));
				retVal.setSoba(new Soba(rs.getInt(2),rs.getInt(10),rs.getInt(11),rs.getDouble(12)));

				Popust p=new Popust(rs.getInt(14),0,false);

				Racun r=new Racun(rs.getInt(1),rs.getBoolean(13),p,null);

				psPopust.setInt(1, p.getKodPopusta());
				rsPopust=psPopust.executeQuery();
				if(rsPopust.next()){
					p.setKodPopusta(rsPopust.getInt(1));
					p.setProcenat(rsPopust.getDouble(2));
					p.setAktivan(rsPopust.getBoolean(3));
				}
				r.setPopust(p);
				psStavke.setInt(1, r.getIdRacuna());
				rsStavke = psStavke.executeQuery();
				ArrayList<Stavka> retStavke=new ArrayList<Stavka>();
				while(rsStavke.next()){
					Usluga u=new Usluga(rsStavke.getInt(1),rsStavke.getString(5),rsStavke.getDouble(6));
					Stavka s=new Stavka(rsStavke.getInt(2),rsStavke.getDate(3),u);
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
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE korisnik SET "
				+ "LozinkaHash=? "
				+ "WHERE Username=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, gost.getLozinkaHash());
			ps.setString(2, gost.getUsername());

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
