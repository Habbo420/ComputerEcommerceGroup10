package za.ac.cput.service;

/* CountryService.java
Interface for Country.java
Author: Michael Daniel Johnson 221094040
Date: 19 August 2023
*/

import za.ac.cput.domain.Country;

import java.util.List;


public interface CountryService extends IService<Country, Long>{
    List<Country> getAll();
}
