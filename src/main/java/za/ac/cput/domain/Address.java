package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/*
Author: Alexander Draai - 220118744
This is the Pojo + Builder for the Address.
Date: 04 - 08 - 2023
 */
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or other strategy
    public Long addressID ;
    public String streetAddress ;
    public String postalCode;

    @ManyToOne
    @JoinColumn(name = "cityID")
    public City city;

    public Address() {}

    private Address(Builder builder) {
        this.addressID = builder.addressID;
        this.streetAddress = builder.streetAddress;
        this.postalCode = builder.postalCode;
        this.city = builder.city;
    }


    public Long getAddressID() {return addressID;}
    public String getStreetAddress() {return streetAddress;}
    public String getPostalCode() {return postalCode;}
    public City getCity() {return city;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressID, address.addressID) && Objects.equals(streetAddress, address.streetAddress) && Objects.equals(postalCode, address.postalCode) && Objects.equals(city, address.city);
    }
    @Override
    public int hashCode() {
        return Objects.hash(addressID, streetAddress, postalCode, city);
    }

    //To String
    @Override
    public String toString() {
        return "Address{" +
                "addressID='" + addressID + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city=" + city +
                '}';
    }


    public static class Builder {
        public Long addressID ;
        public String streetAddress ;
        public String postalCode;
        public City city;

        public Builder setAddressID(Long addressID){
            this.addressID = addressID;
            return this;
        }

        public Builder setStreetAddress(String streetAddress){
            this.streetAddress = streetAddress;
            return this;
        }

        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setCity(za.ac.cput.domain.City city) {
            this.city = city;
            return this;
        }

        public Address.Builder copy(Address address) {
            this.addressID = address.addressID;
            this.streetAddress = address.streetAddress;
            this.postalCode = address.postalCode;
            this.city = address.city;
            return this;
        }

        public Address build() {
            return new Address(this);
        }

    }

}