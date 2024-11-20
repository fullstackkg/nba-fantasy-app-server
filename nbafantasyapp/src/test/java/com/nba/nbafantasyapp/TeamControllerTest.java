package com.nba.nbafantasyapp;

import com.nba.nbafantasyapp.team.Team;
import com.nba.nbafantasyapp.team.TeamController;
import com.nba.nbafantasyapp.team.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest(TeamController.class)
public class TeamControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @MockBean
    TeamService teamService;

    @Test
    public void testGetAllTeams() {
        // Mock data
        Team team1 = new Team(
                1610612737L,
                "ATL",
                "Atlanta Hawks",
                "Atlanta",
                "Georgia",
                "https://cdn.nba.com/logos/nba/1610612737/global/L/logo.svg",
                "Eastern",
                "Southeast"
        );

        Team team2 = new Team(
                1610612738L,
                "BOS",
                "Boston Celtics",
                "Boston",
                "Massachusetts",
                "https://cdn.nba.com/logos/nba/1610612738/global/L/logo.svg",
                "Eastern",
                "Atlantic"
        );

        Flux<Team> teamFlux = Flux.just(team1, team2);

        // Mock service response
        when(teamService.findAllTeams()).thenReturn(teamFlux);

        // Perform test
        webTestClient.get().uri("/api/teams")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Team.class)
                .hasSize(2)
                .contains(team1, team2);
    }

    @Test
    public void testGetTeamById() {
        // Mock data
        Team team1 = new Team(
                1610612737L,
                "ATL",
                "Atlanta Hawks",
                "Atlanta",
                "Georgia",
                "https://cdn.nba.com/logos/nba/1610612737/global/L/logo.svg",
                "Eastern",
                "Southeast"
        );

        Mono<Team> teamMono = Mono.just(team1);

        // Mock service response
        when(teamService.findByTeamId(team1.getTeamId())).thenReturn(teamMono);

        // Perform test
        webTestClient.get().uri("/api/teams/" + team1.getTeamId())
                .exchange().expectStatus()
                .isOk()
                .expectBody(Team.class);
    }
}
