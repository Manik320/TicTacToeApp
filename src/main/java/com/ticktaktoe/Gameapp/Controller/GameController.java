package com.ticktaktoe.Gameapp.Controller;

import com.ticktaktoe.Gameapp.DTO.MoveRequest;
import com.ticktaktoe.Gameapp.Model.Game;
import com.ticktaktoe.Gameapp.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/new")
    public ResponseEntity<Game> createGame() {
        Game game = gameService.createGame();
        return ResponseEntity.ok(game);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable String gameId) {
        Game game = gameService.getGame(gameId);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }

    @PostMapping("/{gameId}/move")
    public ResponseEntity<Game> makeMove(@PathVariable String gameId, @RequestBody MoveRequest moveRequest) {
        Game game = gameService.makeMove(gameId, moveRequest.getRow(), moveRequest.getCol());
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }

    @PostMapping("/{gameId}/reset")
    public ResponseEntity<Game> resetGame(@PathVariable String gameId) {
        Game game = gameService.resetGame(gameId);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }
    @GetMapping("/")
    public String index() {
        return "index";
    }

}