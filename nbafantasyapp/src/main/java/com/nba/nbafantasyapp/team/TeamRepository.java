package com.nba.nbafantasyapp.team;

import com.nba.nbafantasyapp.player.Player;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TeamRepository extends ReactiveCrudRepository<Team, Long> {
    @Query("SELECT * FROM team ORDER BY full_name")
    Flux<Team> findAll();

    @Query("SELECT * FROM team WHERE team_id = :teamId")
    Mono<Team> findByTeamId(long teamId);

    @Query("SELECT * FROM player WHERE team_id = :teamId")
    Flux<Player> findPlayerByTeamId(long teamId);
}
