import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

// Represents individual foundation piles

class FoundationPile extends Pile {

    private int suit; // Number to represent foundation suit

    /** Construct a Foundation pile with dark green border and suit image **/
    FoundationPile(double xCord, double yCord, int num) {
        super(xCord, yCord, num);
        this.suit = num;
        setFill(new ImagePattern(new Image("file:images/suit_image/" + num + "s.png"))); // Adds image representing suit
    }

    /** Add card to this pile **/
    void addCard(Card card) {
        Deck.lastMoved = card;
        cardsInPile.add(card);
        card.setOldPileNum(card.getPileNum());
        card.setPileNum(suit - 1);
        card.setInTableau(false);
        card.setInFoundations(true);
        card.setX(getX() + 2.5);
        card.setY(getY() + 2.5);
        card.getCardImage().toFront();
        GamePane.gameBoard.getChildren().add(card.getCardImage());
    }
}
