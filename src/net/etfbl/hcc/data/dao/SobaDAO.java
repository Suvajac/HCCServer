package net.etfbl.hcc.data.dao;

import java.util.ArrayList;

import net.etfbl.hcc.model.Soba;

public interface SobaDAO {
	public Soba getSobu(int brojKreveta);
	public Soba getSobuSaBrojem(int broj);
	public ArrayList<Soba> getSobe();
}
