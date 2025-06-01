package com.ticktaktoe.Gameapp.Service;

import com.ticktaktoe.Gameapp.Model.Game;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {
    private final Map<String, Game> games = new ConcurrentHashMap<>();

    public Game createGame() {
        Game game = new Game();
        games.put(game.getGameId(), game);
        return game;
    }

    public Game getGame(String gameId) {
        return games.get(gameId);
    }

    public Game makeMove(String gameId, int row, int col) {
        Game game = games.get(gameId);
        if (game == null || game.isGameOver()) {
            return game;
        }

        // Check if position is valid and empty
        if (row < 0 || row > 2 || col < 0 || col > 2 || !game.getBoard()[row][col].isEmpty()) {
            return game;
        }

        // Make the move
        game.getBoard()[row][col] = game.getCurrentPlayer();

        // Check for winner
        if (checkWinner(game)) {
            game.setWinner(game.getCurrentPlayer());
            game.setGameOver(true);
        } else if (isBoardFull(game)) {
            game.setGameOver(true);
        } else {
            // Switch player
            game.setCurrentPlayer(game.getCurrentPlayer().equals("X") ? "O" : "X");
        }

        return game;
    }

    private boolean checkWinner(Game game) {
        String[][] board = game.getBoard();
        String player = game.getCurrentPlayer();

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j].equals(player) && board[1][j].equals(player) && board[2][j].equals(player)) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull(Game game) {
        String[][] board = game.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Game resetGame(String gameId) {
        Game game = games.get(gameId);
        if (game != null) {
            game.setBoard(new String[3][3]);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    game.getBoard()[i][j] = "";
                }
            }
            game.setCurrentPlayer("X");
            game.setWinner(null);
            game.setGameOver(false);
        }
        return game;
    }
}

