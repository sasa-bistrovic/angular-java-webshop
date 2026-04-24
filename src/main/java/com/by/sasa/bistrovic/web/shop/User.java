package com.by.sasa.bistrovic.web.shop;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_item")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String role;

    private String streetName;
    private String streetNumber;
    private String city;
    private String country;
    
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> savedAddresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CreditCard> savedCards;

    public User() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStreetName() { return streetName; }
    public void setStreetName(String streetName) { this.streetName = streetName; }

    public String getStreetNumber() { return streetNumber; }
    public void setStreetNumber(String streetNumber) { this.streetNumber = streetNumber; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }    

    public List<Address> getSavedAddresses() { return savedAddresses; }
    public void setSavedAddresses(List<Address> savedAddresses) { this.savedAddresses = savedAddresses; }

    public List<CreditCard> getSavedCards() { return savedCards; }
    public void setSavedCards(List<CreditCard> savedCards) { this.savedCards = savedCards; }
}
