package com.agpaytech.countriesupdate;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class DummyCountryCache {
    
	//Cache to make search faster for a country name [used to check before inserting to list.]
	private Map<String,Boolean> countryCache = Stream.of(
			   new AbstractMap.SimpleEntry<>("Afghanistan", true), 
			    new AbstractMap.SimpleEntry<>("Algeria", true),
			    new AbstractMap.SimpleEntry<>("Australia", true), 
			    new AbstractMap.SimpleEntry<>("Belgium", true),
				new AbstractMap.SimpleEntry<>("Belarus", true), 
				new AbstractMap.SimpleEntry<>("Bhutan", true),
				new AbstractMap.SimpleEntry<>("Colombia", true), 
				new AbstractMap.SimpleEntry<>("Costa Rica", true),
				new AbstractMap.SimpleEntry<>("Cuba", true), 
				new AbstractMap.SimpleEntry<>("Equatorial Guinea", true),
				new AbstractMap.SimpleEntry<>("Federated States of Micronesia", true), 
				new AbstractMap.SimpleEntry<>("Finland", true),
				new AbstractMap.SimpleEntry<>("France", true), 
				new AbstractMap.SimpleEntry<>("Georgia", true),
				new AbstractMap.SimpleEntry<>("Germany", true))
			  .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

	//Check function to look up by country name.
	public boolean checkCache(String countryName) {
	           if(countryCache.get(countryName)==null)return false;
	           else return true;
	}
	
	//Updating cache while inserting new country to list.
	public boolean insertToCache(String countryName) {
		countryCache.put(countryName, true);
		 if(countryCache.get(countryName)==null)return false;
         else return true;
	}
	
	//Removal from cache while removed from original list.
	public boolean removeFromCache(String countryName) {
		return countryCache.remove(countryName);
	}
	
	public Map<String, Boolean> getCountryCache() {
		return countryCache;
	}

	public void setCountryCache(Map<String, Boolean> countryCache) {
		this.countryCache = countryCache;
	}
	
}
