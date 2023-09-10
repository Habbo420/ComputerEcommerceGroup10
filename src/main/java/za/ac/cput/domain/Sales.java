package za.ac.cput.domain;

/* Sales.java
POJO for the Sales
Author: David Henriques Garrancho (221475982)
Date: 16 August 2023
*/

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Sales implements Serializable {
    @Id
    private String saleID;
    private String saleDate;
    private Double totalAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @OneToMany(mappedBy = "sales")
    private List<SalesItem> salesItems = new ArrayList<>();

    public Sales() {
    }

    private Sales(Builder b){
        this.saleID = b.saleID;
        this.saleDate = b.saleDate;
        this.totalAmount = b.totalAmount;
        this.customer = b.customer;
    }

    public String getSaleID() {
        return saleID;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sales sales = (Sales) o;
        return Objects.equals(saleID, sales.saleID) && Objects.equals(saleDate, sales.saleDate) && Objects.equals(totalAmount, sales.totalAmount) && Objects.equals(customer, sales.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleID, saleDate, totalAmount, customer);
    }

    @Override
    public String toString() {
        return "Sales{" +
                "saleID='" + saleID + '\'' +
                ", saleDate='" + saleDate + '\'' +
                ", totalAmount=" + totalAmount +
                ", customer=" + customer +
                '}';
    }


    public static class Builder {
        private String saleID;
        private String saleDate;
        private Double totalAmount;
        private Customer customer;

        public Builder setSaleID(String saleID) {
            this.saleID = saleID;
            return this;
        }

        public Builder setSaleDate(String saleDate) {
            this.saleDate = saleDate;
            return this;
        }

        public Builder setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder copy(Sales sales){
            this.saleID = sales.saleID;
            this.saleDate = sales.saleDate;
            this.totalAmount = sales.totalAmount;
            this.customer = sales.customer;
            return this;
        }

        public Sales build(){return new Sales(this);}
    }
}
