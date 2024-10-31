package com.nba.nbafantasyapp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    private int playerId;  // Primary Key

    private String firstName;
    private String lastName;
    private Date birthDate;
    private String height;
    private short weight;
    private byte seasonExperience;
    private byte jersey;
    private String position;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team teamId;  // Foreign Key that references Primary Key in Team table

    private String imageUrl;
}
