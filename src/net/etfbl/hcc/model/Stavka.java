package net.etfbl.hcc.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Stavka implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int idStavke;
	private LocalDateTime datum;
	private Usluga usluga;

	public Stavka() {
		// TODO Auto-generated constructor stub
	}
	public Stavka(int idStavke, LocalDateTime datum,Usluga u) {
		this.idStavke = idStavke;
		this.datum = datum;
		this.usluga=u;
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
	public Usluga getUsluga() {
		return usluga;
	}
	public void setUsluga(Usluga usluga) {
		this.usluga = usluga;
	}
	public int getIdStavke() {
		return idStavke;
	}
	public void setIdStavke(int idStavke) {
		this.idStavke = idStavke;
	}
	public LocalDateTime getDatum() {
		return datum;
	}
	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

}
