package net.etfbl.hcc.data.dao;

import java.util.ArrayList;

import net.etfbl.hcc.model.Obavjestenje;

public interface ObavjestenjeDAO {
	public ArrayList<Obavjestenje> getObavjestenja();
	public boolean procitajObavjestenje(Obavjestenje obavjestenje);
}
