import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Timer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Create a text to display
        text = new Text("00:00:00");

        EventHandler<ActionEvent> eventHandler = event -> {
            count(text);
        };

        //Create a timeline for countting
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), eventHandler));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        timeline.play();

        //Vbox to add the text in
        VBox vBox = new VBox(30);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text);

        //Create a scene
        Scene scene = new Scene(vBox, 200, 150);
        primaryStage.setTitle("TimerTest");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    int hours = 0;
    int minutes = 0;
    int seconds = 0;

    Text text = new Text();

    public void count(Text text) {
        if (seconds == 60) {
            minutes++;
            seconds = 0;
        }
        if (minutes == 60) {
            hours++;
            minutes = 0;
        }
        if (hours == 24) {
            hours = 0;
        }
        text.setText((((hours / 10) == 0) ? "0" : "") + hours + ":"
                + (((minutes / 10) == 0) ? "0" : "") + minutes + ":"
                + (((seconds / 10) == 0) ? "0" : (((seconds / 100) == 0) ? "0" : "")) + seconds++);
    }
}