package net.etfbl.hcc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Gost extends Korisnik implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Racun racun;
	private Soba soba;
	private Date DatumOd;
	private Date DatumDo;
	private ArrayList<Usluga> usluge;

	public Gost() {
		// TODO Auto-generated constructor stub
	}

	public Gost(String username, String ime, String prezime, String brojTelefona, String lozinkaHash) {
		super(username, ime, prezime, brojTelefona, lozinkaHash);
		// TODO Auto-generated constructor stub
		usluge=new ArrayList<Usluga>();
		soba=null;
		racun=null;
	}

	public Date getDatumOd() {
		return DatumOd;
	}

	public void setDatumOd(Date datumOd) {
		DatumOd = datumOd;
	}

	public Date getDatumDo() {
		return DatumDo;
	}

	public void setDatumDo(Date datumDo) {
		DatumDo = datumDo;
	}

	public Racun getRacun() {
		return racun;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}

	public Soba getSoba() {
		return soba;
	}

	public void setSoba(Soba soba) {
		this.soba = soba;
	}

	public ArrayList<Usluga> getUsluge() {
		return usluge;
	}

	public void setUsluge(ArrayList<Usluga> usluge) {
		this.usluge = usluge;
	}

}
