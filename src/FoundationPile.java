import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class FoundationPile {

    private double xCord;
    private double yCord;
    private Rectangle stackField;
    private int num;

    FoundationPile(double xCord, double yCord, int num) {
        stackField = new Rectangle(105, 155);
        stackField.setX(xCord);
        stackField.setY(yCord);
        this.xCord = xCord;
        this.yCord = yCord;
        this.num = num;
        stackField.setFill(new ImagePattern(new Image("file:images/suit_image/" + num + "s.png")));
        stackField.setStroke(Color.DARKGREEN);
        stackField.setStrokeWidth(5);
    }

    public Rectangle getRec() {
        return this.stackField;
    }

    public static Pane getFoundations(Pane pane) {
        ArrayList<FoundationPile> foundation = new ArrayList<>();
        for(int i = 0, n = 220, c = 1; i < 4; i++, c++) {
            foundation.add(new FoundationPile(n += 130, 25, c));
            pane.getChildren().add(foundation.get(i).getRec());
        }
        return pane;
    }
}
