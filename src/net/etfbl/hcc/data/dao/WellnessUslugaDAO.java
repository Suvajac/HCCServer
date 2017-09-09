package net.etfbl.hcc.data.dao;

import net.etfbl.hcc.model.WellnessUsluga;

public interface WellnessUslugaDAO {
	public int dodaj(WellnessUsluga usluga);
	public boolean obrisi(WellnessUsluga usluga);
}
