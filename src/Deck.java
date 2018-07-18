import java.util.ArrayList;

// Represents each deck of cards.

public class Deck {

    static ArrayList<Card> masterDeck;

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
                Pile.makeDraggable(card);
                masterDeck.add(card);
                number++;
            }
        }
    }
}
