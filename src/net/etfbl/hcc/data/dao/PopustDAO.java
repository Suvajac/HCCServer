package net.etfbl.hcc.data.dao;

import net.etfbl.hcc.model.Popust;;

public interface PopustDAO {
	public Popust potvrdiPopust(String kodPopusta);
	public boolean dodaj(Popust popust);
	public boolean obrisi(Popust popust);
}
