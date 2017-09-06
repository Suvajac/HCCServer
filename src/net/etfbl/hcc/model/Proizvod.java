package net.etfbl.hcc.model;

import java.io.Serializable;

public class Proizvod implements Serializable{
	private int idProizvoda;
	private String tip;
	private String naziv;
	private double cijena;

	public Proizvod() {
		// TODO Auto-generated constructor stub
	}
	public Proizvod(int idProizvoda, String tip, String naziv, double cijena) {
		this.idProizvoda = idProizvoda;
		this.tip = tip;
		this.naziv = naziv;
		this.cijena = cijena;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProizvoda;
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
		Proizvod other = (Proizvod) obj;
		if (idProizvoda != other.idProizvoda)
			return false;
		return true;
	}
	public int getIdProizvoda() {
		return idProizvoda;
	}
	public void setIdProizvoda(int idProizvoda) {
		this.idProizvoda = idProizvoda;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public double getCijena() {
		return cijena;
	}
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}

}
