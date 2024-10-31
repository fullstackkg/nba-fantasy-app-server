package com.nba.nbafantasyapp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    private int teamId;  // Primary Key
    private String abbreviation;
    private String fullName;
    private String city;
    private String state;
    private String imageUrl;
    private String conference;
    private String division;
}
