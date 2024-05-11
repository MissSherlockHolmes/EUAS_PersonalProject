package com.MissSherlockHolmes.Farmers.Market.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table("user")  // Map to the user table in your database
public class User {
    @Id
    private Long id;

    @Column("username")  // Explicitly specifying the column name
    private String username;

    @Column("password")
    private String password;

    @Column("firstName")
    private String firstName;

    @Column("surname")
    private String surname;

    @Column("email")
    private String email;

    @Column("street")
    private String street;

    @Column("house_number")
    private String house_numb;

    @Column("city")
    private String city;

    @Column("region")
    private String region;

    @Column("country")
    private String country;

    @Column("postal_code")
    private String postal_code;

    public User() {  // No-arg constructor, if required
    }
}
