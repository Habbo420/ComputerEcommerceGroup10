package za.ac.cput.service;

/* CityService.java
Interface for City.java
Author: Michael Daniel Johnson 221094040
Date: 19 August 2023
*/

import za.ac.cput.domain.City;

import java.util.List;

public interface CityService extends IService<City, Long>{
    List<City> getAll();
}
