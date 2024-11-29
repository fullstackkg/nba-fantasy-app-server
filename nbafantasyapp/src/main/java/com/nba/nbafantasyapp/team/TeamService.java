package com.nba.nbafantasyapp.team;

import com.nba.nbafantasyapp.player.Player;
import com.nba.nbafantasyapp.player.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public Flux<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    public Mono<Team> findByTeamId(long teamId) {
        return teamRepository.findByTeamId(teamId);
    }

    public Flux<Player> findPlayersByTeamId(long teamId) {
        return playerRepository.findPlayerByTeamId(teamId);
    }
}
