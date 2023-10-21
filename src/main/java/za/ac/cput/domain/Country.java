package za.ac.cput.domain;

/* Country.java
Class for Country.java
Author: Michael Daniel Johnson 221094040
Date: 19 August 2023
*/

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Country implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long countryID;

    public String countryName;

    // Public no-argument constructor
    public Country() {
    }

    public Long getCountryID() {
        return countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return Objects.equals(getCountryID(), country.getCountryID()) && Objects.equals(getCountryName(), country.getCountryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountryID(), getCountryName());
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryID='" + countryID + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }

    public static class Builder {
        public Long countryID;
        public String countryName;

        public Builder setCountryID(Long countryID) {
            this.countryID = countryID;
            return this;
        }

        public Builder setCountryName(String countryName) {
            this.countryName = countryName;
            return this;
        }

        public Builder copy(Country country) {
            this.countryID = country.countryID;
            this.countryName = country.countryName;
            return this;
        }

        public Country build() {
            return new Country(this);
        }
    }

    // Public constructor that allows creating instances without the builder
    public Country(Builder builder) {
        this.countryID = builder.countryID;
        this.countryName = builder.countryName;
    }
}
