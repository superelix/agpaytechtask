package com.agpaytech.countriesupdate;

public class DummyCountry {

	private String capital;
	private String country;
	
	public DummyCountry() {
		
	}

	public DummyCountry(String capital, String country) {
		
		this.capital = capital;
		this.country = country;
	}

	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "DummyCountry [capital=" + capital + ", country=" + country + "]";
	}


	
}
