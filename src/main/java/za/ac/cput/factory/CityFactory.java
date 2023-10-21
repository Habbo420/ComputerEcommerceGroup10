package za.ac.cput.factory;

/* CityFactory.java
Factory class for City.java
Author: Michael Daniel Johnson 221094040
Date: 19 August 2023
*/

import za.ac.cput.domain.City;
import za.ac.cput.domain.Country;
import za.ac.cput.util.Helper;

public class CityFactory {

    public static City createCity(String cityName, Country country) {

        if (Helper.isNullOrEmpty(cityName)) {
            return null;
        }

        return new City.Builder()
                .setCityName(cityName)
                .setCountry(country)
                .build();

    }

    public static City createTestCity(Long id) {

        return new City.Builder().setCityID(id)
                .build();

    }

}
