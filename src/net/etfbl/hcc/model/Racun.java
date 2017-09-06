package net.etfbl.hcc.model;

import java.util.ArrayList;

public class Racun {

	private int idRacuna;
	private boolean placen;
	private Popust popust;
	private ArrayList<Stavka> stavke;

	public Racun() {
		// TODO Auto-generated constructor stub
	}

	public Racun(int idRacuna, boolean placen,Popust p,ArrayList<Stavka> al) {
		this.idRacuna = idRacuna;
		this.placen = placen;
		this.popust=p;
		this.stavke=al;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idRacuna;
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
		Racun other = (Racun) obj;
		if (idRacuna != other.idRacuna)
			return false;
		return true;
	}

	public ArrayList<Stavka> getStavke() {
		return stavke;
	}

	public void setStavke(ArrayList<Stavka> stavke) {
		this.stavke = stavke;
	}

	public Popust getPopust() {
		return popust;
	}

	public void setPopust(Popust popust) {
		this.popust = popust;
	}

	public int getIdRacuna() {
		return idRacuna;
	}

	public void setIdRacuna(int idRacuna) {
		this.idRacuna = idRacuna;
	}

	public boolean isPlacen() {
		return placen;
	}

	public void setPlacen(boolean placen) {
		this.placen = placen;
	}

}
