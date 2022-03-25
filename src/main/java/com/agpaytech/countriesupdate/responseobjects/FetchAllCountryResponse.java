package com.agpaytech.countriesupdate.responseobjects;

import java.util.ArrayList;
import java.util.List;

import com.agpaytech.countriesupdate.DummyCountry;

public class FetchAllCountryResponse {

	private List<DummyCountry> countryList=new ArrayList<DummyCountry>();
	private String message;

	public FetchAllCountryResponse() {
		
	}
	
	

	public FetchAllCountryResponse(List<DummyCountry> countryList, String message) {
		this.countryList = countryList;
		this.message = message;
	}



	public List<DummyCountry> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<DummyCountry> countryList) {
		this.countryList = countryList;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	} 
	
	
}
