package net.etfbl.hcc.model;

import java.io.Serializable;
import java.util.ArrayList;

public class UslugaRestorana extends Usluga implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String vrijeme;
	private ArrayList<Proizvod> listaProizvoda;
	private int brojStolica;

	public UslugaRestorana() {
		// TODO Auto-generated constructor stub
	}

	public UslugaRestorana(int idUsluge, String naziv,double c,String vrijeme,int brojStolica) {
		super(idUsluge, naziv,c);
		// TODO Auto-generated constructor stub
		this.vrijeme=vrijeme;
		this.brojStolica=brojStolica;
	}

	public String getVrijeme() {
		return vrijeme;
	}

	public void setVrijeme(String vrijeme) {
		this.vrijeme = vrijeme;
	}

	public ArrayList<Proizvod> getListaProizvoda() {
		return listaProizvoda;
	}

	public void setListaProizvoda(ArrayList<Proizvod> listaProizvoda) {
		this.listaProizvoda = listaProizvoda;
	}

	public int getBrojStolica() {
		return brojStolica;
	}

	public void setBrojStolica(int brojStolica) {
		this.brojStolica = brojStolica;
	}
	
	

}
