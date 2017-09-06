package net.etfbl.hcc.data.dao;

import java.util.ArrayList;

import net.etfbl.hcc.model.Racun;
import net.etfbl.hcc.model.Stavka;

public interface StavkaDAO {
	public boolean dodaj(Stavka stavka,Racun racun);
	public ArrayList<Stavka> getStavke(Racun racun);
}
