package com.agpaytech.countriesupdate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class DummyCountryCollection {
      
	//Considering the country name as PK for dummy data set.
	private List<DummyCountry> countryList=new ArrayList<DummyCountry>(
			Arrays.asList(
                  
		            new DummyCountry("Kabul","Afghanistan"),
		            new DummyCountry("Algiers","Algeria"),
		            new DummyCountry("Canberra","Australia"),
		            new DummyCountry("Brussels","Belgium"),
		            new DummyCountry("Minsk","Belarus"),
		            new DummyCountry("Thimphu","Bhutan"),
		            new DummyCountry("Bogota","Colombia"),
		            new DummyCountry("San Jose","Costa Rica"),
		            new DummyCountry("Havana","Cuba"),
		            new DummyCountry("Malabo","Equatorial Guinea"),
		            new DummyCountry("Palikir","Federated States of Micronesia"),
		            new DummyCountry("Helsinki","Finland"),
		            new DummyCountry("Paris","France"),
		            new DummyCountry("Tbilisi","Georgia"),
		            new DummyCountry("Berlin","Germany")
		    )
			);
	

 
	
	//Return boolean [true if added else false]
	public boolean insertCountry(DummyCountry countryItem) {
		return countryList.add(countryItem);
	}
	
	//Return boolean [true if removed else false]
	public boolean removeCountry(DummyCountry countryItem) {
		return countryList.removeIf(
				currentItem-> currentItem.getCountry().equals(countryItem.getCountry())
				);
	}

	public List<DummyCountry> getCountryList() {
		return countryList;
	}
	
	
	public void setCountryList(List<DummyCountry> countryList) {
		this.countryList = countryList;
	}

	//Return the list of entries based on search match.
	public List<DummyCountry> searchCountry(String searchName) {
		 List<DummyCountry> searchedCountryList=new ArrayList<DummyCountry>();
		 for(DummyCountry tempCountry : countryList) {

          boolean checkMatch = Pattern.compile(
								Pattern.quote(searchName),
								Pattern.CASE_INSENSITIVE)
                                .matcher(tempCountry.getCountry())
                                .find();
          if(checkMatch) {
        	  searchedCountryList.add(tempCountry);
          }
		 }
		 return searchedCountryList;
		
	}
	
}
