package net.etfbl.hcc.data.dao;

import net.etfbl.hcc.model.SportUsluga;

public interface SportUslugaDAO {
	public int dodaj(SportUsluga usluga);
	public boolean obrisi(SportUsluga usluga);
}
