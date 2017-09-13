package net.etfbl.hcc.data.dao;

import java.util.ArrayList;

import net.etfbl.hcc.model.SportskaOprema;
import net.etfbl.hcc.model.SportUsluga;;

public interface OpremaSportUslugaDAO {
	public ArrayList<SportskaOprema> getOprema(SportUsluga usluga);
	public boolean setOprema(SportUsluga usluga);
}
