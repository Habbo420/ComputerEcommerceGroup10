package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.City;
import za.ac.cput.util.Helper;

/*
Author: Alexander Draai - 220118744
This is the factory for Address
Date: 19 - 08 - 2023
 */
public class AddressFactory {

    public static Address buildAddress(String streetAddress, String postalCode, City city){


        if ( Helper.isNullOrEmpty(streetAddress) ||
             Helper.isNullOrEmpty(postalCode)){
             return null ;
        }

        return new Address.Builder().setStreetAddress(streetAddress)
                                    .setPostalCode(postalCode)
                                    .setCity(city)
                                    .build();
    } // End of Address - buildAddress

    public static Address buildTestAddress(Long id){

        return new Address.Builder().setAddressID(id)
                .build();
    }
}
