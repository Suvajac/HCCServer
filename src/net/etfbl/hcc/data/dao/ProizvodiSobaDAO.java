package net.etfbl.hcc.data.dao;

import java.util.ArrayList;

import net.etfbl.hcc.model.Proizvod;
import net.etfbl.hcc.model.SobnaUsluga;

public interface ProizvodiSobaDAO {
	public ArrayList<Proizvod> getProizvodi(SobnaUsluga usluga);
	public boolean setProizvodi(SobnaUsluga usluga);
}
