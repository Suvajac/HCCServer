package net.etfbl.hcc.protokol;

import java.util.ArrayList;

public abstract class ProtokolPorukaBuilder {
	protected ProtokolPoruka protokolPoruka;
	
	
	public ProtokolPorukaBuilder() {
		protokolPoruka = new ProtokolPoruka();
	}
	
	public abstract void buildObjekti();
	public abstract void buildObjekti(ArrayList<Object> lista);
	
	public ProtokolPoruka getProtokolPoruka(){
		return protokolPoruka;
	}
}
