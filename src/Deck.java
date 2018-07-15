import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

// Represents each deck of cards.

public class Deck {

    static ArrayList<Card> masterDeck;
    static ArrayList<Card> deck; // Deck without kings (48 cards)
    static ArrayList<Card> kings; // Kings (4 cards)

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
                masterDeck.add(new Card(number, suit));
                number++;
            }
        }
        // Extract kings
        deck = new ArrayList<>();
        deck = masterDeck;
        kings = new ArrayList<>(); // List for kings
        for (int i = 12; i <= 51; i += 13)
            kings.add(deck.get(i));
        deck.remove(51);
        deck.remove(38);    // Add kings to list and remove from deck
        deck.remove(25);
        deck.remove(12);
    }
}
