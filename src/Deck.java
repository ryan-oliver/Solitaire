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
                MouseEvents.makeDraggable(card);
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
