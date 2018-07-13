import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameBoard extends Application {

    private static boolean gameStarted = false;

    public void start(Stage primaryStage) {


        // Create main pane ------------------------------------------------------------
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #008000");

        // Add foundations and tableau -------------------------------------------------
        FoundationPile.getFoundations(pane);

        Tableau tableau = new Tableau();
        tableau.getTableau(pane);

        // Create buttons --------------------------------------------------------------
        Button start = new Button("Start");
        start.setLayoutX(100);
        start.setLayoutY(30);
        start.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        pane.getChildren().add(start);
        start.setOnAction((e) -> {
            if (!gameStarted) {
                tableau.deal(pane);
            }
        });

        Button undo = new Button("Undo");
        undo.setLayoutX(100);
        undo.setLayoutY(70);
        undo.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        pane.getChildren().add(undo);

        Button redo = new Button("Redo");
        redo.setLayoutX(100);
        redo.setLayoutY(110);
        redo.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        pane.getChildren().add(redo);

        Button restart = new Button("Restart");
        restart.setLayoutX(100);
        restart.setLayoutY(150);
        restart.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        pane.getChildren().add(restart);
        restart.setOnAction((e) -> {
            if (gameStarted) {
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

    public static void setGameStarted(boolean status) {
        gameStarted = status;
    }
}
