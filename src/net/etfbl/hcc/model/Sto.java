package net.etfbl.hcc.model;

import java.io.Serializable;

public class Sto implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int idStola;
	private int brojStolica;
	private boolean rezervisan;

	public Sto() {
		// TODO Auto-generated constructor stub
	}
	public Sto(int idStola, int brojStolica, boolean rezervisan) {
		this.idStola = idStola;
		this.brojStolica = brojStolica;
		this.rezervisan = rezervisan;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idStola;
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
		Sto other = (Sto) obj;
		if (idStola != other.idStola)
			return false;
		return true;
	}
	public int getIdStola() {
		return idStola;
	}
	public void setIdStola(int idStola) {
		this.idStola = idStola;
	}
	public int getBrojStolica() {
		return brojStolica;
	}
	public void setBrojStolica(int brojStolica) {
		this.brojStolica = brojStolica;
	}
	public boolean isRezervisan() {
		return rezervisan;
	}
	public void setRezervisan(boolean rezervisan) {
		this.rezervisan = rezervisan;
	}



}
