package net.etfbl.hcc.data.dao;

import net.etfbl.hcc.model.Termin;

public interface TerminDAO {
	public boolean provjeriTermin(Termin termin);
	public boolean dodaj(Termin termin);
	public boolean obrisi(Termin termin);
}
