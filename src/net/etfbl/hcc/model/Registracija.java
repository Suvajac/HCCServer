package net.etfbl.hcc.model;

import java.util.Date;

public class Registracija {
	private Date datumOd;
	private Date datumDo;
	private Korisnik korisnik;
	private Soba soba;

	public Registracija() {
		// TODO Auto-generated constructor stub
	}
	public Registracija(Date datumOd, Date datumDo, Korisnik username, Soba idSobe) {
		this.datumOd = datumOd;
		this.datumDo = datumDo;
		this.korisnik = username;
		this.soba = idSobe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((korisnik == null) ? 0 : korisnik.hashCode());
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
		if (korisnik == null) {
			if (other.korisnik != null)
				return false;
		} else if (!korisnik.equals(other.korisnik))
			return false;
		if (soba == null) {
			if (other.soba != null)
				return false;
		} else if (!soba.equals(other.soba))
			return false;
		return true;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public Soba getSoba() {
		return soba;
	}
	public void setSoba(Soba soba) {
		this.soba = soba;
	}
	public Date getDatumOd() {
		return datumOd;
	}
	public void setDatumOd(Date datumOd) {
		this.datumOd = datumOd;
	}
	public Date getDatumDo() {
		return datumDo;
	}
	public void setDatumDo(Date datumDo) {
		this.datumDo = datumDo;
	}



}
