package net.etfbl.hcc.data.dao;

import net.etfbl.hcc.model.Usluga;

public interface UslugaDAO {
	public boolean dodaj(Usluga usluga);
	public boolean obrisi(Usluga usluga);
}
