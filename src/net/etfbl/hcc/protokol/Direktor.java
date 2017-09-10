package net.etfbl.hcc.protokol;

public class Direktor {

	private ProtokolPorukaBuilder protokolPorukaBuilder;

	public Direktor(ProtokolPorukaBuilder protokolPorukaBuilder) {
		super();
		this.protokolPorukaBuilder = protokolPorukaBuilder;
	}

	public void construct() {
		protokolPorukaBuilder.buildTipPoruke();;
		protokolPorukaBuilder.buildObjekti();
	}

	public ProtokolPorukaBuilder getProtokolPorukaBuilder() {
		return protokolPorukaBuilder;
	}

	public void setProtokolPorukaBuilder(ProtokolPorukaBuilder protokolPorukaBuilder) {
		this.protokolPorukaBuilder = protokolPorukaBuilder;
	}

}
