package net.etfbl.hcc.util;

import java.io.Serializable;
import java.util.ArrayList;

public class ProtokolPoruka implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String tip;
	private Object[] objekti;


	public ProtokolPoruka() {
		super();
	}

	public ProtokolPoruka(String tip){
		this.tip = tip;
	}

	public ProtokolPoruka(String tip, Object[] objekti) {
		super();
		this.tip = tip;
		this.objekti = objekti;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Object[] getObjekti() {
		return objekti;
	}

	public void setObjekti(Object[] objekti) {
		this.objekti = objekti;
	}


	

}
