package com.nba.nbafantasyapp.team;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final List<Team> allTeams;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
        this.allTeams = teamRepository.findAll();
    }

    // GET REQUESTS
    // GET ALL TEAMS
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    // GET TEAM BY ID
    public Optional<Team> findById(long id) {
        for (Team team : allTeams) {
            if (team.getTeamId() == id) {
                return Optional.of(team);
            }
        }

        return Optional.empty();
    }
}
