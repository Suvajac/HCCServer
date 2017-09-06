package net.etfbl.hcc.model;

public class Popust {
	private String kodPopusta;
	private double procenat;
	private boolean aktivan;

	public Popust() {
		// TODO Auto-generated constructor stub
	}

	public Popust(String kodPopusta, double procenat, boolean aktivan) {
		this.kodPopusta = kodPopusta;
		this.procenat = procenat;
		this.aktivan = aktivan;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kodPopusta == null) ? 0 : kodPopusta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Popust other = (Popust) obj;
		if (kodPopusta == null) {
			if (other.kodPopusta != null)
				return false;
		} else if (!kodPopusta.equals(other.kodPopusta))
			return false;
		return true;
	}

	public String getKodPopusta() {
		return kodPopusta;
	}

	public void setKodPopusta(String kodPopusta) {
		this.kodPopusta = kodPopusta;
	}

	public double getProcenat() {
		return procenat;
	}

	public void setProcenat(double procenat) {
		this.procenat = procenat;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}


}
