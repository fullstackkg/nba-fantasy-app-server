package com.nba.nbafantasyapp.team;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    private long teamId;  // Primary Key

    private String abbreviation;
    private String fullName;
    private String city;
    private String state;
    private String imageUrl;
    private String conference;
    private String division;
}
