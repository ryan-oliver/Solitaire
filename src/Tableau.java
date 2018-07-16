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
        for(int i = 0; i < 13; i++) {
            if (tableauPiles.get(i).getRectangle().contains(xCord, yCord))
                return tableauPiles.get(i);
        }
        return null;
    }

    static void addCard(int number, TableauPile pile) {
        tableauPiles.get(pile.getNum() - 1).addCard(Deck.masterDeck.get(number));
        Deck.masterDeck.get(number).setTableauPileNum(pile.getNum());
        Deck.masterDeck.get(number).setInTableau(true);
    }

    static void removeCard(int number, TableauPile pile) {
        tableauPiles.get(pile.getNum()).removeCard(Deck.masterDeck.get(number));
    }
}
