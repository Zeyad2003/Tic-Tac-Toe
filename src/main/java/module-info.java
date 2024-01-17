module com.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.tictactoe to javafx.fxml;
    exports com.tictactoe;
}