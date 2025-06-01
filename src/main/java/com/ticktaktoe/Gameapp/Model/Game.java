package com.ticktaktoe.Gameapp.Model;

public class Game {

        private String[][] board;
        private String currentPlayer;
        private String winner;
        private boolean gameOver;
        private String gameId;

        public Game() {
            this.board = new String[3][3];
            this.currentPlayer = "X";
            this.winner = null;
            this.gameOver = false;
            this.gameId = java.util.UUID.randomUUID().toString();
            initializeBoard();
        }

        private void initializeBoard() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = "";
                }
            }
        }

        // Getters and Setters
        public String[][] getBoard() { return board; }
        public void setBoard(String[][] board) { this.board = board; }

        public String getCurrentPlayer() { return currentPlayer; }
        public void setCurrentPlayer(String currentPlayer) { this.currentPlayer = currentPlayer; }

        public String getWinner() { return winner; }
        public void setWinner(String winner) { this.winner = winner; }

        public boolean isGameOver() { return gameOver; }
        public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }

        public String getGameId() { return gameId; }
        public void setGameId(String gameId) { this.gameId = gameId; }
    }

