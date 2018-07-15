import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;

/** public class CardTest extends Application {

    public void start(Stage primaryStage) {

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(5,5,5,5));
        pane.setHgap(5);
        pane.setVgap(5);

        Deck deck = new Deck();

        int cardOrder = 0;
        for (int r = 0; r <= 3; r++) { // row
            for (int c = 0; c <= 11; c++) { // card
                pane.add((deck.sendImage(deck.getDeck().get(cardOrder))), c, r);
                cardOrder++;
            }
        }

        cardOrder = 0;
        for (int r = 0; r <= 3; r++) {
            pane.add((deck.sendImage(deck.getKings().get(cardOrder))), 12, r);
            cardOrder++;
        }

        Scene scene = new Scene(pane,1370, 625);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Test Deck");
        primaryStage.show();
    }
} **/