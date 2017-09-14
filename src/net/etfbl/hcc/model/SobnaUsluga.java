package net.etfbl.hcc.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SobnaUsluga extends Usluga implements Serializable{

	/**
	 *
	 */

	private ArrayList<Proizvod> listaProizvoda;

	private static final long serialVersionUID = 1L;

	private String tip;

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public SobnaUsluga() {
		// TODO Auto-generated constructor stub
		listaProizvoda=new ArrayList<Proizvod>();
	}

	public SobnaUsluga(int idUsluge, String naziv,double c,String t) {
		super(idUsluge, naziv,c);
		this.tip=t;
		listaProizvoda=new ArrayList<Proizvod>();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Proizvod> getListaProizvoda() {
		return listaProizvoda;
	}

	public void setListaProizvoda(ArrayList<Proizvod> listaProizvoda) {
		this.listaProizvoda = listaProizvoda;
	}

}
