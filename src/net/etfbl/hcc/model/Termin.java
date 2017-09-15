package net.etfbl.hcc.model;

import java.io.Serializable;
import java.util.Date;

public class Termin implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int idTermina;
	private Date datum;
	private String vrijeme;

	public Termin() {
		// TODO Auto-generated constructor stub
	}
	public Termin(int idTermina, Date datum,String vrijeme) {
		this.idTermina = idTermina;
		this.datum = datum;
		this.vrijeme = vrijeme;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTermina;
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
		Termin other = (Termin) obj;
		if (idTermina != other.idTermina)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Termin datum=" + datum + ", vrijeme=" + vrijeme;
	}
	
	public String getVrijeme() {
		return vrijeme;
	}
	public void setVrijeme(String vrijeme) {
		this.vrijeme = vrijeme;
	}
	public int getIdTermina() {
		return idTermina;
	}
	public void setIdTermina(int idTermina) {
		this.idTermina = idTermina;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}


}
