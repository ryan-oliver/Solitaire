import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

// Represents individual foundation piles

class FoundationPile extends Rectangle{

    private double xCord; // x location on grid
    private double yCord; // y location on grid
    private int suit; // Number to represent card suit
    private ArrayList<Card> cardsInPile;

    /** Creates a location for a foundation pile**/
    FoundationPile(double xCord, double yCord, int num) {
        setWidth(105);
        setHeight(155);
        setX(xCord);
        setY(yCord);
        this.xCord = xCord;
        this.yCord = yCord;
        this.suit = num;
        setFill(new ImagePattern(new Image("file:images/suit_image/" + num + "s.png"))); // Adds image representing suit
        setStroke(Color.DARKGREEN); // Makes a border for the pile
        setStrokeWidth(5);
        cardsInPile = new ArrayList<>();
    }

    Rectangle getRectangle() {
        return this;
    }

    int getFNumber() {
        return suit;
    }
    Card getTopCard() {
        return cardsInPile.get(cardsInPile.size() - 1);
    }

    /** Get amount of cards in pile**/
    public int getPileSize() {
        return cardsInPile.size();
    }

    void addCard(Card card) {
        cardsInPile.add(card);
        card.setFoundationsPileNum(suit - 1);
        card.setInTableau(false);
        card.setInFoundations(true);
        card.setX(xCord + 2.5);
        card.setY(yCord + 2.5);
    }

    /** Remove card from pile **/
    public void removeCard(Card card) {
        cardsInPile.remove(card);
        GamePane.gameBoard.getChildren().remove(card);
    }

    /** Clear pile for restart **/
    void clearPile() {
        cardsInPile.clear();
    }

    boolean foundationMoveable(Card card) {
        if (checkSuit(card)) {
            if (card.isAce())
                return true;
            else if (card.getNumber() == cardsInPile.get(cardsInPile.size() - 1).getNumber() + 1)
                return true;
        }
        return false;
    }

    boolean checkSuit(Card card) {
        if (card.getSuit() == suit)
            return true;
        return false;
    }
}
