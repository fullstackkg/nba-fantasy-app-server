package com.nba.nbafantasyapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class NbaFantasyAppServerApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(NbaFantasyAppServerApplication.class);
    private final PlayerRepository playerRepository;

    public NbaFantasyAppServerApplication(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(NbaFantasyAppServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("The CommandLineRunner has started...");
        Pageable page = PageRequest.of(0, 50);
        Page<Player> listOfPlayers = playerRepository.findAllPlayersAndSortByName(page);
        Iterable<Player> players = listOfPlayers.getContent();

        while (listOfPlayers.hasNext()) {
            for (Player player : players) {
                log.info(player.getFirstName() + " " + player.getLastName());
            }

            listOfPlayers = playerRepository.findAllPlayersAndSortByName(listOfPlayers.nextPageable());
            players = listOfPlayers.getContent();
        }
    }
}
