package net.etfbl.hcc.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Obavjestenje implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int idObavjestenje;
	private String tekst;
	private LocalDateTime datum;
	private boolean procitano;

	public Obavjestenje(int idObavjestenje, String tekst, LocalDateTime datum, boolean procitano) {
		this.idObavjestenje = idObavjestenje;
		this.tekst = tekst;
		this.datum = datum;
		this.procitano = procitano;
	}

	public Obavjestenje(String tekst, LocalDateTime datum, boolean procitano) {
		this.tekst = tekst;
		this.datum = datum;
		this.procitano = procitano;
	}

	public Obavjestenje() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idObavjestenje;
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
		Obavjestenje other = (Obavjestenje) obj;
		if (idObavjestenje != other.idObavjestenje)
			return false;
		return true;
	}

	public int getIdObavjestenje() {
		return idObavjestenje;
	}

	public void setIdObavjestenje(int idObavjestenje) {
		this.idObavjestenje = idObavjestenje;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public LocalDateTime getDatum() {
		return datum;
	}

	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

	public boolean isProcitano() {
		return procitano;
	}

	public void setProcitano(boolean procitano) {
		this.procitano = procitano;
	}


}
