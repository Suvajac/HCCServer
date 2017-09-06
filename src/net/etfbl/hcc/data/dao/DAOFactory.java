package net.etfbl.hcc.data.dao;

import net.etfbl.hcc.data.dao.mysql.MySQLDAOFactory;

public abstract class DAOFactory {

	public static DAOFactory getDAOFactory() {
		return new MySQLDAOFactory();
	}

}
