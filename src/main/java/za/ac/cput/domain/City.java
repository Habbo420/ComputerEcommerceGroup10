package za.ac.cput.domain;
/* City.java
Class for City.java
Author: Michael Daniel Johnson 221094040
Date: 19 August 2023
*/

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or other strategy
    public Long cityID;
    public String cityName;


    @ManyToOne
    @JoinColumn(name = "countryid")
    public Country country;

    public Long getCityID() {
        return cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City city)) return false;
        return Objects.equals(getCityID(), city.getCityID()) && Objects.equals(getCityName(), city.getCityName()) && Objects.equals(getCountry(), city.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCityID(), getCityName(), getCountry());
    }

    @Override
    public String toString() {
        return "City{" +
                "CityID='" + cityID + '\'' +
                ", cityName='" + cityName + '\'' +
                ", country=" + country +
                '}';
    }

    public City(){}

    private City(Builder builder) {
        this.cityID = builder.cityID;
        this.cityName = builder.cityName;
        this.country = builder.country;
    }

    public static class Builder {
        private Long cityID;
        private String cityName;

        private Country country;

        public Builder setCityID(Long cityID) {
            this.cityID = cityID;
            return this;
        }

        public Builder setCityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        public Builder setCountry(Country country) {
            this.country = country;
            return this;
        }

        public Builder copy(City city) {
            this.cityID = city.cityID;
            this.cityName = city.cityName;
            this.country = city.country;
            return this;
        }

        public City build() {
            return new City(this);
        }

    }

}
