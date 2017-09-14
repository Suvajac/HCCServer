package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.GostDAO;
import net.etfbl.hcc.model.Popust;
import net.etfbl.hcc.model.Racun;
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
				+ "WHERE username= ? and DatumDo > curdate() ";
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
				/*retVal.setDatumOd(new java.util.Date(rs.getDate(8).getTime()));
				retVal.setDatumDo(new java.util.Date(rs.getDate(9).getTime()));
				retVal.setSoba(new Soba(rs.getInt(2),rs.getInt(10),rs.getInt(11),rs.getDouble(12)));*/

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
					Stavka s=new Stavka(rsStavke.getInt(2),rsStavke.getTimestamp(3).toLocalDateTime(),u);
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
	public boolean dodaj(Gost gost) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "call insert_into_gost "
				+ "(?, ?, ?, ?, ? ,?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, gost.getUsername());
			ps.setString(2, gost.getIme());
			ps.setString(3, gost.getPrezime());
			ps.setString(4, gost.getBrojTelefona());
			ps.setString(5, gost.getLozinkaHash());
			ps.setInt(6, gost.getRacun().getIdRacuna());

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

	@Override
	public ArrayList<Gost> getKorisnike() {
		ArrayList<Gost> retVal = new ArrayList<Gost>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement psStavke = null;
		ResultSet rsStavke = null;
		PreparedStatement psPopust = null;
		ResultSet rsPopust = null;

		String query = "SELECT *FROM korisnik NATURAL JOIN gost natural join registracija natural join soba "
				+ "natural join racun where DatumDo > curdate()";
		String queryStavke="SELECT * FROM stavka natural join usluga where IdRacuna = ? ";
		String queryPopust="SELECT * FROM popust where KodPopusta = ? ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			psStavke=conn.prepareStatement(queryStavke);
			psPopust=conn.prepareStatement(queryPopust);

			rs = ps.executeQuery();
			Gost tempGost;
			while(rs.next()){
				tempGost = new Gost(rs.getString(3), rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7));
				/*tempGost.setDatumOd(new java.util.Date(rs.getDate(8).getTime()));
				tempGost.setDatumDo(new java.util.Date(rs.getDate(9).getTime()));
				tempGost.setSoba(new Soba(rs.getInt(2),rs.getInt(10),rs.getInt(11),rs.getDouble(12)));*/

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
					Stavka s=new Stavka(rsStavke.getInt(2),rsStavke.getTimestamp(3).toLocalDateTime(),u);
					retStavke.add(s);
				}
				r.setStavke(retStavke);
				tempGost.setRacun(r);
				retVal.add(tempGost);
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
	public boolean noviRacun(Gost gost) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE gost SET "
				+ "IdRacuna=? "
				+ "WHERE Username=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, gost.getRacun().getIdRacuna());
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

	@Override
	public Gost getKorisnikOld(String username) {
		Gost retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement psStavke = null;
		ResultSet rsStavke = null;
		PreparedStatement psPopust = null;
		ResultSet rsPopust = null;

		String query = "SELECT *FROM korisnik NATURAL JOIN gost "
				+ "natural join racun "
				+ "WHERE username= ? ";
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
				retVal = new Gost(rs.getString(2), rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6));

				Popust p=new Popust(rs.getInt(8),0,false);

				Racun r=new Racun(rs.getInt(1),rs.getBoolean(7),p,null);

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
					Stavka s=new Stavka(rsStavke.getInt(2),rsStavke.getTimestamp(3).toLocalDateTime(),u);
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

}
