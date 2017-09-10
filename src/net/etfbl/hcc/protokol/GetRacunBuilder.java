package net.etfbl.hcc.protokol;

import java.util.ArrayList;

public class GetRacunBuilder extends ProtokolPorukaBuilder{
	
	public GetRacunBuilder(){
		protokolPoruka.setTip("racun");
	}
	
	public void buildObjekti(){
		protokolPoruka.setListaObjekata(null);
	}
	
	public void buildObjekti(ArrayList<Object> lista){
		protokolPoruka.setListaObjekata(lista);
	}
}
