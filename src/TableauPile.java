import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Collections;

// Represents each individual pile in tableau

public class TableauPile {

    private double xCord; // x location of pile
    private double yCord; // y location of pile
    private Rectangle stackField; // rectangle to hold area of stack on grid
    private ArrayList<Card> cardsInPile; // array of cards currently in the pile
    private int num;


    TableauPile(double xCord, double yCord, int num) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.num = num;

        stackField = new Rectangle(105, 155);
        stackField.setX(xCord);
        stackField.setY(yCord);
        stackField.setFill(Color.GREEN);
        stackField.setStroke(Color.DARKGREEN);
        stackField.setStrokeWidth(5);
        cardsInPile = new ArrayList<>();
    }

    /** Return physical rectangle of pile**/
    Rectangle getRectangle() {
        return this.stackField;
    }

    Rectangle getCardImage(Card card) {
        return card.getCardImage();
    }

    Card getCard(int index) {
        return cardsInPile.get(index);
    }

    public double getXCord() {
        return xCord;
    }

    public double getYCord() {
        return yCord;
    }

    int getNum() {
        return num;
    }

    /** Get amount of cards in pile**/
    public int getPileSize() {
        return cardsInPile.size();
    }

    /** Add card to pile **/
    public void addCard(Card card) {
        cardsInPile.add(card);
        // Increment location of card to make whole stack visible
        if (cardsInPile.size() == 1) {
            card.setXCord(xCord + 2.5);
            card.setYCord(yCord + 2.5);
        }
        else if (cardsInPile.size() > 1){
            card.setXCord(xCord + 2.5);
            card.setYCord(yCord + (25 * (cardsInPile.size() - 1)));
        }
    }
    /** Remove card from pile **/
    public void removeCard(Card card) {
        cardsInPile.remove(card);
    }

    /** Clear pile for restart **/
    public void clearPile() {
        cardsInPile.clear();
    }

}
