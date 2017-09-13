package net.etfbl.hcc.data.dao;

import java.util.ArrayList;

import net.etfbl.hcc.model.Registracija;

public interface RegistracijaDAO {
	public boolean dodaj(Registracija registracija);
	public ArrayList<Registracija> getRegistracije();
}
