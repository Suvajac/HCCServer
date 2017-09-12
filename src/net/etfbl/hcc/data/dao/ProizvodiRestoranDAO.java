package net.etfbl.hcc.data.dao;

import java.util.ArrayList;

import net.etfbl.hcc.model.Proizvod;
import net.etfbl.hcc.model.UslugaRestorana;

public interface ProizvodiRestoranDAO {
	public ArrayList<Proizvod> getProizvodi(UslugaRestorana usluga);
	public boolean setProizvodi(UslugaRestorana usluga);
}
