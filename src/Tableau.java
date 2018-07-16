import java.util.ArrayList;

// Class representing the full Tableau section

public class Tableau {

    static ArrayList<TableauPile> tableauPiles; // Array of the four tableau piles

    Tableau() {
    }

    public static boolean isTableau(double xCord, double yCord) {
        for (TableauPile tableau: tableauPiles)
            if (tableau.getRectangle().contains(xCord, yCord))
                return true;
        return false;
    }

    public static TableauPile getTableau(double xCord, double yCord) {
        // Change this method to get tableau number from card coordinates.
        // Card can then be dropped on stack instead of tableau
        for(int i = 0; i < 13; i++) {
            if (tableauPiles.get(i).getRectangle().contains(xCord, yCord))
                return tableauPiles.get(i);
        }
        return null;
    }
}
