package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.RecepcionarDAO;
import net.etfbl.hcc.model.Gost;
import net.etfbl.hcc.model.Popust;
import net.etfbl.hcc.model.Racun;
import net.etfbl.hcc.model.Recepcionar;
import net.etfbl.hcc.model.Soba;
import net.etfbl.hcc.model.Stavka;
import net.etfbl.hcc.model.Usluga;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLRecepcionarDAO implements RecepcionarDAO{

	public MySQLRecepcionarDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Recepcionar getKorisnik(String username) {
		Recepcionar retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT *FROM korisnik NATURAL JOIN recepcionar "
				+ "WHERE username= ?";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next())
				retVal = new Recepcionar(rs.getString(1), rs.getString(2),
						rs.getString(3),rs.getString(4),rs.getString(5));

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
	public boolean dodaj(Recepcionar recepcionar) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "call insert_into_recepcionar "
				+ "(?, ?, ?, ?, ? ) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, recepcionar.getUsername());
			ps.setString(2, recepcionar.getIme());
			ps.setString(3, recepcionar.getPrezime());
			ps.setString(4, recepcionar.getBrojTelefona());
			ps.setString(5, recepcionar.getLozinkaHash());

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
	public boolean azuriraj(Recepcionar recepcionar) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE korisnik SET "
				+ "LozinkaHash=? "
				+ "WHERE Username=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, recepcionar.getLozinkaHash());
			ps.setString(2, recepcionar.getUsername());

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
