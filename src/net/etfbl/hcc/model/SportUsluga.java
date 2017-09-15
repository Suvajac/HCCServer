package net.etfbl.hcc.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SportUsluga extends Usluga implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

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
	
	@Override
	public String toString() {
		String temp = "Sport usluga [termin=" + sportTermin + "]\n";
		
		if(listaOpreme.size()>0){
			ArrayList<SportskaOprema> tempLista = new ArrayList<>();
			for(SportskaOprema op : listaOpreme){
				tempLista.add(op);
			}
			for(int i=0;i<tempLista.size();i++){
				Proizvod p = tempLista.get(i);
				int kolicina = 1;
				for(int j=0;j<tempLista.size();j++){
					Proizvod pp = tempLista.get(j);
					if(i!=j && pp.equals(p)){
						tempLista.remove(pp);
						kolicina++;
					}
				}
				temp+=p+", kolicina="+kolicina+"]\n";
			}
		}
		return temp;
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
