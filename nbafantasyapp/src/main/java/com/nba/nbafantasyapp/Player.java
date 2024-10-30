package com.nba.nbafantasyapp;

import lombok.Data;
import java.util.Date;

@Data
public class Player {
    private int playerId;  // Primary Key
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String height;
    private short weight;
    private byte seasonExperience;
    private byte jersey;
    private String position;
    private int teamId;  // Foreign Key that references Primary Key in Team table
    private String imageUrl;
}
