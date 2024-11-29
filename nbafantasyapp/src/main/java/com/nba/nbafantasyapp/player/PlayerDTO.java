package com.nba.nbafantasyapp.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private Long playerId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String height;
    private Short weight;
    private Byte seasonExperience;
    private Byte jersey;
    private String position;
    private String playerImageUrl;
    private Long teamId;
    private String fullName;
    private String conference;
    private String division;
    private String teamImageUrl;
}
