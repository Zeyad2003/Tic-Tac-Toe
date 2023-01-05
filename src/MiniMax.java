import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Objects;

public class MiniMax {
    private final int len = 3; // length of the board (3x3)

    // This function is used to get the winning cells by checking if the player has won the game
    public ArrayList<Pair<Integer, Integer>> GetWinner(String[][] boardGame, String winner) {
        ArrayList<Pair<Integer, Integer>> WinCells = new ArrayList<>();
        for (int i = 0; i < len; ++i) {

            if (boardGame[i][0].equals(boardGame[i][1]) && boardGame[i][0].equals(boardGame[i][2]) && boardGame[i][0].equals(winner)) {
                WinCells.add(new Pair(i, 0));
                WinCells.add(new Pair(i, 1));
                WinCells.add(new Pair(i, 2));
                return WinCells;
            }

            if (boardGame[0][i].equals(boardGame[1][i]) && boardGame[0][i].equals(boardGame[2][i]) && boardGame[0][i].equals(winner)) {
                WinCells.add(new Pair(0, i));
                WinCells.add(new Pair(1, i));
                WinCells.add(new Pair(2, i));
                return WinCells;
            }
        }

        if (boardGame[0][0].equals(boardGame[1][1]) && boardGame[0][0].equals(boardGame[2][2]) && boardGame[1][1].equals(winner)) {
            WinCells.add(new Pair(0, 0));
            WinCells.add(new Pair(1, 1));
            WinCells.add(new Pair(2, 2));
            return WinCells;
        }
        if (boardGame[1][1].equals(boardGame[2][0]) && boardGame[1][1].equals(boardGame[0][2]) && boardGame[1][1].equals(winner)) {
            WinCells.add(new Pair(0, 2));
            WinCells.add(new Pair(1, 1));
            WinCells.add(new Pair(2, 0));
            return WinCells;
        }
        return WinCells;
    }

    // Loop through the board and check if there is a free cell then the game isn't over
    public boolean IsGameComplete(String[][] boardGame) {
        for (int row = 0; row < len; ++row) {
            for (int col = 0; col < len; ++col) {
                if (Objects.equals(boardGame[row][col], ""))
                    return false;
            }
        }
        return true;
    }

    // return the Cell indicates the best cell to play
    public Pair<Integer, Integer> BestMove(String[][] boardGame) {

        int bestScore = Integer.MIN_VALUE;
        Pair<Integer, Integer> CellToPlay = new Pair(-1, -1); // Initial value with -1 to indicate that there is no best move yet


        for (int row = 0; row < len; ++row) {
            for (int col = 0; col < len; ++col) {
                if (boardGame[row][col].equals("")) {

                    boardGame[row][col] = "O"; // AI is play

                    int Score = MiniMax(boardGame, false);

                    boardGame[row][col] = ""; // Backtrack

                    if (Score > bestScore) { // Always choose the best score
                        CellToPlay = new Pair(row, col);
                        bestScore = Score;
                    }
                }
            }
        }

        return CellToPlay;
    }

    // return the best score then choose the best cell to play in the BestMove function
    public int MiniMax(String[][] boardGame, boolean isMax) {

        // infinity as we use it in the in every stage
        int OO = 1000;
        if (GetWinner(boardGame, "X").size() == 3)
            return -OO; // player X win

        if (GetWinner(boardGame, "O").size() == 3) // AI should win so will try to maximize the score
            return OO; // AI win

        if (IsGameComplete(boardGame))
            return 0; // tie


        int MX = -OO, MN = OO;

        for (int row = 0; row < len; ++row) {
            for (int col = 0; col < len; ++col) {
                if (boardGame[row][col].equals("")) {
                    if (isMax) { // AI turn
                        boardGame[row][col] = "O";

                        int curPath = MiniMax(boardGame, !isMax); // maximize the score with recursive call
                        MX = Math.max(MX, curPath);

                        boardGame[row][col] = ""; // backtrack
                    } else {

                        boardGame[row][col] = "X";

                        int curPath = MiniMax(boardGame, !isMax); // minimize the score with recursive call
                        MN = Math.min(MN, curPath);

                        boardGame[row][col] = "";
                    }
                }
            }
        }
        return isMax ? MX : MN;
    }
}