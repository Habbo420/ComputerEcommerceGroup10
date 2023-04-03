package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.util.Helper;

public class CartFactory {

    public static Cart buildCart(String productName, int productQuantity, double productPrice){
        if(Helper.isNullOrEmpty(productName)){
            return null;
        }

        if(Helper.isInvalidInt(productQuantity)){
            return null;
        }

        if(Helper.isInvalidDouble(productPrice)){
            return null;
        }

        String cartID = Helper.generateID();
        String productID = "GPU1263";
        String fname = "George";
        String lname = "Armin";
        String customerID = Helper.generateCustomerID(fname, lname);

        return new Cart.Builder().setCartID(cartID)
                .setProductID(productID)
                .setCustomerID(customerID)
                .setProductName(productName)
                .setProductQuantity(productQuantity)
                .setProductPrice(productPrice)
                .build();
    }
}
