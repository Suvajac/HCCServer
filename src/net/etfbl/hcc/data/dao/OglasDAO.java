package net.etfbl.hcc.data.dao;

import java.util.ArrayList;

import net.etfbl.hcc.model.Oglas;

public interface OglasDAO {
	public ArrayList<Oglas> getOglasi();
	public boolean dodaj(Oglas oglas);
}
