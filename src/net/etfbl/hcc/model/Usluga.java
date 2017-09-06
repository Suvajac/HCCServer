package net.etfbl.hcc.model;

public class Usluga {
	private int idUsluge;
	private String naziv;
	private Stavka stavka;

	public Usluga() {
		// TODO Auto-generated constructor stub
	}
	public Usluga(int idUsluge, String naziv,Stavka s) {
		this.idUsluge = idUsluge;
		this.naziv = naziv;
		this.stavka=s;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUsluge;
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
		Usluga other = (Usluga) obj;
		if (idUsluge != other.idUsluge)
			return false;
		return true;
	}
	public Stavka getStavka() {
		return stavka;
	}
	public void setStavka(Stavka stavka) {
		this.stavka = stavka;
	}
	public int getIdUsluge() {
		return idUsluge;
	}
	public void setIdUsluge(int idUsluge) {
		this.idUsluge = idUsluge;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


}
