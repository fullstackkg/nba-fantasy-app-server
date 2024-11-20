package com.nba.nbafantasyapp.player;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    private Long playerId;  // Primary Key
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String height;
    private Short weight;
    private Byte seasonExperience;
    private Byte jersey;
    private String position;
    private Long teamId;  // Foreign Key that references Primary Key in Team table
    private String imageUrl;
}

