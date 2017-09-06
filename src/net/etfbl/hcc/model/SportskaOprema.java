package net.etfbl.hcc.model;

public class SportskaOprema {

	private int idSportskeOpreme;
	private String naziv;
	private double cijena;

	public SportskaOprema() {
		// TODO Auto-generated constructor stub
	}
	public SportskaOprema(int idSportskeOpreme, String naziv, double cijena) {
		this.idSportskeOpreme = idSportskeOpreme;
		this.naziv = naziv;
		this.cijena = cijena;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idSportskeOpreme;
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
		SportskaOprema other = (SportskaOprema) obj;
		if (idSportskeOpreme != other.idSportskeOpreme)
			return false;
		return true;
	}
	public int getIdSportskeOpreme() {
		return idSportskeOpreme;
	}
	public void setIdSportskeOpreme(int idSportskeOpreme) {
		this.idSportskeOpreme = idSportskeOpreme;
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
