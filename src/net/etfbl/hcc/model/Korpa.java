package net.etfbl.hcc.model;

import java.util.ArrayList;
import java.util.List;

public class Korpa {
	private ArrayList<Proizvod> listaProizvoda;

	public Korpa(){
		listaProizvoda = new ArrayList<>();
	}

	public boolean add(Proizvod p){
		return listaProizvoda.add(p);
	}

	public boolean remove(Proizvod p){
		return listaProizvoda.remove(p);
	}

	public double getUkupnaCijena(){
		double cijena = 0.0;
		for(Proizvod p : listaProizvoda){
			cijena+=p.cijena;
		}
		return cijena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listaProizvoda == null) ? 0 : listaProizvoda.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korpa other = (Korpa) obj;
		if (listaProizvoda == null) {
			if (other.listaProizvoda != null)
				return false;
		} else if (!listaProizvoda.equals(other.listaProizvoda))
			return false;
		return true;
	}

	public ArrayList<Proizvod> getListaProizvoda() {
		return listaProizvoda;
	}

	public void setListaProizvoda(ArrayList<Proizvod> listaProizvoda) {
		this.listaProizvoda = listaProizvoda;
	}
}
