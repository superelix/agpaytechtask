package com.agpaytech.countriesupdate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agpaytech.countriesupdate.constants.ConstantsInfo;

@Service
public class CountryService {
    
	@Autowired
	DummyCountryCache countryCacheV0;
	@Autowired 
	DummyCountryCollection countryListV0;
	
	//Just to insert a dummy list to in memory storage.

	//Add new country to the list.
	public boolean saveCountry(String countryName,String capitalCity) {
		
		if(countryCacheV0.checkCache(countryName)==false) {
		 countryListV0.insertCountry(new DummyCountry(capitalCity,countryName));
		 countryCacheV0.insertToCache(countryName);
		 return true;
		 }
		else return false;
	    }
	
	//Delete country from list.
	 public boolean deleteCountry(String countryName,String capitalCity) {
		
		if(countryCacheV0.checkCache(countryName)==true) {
		 countryListV0.removeCountry(new DummyCountry(capitalCity,countryName));
		 countryCacheV0.removeFromCache(countryName);
		 return true;
		 }
		else return false;
	    }
	 
	 //Returns the entire list of countries
	 public List<DummyCountry> fetchAllCountries(){
		 return countryListV0.getCountryList();
	 }
	 
	 //Shows only the first five items using pagination concepts as no offset has been passed.
	 //[Both cases are involved i.e search name passed as well as no searched name passed.]
	 public List<DummyCountry> searchCountryByName(String searchName) {
			
         List<DummyCountry> tempCountryList=null;
		 
		 if(searchName.equals(""))tempCountryList=countryListV0.getCountryList();
		 else tempCountryList=countryListV0.searchCountry(searchName);
		 
		 int startIdx=0;
		 int limit=ConstantsInfo.PAGE_SIZE;
		 
		 List<DummyCountry> resultCountryList=new ArrayList<DummyCountry>();
		 
		 if(tempCountryList!=null) {
		
			 
		 for(DummyCountry tempCountry:tempCountryList) {
			 if(startIdx==0) {
				 if(limit>0) {
					 resultCountryList.add(tempCountry);
					 limit--;
				 }
				 if(limit==0)break;
			 }else startIdx--;
		 }
		 }
		 return resultCountryList;
	 }
	 
	 //Shows only the required five items using pagination concepts as  offset has been passed.
	 //[Both cases are involved i.e search name passed as well as no searched name passed.]
	 public List<DummyCountry> searchCountryByNameAndOffset(String searchName,int offset) {
			
		 List<DummyCountry> tempCountryList=null;
		 
		 if(searchName.equals(""))tempCountryList=countryListV0.getCountryList();
		 else tempCountryList=countryListV0.searchCountry(searchName);
		
		 int limit= ConstantsInfo.PAGE_SIZE;
		 int startIdx=(offset-1)*limit;
		 
		 List<DummyCountry> resultCountryList=new ArrayList<DummyCountry>();
		
		 if(tempCountryList!=null) {
		 for(DummyCountry tempCountry:tempCountryList) {
			 if(startIdx==0) {
				 if(limit>0) {
					 resultCountryList.add(tempCountry);
					 limit--;
				 }
				 if(limit==0)break;
			 }else startIdx--;
		 }
		}
		 return resultCountryList;
	 }
	 	 
}
