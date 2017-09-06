package net.etfbl.hcc.model;

import java.io.Serializable;
import java.util.Date;

public class Oglas implements Serializable{
	private int idOglasa;
	private String poruka;
	private Date datum;

	public Oglas() {
		// TODO Auto-generated constructor stub
	}
	public Oglas(int idOglasa, String poruka, Date datum) {
		this.idOglasa = idOglasa;
		this.poruka = poruka;
		this.datum = datum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idOglasa;
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
		Oglas other = (Oglas) obj;
		if (idOglasa != other.idOglasa)
			return false;
		return true;
	}
	public int getIdOglasa() {
		return idOglasa;
	}
	public void setIdOglasa(int idOglasa) {
		this.idOglasa = idOglasa;
	}
	public String getPoruka() {
		return poruka;
	}
	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}

}
