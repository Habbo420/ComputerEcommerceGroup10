package za.ac.cput.domain;
/*
Author: Hanno Visser Immelman 221074414
*/
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
@Entity
public class StoreDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or other strategy
    private Long storeID;
    private String storeName;
    private String storeTel;
    private String storeEmail;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "store_address",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Address address;

    public StoreDetails() {
    }

    public Long getStoreID() {
        return storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public Address getAddress() {
        return address;
    }

    public String getStoreTel() {
        return storeTel;
    }

    public String getStoreEmail() {
        return storeEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreDetails that = (StoreDetails) o;
        return Objects.equals(storeID, that.storeID) && Objects.equals(storeName, that.storeName) && Objects.equals(address, that.address) && Objects.equals(storeTel, that.storeTel) && Objects.equals(storeEmail, that.storeEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeID, storeName, address, storeTel, storeEmail);
    }

    @Override
    public String toString() {
        return "StoreDetails{" +
                "storeID='" + storeID + '\'' +
                ", storeName='" + storeName + '\'' +
                ", address='" + address + '\'' +
                ", storeTel='" + storeTel + '\'' +
                ", storeEmail='" + storeEmail + '\'' +
                '}';
    }

    private StoreDetails(Builder b){
        this.storeID = b.storeID;
        this.storeName = b.storeName;
        this.address = b.address;
        this.storeTel = b.storeTel;
        this.storeEmail = b.storeEmail;
    }
    public static class Builder {
        private Long storeID;
        private String storeName;
        private Address address;
        private String storeTel;
        private String storeEmail;

        public Builder setStoreID(Long storeID){
            this.storeID = storeID;
            return this;
        }

        public Builder setStoreName(String storeName){
            this.storeName = storeName;
            return this;
        }

        public Builder setStoreAddress(Address address){
            this.address = address;
            return this;
        }

        public Builder setStoreTel(String storeTel){
            this.storeTel = storeTel;
            return this;
        }

        public Builder setStoreEmail(String storeEmail){
            this.storeEmail = storeEmail;
            return this;
        }

        public Builder copy(StoreDetails storeDetails){
            this.storeID = storeDetails.storeID;
            this.storeName = storeDetails.storeName;
            this.address = storeDetails.address;
            this.storeTel = storeDetails.storeTel;
            this.storeEmail = storeDetails.storeEmail;
            return this;
        }

        public StoreDetails build(){
            return new StoreDetails(this);
        }
    }
}
