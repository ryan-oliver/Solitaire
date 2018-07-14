import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;

// Class representing the full Tableau section

public class Tableau {

    private static ArrayList<TableauPile> tableauPiles; // Array of the four tableau piles

    Tableau() {
        tableauPiles = new ArrayList<>();
    }

    static ArrayList<TableauPile> getTableauPiles() {
        return tableauPiles;
    }

    /** Add tableau piles to the pane **/
    static Pane getTableau(Pane pane) {
        int c = 1; // number identifier for each pile

        // first row
        for (int t = 0, n = 32; t < 7; t++, c++) { // loops set x location before adding to pane. n = x location
            tableauPiles.add(new TableauPile(n += 130, 210, c)); // creates and adds TableauPile object to tableauPiles array
            pane.getChildren().add(tableauPiles.get(t).getRectangle()); // add pile to the pane
        }

        // second row
        for(int t = 7, n = 97; t <= 12; t++, c++) {
            tableauPiles.add(new TableauPile(n += 130, 500, c));
            pane.getChildren().add(tableauPiles.get(t).getRectangle());
        }
        return pane;
    }

    /** Create and deal deck of cards **/
    void deal(Pane pane) {
        // Set gameStarted to true
        GameBoard.setGameStarted(true);

        // Make new deck
        Deck deck = new Deck();

        // Make array list for random numbers
        ArrayList<Integer> rand = new ArrayList<>();
        for(int i = 1; i <= 13; i++)
            rand.add(i);
        Collections.shuffle(rand); // Shuffle the deck

        // Add kings to four random places
        ArrayList<Card> kings = deck.getKings();
        int tableauPileNum;
        for(int i = 0; i < 4; i++) {
            tableauPileNum = rand.get(i) - 1;
            tableauPiles.get(tableauPileNum).addCard(kings.get(i)); // Add king to array of cards in the TableauPile
            pane.getChildren().add(kings.get(i).getCardImage()); // Add king image to pane. x location set on image request
        }

        // Deal remaining cards
        ArrayList<Card> restOfDeck = deck.getDeck();
        int cardCt = 0;
        for(int i = 0; i < 13; i++) {
            while(tableauPiles.get(i).getPileSize() < 4) { // Deal only four cards
                tableauPiles.get(i).addCard(restOfDeck.get(cardCt)); // Add cards to array in TableauPile
                pane.getChildren().add(restOfDeck.get(cardCt).getCardImage()); // Add card image to pane
                cardCt++;
            }
        }
    }

    static void print(Pane pane) {
        for(int i = 0; i < 13; i++) {
            for(int c = 0; i < tableauPiles.get(i).getPileSize(); c++) {
                pane.getChildren().add(tableauPiles.get(i).getCardImage(tableauPiles.get(i).getCard(c)));
            }
        }
    }

    /** Restart the game with a new deck **/
    public void restart(Pane pane) {
        // Set gameStarted to true
        GameBoard.setGameStarted(true);

        pane.getChildren().clear();
        getTableau(pane);
        FoundationPile.getFoundations(pane);

        // Clear tableau, foundations, and deck arrays
        for(int i = 0; i < 13; i++) {
            tableauPiles.get(i).clearPile();
        }
        Deck.getDeck().clear();
        Deck.getKings().clear();
        // Deal new deck
        deal(pane);
    }

    public static boolean isTableau(double xCord, double yCord) {
        for (TableauPile tableau: tableauPiles) {
            if (tableau.getRectangle().contains(xCord, yCord))
                return true;
        }
        return false;
    }

    public static TableauPile getTableau(double xCord, double yCord) {
        for (TableauPile tableau: tableauPiles) {
            if (tableau.getRectangle().contains(xCord, yCord))
                return tableau;
        }
        return null;
    }

    static void addCard(int number, TableauPile pile) {
        tableauPiles.get(pile.getNum()).addCard(Deck.getMaster().get(number));
    }
}
