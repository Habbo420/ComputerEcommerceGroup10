package za.ac.cput.domain;

/* Customer.java
POJO for the Customer
Author: David Henriques Garrancho (221475982)
Date: 20 March 2023
*/

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.*;


@Entity
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User implements Serializable , UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or other strategy
    private Long customerID;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER  , cascade = CascadeType.PERSIST)
    List<Role> roles ;


    public User(String email , String password , List<Role> roles) {
        this.email= email ;
        this.password=password ;
        this.roles=roles ;}


    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return authorities;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_customerid"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Role> role = new HashSet<>();

    public User(){
    }

    private User(Builder b){
        this.customerID = b.customerID;
        this.firstName = b.firstName;
        this.lastName = b.lastName;
        this.email = b.email;
        this.password = b.password;
    }

    public Long getCustomerID() {return customerID;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<Role> getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User customer = (User) o;
        return Objects.equals(customerID, customer.customerID) && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(email, customer.email) && Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, firstName, lastName, email, password);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static class Builder {
        public Long customerID;
        private String firstName;
        private String lastName;
        private String email;
        private String password;

        public Builder setCustomerID(Long customerID){
            this.customerID = customerID;
            return this;
        }

        public Builder setFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email){
            this.email = email;
            return this;
        }

        public Builder setPassword(String password){
            this.password = password;
            return this;
        }

        public Builder copy(User customer){
            this.customerID = customer.customerID;
            this.firstName = customer.firstName;
            this.lastName = customer.lastName;
            this.email = customer.email;
            this.password = customer.password;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
