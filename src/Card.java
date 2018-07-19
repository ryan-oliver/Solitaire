import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

// Represents card object

public class Card extends Rectangle {

    private String number; // number in order from 1 to 52. Used to pull image to card
    private int suit; // Clubs = 1, Diamonds = 2, Hearts = 3, Spades = 4
    private int pileNum; // Current pile number card is in
    private int oldPileNum; // Last pile number card was in
    private Image cardImage; // Image of card
    private boolean inTableau; // Sets card as in tableau
    private boolean inFoundations; // Sets card as in foundations
    private boolean isTopCard; // Sets card as top card in pile to enable drop on card

    /** Constructs individual card **/
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

    /** Return card image **/
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

    /** Check if card can be move to tableau by checking each suit **/
    boolean tableauMovable(int heldCardNum) {
        boolean tableauMovable;
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

    /** Check if card can be added to foundation **/
    boolean foundationMovable(int heldCardNum) {
        boolean foundationsMovable;
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

    /** Check if card can be added to clubs foundation **/
    private boolean checkFClubs(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == heldCardNum)
            return true;
        else if (topCardNum == heldCardNum - 1 && getSuit() == Deck.masterDeck.get(heldCardNum).getSuit())
            return true;
        return false;
    }

    /** Check if card can be added to diamond foundation **/
    private boolean checkFDiamond(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == heldCardNum)
            return true;
        else if (topCardNum == heldCardNum - 1 && getSuit() == Deck.masterDeck.get(heldCardNum).getSuit())
            return true;
        return false;
    }

    /** Check if card can be added to hearts foundation **/
    private boolean checkFHearts(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == heldCardNum)
            return true;
        else if (topCardNum == heldCardNum - 1 && getSuit() == Deck.masterDeck.get(heldCardNum).getSuit())
            return true;
        return false;
    }

    /** Check if card can be added to spades foundation **/
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

    /** Set cards previous pile number **/
    void setOldPileNum(int oldPileNum) {
        this.oldPileNum = oldPileNum;
    }

    /** Get cards previous pile **/
    int getOldPileNum() {
        return oldPileNum;
    }

    /** Get pile number of current card **/
    int getPileNum() {
        return pileNum;
    }

    /** Return card rectangle **/
    Rectangle getCardImage() {
        return this;
    }
}
