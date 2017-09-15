package net.etfbl.hcc.data.dao;

import java.util.ArrayList;

import net.etfbl.hcc.model.Gost;
import net.etfbl.hcc.model.Popust;;

public interface PopustDAO {
	public boolean potvrdiPopust(int kodPopusta,Gost gost);
	public boolean dodaj(Popust popust);
	public boolean provjeriPopust(int kodPopusta);
	public boolean obrisi(Popust popust);
	public ArrayList<Popust> getPopuste();
	public boolean popustIskoristi(int kodPopusta);
}
