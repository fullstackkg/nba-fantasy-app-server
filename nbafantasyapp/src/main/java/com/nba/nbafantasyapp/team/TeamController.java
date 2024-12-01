package com.nba.nbafantasyapp.team;

import com.nba.nbafantasyapp.player.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    public Flux<Team> getAllTeams() {
        return teamService.findAllTeams();
    }

    @GetMapping("/{teamId}")
    public Mono<ResponseEntity<Team>> getTeamById(@PathVariable long teamId) {
        return teamService.findByTeamId(teamId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

//    @GetMapping("/{teamId}/players")
//    public Flux<Player> getPlayersByTeamId(@PathVariable long teamId) {
//        return teamService.findPlayersByTeamId(teamId)
//                .switchIfEmpty(Flux.empty());
//    }
}
