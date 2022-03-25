# agpaytechtask

### Guidlines for response status:
##### 1.[201-created]
##### 2.[409-conflict]
##### 3.[404-resource not found]
##### 4.[200-success]

### Application features.
##### 1.Search by partial country.
##### 2.Pagination to display at most [limit:5] countries in asingle page.
##### 3.Search based pagination.
##### 4.Insert new country into the in memory database.
##### 5.Remove a country.
##### 6.Some data about 15 countries have been hardcorded.

### Project stucture.
##### 1.[ DummyCountry] class holds the structure of country.
##### 2.[ DummyCountryCollection] holds the collection of country entity.
#####    And some functionalities to deal with the collection i.e dummy DB.
##### 3.[DummyCountryCache] is a map implementation of cache to optimize the search.
##### 4.[CountryService] holds all the business logic.
##### 5.[test -> TestController] holds all the controller logic for the app.

### Added Info
##### 1.[constants->ConstantsInfo] holds all the static values used as constants.
##### 2.[constants->SearchEntity] holds the search payload structure.
##### 3.[responseObjects] holds the wrapper classes for the responses.
