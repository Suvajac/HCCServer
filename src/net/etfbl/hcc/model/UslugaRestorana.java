package net.etfbl.hcc.model;

public class UslugaRestorana extends Usluga {
	private Sto sto;

	public UslugaRestorana() {
		// TODO Auto-generated constructor stub
		sto=null;
	}

	public UslugaRestorana(int idUsluge, String naziv, Stavka s) {
		super(idUsluge, naziv, s);
		// TODO Auto-generated constructor stub
		sto=null;
	}

	public Sto getSto() {
		return sto;
	}

	public void setSto(Sto sto) {
		this.sto = sto;
	}

}
