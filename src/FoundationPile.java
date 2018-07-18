import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

// Represents individual foundation piles

class FoundationPile extends Pile {

    private int suit; // Number to represent foundation suit

    /** Creates a location for a foundation pile**/
    FoundationPile(double xCord, double yCord, int num) {
        super(xCord, yCord, num);
        this.suit = num;
        setFill(new ImagePattern(new Image("file:images/suit_image/" + num + "s.png"))); // Adds image representing suit
    }

    /** Add card to pile **/
    void addCard(Card card) {
        cardsInPile.add(card);
        card.setInTableau(false);
        card.setInFoundations(true);
        card.setX(getX() + 2.5);
        card.setY(getY() + 2.5);
        card.getCardImage().toFront();
        GamePane.gameBoard.getChildren().add(card.getCardImage());
    }
}
