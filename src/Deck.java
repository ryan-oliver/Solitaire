import java.util.ArrayList;

// Represents each deck of cards.

public class Deck {

    static ArrayList<Card> masterDeck; // Deck of cards
    static Card lastMoved; // Used to track last moved card for undo/redo button

    Deck() {
    }

    /** Create a deck of Cards **/
    static void getCards() {
        // Make masterDeck
        masterDeck = new ArrayList<>();
        int number = 1;
        for (int suit = 1; suit <= 4; suit++) {
            for (int n = 1; n <= 13; n++) {
                Card card = new Card(String.valueOf(number), suit);
                if (n == 1) {
                    card.setAce(true);
                }
                Pile.makeDraggable(card);
                masterDeck.add(card);
                number++;
            }
        }
    }
}
