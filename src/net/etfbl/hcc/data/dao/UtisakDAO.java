package net.etfbl.hcc.data.dao;

import java.util.ArrayList;

import net.etfbl.hcc.model.Utisak;

public interface UtisakDAO {
	public boolean dodaj(Utisak utisak);
	public ArrayList<Utisak> getUtisci();
	public boolean obrisi(Utisak utisak);
}
