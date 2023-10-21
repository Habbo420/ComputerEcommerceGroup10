package za.ac.cput.factory;

import za.ac.cput.domain.Invoice;
import za.ac.cput.domain.Sales;
import za.ac.cput.domain.StoreDetails;
import za.ac.cput.util.Helper;
/*
Author: Alexander Draai - 220118744
This is the factory for Invoice Class
Date: 04 - 04 - 2023
 */
public class InvoiceFactory {
    public static Invoice buildInvoice(StoreDetails storeDetails, Sales sales)
    {

        String InvoiceNumber = Helper.generateInvoiceNumber();

        return new Invoice.Builder()
                                    .setStoreDetails(storeDetails)
                                    .setSales(sales)
                                    .build();
    }

}
