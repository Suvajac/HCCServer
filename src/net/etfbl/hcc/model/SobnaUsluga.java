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
	
	@Override
	public String toString() {
		String temp = "Sobna usluga [tip=" + tip + "]\n";
		
		if(listaProizvoda.size()>0){
			ArrayList<Proizvod> tempLista = new ArrayList<>();
			for(Proizvod p : listaProizvoda){
				tempLista.add(p);
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

	public ArrayList<Proizvod> getListaProizvoda() {
		return listaProizvoda;
	}

	public void setListaProizvoda(ArrayList<Proizvod> listaProizvoda) {
		this.listaProizvoda = listaProizvoda;
	}

}
