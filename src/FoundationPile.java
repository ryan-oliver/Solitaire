import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

// Represents individual foundation piles

class FoundationPile extends Rectangle{

    private double xCord; // x location on grid
    private double yCord; // y location on grid
    private int suit; // Number to represent foundation suit
    private ArrayList<Card> cardsInPile; // array of cards in pile
    static FoundationPile selectedPile;

    /** Creates a location for a foundation pile**/
    FoundationPile(double xCord, double yCord, int num) {
        setWidth(105);
        setHeight(155);
        setX(xCord); // Set x location on pane
        setY(yCord);
        this.xCord = xCord;
        this.yCord = yCord;
        this.suit = num;
        setFill(new ImagePattern(new Image("file:images/suit_image/" + num + "s.png"))); // Adds image representing suit
        setStroke(Color.DARKGREEN); // Makes a border for the pile
        setStrokeWidth(5);
        cardsInPile = new ArrayList<>();
    }

    /** Return foundation pile image **/
    Rectangle getRectangle() {
        return this;
    }

    int getSuit() {
        return suit;
    }

    /** Return top card in pile **/
    Card getTopCard() {
        return cardsInPile.get(cardsInPile.size() - 1);
    }

    ArrayList<Card> getCardsInPile() {
        return cardsInPile;
    }

    /** Get amount of cards in pile**/
    public int getPileSize() {
        return cardsInPile.size();
    }

    /** Add card to pile **/
    void addCard(Card card) {
        cardsInPile.add(card);
        card.setFoundationsPileNum(suit - 1);
        card.setInTableau(false);
        card.setInFoundations(true);
        card.setX(xCord + 2.5);
        card.setY(yCord + 2.5);
        card.getCardImage().toFront();
        GamePane.gameBoard.getChildren().add(card.getCardImage());
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

    /** Check if card can be added to foundation **/
    boolean checkSuit(Card card) {
        if (card.getSuit() == suit)
            return true;
        return false;
    }

    static FoundationPile getFPile(double xCord, double yCord) {
        for (int i = 0; i < 4; i++){
            if (Foundation.foundationPiles.get(i).contains(xCord, yCord)) {
                selectedPile = Foundation.foundationPiles.get(i);
            }
        }
        return selectedPile;
    }
}
