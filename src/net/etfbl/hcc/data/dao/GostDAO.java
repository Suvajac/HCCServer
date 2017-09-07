package net.etfbl.hcc.data.dao;

import java.sql.Date;

import net.etfbl.hcc.model.Gost;

public interface GostDAO {
	public Gost getKorisnik(String username);
	public boolean dodaj(Gost gost,Date OD,Date DO);
	public boolean azuriraj(Gost gost);
}
