package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Setter
public class SalesItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or other strategy
    private Long salesItemID;

    @ManyToOne
    @JoinColumn(name = "salesID")
    private Sales sales;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "sales_item_products",
            joinColumns = @JoinColumn(name = "sales_item_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    private int quantity;

    public SalesItem() {
    }

    private SalesItem(Builder b){
        this.salesItemID = b.salesItemID;
        this.sales = b.sales;
        this.products = b.products;
        this.quantity = b.quantity;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getSalesItemID() {
        return salesItemID;
    }

    public Sales getSales() {
        return sales;
    }

    public int getQuantity() {
        return quantity;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesItem salesItem = (SalesItem) o;
        return quantity == salesItem.quantity && Objects.equals(salesItemID, salesItem.salesItemID) && Objects.equals(sales, salesItem.sales) && Objects.equals(products, salesItem.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesItemID, sales, products, quantity);
    }

    @Override
    public String toString() {
        String productIds = products.stream()
                .map(Product::getProductID)
                .map(Object::toString)
                .collect(Collectors.joining(", "));


        return "SalesItem{" +
                "salesItemID='" + salesItemID + '\'' +
                ", quantity=" + quantity +
                ", productIDs=" + productIds +
                '}';
    }

    public static class Builder {
        private Long salesItemID;
        private Sales sales;
        private List<Product> products = new ArrayList<>();
        private int quantity;

        public Builder setSalesItemID(Long salesItemID) {
            this.salesItemID = salesItemID;
            return this;
        }

        public Builder setSales(Sales sales) {
            this.sales = sales;
            return this;
        }

        public Builder addProduct(Product product) {
            this.products.add(product);
            return this;
        }

        public Builder setProducts(List<Product> products) {
            this.products = products;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder copy(SalesItem salesItem){
            this.salesItemID = salesItem.salesItemID;
            this.sales = salesItem.sales;
            this.products = salesItem.products;  // Updated this line
            this.quantity = salesItem.quantity;
            return this;
        }

        public SalesItem build(){
            return new SalesItem(this);
        }
    }

}
