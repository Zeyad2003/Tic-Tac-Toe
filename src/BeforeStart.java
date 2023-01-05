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

    // Constructor
    public BeforeStart() {
        start.setPrefWidth(200); //set the width of the button
        back.setPrefWidth(200); //set the width of the button


        setHgap(20); //set the horizontal gap between the buttons
        setVgap(20); //set the vertical gap between the buttons
        setAlignment(Pos.CENTER); //set the alignment of the buttons
        setPadding(new Insets(50)); //set the padding of the buttons
        setMinSize(400, 500); //set the minimum size of the buttons

        setMargin(start, new Insets(40, 0, 0, 0)); // Top, Right, Bottom, Left
        setHalignment(start, HPos.CENTER); //set the horizontal alignment of the buttons
        setHalignment(back, HPos.CENTER); //set the horizontal alignment of the buttons


        setColumnSpan(start, 2); //set the column span of the buttons
        setColumnSpan(back, 2); //set the column span of the buttons

        add(playerXLabel, 0, 0); //add the button to the grid pane
        add(playerXName, 1, 0); //add the button to the grid pane
        add(start, 0, 1); //add the button to the grid pane
        add(back, 0, 2); //add the button to the grid pane

        //set the action of the button
        start.setOnAction((Action) -> {
            if (!playerXName.getText().equals("") && !playerXName.getText().equals("Computer")) {
                AppController.gameBoard.StartGameBoard(playerXName.getText(), "Computer");
                AppController.ViewPane(AppController.gameBoard);
            }
        });

        //set the action of the button
        back.setOnAction((Action) -> AppController.ViewPane(AppController.startMenu));
    }

    //set the font of the buttons
    public void SetFont(Font newFont) {
        playerXLabel.setFont(newFont);
        playerXName.setFont(newFont);
        start.setFont(newFont);
        back.setFont(newFont);
    }
}