package com.nba.nbafantasyapp.team;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public Flux<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    public Mono<Team> findByTeamId(long teamId) {
        return teamRepository.findByTeamId(teamId);
    }
}
