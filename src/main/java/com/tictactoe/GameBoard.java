package com.tictactoe;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Pair;

import java.util.ArrayList;


public class GameBoard extends GridPane {

    private final int len = 3;
    private final Color colorX = Color.BLUE;
    private final Color colorO = Color.RED;
    private final MiniMax MiniMax = new MiniMax();
    private final Label playerXName = new Label();
    private final Label playerOName = new Label();
    private final Label playerXScore = new Label();
    private final Label playerOScore = new Label();

    private final GridPane boardPane = new GridPane();

    private final Button[][] boardButtons = new Button[len][len];
    private final Button back = new Button("Back");
    private final Button newGame = new Button("New Game");

    private boolean isGameEnd;
    private boolean isXPlay = true;

    private final String[][] boardStr = new String[len][len];


    private final String defaultStyleButton = "-fx-background-color:#F4F4F4;"
            + "-fx-border-color:transparent;"
            + "-fx-border-radius:50%;"
            + "-fx-background-insets:0px;"
            + "-fx-cursor:hand;";

    // Constructor
    public GameBoard() {

        playerXName.setMaxSize(120, Double.MAX_VALUE);
        setHgrow(playerXName, Priority.SOMETIMES);
        playerOName.setMaxSize(120, Double.MAX_VALUE);
        setHgrow(playerOName, Priority.SOMETIMES);
        playerXScore.setMaxSize(120, Double.MAX_VALUE);
        setHgrow(playerXScore, Priority.SOMETIMES);
        playerOScore.setMaxSize(120, Double.MAX_VALUE);
        setHgrow(playerOScore, Priority.SOMETIMES);

        CreateBoard();

        playerXName.setAlignment(Pos.CENTER);
        playerOName.setAlignment(Pos.CENTER);
        playerXScore.setAlignment(Pos.CENTER);
        playerOScore.setAlignment(Pos.CENTER);

        back.setPrefWidth(130);
        newGame.setPrefWidth(130);

        setHgrow(back, Priority.ALWAYS);
        setHgrow(newGame, Priority.ALWAYS);

        setVgap(20);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(50));
        setMinSize(400, 500);

        setMargin(boardPane, new Insets(30, 0, 30, 0));

        setHalignment(playerXName, HPos.CENTER);
        setHalignment(playerOName, HPos.CENTER);
        setHalignment(playerXScore, HPos.CENTER);
        setHalignment(playerOScore, HPos.CENTER);
        setHalignment(boardPane, HPos.CENTER);
        setHalignment(back, HPos.CENTER);
        setHalignment(newGame, HPos.CENTER);

        setValignment(playerXName, VPos.CENTER);
        setValignment(playerOName, VPos.CENTER);
        setValignment(playerXScore, VPos.CENTER);
        setValignment(playerOScore, VPos.CENTER);
        setValignment(boardPane, VPos.CENTER);
        setValignment(back, VPos.CENTER);
        setValignment(newGame, VPos.CENTER);

        setColumnSpan(boardPane, 3);
        setRowSpan(boardPane, 3);

        add(playerXName, 0, 0);
        add(playerOName, 2, 0);
        add(playerXScore, 0, 1);
        add(playerOScore, 2, 1);

        add(boardPane, 0, 2);

        add(back, 0, 5);
        add(newGame, 2, 5);


        newGame.setOnAction(
                (Action) -> {
                    this.StartNewGame(Integer.parseInt(playerXScore.getText()), Integer.parseInt(playerOScore.getText()));
                    this.AIPlay();
                }
        );

        back.setOnAction((Action) -> AppController.ViewPane(AppController.Player));
    }


    public void CreateBoard() {
        boardPane.setHgap(10);
        boardPane.setVgap(10);
        boardPane.setAlignment(Pos.CENTER);

        for (int row = 0; row < len; ++row) {
            for (int col = 0; col < len; ++col) {
                boardButtons[row][col] = new Button("");
                boardStr[row][col] = "";

                boardButtons[row][col].setPrefSize(100, 100);

                boardButtons[row][col].setFocusTraversable(false);
                boardButtons[row][col].setStyle(defaultStyleButton);
                boardButtons[row][col].setFont(Font.font("Comic Sans Ms", FontWeight.BOLD, 35));
                boardButtons[row][col].setAlignment(Pos.CENTER);
                boardButtons[row][col].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

                setHgrow(boardButtons[row][col], Priority.SOMETIMES);
                setVgrow(boardButtons[row][col], Priority.SOMETIMES);
                setHalignment(boardButtons[row][col], HPos.CENTER);
                setValignment(boardButtons[row][col], VPos.CENTER);

                boardPane.add(boardButtons[row][col], col, row);
            }
        }

        // Add event to each button
        for (int row = 0; row < len; ++row) {
            for (int col = 0; col < len; ++col) {
                final int x = row, y = col; // must be final to use in Action

                boardButtons[row][col].setOnAction((Action) -> {
                    if (!isGameEnd && boardButtons[x][y].getText().isEmpty()) {
                        this.SetCurrentPlay(x, y);

                        this.isGameEnd = CheckGameEnd();

                        isXPlay = !isXPlay;

                        this.AIPlay();
                    }
                });
            }
        }
    }

    // The Actions AI will do
    public void AIPlay() {
        if (!isGameEnd && !isXPlay) {
            Pair<Integer, Integer> cellToPlay = MiniMax.BestMove(boardStr); // Return the best Cell for AI to move
            this.SetCurrentPlay(cellToPlay.getKey(), cellToPlay.getValue()); // take the row and col from the pair and set the cell with O
            this.isGameEnd = CheckGameEnd();
            isXPlay = !isXPlay;
        }
    }

    // Set the name of the two players
    public void StartGameBoard(String firstName, String secondName) {
        playerXName.setText(firstName);
        playerOName.setText(secondName);

        this.isXPlay = true;
        this.SetBackgroundBoard(AppController.imageBoard);

        this.StartNewGame(0, 0);
    }

    public void StartNewGame(int firstScore, int secondScore) {
        this.ClearBoard();
        this.SetGameScore(firstScore, secondScore);
        this.isGameEnd = false;
    }

    public void SetGameScore(int firstScore, int secondScore) {
        playerXScore.setText(Integer.toString(firstScore));
        playerOScore.setText(Integer.toString(secondScore));
    }


    // Set the cell with the current player
    public void SetCurrentPlay(int x, int y) {
        if (isXPlay) {
            boardStr[x][y] = "X";
            boardButtons[x][y].setText(boardStr[x][y]);
            boardButtons[x][y].setTextFill(colorX);
        } else {
            boardStr[x][y] = "O";
            boardButtons[x][y].setText(boardStr[x][y]);
            boardButtons[x][y].setTextFill(colorO);
        }
    }

    // Check if there is a player won the game or there's a tie and return true if so
    public boolean CheckGameEnd() {

        ArrayList<Pair<Integer, Integer>> O_WinCells = MiniMax.GetWinner(boardStr, "O");
        ArrayList<Pair<Integer, Integer>> X_WinCells = MiniMax.GetWinner(boardStr, "X");

        if (X_WinCells.size() == 3) {
            this.SetColorWin(X_WinCells);
            this.SetGameScore(Integer.parseInt(playerXScore.getText()) + 1, Integer.parseInt(playerOScore.getText()));

            showWinDialog();

            return true;
        } else if (O_WinCells.size() == 3) {
            this.SetColorWin(O_WinCells);
            this.SetGameScore(Integer.parseInt(playerXScore.getText()), Integer.parseInt(playerOScore.getText()) + 1);

            showLoseDialog();

            return true;
        } else if (MiniMax.IsGameComplete(boardStr)) { // tie
            this.SetGameScore(Integer.parseInt(playerXScore.getText()), Integer.parseInt(playerOScore.getText()));

            showTieDialog();

            return true;
        }
        return false;
    }

    public void SetColorWin(ArrayList<Pair<Integer, Integer>> WinCells) {
        for (Pair<Integer, Integer> p : WinCells) {
            boardButtons[p.getKey()][p.getValue()].setStyle("-fx-background-color:aqua");
        }
    }

    // Show the dialog when the player X win
    public void showWinDialog() {
        Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
        winAlert.setTitle("Congratulations");
        winAlert.setHeaderText(null);
        winAlert.setContentText("Congratulations!! You Win :)");
        winAlert.getDialogPane().setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 20; -fx-font-weight: normal;");
        winAlert.showAndWait();
    }

    // Show the dialog when the player X lose
    public void showLoseDialog() {
        Alert lostAlert = new Alert(Alert.AlertType.ERROR);
        lostAlert.setTitle("Oops");
        lostAlert.setHeaderText(null);
        lostAlert.setContentText("Oops!!, You Lost :(");
        lostAlert.getDialogPane().setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 20; -fx-font-weight: normal;");
        lostAlert.showAndWait();
    }

    // Show a dialog to the user if it is a Tie
    public void showTieDialog() {
        Alert tieAlert = new Alert(Alert.AlertType.INFORMATION);
        tieAlert.setTitle("Tie");
        tieAlert.setHeaderText(null);
        tieAlert.setContentText("It's Draw, You can have another try ;)");
        tieAlert.getDialogPane().setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 20; -fx-font-weight: normal;");
        tieAlert.showAndWait();
    }

    public void ClearBoard() {
        for (int row = 0; row < len; ++row) {
            for (int col = 0; col < len; ++col) {
                boardButtons[row][col].setStyle(defaultStyleButton);
                boardButtons[row][col].setText("");
                boardStr[row][col] = "";
            }
        }
    }

    // Set font
    public void SetFont(Font newFont) {
        playerXName.setFont(newFont);
        playerOName.setFont(newFont);
        playerXScore.setFont(newFont);
        playerOScore.setFont(newFont);
        back.setFont(newFont);
        newGame.setFont(newFont);
    }

    // Design the background of the board
    public void SetBackgroundBoard(Image newImage) {
        BackgroundImage backgroundImage = new BackgroundImage(newImage,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);

        this.boardPane.setBackground(background);
    }
}