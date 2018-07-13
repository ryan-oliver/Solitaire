import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Card {

    private int number;
    private int suit; /** Clubs = 1, Diamonds = 2, Hearts = 3, Spades = 4 **/
    private Rectangle card;
    private double xCord;
    private double yCord;

    Card(int number, int suit) {
        this.number = number;
        this.suit = suit;
        card = new Rectangle(100, 150);
        card.setFill(new ImagePattern(new Image("file:images/card_images/" + number + ".png")));
        card.setY(yCord);
        card.setX(xCord);
    }

    public int getNumber() {
        return this.number;
    }

    public int getSuit() {
        return this.suit;
    }

    public void setXCord(double xCord) {
        this.xCord = xCord;
    }

    public double getxCord() {
        return this.xCord;
    }

    public void setYCord(double yCord) {
        this.yCord = yCord;
    }

    public double getyCord() {
        return this.yCord;
    }

    public Rectangle getCardImage() {
        card.setX(xCord);
        card.setY(yCord);
        return this.card;
    }


}
