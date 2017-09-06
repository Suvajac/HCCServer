package net.etfbl.hcc.model;

public class Racun {

	private int idRacuna;
	private boolean placen;

	public Racun() {
		// TODO Auto-generated constructor stub
	}

	public Racun(int idRacuna, boolean placen) {
		this.idRacuna = idRacuna;
		this.placen = placen;
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
