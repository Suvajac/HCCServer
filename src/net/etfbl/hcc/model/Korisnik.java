package net.etfbl.hcc.model;

import java.io.Serializable;

public class Korisnik implements Serializable{
	private String username;
	private String ime;
	private String prezime;
	private String brojTelefona;
	private String lozinkaHash;

	public Korisnik() {
	}

	public Korisnik(String username, String ime, String prezime, String brojTelefona, String lozinkaHash) {
		this.username = username;
		this.ime = ime;
		this.prezime = prezime;
		this.brojTelefona = brojTelefona;
		this.lozinkaHash = lozinkaHash;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Korisnik other = (Korisnik) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getLozinkaHash() {
		return lozinkaHash;
	}
	public void setLozinkaHash(String lozinkaHash) {
		this.lozinkaHash = lozinkaHash;
	}

}
