package com.nba.nbafantasyapp;

import com.nba.nbafantasyapp.player.Player;
import com.nba.nbafantasyapp.player.PlayerController;
import com.nba.nbafantasyapp.player.PlayerDTO;
import com.nba.nbafantasyapp.player.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

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

        PageRequest pageRequest = PageRequest.of(0, 50);
        List<PlayerDTO> playerList = List.of(player1, player2);
        Page<PlayerDTO> playerPage = new PageImpl<>(playerList, pageRequest, playerList.size());

        // Mock service response
        when(playerService.findAllPlayers(pageRequest)).thenReturn(Mono.just(playerPage));

        // Perform test
        webTestClient.get()
                .uri("/api/players?page=0")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.content").isArray()
                .jsonPath("$.content[0].firstName").isEqualTo("Precious")
                .jsonPath("$.content[1].firstName").isEqualTo("Steven")
                .jsonPath("$.totalElements").isEqualTo(2)
                .jsonPath("$.number").isEqualTo(0)
                .jsonPath("$.size").isEqualTo(50);
    }


    @Test
    public void testGetPlayerById() {
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

        Mono<PlayerDTO> playerMono = Mono.just(player1);

        // Mock service response
        when(playerService.findPlayerById(player1.getPlayerId())).thenReturn(playerMono);

        // Perform test
        webTestClient.get().uri("/api/players/" + player1.getPlayerId())
                .exchange().expectStatus()
                .isOk()
                .expectBody(Player.class);
    }
}
