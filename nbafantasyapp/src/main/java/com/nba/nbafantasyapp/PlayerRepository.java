package com.nba.nbafantasyapp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends PagingAndSortingRepository<Player, Integer> {
    @Query(value = "SELECT * FROM player ORDER BY last_name",
            countQuery = "SELECT count(*) FROM player ORDER BY last_name",
            nativeQuery = true)
    Page<Player> findAllPlayersAndSortByName(Pageable page);

    @Query(value = "SELECT * FROM player WHERE player_id = :playerId", nativeQuery = true)
    Player findPlayerById(int playerId);
}
