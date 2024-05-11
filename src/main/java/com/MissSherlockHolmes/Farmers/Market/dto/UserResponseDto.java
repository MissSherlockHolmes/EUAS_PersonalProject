package com.MissSherlockHolmes.Farmers.Market.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {
    // Getters
    private Long id;
    private String username;
    private String email; // Include other fields you may need but exclude the password

    public UserResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

