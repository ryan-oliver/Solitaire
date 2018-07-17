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
        if (Integer.valueOf(number) == 0) {
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

    // Return number in deck
    String getNumber() {
        return this.number;
    }

    void setAce(boolean isAce) {
        this.isAce = isAce;
    }

    boolean isAce() {
        return isAce;
    }

    void setIsTopCard(boolean isTop) {
        isTopCard = isTop;
    }

    boolean isTopCard() {
        return isTopCard;
    }

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

    private boolean checkClubs(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == heldCardNum + 1) {
            return true;
        }
        else if (topCardNum == heldCardNum + 14) {
            return true;
        }
        else if (topCardNum == heldCardNum + 27) {
            return true;
        }
        else if (topCardNum == heldCardNum + 38) {
            return true;
        }
        else
            return false;
    }

    private boolean checkDiamond(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == heldCardNum - 14) {
            return true;
        }
        else if (topCardNum == heldCardNum + 1) {
            return true;
        }
        else if (topCardNum == heldCardNum + 14) {
            return true;
        }
        else if (topCardNum == heldCardNum + 25) {
            return true;
        }
        else
            return false;
    }

    private boolean checkHearts(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == heldCardNum - 25) {
            return true;
        }
        else if (topCardNum == heldCardNum - 12) {
            return true;
        }
        else if (topCardNum == heldCardNum + 1) {
            return true;
        }
        else if (topCardNum == heldCardNum + 12) {
            return true;
        }
        else
            return false;
    }

    private boolean checkSpades(int heldCardNum) {
        int topCardNum = Integer.valueOf(getNumber());
        if (topCardNum == heldCardNum - 38) {
            return true;
        }
        else if (topCardNum == heldCardNum - 25) {
            return true;
        }
        else if (topCardNum == heldCardNum - 12) {
            return true;
        }
        else if (topCardNum == heldCardNum - 1) {
            return true;
        }
        else
            return false;
    }

    public int getSuit() {
        return this.suit;
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
}
