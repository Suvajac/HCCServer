package net.etfbl.hcc.model;

import java.io.Serializable;

public class Popust implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int kodPopusta;
	private double procenat;
	private boolean aktivan;

	public Popust() {
		// TODO Auto-generated constructor stub
	}

	public Popust(int kodPopusta, double procenat, boolean aktivan) {
		this.kodPopusta = kodPopusta;
		this.procenat = procenat;
		this.aktivan = aktivan;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + kodPopusta;
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
		if (kodPopusta != other.kodPopusta)
			return false;
		return true;
	}

	public int getKodPopusta() {
		return kodPopusta;
	}

	public void setKodPopusta(int kodPopusta) {
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
