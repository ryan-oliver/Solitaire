import java.util.ArrayList;

// Class representing the full Tableau section

public class Tableau {

    static ArrayList<TableauPile> tableauPiles; // Array of the 13 tableau piles
    static TableauPile selectedPile;

    Tableau() {
    }

    static TableauPile getTableau(double xCord, double yCord) {
        for(int i = 0; i < 13; i++) {
            if (tableauPiles.get(i).getTopCard().contains(xCord, yCord))
                selectedPile = tableauPiles.get(i);
        }
        return selectedPile;
    }
}
