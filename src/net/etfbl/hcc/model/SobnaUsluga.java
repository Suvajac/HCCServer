package net.etfbl.hcc.model;

import java.io.Serializable;

public class SobnaUsluga extends Usluga implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String tip;

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public SobnaUsluga() {
		// TODO Auto-generated constructor stub
	}

	public SobnaUsluga(int idUsluge, String naziv,double c,String t) {
		super(idUsluge, naziv,c);
		this.tip=t;
		// TODO Auto-generated constructor stub
	}

}
