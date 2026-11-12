import java.awt.*;
import javax.swing.*;

public class TicTacToe {
    private final int boardWidth = 600;
    private final int boardHeight = 650;

    private final JFrame frame = new JFrame("Tic-Tac-Toe");
    private final JLabel textLabel = new JLabel();
    private final JPanel textPanel = new JPanel();
    private final JPanel boardPanel = new JPanel();
    private final JButton[][] board = new JButton[3][3];

    private final TicTacToeGame game = new TicTacToeGame();

    public TicTacToe() {
        setupWindow();
        setupBoard();
        frame.setVisible(true);
    }

    private void setupWindow() {
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);
    }

    private void setupBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                final int row = r;
                final int col = c;

                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.black);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(e -> handleTileClick(row, col, tile));
            }
        }
    }

    private void handleTileClick(int row, int col, JButton tile) {
        String playerBeforeMove = game.getCurrentPlayer();

        if (game.makeMove(row, col)) {
            tile.setText(playerBeforeMove);
            updateGameStatus();
        }
    }

    private void updateGameStatus() {
        if (game.isGameOver()) {
            if (game.isTie()) {
                highlightTie();
            } else {
                highlightWinner();
            }
        } else {
            textLabel.setText(game.getCurrentPlayer() + "'s turn.");
        }
    }

    private void highlightWinner() {
        for (int[] coord : game.getWinningCoords()) {
            JButton tile = board[coord[0]][coord[1]];
            tile.setForeground(Color.green);
            tile.setBackground(Color.gray);
        }
        textLabel.setText(game.getCurrentPlayer() + " is the winner!");
    }

    private void highlightTie() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setForeground(Color.orange);
                board[r][c].setBackground(Color.gray);
            }
        }
        textLabel.setText("Tie!");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}