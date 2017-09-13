package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.RegistracijaDAO;
import net.etfbl.hcc.model.Registracija;
import net.etfbl.hcc.util.DBUtilities;
import net.etfbl.hcc.util.HCCUtil;

public class MySQLRegistracijaDAO implements RegistracijaDAO {

	public MySQLRegistracijaDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dodaj(Registracija registracija) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "insert into registracija(Username,DatumOd,DatumDo,BrSobe) values (?,?,?,?)";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, registracija.getGost().getUsername());
			ps.setDate(2, java.sql.Date.valueOf(registracija.getDatumOd()));
			ps.setDate(3, java.sql.Date.valueOf(registracija.getDatumDo()));
			ps.setInt(4, registracija.getSoba().getBrSobe());
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
	public ArrayList<Registracija> getRegistracije() {
		ArrayList<Registracija> retVal = new ArrayList<Registracija>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * from registracija ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Registracija(rs.getDate(2).toLocalDate(),rs.getDate(3).toLocalDate(),
						HCCUtil.getDAOFactory().getGostDAO().getKorisnik(rs.getString(1)),
						HCCUtil.getDAOFactory().gesSobaDAO().getSobuSaBrojem(rs.getInt(4))));
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
