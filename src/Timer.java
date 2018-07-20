import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Timer {

    // Data Fields
    int hour;
    int minute;
    int second;
    Text text = new Text();
    Timeline animation;

    // Construct stopwatch
    public Timer() {
        text.setFont(Font.font(15));
        text.setStyle("-fx-fill: #BDB76B;");
        animation = new Timeline(
                new KeyFrame(Duration.millis(1000), e -> run()));
        animation.setCycleCount(Timeline.INDEFINITE);
    }

    // Play animation
    void play() {
        animation.play();
    }

    // Animate stopwatch
    protected void run() {
        if (minute == 59)
            hour = hour + 1;
        if (second == 59)
            minute = minute + 1;
        second = second < 59 ? second + 1 : 0;
        text.setText(getTime());
    }

    // Return time as a string
    private String getTime() {
        return pad(hour) + ":" + pad(minute) + ":" + pad(second);
    }

    // Return padded string
    private String pad(int n) {
        return n < 10 ? "0" + Integer.toString(n) : Integer.toString(n) + "";
    }
}
