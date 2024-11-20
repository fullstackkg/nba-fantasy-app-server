package com.nba.nbafantasyapp.player;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;

    // GET REQUESTS
    @GetMapping
    public Flux<Player> getAllPlayers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        return playerService.findAllPlayers(page, size);
    }

    @GetMapping("/{playerId}")
    public Mono<ResponseEntity<Player>> getPlayerById(@PathVariable long playerId) {
        return playerService.findPlayerById(playerId)
                .map(player -> ResponseEntity.ok(player))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/team/{teamId}")
    public Flux<Player> getPlayerByTeamId(@PathVariable long teamId) {
        return playerService.findPlayerByTeamId(teamId).switchIfEmpty(Flux.empty());
    }
}
