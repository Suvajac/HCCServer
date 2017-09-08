package net.etfbl.hcc.model;

import java.io.Serializable;

public class Recepcionar extends Korisnik implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Recepcionar() {
		// TODO Auto-generated constructor stub
	}

	public Recepcionar(String username, String ime, String prezime, String brojTelefona, String lozinkaHash) {
		super(username, ime, prezime, brojTelefona, lozinkaHash);
		// TODO Auto-generated constructor stub
	}

}
