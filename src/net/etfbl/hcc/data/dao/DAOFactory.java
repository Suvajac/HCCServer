package net.etfbl.hcc.data.dao;

import net.etfbl.hcc.data.dao.mysql.MySQLDAOFactory;
import net.etfbl.hcc.data.dao.mysql.MySQLGostDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLObavjestenjeDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLOglasDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLOpremaSportUslugaDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLPopustDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLProizvodDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLProizvodiRestoranDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLProizvodiSobaDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLRacunDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLRecepcionarDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLRegistracijaDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLSobaDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLSobnaUslugaDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLSportTerminDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLSportUslugaDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLSportskaOpremaDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLStavkaDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLUslugaRestoranaDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLUtisakDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLWellnessTerminDAO;
import net.etfbl.hcc.data.dao.mysql.MySQLWellnessUslugaDAO;

public abstract class DAOFactory {

	public static DAOFactory getDAOFactory() {
		return new MySQLDAOFactory();
	}

	public abstract MySQLGostDAO getGostDAO();
	public abstract MySQLObavjestenjeDAO getObavjestenjeDAO();
	public abstract MySQLOglasDAO getOglasDAO();
	public abstract MySQLPopustDAO getPopustDAO();
	public abstract MySQLProizvodDAO getProizvodDAO();
	public abstract MySQLRacunDAO getRacunDAO();
	public abstract MySQLRecepcionarDAO getRecepcionarDAO();
	public abstract MySQLSobaDAO getSobaDAO();
	public abstract MySQLSobnaUslugaDAO getSobnaUslugaDAO();
	public abstract MySQLSportskaOpremaDAO getSportskaOpremaDAO();
	public abstract MySQLSportTerminDAO getSportTerminDAO();
	public abstract MySQLSportUslugaDAO getSportUslugaDAO();
	public abstract MySQLStavkaDAO getStavkaDAO();
	public abstract MySQLUslugaRestoranaDAO getUslugaRestoranaDAO();
	public abstract MySQLUtisakDAO getUtisakDAO();
	public abstract MySQLWellnessTerminDAO getWellnessTerminDAO();
	public abstract MySQLWellnessUslugaDAO getWellnessUslugaDAO();
	public abstract MySQLProizvodiRestoranDAO getProizvodiRestoranDAO();
	public abstract MySQLProizvodiSobaDAO getProizvodiSobaDAO();
	public abstract MySQLOpremaSportUslugaDAO getOpremaSportUslugaDAO();
	public abstract MySQLRegistracijaDAO getRegistracijaDAO();
}
