package net.etfbl.hcc.protokol;

import java.util.ArrayList;

public class RacunBuilder extends ProtokolPorukaBuilder{

	private ArrayList<Object> lista;
	public RacunBuilder(ArrayList<Object> lista){
		this.lista=lista;
	}

	@Override
	public void buildObjekti(){
		protokolPoruka.setListaObjekata(lista);
	}


	@Override
	public void buildTipPoruke() {
		protokolPoruka.setTip("racun");// i za tip isto ko i za listu jer nije isto drugacije je xD
	}
}
