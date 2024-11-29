package com.nba.nbafantasyapp.player;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface PlayerRepository extends ReactiveSortingRepository<Player, Long> {
    @Query("SELECT * FROM player ORDER BY last_name")
    Flux<Player> findAll();

    @Query("""
                     SELECT\s
                         p.player_id,
                         p.first_name,
                         p.last_name,
                         p.birth_date,
                         p.height,
                         p.weight,
                         p.season_experience,
                         p.jersey,
                         p.position,
                         p.player_image_url,
                         p.team_id,
                         t.full_name,
                         t.conference,
                         t.division,
                         t.team_image_url
                     FROM player p
                     JOIN team t ON p.team_id = t.team_id
            \s""")
    Flux<PlayerDTO> findAllPlayersWithTeam(Pageable pageable);

    Mono<Player> findByPlayerId(long playerId);

    @Query("SELECT * FROM player WHERE team_id = :teamId")
    Flux<Player> findPlayerByTeamId(long teamId);
}
