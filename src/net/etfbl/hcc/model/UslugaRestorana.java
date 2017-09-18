package net.etfbl.hcc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
		listaProizvoda=new ArrayList<Proizvod>();
	}

	public UslugaRestorana(int idUsluge, String naziv,double c,String vrijeme,int brojStolica) {
		super(idUsluge, naziv,c);
		// TODO Auto-generated constructor stub
		this.vrijeme=vrijeme;
		this.brojStolica=brojStolica;
		listaProizvoda=new ArrayList<Proizvod>();
	}

	@Override
	public String toString() {
		String temp = "UslugaRestorana [vrijeme=" + vrijeme + ", brojStolica="
				+ brojStolica + "]\n";
		
		ArrayList<Proizvod> tempLista = new ArrayList<>();
		
		for(Proizvod p : listaProizvoda) {
			if(!tempLista.contains(p)) {
				temp+=p+", kolicina="+Collections.frequency(listaProizvoda, p)+"]\n";
				tempLista.add(p);
			}
		}
		
		tempLista=null;
		return temp;
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
