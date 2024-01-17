package com.tictactoe;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class BeforeStart extends GridPane {

    private final Label playerXLabel = new Label("Player X:");
    private final TextField playerXName = new TextField("Player");
    private final Button start = new Button("Start");
    private final Button back = new Button("Back");


    public BeforeStart() {
        start.setPrefWidth(200);
        back.setPrefWidth(200);


        setHgap(20);
        setVgap(20);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(50));
        setMinSize(400, 500);

        setMargin(start, new Insets(40, 0, 0, 0));
        setHalignment(start, HPos.CENTER);
        setHalignment(back, HPos.CENTER);


        setColumnSpan(start, 2);
        setColumnSpan(back, 2);

        add(playerXLabel, 0, 0);
        add(playerXName, 1, 0);
        add(start, 0, 1);
        add(back, 0, 2);

        start.setOnAction((Action) -> {
            if (!playerXName.getText().isEmpty() && !playerXName.getText().equals("Computer")) {
                AppController.gameBoard.StartGameBoard(playerXName.getText(), "Computer");
                AppController.ViewPane(AppController.gameBoard);
            }
        });

        back.setOnAction((Action) -> AppController.ViewPane(AppController.startMenu));
    }

    public void SetFont(Font newFont) {
        playerXLabel.setFont(newFont);
        playerXName.setFont(newFont);
        start.setFont(newFont);
        back.setFont(newFont);
    }
}