import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

// Represents card object

public class Card {

    private int number; // number in order from 1 to 52. Used to pull image to card
    private int suit; // Clubs = 1, Diamonds = 2, Hearts = 3, Spades = 4
    private Rectangle card; // Physical representation of card
    private double xCord; // x location of card
    private double yCord; // y location of card

    Card(int number, int suit) {
        this.number = number;
        this.suit = suit;
        card = new Rectangle(100, 150);
        card.setFill(new ImagePattern(new Image("file:images/card_images/" + number + ".png")));
        card.setY(yCord);
        card.setX(xCord);
    }


    int getNumber() {
        return this.number;
    }

    public int getSuit() {
        return this.suit;
    }

    void setXCord(double xCord) {
        this.xCord = xCord;
    }

    public double getxCord() {
        return this.xCord;
    }

    void setYCord(double yCord) {
        this.yCord = yCord;
    }

    public double getyCord() {
        return this.yCord;
    }

    Rectangle getCardImage() {
        // Set physical coordinates of card each time image is requested
        card.setX(xCord);
        card.setY(yCord);
        return this.card;
    }

}
