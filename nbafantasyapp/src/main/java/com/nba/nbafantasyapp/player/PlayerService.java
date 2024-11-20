package com.nba.nbafantasyapp.player;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Flux<Player> findAllPlayers(int page, int size) {
        return playerRepository.findAll(PageRequest.of(page, size));
    }

    public Mono<Player> findPlayerById(long playerId) {
        return playerRepository.findByPlayerId(playerId);
    }

    public Flux<Player> findPlayerByTeamId(long teamId) {
        return playerRepository.findByTeamId(teamId);
    }
}