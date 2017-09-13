package net.etfbl.hcc.data.dao;

import net.etfbl.hcc.model.SobnaUsluga;;

public interface SobnaUslugaDAO {
	public int dodaj(SobnaUsluga usluga);
	public boolean obrisi(SobnaUsluga usluga);
}
