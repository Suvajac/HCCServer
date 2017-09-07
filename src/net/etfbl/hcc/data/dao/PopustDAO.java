package net.etfbl.hcc.data.dao;

import net.etfbl.hcc.model.Gost;
import net.etfbl.hcc.model.Popust;;

public interface PopustDAO {
	public Popust potvrdiPopust(String kodPopusta,Gost gost);
	public boolean dodaj(Popust popust);
	public boolean obrisi(Popust popust);
}
