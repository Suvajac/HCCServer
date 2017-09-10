package net.etfbl.hcc.protokol;

public abstract class ProtokolPorukaBuilder {

	protected ProtokolPoruka protokolPoruka;

	public ProtokolPorukaBuilder() {
		protokolPoruka = new ProtokolPoruka();
	}

	public abstract void buildTipPoruke();
	public abstract void buildObjekti();

	public ProtokolPoruka getProtokolPoruka(){
		return this.protokolPoruka;
	}

	public void setProtokolPoruka(ProtokolPoruka protokolPoruka) {
		this.protokolPoruka = protokolPoruka;
	}
}
