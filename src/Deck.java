import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;

// Represents each deck of cards.

public class Deck {

    private static ArrayList<Card> masterDeck;
    private static ArrayList<Card> deck; // Deck without kings (48 cards)
    private static ArrayList<Card> kings; // Kings (4 cards)

    Deck() {
        masterDeck = getCards(); // Add all cards to deck
        deck = new ArrayList<>();
        deck = masterDeck;
        kings = new ArrayList<>(); // List for kings
        for (int i = 12; i <= 51; i += 13)
            kings.add(deck.get(i));
        deck.remove(51);
        deck.remove(38);    // Add kings to list and remove from deck
        deck.remove(25);
        deck.remove(12);
        Collections.shuffle(deck);
    }

    public static int getCardNumber(Card card) {
        return card.getNumber();
    }

    /**
     * Return ArrayList of cards
     **/
    static ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Return ArrayList of kings
     **/
    static ArrayList<Card> getKings() {
        return kings;
    }

    /**
     * Return ArrayList of masterDeck
     **/
    static ArrayList<Card> getMaster() {
        return masterDeck;
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
    private ArrayList<Card> getCards() {
        ArrayList<Card> deck = new ArrayList<>();
        int number = 1;
        for (int suit = 1; suit <= 4; suit++) {
            for (int n = 1; n <= 13; n++) {
                deck.add(new Card(number, suit));
                number++;
            }
        }
        return deck;

    }
}
