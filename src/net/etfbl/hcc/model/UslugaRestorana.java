package net.etfbl.hcc.model;

import java.io.Serializable;

public class UslugaRestorana extends Usluga implements Serializable{
	private Sto sto;

	public UslugaRestorana() {
		// TODO Auto-generated constructor stub
		sto=null;
	}

	public UslugaRestorana(int idUsluge, String naziv,double c) {
		super(idUsluge, naziv,c);
		// TODO Auto-generated constructor stub
		sto=null;
	}

	public Sto getSto() {
		return sto;
	}

	public void setSto(Sto sto) {
		this.sto = sto;
	}

}
