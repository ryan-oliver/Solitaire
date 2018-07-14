import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

// Represents individual foundation piles

class FoundationPile {

    private double xCord; // x location on grid
    private double yCord; // y location on grid
    private Rectangle stackLocation; // rectangle to hold area of this stack on grid
    private int num; // Number to represent card suit

    /** Creates a location for a foundation pile**/
    FoundationPile(double xCord, double yCord, int num) {
        stackLocation = new Rectangle(105, 155);
        stackLocation.setX(xCord);
        stackLocation.setY(yCord);
        this.xCord = xCord;
        this.yCord = yCord;
        this.num = num;
        stackLocation.setFill(new ImagePattern(new Image("file:images/suit_image/" + num + "s.png"))); // Adds image representing suit
        stackLocation.setStroke(Color.DARKGREEN); // Makes a border for the pile
        stackLocation.setStrokeWidth(5);
    }

    private Rectangle getRec() {
        return this.stackLocation;
    }

    /** Adds foundations to the pane**/
    static Pane getFoundations(Pane pane) {
        ArrayList<FoundationPile> foundation = new ArrayList<>();
        for(int i = 0, n = 220, c = 1; i < 4; i++, c++) {
            foundation.add(new FoundationPile(n += 130, 25, c));
            pane.getChildren().add(foundation.get(i).getRec());
        }
        return pane;
    }
}
