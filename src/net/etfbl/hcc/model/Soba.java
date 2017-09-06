package net.etfbl.hcc.model;

public class Soba {
	private int brSobe;
	private int brKreveta;
	private int brSprata;
	private double cijenaPoDanu;

	public Soba(int brSobe, int brKreveta, int brSprata, double cijenaPoDanu) {
		this.brSobe = brSobe;
		this.brKreveta = brKreveta;
		this.brSprata = brSprata;
		this.cijenaPoDanu = cijenaPoDanu;
	}


	public Soba() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brSobe;
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
		Soba other = (Soba) obj;
		if (brSobe != other.brSobe)
			return false;
		return true;
	}


	public int getBrSobe() {
		return brSobe;
	}


	public void setBrSobe(int brSobe) {
		this.brSobe = brSobe;
	}


	public int getBrKreveta() {
		return brKreveta;
	}


	public void setBrKreveta(int brKreveta) {
		this.brKreveta = brKreveta;
	}


	public int getBrSprata() {
		return brSprata;
	}


	public void setBrSprata(int brSprata) {
		this.brSprata = brSprata;
	}


	public double getCijenaPoDanu() {
		return cijenaPoDanu;
	}


	public void setCijenaPoDanu(double cijenaPoDanu) {
		this.cijenaPoDanu = cijenaPoDanu;
	}

}
