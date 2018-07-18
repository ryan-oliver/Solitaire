import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// Represents each individual pile in tableau

public class TableauPile extends Pile {

    TableauPile(double xCord, double yCord, int num) {
        super(xCord, yCord, num);
        setFill(Color.GREEN);
        setStroke(Color.DARKGREEN);
    }

    /** Add card to tableau pile **/
    void addCard(Card card) {
        cardsInPile.add(card);
        card.setPileNum(num - 1);
        card.setInTableau(true);
        card.setInFoundations(false);
        card.setX(getX() + 2.5);
        card.setY(getY() + (25 * (cardsInPile.size() - 1)));
        card.getCardImage().toFront();
        GamePane.gameBoard.getChildren().add(card);
    }
}
