package net.etfbl.hcc.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Registracija implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private LocalDate datumOd;
	private LocalDate datumDo;
	private Gost gost;
	private Soba soba;

	public Registracija() {
		// TODO Auto-generated constructor stub
	}
	public Registracija(LocalDate datumOd, LocalDate datumDo, Gost gost, Soba idSobe) {
		this.datumOd = datumOd;
		this.datumDo = datumDo;
		this.gost = gost;
		this.soba = idSobe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gost == null) ? 0 : gost.hashCode());
		result = prime * result + ((soba == null) ? 0 : soba.hashCode());
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
		Registracija other = (Registracija) obj;
		if (gost == null) {
			if (other.gost != null)
				return false;
		} else if (!gost.equals(other.gost))
			return false;
		if (soba == null) {
			if (other.soba != null)
				return false;
		} else if (!soba.equals(other.soba))
			return false;
		return true;
	}
	public Gost getGost() {
		return gost;
	}
	public void setGost(Gost gost) {
		this.gost = gost;
	}
	public Soba getSoba() {
		return soba;
	}
	public void setSoba(Soba soba) {
		this.soba = soba;
	}
	public LocalDate getDatumOd() {
		return datumOd;
	}
	public void setDatumOd(LocalDate datumOd) {
		this.datumOd = datumOd;
	}
	public LocalDate getDatumDo() {
		return datumDo;
	}
	public void setDatumDo(LocalDate datumDo) {
		this.datumDo = datumDo;
	}



}
