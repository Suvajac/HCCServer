package net.etfbl.hcc.model;

import java.io.Serializable;

public class WellnessUsluga extends Usluga implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private WellnessTermin wellnessTermin;

	public WellnessUsluga() {
		// TODO Auto-generated constructor stub
		wellnessTermin=null;
	}

	public WellnessUsluga(int idUsluge, String naziv,double c) {
		super(idUsluge, naziv,c);
		wellnessTermin=null;
		// TODO Auto-generated constructor stub
	}

	public WellnessTermin getWellnessTermin() {
		return wellnessTermin;
	}

	public void setWellnessTermin(WellnessTermin wellnessTermin) {
		this.wellnessTermin = wellnessTermin;
	}

}
