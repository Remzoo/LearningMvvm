package morawski.rafal.ticktactoe.model;

/**
 * Created by Rafal on 2018-03-28.
 */

public class Game {

    private static final String TAG = Game.class.getSimpleName();
    private static final int BOARD_SIZE = 3;

    private Player player1;
    private Player player2;
    private Player winner;
    private Player currentPlayer;

    private Cell[][] board;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;

        board = new Cell[BOARD_SIZE][BOARD_SIZE];
    }

    public boolean isEmptyCell(int row, int column) {
        return board[row][column] == null;
    }

    public void setPlayerAt(int row, int column) {
        board[row][column] = new Cell(currentPlayer);
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean hasGameEnded() {
        // Check for winner
        if(hasHorizontal() || hasVertical() || hasDiagonal()) {
            winner = currentPlayer;
            return true;
        }

        // Tie, board is full
        if(isBoardFull()) {
            winner = null;
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                if(board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hasHorizontal() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            if(areEqual(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean hasVertical() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            if(areEqual(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDiagonal() {
        return areEqual(board[0][0], board[1][1], board[2][2])
                || areEqual(board[0][2], board[1][1], board[2][0]);
    }

    private boolean areEqual(Cell... cells) {
        if(cells == null || cells.length == 0) {
            return false;
        }

        for(Cell cell : cells) {
            if(cell == null) {
                return false;
            }
        }

        Cell base = cells[0];
        for(int i = 1; i < BOARD_SIZE; i++) {
            if(!cells[i].getPlayer().getSign().equals(base.getPlayer().getSign())) {
                return false;
            }
        }

        return true;
    }
}
