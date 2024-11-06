package com.nba.nbafantasyapp.player;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PagedModel;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // GET REQUESTS
    @GetMapping
    public PagedModel<Player> getAllPlayers(@RequestParam int pageNumber, @RequestParam int pageSize) {
        Page<Player> page = playerService.findAll(pageNumber, pageSize);
        return new PagedModel<>(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable long id) {
        return playerService.findPlayer(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
