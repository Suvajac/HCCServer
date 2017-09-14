package net.etfbl.hcc.data.dao.mysql;

import net.etfbl.hcc.data.dao.DAOFactory;

public class MySQLDAOFactory extends DAOFactory{

	@Override
	public MySQLGostDAO getGostDAO(){
		return new MySQLGostDAO();
	}

	@Override
	public MySQLObavjestenjeDAO getObavjestenjeDAO() {
		return new MySQLObavjestenjeDAO();
	}

	@Override
	public MySQLOglasDAO getOglasDAO() {
		return new MySQLOglasDAO();
	}

	@Override
	public MySQLPopustDAO getPopustDAO() {
		return new MySQLPopustDAO();
	}

	@Override
	public MySQLProizvodDAO getProizvodDAO() {
		return new MySQLProizvodDAO();
	}

	@Override
	public MySQLRacunDAO getRacunDAO() {
		return new MySQLRacunDAO();
	}

	@Override
	public MySQLRecepcionarDAO getRecepcionarDAO() {
		return new MySQLRecepcionarDAO();
	}

	@Override
	public MySQLSobaDAO getSobaDAO() {
		return new MySQLSobaDAO();
	}

	@Override
	public MySQLSobnaUslugaDAO getSobnaUslugaDAO() {
		return new MySQLSobnaUslugaDAO();
	}

	@Override
	public MySQLSportskaOpremaDAO getSportskaOpremaDAO() {
		return new MySQLSportskaOpremaDAO();
	}

	@Override
	public MySQLSportTerminDAO getSportTerminDAO() {
		return new MySQLSportTerminDAO();
	}

	@Override
	public MySQLSportUslugaDAO getSportUslugaDAO() {
		return new MySQLSportUslugaDAO();
	}

	@Override
	public MySQLStavkaDAO getStavkaDAO() {
		return new MySQLStavkaDAO();
	}

	@Override
	public MySQLUslugaRestoranaDAO getUslugaRestoranaDAO() {
		return new MySQLUslugaRestoranaDAO();
	}

	@Override
	public MySQLUtisakDAO getUtisakDAO() {
		return new MySQLUtisakDAO();
	}

	@Override
	public MySQLWellnessTerminDAO getWellnessTerminDAO() {
		return new MySQLWellnessTerminDAO();
	}

	@Override
	public MySQLWellnessUslugaDAO getWellnessUslugaDAO() {
		return new MySQLWellnessUslugaDAO();
	}

	@Override
	public MySQLProizvodiRestoranDAO getProizvodiRestoranDAO() {
		return new MySQLProizvodiRestoranDAO();
	}

	@Override
	public MySQLProizvodiSobaDAO getProizvodiSobaDAO() {
		return new MySQLProizvodiSobaDAO();
	}

	@Override
	public MySQLOpremaSportUslugaDAO getOpremaSportUslugaDAO() {
		return new MySQLOpremaSportUslugaDAO();
	}

	@Override
	public MySQLRegistracijaDAO getRegistracijaDAO() {
		return new MySQLRegistracijaDAO();
	}

}
