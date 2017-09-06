package net.etfbl.hcc.model;

import java.util.Date;

public class Stavka {

	private int idStavke;
	private double cijena;
	private Date datum;

	public Stavka() {
		// TODO Auto-generated constructor stub
	}
	public Stavka(int idStavke, double cijena, Date datum) {
		this.idStavke = idStavke;
		this.cijena = cijena;
		this.datum = datum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idStavke;
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
		Stavka other = (Stavka) obj;
		if (idStavke != other.idStavke)
			return false;
		return true;
	}
	public int getIdStavke() {
		return idStavke;
	}
	public void setIdStavke(int idStavke) {
		this.idStavke = idStavke;
	}
	public double getCijena() {
		return cijena;
	}
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}

}
