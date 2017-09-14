package net.etfbl.hcc.data.dao;

import java.util.ArrayList;

import net.etfbl.hcc.model.Gost;

public interface GostDAO {
	public Gost getKorisnik(String username);
	public Gost getKorisnikOld(String username);
	public ArrayList<Gost> getKorisnike();
	public boolean dodaj(Gost gost);
	public boolean azuriraj(Gost gost);//samo sifra
	public boolean noviRacun(Gost gost);
}
