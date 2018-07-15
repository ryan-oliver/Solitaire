import javafx.scene.layout.Pane;
import java.util.ArrayList;

// Class representing the full Tableau section

public class Tableau {

    static ArrayList<TableauPile> tableauPiles; // Array of the four tableau piles

    Tableau() {
    }

    static void print(Pane pane) {
        for(int i = 0; i < 13; i++) {
            for(int c = 0; i < tableauPiles.get(i).getPileSize(); c++) {
                pane.getChildren().add(tableauPiles.get(i).getCardImage(tableauPiles.get(i).getCard(c)));
            }
        }
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
        tableauPiles.get(pile.getNum()).addCard(Deck.masterDeck.get(number));
    }
}
