import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Collections;

public class TableauPile {

    private double xCord;
    private double yCord;
    private Rectangle stackField;
    private ArrayList<Card> cardsInPile;


    TableauPile(double xCord, double yCord, int num) {
        // Set coordinates for tableau pile
        this.xCord = xCord;
        this.yCord = yCord;
        // Create rectangle for tableau pile
        stackField = new Rectangle(105, 155);
        stackField.setX(xCord);
        stackField.setY(yCord);
        stackField.setFill(Color.GREEN);
        stackField.setStroke(Color.DARKGREEN);
        stackField.setStrokeWidth(5);
        // Array to hold cards in pile
        cardsInPile = new ArrayList<>();
    }

    public Rectangle getRectangle() {
        return this.stackField;
    }

    public double getXCord() {
        return xCord;
    }

    public double getYCord() {
        return yCord;
    }

    public int getPileSize() {
        return cardsInPile.size();
    }

    public void addCard(Card card) {
        cardsInPile.add(card);
        if (cardsInPile.size() == 1) {
            card.setXCord(xCord + 2.5);
            card.setYCord(yCord + 2.5);
        }
        else if (cardsInPile.size() > 1){
            card.setXCord(xCord + 2.5);
            card.setYCord(yCord + (25 * (cardsInPile.size() - 1)));
        }
    }

    public void removeCard(Card card) {
        cardsInPile.remove(card);
    }

    public void clearPile() {
        cardsInPile.clear();
    }
}
