package za.ac.cput.factory;

/* CustomerFactory.java
Factory for Customer
Author: David Henriques Garrancho (221475982)
Date: 20 March 2023
*/

import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;


public class UserFactory {

    public static User buildCustomer(String firstName, String lastName, String email, String password) {
        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName) || Helper.isNullOrEmpty(password)) {
            return null;
        }

        if (!Helper.isValidEmail(email)) {
            return null;
        }

        return new User.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .build();
    }

    public static User buildTestCustomer(Long id) {

        return new User.Builder().setCustomerID(id)
                .build();
    }

}
