package net.etfbl.hcc.data.dao;

import net.etfbl.hcc.model.Korisnik;

public interface KorisnikDAO {
	public Korisnik getKorisnik(String username);
	public boolean dodaj(Korisnik korisnik);
	public boolean azuriraj(Korisnik korisnik);
}
