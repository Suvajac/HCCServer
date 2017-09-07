package net.etfbl.hcc.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SportUsluga extends Usluga implements Serializable{
	private ArrayList<SportskaOprema> listaOpreme;
	private SportTermin sportTermin;

	public SportUsluga() {
		// TODO Auto-generated constructor stub
		listaOpreme=new ArrayList<SportskaOprema>();
		sportTermin=null;
	}

	public SportUsluga(int idUsluge, String naziv,double c) {
		super(idUsluge, naziv,c);
		listaOpreme=new ArrayList<SportskaOprema>();
		sportTermin=null;
		// TODO Auto-generated constructor stub
	}

	public ArrayList<SportskaOprema> getListaOpreme() {
		return listaOpreme;
	}

	public void setListaOpreme(ArrayList<SportskaOprema> listaOpreme) {
		this.listaOpreme = listaOpreme;
	}

	public SportTermin getSportTermin() {
		return sportTermin;
	}

	public void setSportTermin(SportTermin sportTermin) {
		this.sportTermin = sportTermin;
	}

}
