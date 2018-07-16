import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

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
        // Physical aspect of pile
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

    /** Deal cards to  tableau **/
    public void dealCards(Card card) {
        cardsInPile.add(card);
        card.setInTableau(true);
        card.setInFoundations(false);
        card.setTableauPileNum(num - 1);
        // Increment location of card to make whole stack visible
        if (cardsInPile.size() == 1) {
            card.setX(xCord + 2.5);
            card.setXCord(card.getX());
            card.setY(yCord + 2.5);
            card.setYCord(card.getY());
        }
        else if (cardsInPile.size() > 1){
            card.setX(xCord + 2.5);
            card.setXCord(card.getX());
            card.setY(yCord + (25 * (cardsInPile.size() - 1)));
            card.setYCord(card.getY());

        }
    }

    /** Add card to tableau pile **/
    void addCard(Card card) {
        if (cardsInPile.get(cardsInPile.size() - 1).getNumber() != card.getNumber()) {
            cardsInPile.add(card);
            card.setTableauPileNum(num - 1);
            card.setInTableau(true);
            card.setInFoundations(false);
            card.setX(xCord + 2.5);
            card.setXCord(card.getX());
            card.setY(yCord + (25 * (cardsInPile.size() - 1)));
            card.setYCord(card.getY());
            card.getCardImage().toFront();
            GamePane.gameBoard.getChildren().add(card);
        }
        else if (cardsInPile.get(cardsInPile.size() - 1).getNumber() == card.getNumber()) {
            card.setX(card.getXCord());
            card.setY(card.getYCord());
        }
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
