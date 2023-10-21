package za.ac.cput.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
/*
Author: Alexander Draai - 220118744
This is the Pojo + Builder for the Invoice Class.
Date: 04 - 04 - 2023
 */
@Entity
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or other strategy
    public Long invoiceNumber;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "storeID")
    public StoreDetails storeDetails;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "salesID")
    public Sales sales;
    public Invoice(){}

    public Long getInvoiceNumber() {return invoiceNumber;}
    public StoreDetails getStoreDetails() {return storeDetails;}
    public Sales getSales() {return sales;}

    // Equals , Hashcode & ToString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(invoiceNumber, invoice.invoiceNumber) && Objects.equals(storeDetails, invoice.storeDetails) && Objects.equals(sales, invoice.sales);
    }
    @Override
    public int hashCode() {return Objects.hash(invoiceNumber, storeDetails, sales);}
    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", storeDetails=" + storeDetails +
                ", sales=" + sales +
                '}';
    }

    // Builder
    private Invoice (Builder builder){
        this.invoiceNumber = builder.invoiceNumber;
        this.storeDetails = builder.storeDetails;
        this.sales = builder.sales;
    }

    public static class Builder {
        public Long invoiceNumber ;
        public StoreDetails storeDetails ;
        public Sales sales ;

        public Builder setInvoiceNumber(Long invoiceNumber){
            this.invoiceNumber = invoiceNumber;
            return this;
        }

        public Builder setStoreDetails(StoreDetails storeDetails){
            this.storeDetails = storeDetails;
            return this;
        }

        public Builder setSales(Sales sales){
            this.sales = sales;
            return this;
        }

        public Builder copy(Invoice invoice){
            this.invoiceNumber = invoice.invoiceNumber;
            this.storeDetails = invoice.storeDetails;
            this.sales = invoice.sales;
            return this;
        }
        public Invoice build() {
            return new Invoice(this);
        }
    } // End of Builder

}