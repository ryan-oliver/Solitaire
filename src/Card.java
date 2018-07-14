import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

// Represents card object

public class Card {

    private int number; // number in order from 1 to 52. Used to pull image to card
    private int suit; // Clubs = 1, Diamonds = 2, Hearts = 3, Spades = 4
    private Rectangle card; // Physical representation of card
    private double xCord; // x location of card
    private double yCord; // y location of card
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;

    Card(int number, int suit) {
        this.number = number;
        this.suit = suit;
        card = new Rectangle(100, 150);
        card.setFill(new ImagePattern(new Image("file:images/card_images/" + number + ".png")));
        card.setY(yCord);
        card.setX(xCord);

        card.setCursor(Cursor.HAND);
        card.setOnMousePressed(circleOnMousePressedEventHandler);
        card.setOnMouseDragged(circleOnMouseDraggedEventHandler);
        // card.setOnMouseReleased(circleOnMouseReleasedEventHandler);
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

    EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent e) {
                    card.toFront();
                    orgSceneX = e.getSceneX();
                    orgSceneY = e.getSceneY();
                    orgTranslateX = ((Rectangle)(e.getSource())).getTranslateX();
                    orgTranslateY = ((Rectangle)(e.getSource())).getTranslateY();
                }
            };

    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent e) {
                    card.toFront();
                    double offsetX = e.getSceneX() - orgSceneX;
                    double offsetY = e.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((Rectangle)(e.getSource())).setTranslateX(newTranslateX);
                    ((Rectangle)(e.getSource())).setTranslateY(newTranslateY);
                }
            };

    EventHandler<MouseEvent> circleOnMouseReleasedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent e) {
                    if (Tableau.isTableau(e.getSceneX(), e.getSceneY())) {
                        Tableau.addCard(number, Tableau.getTableau(e.getSceneX(), e.getSceneY()));
                        GameBoard.pane.getChildren().clear();
                        Tableau.getTableau(GameBoard.pane);
                        FoundationPile.getFoundations(GameBoard.pane);
                        GameBoard.getButtons(GameBoard.pane);
                        Tableau.print(GameBoard.pane);
                    }
                }
            };

}
