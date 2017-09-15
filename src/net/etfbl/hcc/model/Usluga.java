package net.etfbl.hcc.model;

import java.io.Serializable;

public class Usluga implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int idUsluge;
	private String naziv;
	private double cijena;

	public Usluga() {
		// TODO Auto-generated constructor stub
	}
	public Usluga(int idUsluge, String naziv,double cijena) {
		this.idUsluge = idUsluge;
		this.naziv = naziv;
		this.cijena=cijena;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUsluge;
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
		Usluga other = (Usluga) obj;
		if (idUsluge != other.idUsluge)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Usluga [naziv=" + naziv + ", cijena=" + cijena + "]";
	}
	
	public double getCijena() {
		return cijena;
	}
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
	public int getIdUsluge() {
		return idUsluge;
	}
	public void setIdUsluge(int idUsluge) {
		this.idUsluge = idUsluge;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


}
