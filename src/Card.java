import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

// Represents card object

public class Card extends Rectangle {

    private int number; // number in order from 1 to 52. Used to pull image to card
    private int suit; // Clubs = 1, Diamonds = 2, Hearts = 3, Spades = 4
    private double xCord; // x location of card
    private double yCord; // y location of card
    private boolean inTableau;
    private int tableauPileNum;
    private boolean inFoundations;
    private int foundationsPileNum;
    private double translateX, translateY;

    Card(int number, int suit) {
        this.number = number;
        this.suit = suit;
        setHeight(150);
        setWidth(100);
        setFill(new ImagePattern(new Image("file:images/card_images/" + number + ".png")));
        setY(yCord);
        setX(xCord);

        setCursor(Cursor.HAND);
    }

    int getNumber() {
        return this.number;
    }

    public int getSuit() {
        return this.suit;
    }

    void setXCord(double xCord) {
        setX(xCord);
        this.xCord = xCord;
    }

    public double getXCord() {
        return this.xCord;
    }

    void setYCord(double yCord) {
        setY(yCord);
        this.yCord = yCord;
    }

    public double getYCord() {
        return this.yCord;
    }
    
    public void setInTableau(boolean inTableau) {
        this.inTableau = inTableau;
    }

    public boolean isInTableau() {
        return inTableau;
    }

    public void setInFoundations(boolean inFoundations) {
        this.inFoundations = inFoundations;
    }

    public boolean isInFoundations() {
        return inFoundations;
    }

    public void setTableauPileNum(int tableauPileNum) {
        this.tableauPileNum = tableauPileNum;
    }

    public int getTableauPileNum() {
        return tableauPileNum;
    }

    public void setFoundationsPileNum(int foundationsPileNum) {
        this.foundationsPileNum = foundationsPileNum;
    }

    public int getFoundationsPileNum() {
        return foundationsPileNum;
    }

    Rectangle getCardImage() {
        // Set physical coordinates of card each time image is requested
        setX(xCord);
        setY(yCord);
        return this;
    }

    static boolean isCard(double xCord, double yCord) {
        for(int i = 0; i < 52; i++) {
            if (Deck.masterDeck.get(i).contains(xCord, yCord)) {
                return true;
            }
        }
        return false;
    }
}
