import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

import java.util.ArrayList;

// Represents each deck of cards.

public class Deck {

    static ArrayList<Card> masterDeck;

    Deck() {
    }

    public static String getCardNumber(Card card) {
        return card.getNumber();
    }

    /**
     * Create a deck of Cards
     **/
    static void getCards() {
        // Make masterDeck
        masterDeck = new ArrayList<>();
        int number = 1;
        for (int suit = 1; suit <= 4; suit++) {
            for (int n = 1; n <= 13; n++) {
                Card card = new Card(String.valueOf(number), suit);
                makeDraggable(card);
                masterDeck.add(card);
                number++;
            }
        }
    }

    static Card getCard(String num) {
        return masterDeck.get(Integer.valueOf(num));
    }

    static void makeDraggable(Card card) {
        card.setOnDragDetected(e -> {
            Dragboard db = card.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString(card.getNumber());
            content.putImage(card.getImage());
            db.setContent(content);
            Tableau.tableauPiles.get(card.getTableauPileNum()).removeCard(card);
            e.consume();
        });

        card.setOnDragOver(e -> {

        });
    }
}
