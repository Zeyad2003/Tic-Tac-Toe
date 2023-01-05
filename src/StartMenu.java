import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.FileInputStream;


public class StartMenu extends GridPane {

    private final Button startPlay = new Button("Let's Play");
    private final Button exit = new Button("Exit");

    private Image image;

    // Constructor
    public StartMenu() {

        startPlay.setPrefWidth(240); // set the width of the button

        exit.setPrefWidth(240); // set the width of the button

        setHgap(10); // Horizontal gap between columns
        setVgap(10); // Vertical gap between rows
        setAlignment(Pos.CENTER); // Center the pane
        setPadding(new Insets(50)); // Set all sides to 50
        setMinSize(400, 500); // Set the minimum size

        //SetBackGround image for start menu
        setBackground(SetBackground());

        //add the button to the grid pane
        add(startPlay, 0, 0);

        add(exit, 0, 3);

        // View the player pane
        startPlay.setOnAction((Action) -> AppController.ViewPane(AppController.Player));

        // Exit the game
        exit.setOnAction((Action) -> System.exit(0));
    }


    //Set the background image for the start menu
    public Background SetBackground() {

        try {
            image = new Image(new FileInputStream("./src/img/Background.jpg"));
        } catch (Exception e) {
            System.out.println("Error with img path: " + e);
            System.exit(0);
        }

        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        return new Background(backgroundImage);
    }

    // Set the font for the buttons
    public void SetFont(Font newFont) {
        startPlay.setFont(newFont);
        exit.setFont(newFont);
    }
}