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

    public Mono<Page<PlayerDTO>> findAllPlayers(PageRequest pageRequest) {
        return playerRepository.findAllPlayers(pageRequest)
                .collectList()
                .zipWith(playerRepository.count())
                .map(playerTuple -> new PageImpl<>(playerTuple.getT1(), pageRequest, playerTuple.getT2()));
    }

    public Mono<PlayerDTO> findPlayerById(long playerId) {
        return playerRepository.findPlayerById(playerId);
    }
}