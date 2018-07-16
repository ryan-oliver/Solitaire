import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

// Represents each deck of cards.

public class Deck {

    static ArrayList<Card> masterDeck;

    Deck() {
    }

    public static int getCardNumber(Card card) {
        return card.getNumber();
    }

    /**
     * Return an image of the card
     **/
    Rectangle sendImage(Card card) {
        return card.getCardImage();
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
                Card card = new Card(number, suit);
                MouseEvents me = new MouseEvents();
                me.makeDraggable(card);
                masterDeck.add(card);
                number++;
            }
        }
    }

    static Card getCard(double xCord, double yCord) {
        for(int i = 0; i < 52; i++) {
            if (Deck.masterDeck.get(i).contains(xCord, yCord)) {
                return Deck.masterDeck.get(i);
            }
        }
        return null;
    }
}
