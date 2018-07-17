import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

// Represents card object

public class Card extends Rectangle {

    private String number; // number in order from 1 to 52. Used to pull image to card
    private int suit; // Clubs = 1, Diamonds = 2, Hearts = 3, Spades = 4
    private Image cardImage;
    private boolean inTableau;
    private int tableauPileNum;
    private boolean inFoundations;
    private int foundationsPileNum;
    private boolean isTopCard;
    private boolean isAce;

    Card(String number, int suit) {
        this.number = number;
        this.suit = suit;
        setHeight(150);
        setWidth(100);
        if (Integer.valueOf(number) == 0) { // Add invisible cards to allow ace transfer to foundations
            setFill(Color.TRANSPARENT);

        }
        if (Integer.valueOf(number) > 0) {
            setFill(new ImagePattern(new Image("file:images/card_images/" + number + ".png")));
            cardImage = new Image("file:images/card_images/" + number + ".png", 100, 150, true, true);
        }
        setCursor(Cursor.HAND);
        setAce(false);
    }

    Image getImage() {
        return cardImage;
    }

    /** Return number in deck **/
    String getNumber() {
        return this.number;
    }

    /** Set ace card as ace **/
    void setAce(boolean isAce) {
        this.isAce = isAce;
    }

    /** Check if ace **/
    boolean isAce() {
        return isAce;
    }

    /** Set top card so only top card can be dragged **/
    void setIsTopCard(boolean isTop) {
        isTopCard = isTop;
    }

    /** Check if top card **/
    boolean isTopCard() {
        return isTopCard;
    }

    /** Check if card can be move to tableau by checking each suit **/
    boolean tableauMovable(int heldCardNum) {
        if (checkClubs(heldCardNum)) {
            return true;
        }
        else if (checkDiamond(heldCardNum)) {
            return true;
        }
        else if (checkHearts(heldCardNum)) {
            return true;
        }
        else if (checkSpades(heldCardNum)) {
            return true;
        }
        else
            return false;
    }

    /** Check if held card value is one higher then the card under **/
    private boolean checkClubs(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == 0)
            return false;
        if (topCardNum == heldCardNum + 1)
            return true;
        else if (topCardNum == heldCardNum + 14)
            return true;
        else if (topCardNum == heldCardNum + 27)
            return true;
        else if (topCardNum == heldCardNum + 40)
            return true;
        else
            return false;
    }

    /** Check if held card value is one higher then the card under **/
    private boolean checkDiamond(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == 0)
            return false;
        if (topCardNum == heldCardNum - 12)
            return true;
        else if (topCardNum == heldCardNum + 1)
            return true;
        else if (topCardNum == heldCardNum + 14)
            return true;
        else if (topCardNum == heldCardNum + 27)
            return true;
        else
            return false;
    }

    /** Check if held card value is one higher then the card under **/
    private boolean checkHearts(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == 0)
            return false;
        if (topCardNum == heldCardNum - 25)
            return true;
        else if (topCardNum == heldCardNum - 12)
            return true;
        else if (topCardNum == heldCardNum + 1)
            return true;
        else if (topCardNum == heldCardNum + 14)
            return true;
        else
            return false;
    }

    /** Check if held card value is one higher then the card under **/
    private boolean checkSpades(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == 0)
            return false;
        if (topCardNum == heldCardNum - 38)
            return true;
        else if (topCardNum == heldCardNum - 25)
            return true;
        else if (topCardNum == heldCardNum - 12)
            return true;
        else if (topCardNum == heldCardNum + 1)
            return true;
        else
            return false;
    }

    /** Return card suit **/
    public int getSuit() {
        return this.suit;
    }

    /** Mark card as in tableau **/
    void setInTableau(boolean inTableau) {
        this.inTableau = inTableau;
    }

    public boolean isInTableau() {
        return inTableau;
    }

    /** Mark card as in foundations **/
    public void setInFoundations(boolean inFoundations) {
        this.inFoundations = inFoundations;
    }

    public boolean isInFoundations() {
        return inFoundations;
    }

    /** Set tableau that card is in **/
    void setTableauPileNum(int tableauPileNum) {
        this.tableauPileNum = tableauPileNum;
    }

    int getTableauPileNum() {
        return tableauPileNum;
    }

    /** Set foundations that card is in **/
    public void setFoundationsPileNum(int foundationsPileNum) {
        this.foundationsPileNum = foundationsPileNum;
    }

    public int getFoundationsPileNum() {
        return foundationsPileNum;
    }

    /** Return image of card**/
    Rectangle getCardImage() {
        return this;
    }
}
