package net.etfbl.hcc.util;

import java.io.Serializable;
import java.util.ArrayList;

import net.etfbl.hcc.model.ObjectHCC;

public class ProtokolPoruka implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String tip;
	private ArrayList<ObjectHCC> listaObjekata;


	public ProtokolPoruka() {
		super();
	}

	public ProtokolPoruka(String tip){
		this.tip = tip;
		listaObjekata = new ArrayList<>();
	}

	public ProtokolPoruka(String tip, ArrayList<ObjectHCC> listaObjekata) {
		super();
		this.tip = tip;
		this.listaObjekata = listaObjekata;
	}

	public void add(ObjectHCC o){
		listaObjekata.add(o);
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public ArrayList<ObjectHCC> getListaObjekata() {
		return listaObjekata;
	}

	public void setListaObjekata(ArrayList<ObjectHCC> listaObjekata) {
		this.listaObjekata = listaObjekata;
	}



}
