package com.nba.nbafantasyapp;

import com.nba.nbafantasyapp.player.Player;
import com.nba.nbafantasyapp.player.PlayerController;
import com.nba.nbafantasyapp.player.PlayerDTO;
import com.nba.nbafantasyapp.player.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Month;

import static org.mockito.Mockito.when;


@WebFluxTest(PlayerController.class)
public class PlayerControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PlayerService playerService;

    @Test
    public void testGetAllPlayersWithTeam() {
        // Mock data
        PlayerDTO player1 = new PlayerDTO(
                1630173L,
                "Precious",
                "Achiuwa",
                LocalDate.of(1999, Month.SEPTEMBER, 19),
                "6-8",
                (short) 243,
                (byte) 4,
                (byte) 5,
                "Forward",
                "https://cdn.nba.com/headshots/nba/latest/1040x760/1630173.png",
                1610612752L,
                "New York Knicks",
                "Eastern",
                "Atlantic",
                "https://cdn.nba.com/logos/nba/1610612752/global/L/logo.svg"
        );

        PlayerDTO player2 = new PlayerDTO(
                203500L,
                "Steven",
                "Adams",
                LocalDate.of(1993, Month.JULY, 20),
                "6-11",
                (short) 265,
                (byte) 10,
                (byte) 12,
                "Center",
                "https://cdn.nba.com/headshots/nba/latest/1040x760/203500.png",
                1610612745L,
                "Houston Rockets",
                "Western",
                "Southwest",
                "https://cdn.nba.com/logos/nba/1610612745/global/L/logo.svg"
        );

        Flux<PlayerDTO> playerFlux = Flux.just(player1, player2);

        // Mock service response
        when(playerService.findAllPlayerDTO(0)).thenReturn(Page<PlayerDTO>);

        // Perform test
        webTestClient.get().uri("/api/players?page=0")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(PlayerDTO.class)
                .hasSize(2)
                .contains(player1, player2);
    }

    @Test
    public void testGetPlayerById() {
        // Mock data
        Player player1 = new Player(
                1630173L,
                "Precious",
                "Achiuwa",
                LocalDate.of(1999, Month.SEPTEMBER, 19),
                "6-8",
                (short) 243,
                (byte) 4,
                (byte) 5,
                "Forward",
                1610612752L,
                "https://cdn.nba.com/headshots/nba/latest/1040x760/1630173.png"
        );

        Mono<Player> playerMono = Mono.just(player1);

        // Mock service response
        when(playerService.findPlayerById(player1.getPlayerId())).thenReturn(playerMono);

        // Perform test
        webTestClient.get().uri("/api/players/" + player1.getPlayerId())
                .exchange().expectStatus()
                .isOk()
                .expectBody(Player.class);
    }

    @Test
    public void testGetPlayerByTeamId() {
        // Mock data
        Player player1 = new Player(
                1630173L,
                "Precious",
                "Achiuwa",
                LocalDate.of(1999, Month.SEPTEMBER, 19),
                "6-8",
                (short) 243,
                (byte) 4,
                (byte) 5,
                "Forward",
                1610612752L,
                "https://cdn.nba.com/headshots/nba/latest/1040x760/1630173.png"
        );

        Player player2 = new Player(
                1628384L,
                "OG",
                "Anunoby",
                LocalDate.of(1997, Month.JULY, 17),
                "6-7",
                (short) 240,
                (byte) 7,
                (byte) 8,
                "Forward-Guard",
                1610612752L,
                "https://cdn.nba.com/headshots/nba/latest/1040x760/1628384.png"
        );

        Flux<Player> playerFlux = Flux.just(player1, player2);

        // Mock service response
        when(playerService.findPlayerByTeamId(player1.getTeamId())).thenReturn(playerFlux);

        // Perform test
        webTestClient.get().uri("/api/players/team/" + player1.getTeamId())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Player.class)
                .hasSize(2)
                .contains(player1, player2);
    }
}
