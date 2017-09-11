package net.etfbl.hcc.util;

import java.io.Serializable;
import java.util.ArrayList;

public class ProtokolPoruka implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String tip;
	private ArrayList<Object> listaObjekata;


	public ProtokolPoruka() {
		super();
	}

	public ProtokolPoruka(String tip){
		this.tip = tip;
	}
	
	public ProtokolPoruka(String tip, ArrayList<Object> listaObjekata) {
		super();
		this.tip = tip;
		this.listaObjekata = listaObjekata;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public ArrayList<Object> getListaObjekata() {
		return listaObjekata;
	}

	public void setListaObjekata(ArrayList<Object> listaObjekata) {
		this.listaObjekata = listaObjekata;
	}
}
