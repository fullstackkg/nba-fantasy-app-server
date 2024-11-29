package com.nba.nbafantasyapp.player;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Flux<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public Mono<Page<PlayerDTO>> findAllPlayerDTO(PageRequest pageRequest) {
        return playerRepository.findAllPlayersWithTeam(pageRequest)
                .collectList()
                .zipWith(playerRepository.count())
                .map(player -> new PageImpl<>(player.getT1(), pageRequest, player.getT2()));
    }

    public Mono<Player> findPlayerById(long playerId) {
        return playerRepository.findByPlayerId(playerId);
    }

    public Flux<Player> findPlayerByTeamId(long teamId) {
        return playerRepository.findPlayerByTeamId(teamId);
    }
}