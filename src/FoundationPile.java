import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

// Represents individual foundation piles

class FoundationPile extends Rectangle{

    private double xCord; // x location on grid
    private double yCord; // y location on grid
    private int num; // Number to represent card suit
    private ArrayList<Card> cardsInPile;

    /** Creates a location for a foundation pile**/
    FoundationPile(double xCord, double yCord, int num) {
        setWidth(105);
        setHeight(155);
        setX(xCord);
        setY(yCord);
        this.xCord = xCord;
        this.yCord = yCord;
        this.num = num;
        setFill(new ImagePattern(new Image("file:images/suit_image/" + num + "s.png"))); // Adds image representing suit
        setStroke(Color.DARKGREEN); // Makes a border for the pile
        setStrokeWidth(5);
    }

    Rectangle getRectangle() {
        return this;
    }

    Card getTopCard() {
        return cardsInPile.get(cardsInPile.size() - 1);
    }

    /** Get amount of cards in pile**/
    public int getPileSize() {
        return cardsInPile.size();
    }

    void addCard(Card card) {

    }

    /** Remove card from pile **/
    public void removeCard(Card card) {
        cardsInPile.remove(card);
        GamePane.gameBoard.getChildren().remove(card);
    }

    /** Clear pile for restart **/
    public void clearPile() {
        cardsInPile.clear();
    }
}
