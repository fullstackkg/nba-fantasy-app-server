package com.nba.nbafantasyapp.team;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {
    @Query(value = "SELECT * FROM team ORDER BY full_name", nativeQuery = true)
    List<Team> findAll();

    @Query(value = "SELECT * FROM TEAM WHERE team_id = :teamId", nativeQuery = true)
    Team findById(long teamId);
}
