package com.tictactoe;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;


public class AppController {
    static StartMenu startMenu = new StartMenu();

    static BeforeStart Player = new BeforeStart();

    static GameBoard gameBoard = new GameBoard();

    static BorderPane rootPane = new BorderPane();

    static Font currentFont;

    static Image imageBoard;

    public static void ViewPane(GridPane currentPane) {
        rootPane.getChildren().clear();
        rootPane.setCenter(currentPane);
    }

    public static void SetFontControl() {
        startMenu.SetFont(currentFont);
        Player.SetFont(currentFont);
        gameBoard.SetFont(currentFont);
    }

    public static void SetStartControl() {
        AppController.SetFontSize(20);
        AppController.SetImageBoard("src/main/resources/img/board.jpg");
        SetFontControl();
    }

    public static void SetFontSize(int fontSize) {
        AppController.currentFont = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, fontSize);
    }

    public static void SetImageBoard(String background) {
        try {
            AppController.imageBoard = new Image(new FileInputStream(background));
        } catch (Exception e) {
            System.out.println("Can Not Load Image Board !!!" + e);
            System.exit(0);
        }
    }
}