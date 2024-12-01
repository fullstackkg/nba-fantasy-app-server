package com.nba.nbafantasyapp.player;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;

        // GET REQUESTS
        @GetMapping
        public Mono<Page<PlayerDTO>> getAllPlayers(@RequestParam(defaultValue = "0") int page) {
            int pageSize = 50;
            PageRequest pageRequest = PageRequest.of(page, pageSize);
            return playerService.findAllPlayers(pageRequest);
        }

    @GetMapping("/{playerId}")
    public Mono<ResponseEntity<PlayerDTO>> getPlayerById(@PathVariable long playerId) {
        return playerService.findPlayerById(playerId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
