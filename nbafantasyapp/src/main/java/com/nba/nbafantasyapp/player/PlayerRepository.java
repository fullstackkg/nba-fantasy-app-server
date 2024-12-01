package com.nba.nbafantasyapp.player;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface PlayerRepository extends ReactiveSortingRepository<Player, Long> {
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
                 t.full_name,
                 t.conference,
                 t.division,
                 t.team_image_url
             FROM player p
             JOIN team t ON p.team_id = t.team_id
             ORDER BY p.last_name
             LIMIT :#{#pageable.pageSize} OFFSET :#{#pageable.offset}
            \s""")
    Flux<PlayerDTO> findAllPlayers(Pageable pageable);

    @Query("SELECT COUNT(*) FROM player")
    Mono<Long> count();

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
            t.full_name,
            t.conference,
            t.division,
            t.team_image_url
            FROM player p
            JOIN team t ON p.team_id = t.team_id
            WHERE p.player_id = :playerId
            \s""")
    Mono<PlayerDTO> findPlayerById(long playerId);
}
