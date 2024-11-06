package com.nba.nbafantasyapp.player;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final List<Player> allPlayers;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.allPlayers = playerRepository.findAll();
    }

    // GET REQUESTS
    // GET ALL THE PLAYERS
    Page<Player> findAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return playerRepository.findAllSortByName(pageRequest);
    }

    // GET ALL THE PLAYERS FROM A SPECIFIC TEAM
    List<Player> findByTeam(long teamId) {
        return playerRepository.findByTeam(teamId);
    }

    // GET A PLAYER BY ID
    Optional<Player> findPlayer(long playerId) {
        for (Player player : allPlayers) {
            if (player.getPlayerId() == playerId) {
                return Optional.of(allPlayers.get(allPlayers.indexOf(player)));
            }
        }

        return Optional.empty();
    }
}
