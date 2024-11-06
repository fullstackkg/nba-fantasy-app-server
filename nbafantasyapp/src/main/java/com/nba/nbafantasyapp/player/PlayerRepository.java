package com.nba.nbafantasyapp.player;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {
    @Query(value = "SELECT * FROM player ORDER BY last_name",
            countQuery = "SELECT count(*) FROM player",
            nativeQuery = true)
    Page<Player> findAllSortByName(Pageable pageable);

    @Query(value = "SELECT * FROM player", nativeQuery = true)
    List<Player> findAll();

    @Query(value = "SELECT * FROM player WHERE player_id = :playerId", nativeQuery = true)
    Player findById(long playerId);

    @Query(value = "SELECT * FROM player JOIN team ON player.team_id = team.team_id WHERE team.team_id = :teamId ORDER BY full_name", nativeQuery = true)
    List<Player> findByTeam(long teamId);
}
