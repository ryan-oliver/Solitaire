import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// Main class. Game runs from here. Represents physical game board.

public class GameBoard extends Application {

    private static boolean gameStarted = false; // Boolean to know if game has started

    public void start(Stage primaryStage) {

        // Create main pane
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #008000");

        // Add foundations and tableau to pane
        FoundationPile.getFoundations(pane);

        Tableau tableau = new Tableau();
        tableau.getTableau(pane);

        // Start button
        Button start = new Button("Start");
        start.setLayoutX(100);
        start.setLayoutY(30);
        start.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        pane.getChildren().add(start);
        start.setOnAction((e) -> {
            if (!gameStarted) { // Prevents start button use after game start
                tableau.deal(pane);
            }
        });

        // Undo button
        Button undo = new Button("Undo");
        undo.setLayoutX(100); // X and Y locations of button
        undo.setLayoutY(70);
        undo.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        pane.getChildren().add(undo);

        // Redo button
        Button redo = new Button("Redo");
        redo.setLayoutX(100);
        redo.setLayoutY(110);
        redo.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        pane.getChildren().add(redo);

        // Restart button
        Button restart = new Button("Restart");
        restart.setLayoutX(100);
        restart.setLayoutY(150);
        restart.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        pane.getChildren().add(restart);
        restart.setOnAction((e) -> {
            if (gameStarted) { // prevents restart button use if game has not started
                tableau.restart(pane);
            }
        });

        // Set scene and show stage
        Scene scene = new Scene(pane, 1200, 800);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bakers Dozen");
        primaryStage.show();
    }

    static void setGameStarted(boolean status) {
        gameStarted = status;
    }

}
