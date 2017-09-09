package net.etfbl.hcc.data.dao;

import net.etfbl.hcc.model.SportTermin;

public interface SportTerminDAO extends TerminDAO {
	public boolean dodaj(SportTermin termin);
	public boolean obrisi(SportTermin termin);
}
