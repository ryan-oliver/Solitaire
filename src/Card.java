import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

// Represents card object

public class Card extends Rectangle {

    private String number; // number in order from 1 to 52. Used to pull image to card
    private int suit; // Clubs = 1, Diamonds = 2, Hearts = 3, Spades = 4
    int pileNum;
    int oldPileNum;
    private Image cardImage;
    private boolean inTableau;
    boolean tableauMovable;
    boolean foundationsMovable;
    private boolean inFoundations;
    private boolean isTopCard;
    private boolean isAce;

    Card(String number, int suit) {
        this.number = number;
        this.suit = suit;
        setHeight(150);
        setWidth(100);
        if (Integer.valueOf(number) > 0) {
            setFill(new ImagePattern(new Image("file:images/card_images/" + number + ".png")));
            cardImage = new Image("file:images/card_images/" + number + ".png", 100, 150, true, true);
        }
        setCursor(Cursor.HAND);
    }

    Image getImage() {
        return cardImage;
    }

    /** Return number in deck **/
    String getNumber() {
        return this.number;
    }

    /** Set top card so only top card can be dragged **/
    void setIsTopCard(boolean isTop) {
        isTopCard = isTop;
    }

    /** Check if top card **/
    boolean isTopCard() {
        return isTopCard;
    }

    void setAce(boolean isAce) {
        this.isAce = isAce;
    }

    /** Check if card can be move to tableau by checking each suit **/
    boolean tableauMovable(int heldCardNum) {
        if (checkTClubs(heldCardNum)) {
            tableauMovable =  true;
        }
        else if (checkTDiamond(heldCardNum)) {
            tableauMovable =  true;
        }
        else if (checkTHearts(heldCardNum)) {
            tableauMovable =  true;
        }
        else if (checkTSpades(heldCardNum)) {
            tableauMovable =  true;
        }
        else
            tableauMovable =  false;
        return tableauMovable;
    }

    /** Check if held card value is one higher than the card under **/
    private boolean checkTClubs(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == 0)
            return false;
        else if (topCardNum == heldCardNum + 1)
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

    /** Check if held card value is one higher than the card under **/
    private boolean checkTDiamond(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == 0)
            return false;
        else if (topCardNum == heldCardNum - 12)
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

    /** Check if held card value is one higher than the card under **/
    private boolean checkTHearts(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == 0)
            return false;
        else if (topCardNum == heldCardNum - 25)
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

    /** Check if held card value is one higher than the card under **/
    private boolean checkTSpades(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == 0)
            return false;
        else if (topCardNum == heldCardNum - 38)
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

    /** (In Progress) Check if card can be added to foundation **/
    boolean foundationMovable(int heldCardNum) {
        if (checkFClubs(heldCardNum))
            foundationsMovable = true;
        else if (checkFDiamond(heldCardNum))
            foundationsMovable = true;
        else if (checkFHearts(heldCardNum))
            foundationsMovable = true;
        else if (checkFSpades(heldCardNum))
            foundationsMovable = true;
        else
            foundationsMovable = false;
        return foundationsMovable;
    }

    private boolean checkFClubs(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == heldCardNum)
            return true;
        else if (topCardNum == heldCardNum - 1 && getSuit() == Deck.masterDeck.get(heldCardNum).getSuit())
            return true;
        return false;
    }

    private boolean checkFDiamond(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == heldCardNum)
            return true;
        else if (topCardNum == heldCardNum - 1 && getSuit() == Deck.masterDeck.get(heldCardNum).getSuit())
            return true;
        return false;
    }

    private boolean checkFHearts(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == heldCardNum)
            return true;
        else if (topCardNum == heldCardNum - 1 && getSuit() == Deck.masterDeck.get(heldCardNum).getSuit())
            return true;
        return false;
    }

    private boolean checkFSpades(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == heldCardNum)
            return true;
        else if (topCardNum == heldCardNum - 1 && getSuit() == Deck.masterDeck.get(heldCardNum).getSuit())
            return true;
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

    /** Mark card as in foundations **/
    public void setInFoundations(boolean inFoundations) {
        this.inFoundations = inFoundations;
    }

    /** Set tableau that card is in **/
    void setPileNum(int pileNum) {
        this.pileNum = pileNum;
    }

    void setOldPileNum(int oldPileNum) {
        this.oldPileNum = oldPileNum;
    }

    int getOldPileNum() {
        return oldPileNum;
    }

    int getPileNum() {
        return pileNum;
    }

    /** Return image of card**/
    Rectangle getCardImage() {
        return this;
    }
}
