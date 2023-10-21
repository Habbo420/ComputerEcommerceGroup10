package za.ac.cput.factory;
/* ProductFactory.java
 Entity for the ProductFactory
 Author: Reece Bergstedt - 221075240
 Date: 22 March 2023
*/

import za.ac.cput.domain.Product;
import za.ac.cput.util.Helper;


public class ProductFactory {

    public static Product buildProduct(String productName, String productType, String productDescription, double productPrice, double costPrice, boolean isStock) {
        if (Helper.isNullOrEmpty(productName) || Helper.isNullOrEmpty(productType) || Helper.isNullOrEmpty(productDescription)) {
            return null;
        }

        if(Helper.isInvalidDouble(productPrice) || Helper.isInvalidDouble(costPrice)){
            return null;
        }

        return new Product.Builder()
                .setProductName(productName)
                .setProductPrice(productPrice)
                .setProductCostPrice(costPrice)
                .setProductType(productType)
                .setProductDescription(productDescription)
                .setStock(isStock)
                .build();
    }

    public static Product buildTestProduct(Long id) {

        return new Product.Builder().setProductID(id).build();
    }
}



