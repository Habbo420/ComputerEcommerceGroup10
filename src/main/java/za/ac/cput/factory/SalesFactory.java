package za.ac.cput.factory;

/* SalesFactory.java
Factory for Sales
Author: David Henriques Garrancho (221475982)
Date: 16 August 2023
*/

import za.ac.cput.domain.User;
import za.ac.cput.domain.Sales;
import za.ac.cput.util.Helper;

public class SalesFactory {

    public static Sales buildSales(String salesDate, Double totalAmount, User customer) {
        if (Helper.isValidDate(salesDate) == null) {
            return null;
        }

        if (Helper.isInvalidDouble(totalAmount)) {
            return null;
        }

        String salesID = Helper.generateID();

        return new Sales.Builder()
                .setSaleDate(salesDate)
                .setTotalAmount(totalAmount)
                .setCustomer(customer)
                .build();
    }

    public static Sales buildTestSales(Long id) {


        return new Sales.Builder().setSaleID(id)
                .build();
    }
}
