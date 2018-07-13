import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck;
    private ArrayList<Card> kings;

    Deck() {
        deck = getCards();
        kings = new ArrayList<>();
        for(int i = 12; i <= 51; i += 13)
            kings.add(deck.get(i));
        deck.remove(51);
        deck.remove(38);
        deck.remove(25);
        deck.remove(12);
        Collections.shuffle(deck);
    }

    public static int getCardNumber(Card card) {
        return card.getNumber();
    }

    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    public ArrayList<Card> getKings() {
        return this.kings;
    }

    public Rectangle sendImage(Card card) {
        return card.getCardImage();
    }

    public ArrayList<Card> getCards() {
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
