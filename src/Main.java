import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author: Zeyad Nasef
 * @Created: 1/5/2023
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AppController.SetStartControl();
        AppController.ViewPane(AppController.startMenu);

        Scene scene = new Scene(AppController.rootPane, 450, 600);

        primaryStage.setTitle("Tic Tac Toe Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}