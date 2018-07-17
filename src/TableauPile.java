import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

// Represents each individual pile in tableau

public class TableauPile extends Rectangle {

    private double xCord; // x location of pile
    private double yCord; // y location of pile
    private ArrayList<Card> cardsInPile; // array of cards currently in the pile
    private int num;


    TableauPile(double xCord, double yCord, int num) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.num = num;
        // Physical aspect of pile
        setWidth(105);
        setHeight(155);
        setX(xCord);
        setY(yCord);
        setFill(Color.GREEN);
        setStroke(Color.DARKGREEN);
        setStrokeWidth(5);
        cardsInPile = new ArrayList<>(); // Holds cards in this pile
    }

    /** Return physical rectangle of pile**/
    Rectangle getRectangle() {
        return this;
    }

    /** Return top card in tableau pile **/
    Card getTopCard() {
        return cardsInPile.get(cardsInPile.size() - 1);
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

    /** Deal cards to  tableau **/
    public void dealCards(Card card) {
        cardsInPile.add(card);
        card.setInTableau(true);
        card.setInFoundations(false);
        card.setTableauPileNum(num - 1);
        // Increment location of card to make whole stack visible
        if (cardsInPile.size() == 1) {
            card.setX(xCord + 2.5);
            card.setY(yCord + 2.5);
        }
        else if (cardsInPile.size() > 1){
            card.setX(xCord + 2.5);
            card.setY(yCord + (25 * (cardsInPile.size() - 1)));

        }
    }

    /** Add card to tableau pile **/
    void addCard(Card card) {
        cardsInPile.add(card);
        card.setTableauPileNum(num - 1);
        card.setInTableau(true);
        card.setInFoundations(false);
        card.setX(xCord + 2.5);
        card.setY(yCord + (25 * (cardsInPile.size() - 1)));
        card.getCardImage().toFront();
        GamePane.gameBoard.getChildren().add(card);
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
