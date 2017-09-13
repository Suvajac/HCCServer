package net.etfbl.hcc.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Gost extends Korisnik implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Racun racun;
	private ArrayList<Usluga> usluge;

	public Gost() {
		// TODO Auto-generated constructor stub
	}

	public Gost(String username, String ime, String prezime, String brojTelefona, String lozinkaHash) {
		super(username, ime, prezime, brojTelefona, lozinkaHash);
		// TODO Auto-generated constructor stub
		usluge=new ArrayList<Usluga>();
		racun=null;
	}

	public Racun getRacun() {
		return racun;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}

	public ArrayList<Usluga> getUsluge() {
		return usluge;
	}

	public void setUsluge(ArrayList<Usluga> usluge) {
		this.usluge = usluge;
	}

}
