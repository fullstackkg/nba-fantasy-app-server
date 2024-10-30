package com.nba.nbafantasyapp;

import lombok.Data;

@Data
public class Team {
    private int teamId;  // Primary Key
    private String abbreviation;
    private String fullName;
    private String city;
    private String state;
    private String imageUrl;
    private String conference;
    private String division;
}
