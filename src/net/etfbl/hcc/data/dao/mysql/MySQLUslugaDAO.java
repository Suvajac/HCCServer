package net.etfbl.hcc.data.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import net.etfbl.hcc.connection.ConnectionPool;
import net.etfbl.hcc.data.dao.UslugaDAO;
import net.etfbl.hcc.model.Usluga;
import net.etfbl.hcc.util.DBUtilities;

public class MySQLUslugaDAO implements UslugaDAO{
	@Override
	public int dodaj(Usluga usluga) {
		Connection conn = null;
		PreparedStatement ps = null;
		CallableStatement proc = null;

		int id=-1;
		try {
			conn = ConnectionPool.getInstance().checkOut();
			proc = conn.prepareCall("call insert_into_usluga (?,?,?) ");
			proc.registerOutParameter(3, Types.INTEGER);
			proc.setString(1, usluga.getNaziv());
			proc.setDouble(2,usluga.getCijena());
			proc.execute();
			id = proc.getInt(3);
			
		} catch (SQLException e) {
			e.printStackTrace();
			DBUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtilities.getInstance().close(ps);
		}
		return id;
	}
}
