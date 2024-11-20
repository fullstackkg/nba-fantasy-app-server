package com.nba.nbafantasyapp.team;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {
    @Query("SELECT * FROM team ORDER BY full_name")
    Flux<Team> findAll();

    @Query("SELECT * FROM TEAM WHERE team_id = :teamId")
    Mono<Team> findByTeamId(long teamId);
}
