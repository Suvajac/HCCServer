package net.etfbl.hcc.data.dao;

import java.sql.Date;

import net.etfbl.hcc.model.Recepcionar;

public interface RecepcionarDAO {
	public Recepcionar getKorisnik(String username);
	public boolean dodaj(Recepcionar recepcionar);
	public boolean azuriraj(Recepcionar recepcionar);//samo sifra
}
