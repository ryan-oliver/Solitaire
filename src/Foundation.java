import java.util.ArrayList;

public class Foundation {

    static ArrayList<FoundationPile> foundationPiles; // Array of the 4 foundation piles
    static FoundationPile selectedPile; // Used to return pile that mouse is over on pane

    Foundation() {
    }

    /** Get selected foundation pile **/
    static FoundationPile getFoundation(double xCord, double yCord) {
        for(int i = 0; i < 4; i++) {
            if(foundationPiles.get(i).contains(xCord, yCord))
                selectedPile = foundationPiles.get(i);
        }
        return selectedPile;
    }
}
