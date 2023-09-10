package za.ac.cput.domain;

/*
    POJO for the SupplierOrder
    Author: Ethan Andrew Botes (220101299)
    Date: 6 April 2023
*/


import jakarta.persistence.*;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class SupplierOrder implements Serializable {


    @Id
    private String orderID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="supplierid")
    public Supplier supplier;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "supplier_order_products",
            joinColumns = @JoinColumn(name = "orderid"),
            inverseJoinColumns = @JoinColumn(name = "productid")
    )
    private List<Product> products = new ArrayList<>();
    public int productQuantity;
    public double productIndividualPrice;
    private String dateOfOrder;
    private String expectedDeliveryDate;
    private String actualDeliveryDate;
    private double importTax;
    private double totalCost;


    public SupplierOrder(){
    }

    private SupplierOrder(Builder builder){

        this.supplier = builder.supplier;
        this.products = builder.products;
        this.productQuantity = builder.productQuantity;
        this.productIndividualPrice = builder.productIndividualPrice;
        this.orderID = builder.orderID;
        this.dateOfOrder = builder.dateOfOrder;
        this.expectedDeliveryDate = builder.expectedDeliveryDate;
        this.actualDeliveryDate = builder.actualDeliveryDate;
        this.importTax = builder.importTax;
        this.totalCost = builder.totalCost;

    }

    public Supplier getSupplier() {
        return supplier;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public double getProductIndividualPrice() {
        return productIndividualPrice;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public String getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public double getImportTax() {
        return importTax;
    }

    public double getTotalCost() {
        return totalCost;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierOrder that = (SupplierOrder) o;
        return productQuantity == that.productQuantity && Double.compare(that.productIndividualPrice, productIndividualPrice)
                == 0 && Double.compare(that.importTax, importTax) == 0 && Double.compare(that.totalCost, totalCost) == 0 &&
                Objects.equals(supplier, that.supplier) && Objects.equals(products, that.products) && Objects.equals(orderID, that.orderID)
                && Objects.equals(dateOfOrder, that.dateOfOrder) && Objects.equals(expectedDeliveryDate, that.expectedDeliveryDate) &&
                Objects.equals(actualDeliveryDate, that.actualDeliveryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplier, products, productQuantity, productIndividualPrice, orderID, dateOfOrder, expectedDeliveryDate, actualDeliveryDate, importTax, totalCost);
    }

    @Override
    public String toString() {

        String productsIds = products.stream()
                .map(Product::getProductID)
                .collect(Collectors.joining(", "));

        return "SupplierOrder{" +
                "supplier='" + supplier + '\'' +
                ", productsIDs='" + productsIds + '\'' +
                ", productQuantity=" + productQuantity +
                ", productPrice=" + productIndividualPrice +
                ", orderID='" + orderID + '\'' +
                ", dateOfOrder='" + dateOfOrder + '\'' +
                ", expectedDeliveryDate='" + expectedDeliveryDate + '\'' +
                ", actualDeliveryDate='" + actualDeliveryDate + '\'' +
                ", importTax=" + importTax +
                ", totalCost=" + totalCost +
                '}';
    }





    public static class Builder{

        private Supplier supplier;
        private List<Product> products = new ArrayList<>();
        private int productQuantity;
        private double productIndividualPrice;
        private String orderID;
        private String dateOfOrder;
        private String expectedDeliveryDate;
        private String actualDeliveryDate;
        private double importTax;
        private double totalCost;



        public Builder setSupplier(Supplier supplier) {
            this.supplier = supplier;
            return this;
        }

        public Builder setProducts(List<Product> products) {
            this.products = products;
            return this;
        }

        public Builder setProductQuantity(int productQuantity) {
            this.productQuantity = productQuantity;
            return this;
        }

        public Builder setProductIndividualPrice(double productIndividualPrice) {
            this.productIndividualPrice = productIndividualPrice;
            return this;
        }

        public Builder setOrderID(String orderID) {
            this.orderID = orderID;
            return this;
        }

        public Builder setDateOfOrder(String dateOfOrder) {
            this.dateOfOrder = dateOfOrder;
            return this;
        }

        public Builder setExpectedDeliveryDate(String expectedDeliveryDate) {
            this.expectedDeliveryDate = expectedDeliveryDate;
            return this;
        }

        public Builder setActualDeliveryDate(String actualDeliveryDate) {
            this.actualDeliveryDate = actualDeliveryDate;
            return this;
        }

        public Builder setImportTax(double importTax) {
            this.importTax = importTax;
            return this;
        }

        public Builder setTotalCost(double totalCost) {
            this.totalCost = totalCost;
            return this;
        }



        public Builder copy(SupplierOrder supplierOrder){
            this.supplier = supplierOrder.supplier;
            this.products = supplierOrder.products;
            this.productQuantity = supplierOrder.productQuantity;
            this.productIndividualPrice = supplierOrder.productIndividualPrice;
            this.orderID = supplierOrder.orderID;
            this.dateOfOrder = supplierOrder.dateOfOrder;
            this.expectedDeliveryDate = supplierOrder.expectedDeliveryDate;
            this.actualDeliveryDate = supplierOrder.actualDeliveryDate;
            this.importTax = supplierOrder.importTax;
            this.totalCost = supplierOrder.totalCost;
            return this;
        }

        public SupplierOrder build(){
            return new SupplierOrder(this);
        }

    }


}
