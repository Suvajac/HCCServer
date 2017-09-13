package net.etfbl.hcc.data.dao;

import java.util.ArrayList;

import net.etfbl.hcc.model.Proizvod;;

public interface ProizvodDAO {
	public int dodaj(Proizvod proizvod);
	public boolean obrisi(Proizvod proizvod);
	public ArrayList<Proizvod> getProizvodi();
}
