package za.ac.cput.factory;

import za.ac.cput.domain.Product;
import za.ac.cput.domain.Sales;
import za.ac.cput.domain.SalesItem;
import za.ac.cput.util.Helper;

import java.util.List;

public class SalesItemFactory {
    public static SalesItem buildSales(Sales sales, List<Product> products, int quantity) {

        return new SalesItem.Builder()
                .setSales(sales)
                .setProducts(products)
                .setQuantity(quantity)
                .build();
    }
}
