package com.MissSherlockHolmes.Farmers.Market.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Lombok annotation to generate getters, setters, equals, hashCode and toString methods
@NoArgsConstructor // Generates a no-arg constructor
public class User {
    private Long id;
    private String password;
    private String firstName;
    private String surname;
    private String email;
    private String username;
    private String street;
    private String houseNumber;
    private String city;
    private String region;
    private String country;
    private String postalCode;
}
