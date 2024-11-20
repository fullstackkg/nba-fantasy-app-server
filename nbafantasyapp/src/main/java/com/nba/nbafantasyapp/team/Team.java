package com.nba.nbafantasyapp.team;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    private Long teamId;  // Primary Key
    private String abbreviation;
    private String fullName;
    private String city;
    private String state;
    private String imageUrl;
    private String conference;
    private String division;
}
