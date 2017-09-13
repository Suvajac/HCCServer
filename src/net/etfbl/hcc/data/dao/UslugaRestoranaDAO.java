package net.etfbl.hcc.data.dao;

import net.etfbl.hcc.model.UslugaRestorana;

public interface UslugaRestoranaDAO {
	public int dodaj(UslugaRestorana usluga);
	public boolean obrisi(UslugaRestorana usluga);
}
