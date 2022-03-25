package com.agpaytech.countriesupdate.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agpaytech.countriesupdate.CountryService;
import com.agpaytech.countriesupdate.DummyCountry;
import com.agpaytech.countriesupdate.constants.ConstantsInfo;
import com.agpaytech.countriesupdate.constants.SearchEntity;
import com.agpaytech.countriesupdate.responseobjects.FetchAllCountryResponse;
import com.agpaytech.countriesupdate.responseobjects.NewCountryCDResponse;

@RestController
public class TestController {

	@Autowired
	CountryService countryService;
	
	
  @RequestMapping("/showallcountries") 
	public ResponseEntity<FetchAllCountryResponse> checkController() {
    	 List<DummyCountry> resultCountryList=null;
    	 resultCountryList=countryService.fetchAllCountries();
    	 if(resultCountryList.size()==0) {
    		 resultCountryList=new ArrayList<DummyCountry>();
    	 }
    	 FetchAllCountryResponse responseContainer=new FetchAllCountryResponse(
    			         resultCountryList,
    					 "All country Information"
    			 );
    	 
      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.set("ResponseHeader","All country List");
      
   	  return new ResponseEntity<FetchAllCountryResponse>(
   			  responseContainer,
   			  responseHeaders, 
   			  ConstantsInfo.STATUS_CODE_SUCCESS);
     }
    	 
  
  @PostMapping("/addcountry")
  public ResponseEntity<NewCountryCDResponse> addCountry(@RequestBody DummyCountry countryItem) {
	 boolean savedflag= countryService.saveCountry(
			  countryItem.getCountry(),
			  countryItem.getCapital());
	 
  HttpHeaders responseHeaders = new HttpHeaders();
  
  if(savedflag)
	  {
	  responseHeaders.set("ResponseHeader", "Successfully saved");
	  
	  return new ResponseEntity<NewCountryCDResponse>(
			  new NewCountryCDResponse(countryItem,"Successfully saved"),
			  responseHeaders, 
			  HttpStatus.CREATED);
	  }
  else {
	  responseHeaders.set("ResponseHeader","Already present in the collection");
	  return new ResponseEntity<NewCountryCDResponse>(
			  new NewCountryCDResponse(countryItem,"Already present in the collection"),
			  responseHeaders, 
			  ConstantsInfo.STATUS_CODE_CONFILT);
  }
 
  }
  
  @PostMapping("/removecountry")
  public ResponseEntity<NewCountryCDResponse> removeCountry(@RequestBody DummyCountry countryItem) {
	 boolean removedflag= countryService.deleteCountry(
			  countryItem.getCountry(),
			  countryItem.getCapital());
	 
  HttpHeaders responseHeaders = new HttpHeaders();
	 
  if(removedflag)
  {
  responseHeaders.set("ResponseHeader","Successfully deleted");
  return new ResponseEntity<NewCountryCDResponse>(
		  new NewCountryCDResponse(countryItem,"Successfully deleted"),
		  responseHeaders, 
		  ConstantsInfo.STATUS_CODE_SUCCESS);
  }
else {
  responseHeaders.set("ResponseHeader","Not present in List");
  return new ResponseEntity<NewCountryCDResponse>(
		  new NewCountryCDResponse(countryItem,"Not present in List"),
		  responseHeaders, 
		  ConstantsInfo.STATUS_CODE_NOTFOUND);
}

  }
  
  
  @GetMapping("/countrydetails")
  public ResponseEntity<FetchAllCountryResponse> getCountryList(){
	  List <DummyCountry> resultCountryList=new ArrayList<DummyCountry>();
	  
	  resultCountryList=countryService.searchCountryByName("");
	  
	  int statusCode=((resultCountryList.size()==0)?
			  ConstantsInfo.STATUS_CODE_NOTFOUND:
			  ConstantsInfo.STATUS_CODE_SUCCESS);
	 
	  HttpHeaders responseHeaders = new HttpHeaders();
	  
	  responseHeaders.set("ResponseHeader","Country Info List for page number :"+1);
	  return new ResponseEntity<FetchAllCountryResponse>(
			  new FetchAllCountryResponse(resultCountryList,
					  "Country Info List for page number :"+1),
			  responseHeaders, 
			  statusCode);
  }
  
  @GetMapping("/countrydetails/pageoffset/{pagenumber}")
  public ResponseEntity<FetchAllCountryResponse> getCountryListByOffset(@PathVariable("pagenumber") int pagenumber){
	  List <DummyCountry> resultCountryList=new ArrayList<DummyCountry>();
	
	  resultCountryList=countryService.searchCountryByNameAndOffset(
			  "",
			  pagenumber
			  );
	  
	  HttpHeaders responseHeaders = new HttpHeaders();
	  
	 
	  if(resultCountryList.size()!=0)
	  {
	  responseHeaders.set("ResponseHeader", "Country Information List , page number :"+pagenumber);
	  
	  return new ResponseEntity<FetchAllCountryResponse>(
			  new FetchAllCountryResponse(resultCountryList,"Country Info List for page number :"+pagenumber),
			  responseHeaders, 
			  ConstantsInfo.STATUS_CODE_SUCCESS);
	  }
	  
	  else {
		  resultCountryList=countryService.searchCountryByName("");
		  responseHeaders.set("ResponseHeader", "Country Information List , page number :"+1);
		 
		  int statusCode=((resultCountryList.size()==0)?
				  ConstantsInfo.STATUS_CODE_NOTFOUND:
				  ConstantsInfo.STATUS_CODE_SUCCESS);
		  
		  return new ResponseEntity<FetchAllCountryResponse>(
				  new FetchAllCountryResponse(resultCountryList,"No country info available for page number ["+pagenumber+"] ,redirecting to page 1"),
				  responseHeaders, 
				  statusCode);
		  
	  } 
 
  }
  
  @PostMapping("/countrydetails")
  public ResponseEntity<FetchAllCountryResponse> getCountryListByName(@RequestBody SearchEntity searchItem){
	  List <DummyCountry> resultCountryList=new ArrayList<DummyCountry>();
	  resultCountryList=countryService.searchCountryByName(searchItem.getQueryName());
	 
	  int statusCode=((resultCountryList.size()==0)?
			  ConstantsInfo.STATUS_CODE_NOTFOUND:
			  ConstantsInfo.STATUS_CODE_SUCCESS);
	 
	  HttpHeaders responseHeaders = new HttpHeaders();
	  
	  responseHeaders.set("ResponseHeader","Contry Info List for page number :"+1);
	  return new ResponseEntity<FetchAllCountryResponse>(
			  new FetchAllCountryResponse(resultCountryList,
					  "Country Info List for page number :"+1),
			  responseHeaders, 
			  statusCode);
  }
 
  
  @PostMapping("/countrydetails/pageoffset/{pagenumber}")
  public ResponseEntity<FetchAllCountryResponse> getCountryListByNameAndOffset(@PathVariable("pagenumber") int pagenumber,
		  @RequestBody SearchEntity searchItem){
	  List <DummyCountry> resultCountryList=new ArrayList<DummyCountry>();
	 
	  resultCountryList=countryService.searchCountryByNameAndOffset(
			  searchItem.getQueryName(),
			  pagenumber
			  );
	  HttpHeaders responseHeaders = new HttpHeaders();
	  
	  
	  if(resultCountryList.size()!=0)
	  {
	  responseHeaders.set("ResponseHeader", "Country Information List , page number :"+pagenumber);
	  
	  return new ResponseEntity<FetchAllCountryResponse>(
			  new FetchAllCountryResponse(resultCountryList,"Country Info List for page number :"+pagenumber),
			  responseHeaders, 
			  ConstantsInfo.STATUS_CODE_SUCCESS);
	  }
	  else {
		  resultCountryList=countryService.searchCountryByName(
				  searchItem.getQueryName());
		  responseHeaders.set("ResponseHeader", "Country Information List , page number :"+1);
		 
		  int statusCode=((resultCountryList.size()==0)?
				  ConstantsInfo.STATUS_CODE_NOTFOUND:
				  ConstantsInfo.STATUS_CODE_SUCCESS);
		  
		  return new ResponseEntity<FetchAllCountryResponse>(
				  new FetchAllCountryResponse(resultCountryList,"No country info available for page number ["+pagenumber+"] ,redirecting to page 1"),
				  responseHeaders, 
				  statusCode);
		  
	  } 
  }
  

}
