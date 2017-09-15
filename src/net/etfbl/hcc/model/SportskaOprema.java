package net.etfbl.hcc.model;

import java.io.Serializable;

public class SportskaOprema extends Proizvod implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String velicina;
	
	public SportskaOprema() {
		// TODO Auto-generated constructor stub
	}
		
	public SportskaOprema(int idProizvoda, String naziv, double cijena,String velicina) {
		super(idProizvoda,"oprema",naziv,cijena);
		this.velicina = velicina;
	}	

	@Override
	public String toString() {
		return "SportskaOprema [naziv=" + naziv + ", cijena=" + cijena+ "[velicina=" + velicina;
	}

	public String getVelicina() {
		return velicina;
	}

	public void setVelicina(String velicina) {
		this.velicina = velicina;
	}
	
	
}
