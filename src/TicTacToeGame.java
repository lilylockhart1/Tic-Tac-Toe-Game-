public class TicTacToeGame {
    private final String[][] grid = new String[3][3];
    private String currentPlayer = "X";
    private boolean gameOver = false;
    private boolean isTie = false;
    private int turns = 0;
    private int[][] winningCoords = new int[3][2];

    public TicTacToeGame() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                grid[r][c] = "";
            }
        }
    }

    // Public interface to interact with the game safely
    public boolean makeMove(int row, int col) {
        if (gameOver || !grid[row][col].isEmpty()) {
            return false;
        }

        grid[row][col] = currentPlayer;
        turns++;
        checkWinner();

        if (!gameOver) {
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }
        return true;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isTie() {
        return isTie;
    }

    public int[][] getWinningCoords() {
        return winningCoords;
    }

    // Internal logic hidden from the UI
    private void checkWinner() {
        // Horizontal
        for (int r = 0; r < 3; r++) {
            if (!grid[r][0].isEmpty() &&
                    grid[r][0].equals(grid[r][1]) &&
                    grid[r][1].equals(grid[r][2])) {
                setWinState(new int[][]{{r, 0}, {r, 1}, {r, 2}});
                return;
            }
        }

        // Vertical
        for (int c = 0; c < 3; c++) {
            if (!grid[0][c].isEmpty() &&
                    grid[0][c].equals(grid[1][c]) &&
                    grid[1][c].equals(grid[2][c])) {
                setWinState(new int[][]{{0, c}, {1, c}, {2, c}});
                return;
            }
        }

        // Diagonal
        if (!grid[0][0].isEmpty() &&
                grid[0][0].equals(grid[1][1]) &&
                grid[1][1].equals(grid[2][2])) {
            setWinState(new int[][]{{0, 0}, {1, 1}, {2, 2}});
            return;
        }

        // Anti-diagonal
        if (!grid[0][2].isEmpty() &&
                grid[0][2].equals(grid[1][1]) &&
                grid[1][1].equals(grid[2][0])) {
            setWinState(new int[][]{{0, 2}, {1, 1}, {2, 0}});
            return;
        }

        // Tie check
        if (turns == 9) {
            isTie = true;
            gameOver = true;
        }
    }

    private void setWinState(int[][] coords) {
        this.winningCoords = coords;
        this.gameOver = true;
    }
}