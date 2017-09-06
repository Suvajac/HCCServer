package net.etfbl.hcc.data.dao;

import java.util.ArrayList;

import net.etfbl.hcc.model.Korisnik;
import net.etfbl.hcc.model.Utisak;

public interface UtisakDAO {
	public boolean dodaj(Korisnik korisnik,Utisak utisak);
	public ArrayList<Utisak> getUtisci(int OD,int DO);
	public boolean obrisi(Utisak utisak);
}
