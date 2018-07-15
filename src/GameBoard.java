import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Main class. Game runs from here. Represents physical game board.

public class GameBoard extends Application {

    public void start(Stage primaryStage) {

        GamePane.begin();

        // Set scene and show stage
        Scene scene = new Scene(GamePane.gameBoard, 1200, 800);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bakers Dozen");
        primaryStage.show();
    }



}
