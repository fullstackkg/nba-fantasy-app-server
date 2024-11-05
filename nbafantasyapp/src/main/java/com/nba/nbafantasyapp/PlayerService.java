package com.nba.nbafantasyapp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // GET REQUESTS
    // GET ALL THE PLAYERS
    List<Player> findAll() {
        Page<Player> pageOfPlayers = playerRepository.findAllPlayersAndSortByName(PageRequest.of(0, 50));
        return pageOfPlayers.getContent();
    }

    // GET ALL THE PLAYERS FROM A SPECIFIC TEAM
    List<Player> findByTeam(int teamId) {
        return playerRepository.findPlayersByTeam(teamId);
    }

    // GET A PLAYER BY ID
    Player findById(int playerId) {
        return playerRepository.findPlayerById(playerId);
    }
}
