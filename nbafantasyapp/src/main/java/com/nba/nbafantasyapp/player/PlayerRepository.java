package com.nba.nbafantasyapp.player;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface PlayerRepository extends ReactiveCrudRepository<Player, Long> {
    @Query("SELECT * FROM player ORDER BY last_name")
    Flux<Player> findAll(Pageable pageable);

    Mono<Player> findByPlayerId(long playerId);

    @Query(value = "SELECT * FROM player JOIN team ON player.team_id = team.team_id WHERE team.team_id = :teamId ORDER BY last_name")
    Flux<Player> findByTeamId(long teamId);
}
