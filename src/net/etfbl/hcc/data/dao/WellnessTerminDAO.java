package net.etfbl.hcc.data.dao;

import net.etfbl.hcc.model.WellnessTermin;

public interface WellnessTerminDAO extends TerminDAO {
	public int dodaj(WellnessTermin termin);
	public boolean obrisi(WellnessTermin termin);
}
