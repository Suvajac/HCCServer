package net.etfbl.hcc.util;

import net.etfbl.hcc.data.dao.DAOFactory;

public class HCCUtil {

	private static DAOFactory daoFactory;

	public static DAOFactory getDAOFactory() {
		if (daoFactory == null)
			daoFactory = DAOFactory.getDAOFactory();
		return daoFactory;
	}
}
