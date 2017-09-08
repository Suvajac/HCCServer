package net.etfbl.hcc.model;

import java.io.Serializable;
import java.util.Date;

public class WellnessTermin extends Termin implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public WellnessTermin() {
		// TODO Auto-generated constructor stub
	}

	public WellnessTermin(int idTermina, Date datum, String vrijeme) {
		super(idTermina, datum, vrijeme);
		// TODO Auto-generated constructor stub
	}

}
