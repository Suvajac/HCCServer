package net.etfbl.hcc.model;

import java.io.Serializable;

public class UslugaRestorana extends Usluga implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Sto sto;
	private String vrijeme;

	public UslugaRestorana() {
		// TODO Auto-generated constructor stub
		sto=null;
	}

	public UslugaRestorana(int idUsluge, String naziv,double c,String vrijeme) {
		super(idUsluge, naziv,c);
		// TODO Auto-generated constructor stub
		this.vrijeme=vrijeme;
		sto=null;
	}

	public String getVrijeme() {
		return vrijeme;
	}

	public void setVrijeme(String vrijeme) {
		this.vrijeme = vrijeme;
	}

	public Sto getSto() {
		return sto;
	}

	public void setSto(Sto sto) {
		this.sto = sto;
	}

}
