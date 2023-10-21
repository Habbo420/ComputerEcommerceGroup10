package za.ac.cput.domain;
/* Product.java
 Entity for the Product
 Author: Reece Bergstedt - 221075240
 Date: 22 March 2023
*/

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or other strategy
    public Long productID;
    public String productName;
    public String productType;
    public String productDescription;
    public double productCostPrice;
    public double productPrice;
    public boolean isStock;

    @ManyToMany(mappedBy = "products")
    private List<SalesItem> salesItems = new ArrayList<>();

    public Product(){

    }

    private Product(Builder b) {
        this.productID = b.productID;
        this.productType = b.productType;
        this.productDescription = b.productDescription;
        this.productPrice = b.productPrice;
        this.productCostPrice = b.productCostPrice;
        this.productName = b.productName;
        this.isStock = b.isStock;
    }

    public Long getProductID() {
        return productID;
    }

    public String getProductType() {
        return productType;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductCostPrice() {
        return productCostPrice;
    }

    public boolean isStock() {
        return isStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.productCostPrice, productCostPrice) == 0 && Double.compare(product.productPrice, productPrice) == 0 && isStock == product.isStock && Objects.equals(productID, product.productID) && Objects.equals(productName, product.productName) && Objects.equals(productType, product.productType) && Objects.equals(productDescription, product.productDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, productName, productType, productDescription, productCostPrice, productPrice, isStock);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productCostPrice=" + productCostPrice +
                ", productPrice=" + productPrice +
                ", isStock=" + isStock +
                '}';
    }

    public static class Builder {
        public Long productID;
        public String productType;
        public String productDescription;
        public double productPrice;
        public double productCostPrice;
        public String productName;
        public boolean isStock;

        public Builder setProductID(Long productID) {
            this.productID = productID;
            return this;

        }
        public Builder setProductType(String productType) {
            this.productType = productType;
            return this;
        }

        public Builder setProductDescription(String productDescription) {
            this.productDescription = productDescription;
            return this;

        }

        public Builder setProductPrice(double productPrice) {
            this.productPrice = productPrice;
            return this;

        }

        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;

        }

        public Builder setProductCostPrice(double productCostPrice) {
            this.productCostPrice = productCostPrice;
            return this;
        }

        public Builder setStock(boolean stock) {
            isStock = stock;
            return this;
        }

        public Builder copy(Product product) {
            this.productID = product.productID;
            this.productType = product.productType;
            this.productDescription = product.productDescription;
            this.productPrice = product.productPrice;
            this.productCostPrice = product.productCostPrice;
            this.productName = product.productName;
            this.isStock = product.isStock;
            return this;
        }

        public Product build(){
            return new Product(this);
        }

    }
}

