package net.etfbl.hcc.data.dao;

import net.etfbl.hcc.model.Racun;
import net.etfbl.hcc.model.Korisnik;
import net.etfbl.hcc.model.Popust;

public interface RacunDAO {
	public boolean kreiraj(Racun racun);
	public boolean azuriraj(Racun racun);
	public Racun getRacun(Korisnik korisnik);
	public boolean dodajPopust(Popust popust,Racun racun);
}
