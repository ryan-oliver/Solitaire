import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;

public class Tableau {

    private ArrayList<TableauPile> tableauPiles;

    Tableau() {
    }

    public Pane getTableau(Pane pane) {
        tableauPiles = new ArrayList<>();

        int c = 1;
        for (int t = 0, n = 32; t < 7; t++, c++) {
            tableauPiles.add(new TableauPile(n += 130, 210, c));
            pane.getChildren().add(tableauPiles.get(t).getRectangle());
        }
        for(int t = 7, n = 97; t <= 12; t++, c++) {
            tableauPiles.add(new TableauPile(n += 130, 500, c));
            pane.getChildren().add(tableauPiles.get(t).getRectangle());
        }
        return pane;
    }

    public void deal(Pane pane) {
        // Set gameStarted to true
        GameBoard.setGameStarted(true);

        // Make new deck
        Deck deck = new Deck();

        // Make array list for random king placement
        ArrayList<Integer> rand = new ArrayList<>();
        for(int i = 1; i <= 13; i++)
            rand.add(i);
        Collections.shuffle(rand);

        // Add kings to four random places
        ArrayList<Card> kings = deck.getKings();
        int tableauPileNum;
        for(int i = 0; i < 4; i++) {
            tableauPileNum = rand.get(i) - 1;
            tableauPiles.get(tableauPileNum).addCard(kings.get(i));
            pane.getChildren().add(kings.get(i).getCardImage());
        }

        // Deal remaining cards
        ArrayList<Card> restOfDeck = deck.getDeck();
        int cardCt = 0;
        for(int i = 0; i < 13; i++) {
            while(tableauPiles.get(i).getPileSize() < 4) {
                tableauPiles.get(i).addCard(restOfDeck.get(cardCt));
                pane.getChildren().add(restOfDeck.get(cardCt).getCardImage());
                cardCt++;
            }
        }
    }

    public void restart(Pane pane) {
        // Set gameStarted to true
        GameBoard.setGameStarted(true);

        // Clear tableau
        for(int i = 0; i < 13; i++) {
            tableauPiles.get(i).clearPile();
        }

        // Make new deck
        Deck deck = new Deck();

        // Make array list for random king placement
        ArrayList<Integer> rand = new ArrayList<>();
        for(int i = 1; i <= 13; i++)
            rand.add(i);
        Collections.shuffle(rand);

        // Add kings to four random places
        ArrayList<Card> kings = deck.getKings();
        int tableauPileNum;
        for(int i = 0; i < 4; i++) {
            tableauPileNum = rand.get(i) - 1;
            tableauPiles.get(tableauPileNum).addCard(kings.get(i));
            pane.getChildren().add(kings.get(i).getCardImage());
        }

        // Deal remaining cards
        ArrayList<Card> restOfDeck = deck.getDeck();
        int cardCt = 0;
        for(int i = 0; i < 13; i++) {
            while(tableauPiles.get(i).getPileSize() < 4) {
                tableauPiles.get(i).addCard(restOfDeck.get(cardCt));
                pane.getChildren().add(restOfDeck.get(cardCt).getCardImage());
                cardCt++;
            }
        }
    }
}
