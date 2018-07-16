import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

// Represents card object

public class Card extends Rectangle {

    private String number; // number in order from 1 to 52. Used to pull image to card
    private int suit; // Clubs = 1, Diamonds = 2, Hearts = 3, Spades = 4
    private boolean isCard; // Boolean status for mouseEvent
    private double xCord; // x location of card
    private double yCord; // y location of card
    private Image cardImage;
    private boolean inTableau;
    private int tableauPileNum;
    private boolean inFoundations;
    private int foundationsPileNum;

    Card(String number, int suit) {
        this.number = number;
        this.suit = suit;
        isCard = true;
        setHeight(150);
        setWidth(100);
        setFill(new ImagePattern(new Image("file:images/card_images/" + number + ".png")));
        cardImage = new Image("file:images/card_images/" + number + ".png", 100, 150, true, true);
        setY(yCord);
        setX(xCord);

        setCursor(Cursor.HAND);
    }

    Image getImage() {
        return cardImage;
    }

    String getNumber() {
        return this.number;
    }

    public int getSuit() {
        return this.suit;
    }

    void setXCord(double xCord) {
        this.xCord = xCord;
    }

    public double getXCord() {
        return this.xCord;
    }

    void setYCord(double yCord) {
        this.yCord = yCord;
    }

    public double getYCord() {
        return this.yCord;
    }
    
    void setInTableau(boolean inTableau) {
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

    void setTableauPileNum(int tableauPileNum) {
        this.tableauPileNum = tableauPileNum;
    }

    int getTableauPileNum() {
        return tableauPileNum;
    }

    public void setFoundationsPileNum(int foundationsPileNum) {
        this.foundationsPileNum = foundationsPileNum;
    }

    public int getFoundationsPileNum() {
        return foundationsPileNum;
    }

    Rectangle getCardImage() {
        return this;
    }

    static boolean isCard(Card card) {
        return card.isCard;
    }
}
