package net.etfbl.hcc.model;

public class WellnessUsluga extends Usluga {
	private WellnessTermin wellnessTermin;

	public WellnessUsluga() {
		// TODO Auto-generated constructor stub
		wellnessTermin=null;
	}

	public WellnessUsluga(int idUsluge, String naziv, Stavka s) {
		super(idUsluge, naziv, s);
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
