import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Pile extends Rectangle {

    ArrayList<Card> cardsInPile; // Array of cards in this pile
    static ArrayList<Pile> piles; // Array of the piles including both Foundation and Tableau

    int num; // pile number in game board
    static Dragboard db; // Used to drag card data
    static Card topCard; // Represents top card of pile mouse is currently over
    static Card cardInHand; // Represents card that is being dragged
    static Pile selectedPile; // Represents pile that mouse is currently over
    boolean isFoundationPile; // Identifies foundation piles

    Pile(){
    }

    /** Constructs a piles object **/
    Pile(double xCord, double yCord, int num) {
        cardsInPile = new ArrayList<>();
        this.num = num;
        setWidth(105);
        setHeight(155);
        setX(xCord);
        setY(yCord);
        setStroke(Color.DARKGREEN);
        setStrokeWidth(5);

    }

    /** Sets pile as foundation pile **/
    void setIsFoundationPile(boolean isFoundationPile) {
        this.isFoundationPile = isFoundationPile;
    }

    /** Return physical representation of pile **/
    Rectangle getRectangle() {
        return this;
    }

    /** Return top card in pile **/
    Card getTopCard() {
        if (cardsInPile.size() > 0)
            return cardsInPile.get(cardsInPile.size() - 1);
        else
            return cardInHand;
    }

    /** Get amount of cards in pile **/
    public int getPileSize() {
        return cardsInPile.size();
    }

    /** Add card to pile **/
    void addCard(Card card) {
        cardsInPile.add(card);
        card.setX(getX() + 2.5);
        card.setY(getY() + 2.5);
        card.getCardImage().toFront();
        GamePane.gameBoard.getChildren().add(card.getCardImage());
    }

    /** Remove card from pile **/
    public void removeCard(Card card) {
        cardsInPile.remove(card);
        GamePane.gameBoard.getChildren().remove(card);
    }

    /** Deal cards to  tableau **/
    public void dealCards(Card card) {
        cardsInPile.add(card);
        card.setPileNum(num - 1);
        // Increment location of card to make whole stack visible
        if (cardsInPile.size() == 1) {
            card.setX(getX() + 2.5);
            card.setY(getY() + 2.5);
        }
        else if (cardsInPile.size() > 1){
            card.setX(getX() + 2.5);
            card.setY(getY() + (25 * (cardsInPile.size() - 1)));

        }
    }

    static Pile getPile(double xCord, double yCord) {
        for(int i = 0; i < 17; i++) {
            if (Pile.piles.get(i).getTopCard().contains(xCord, yCord))
                selectedPile = Pile.piles.get(i);
            if (Pile.piles.get(i).contains(xCord, yCord))
                selectedPile = Pile.piles.get(i);
        }
        return selectedPile;
    }

    /** Mouse events that make card draggable **/
    static void makeDraggable(Card card) {
        card.setOnDragDetected(e -> {
            cardInHand = card;
            if (card.isTopCard()) {
                db = card.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(card.getNumber());
                content.putImage(card.getImage());
                db.setContent(content);
                Pile.piles.get(card.getPileNum()).removeCard(card);
                if (Pile.piles.get(card.getPileNum()).getPileSize() > 0)
                    Pile.piles.get(card.getPileNum()).getTopCard().setIsTopCard(true);
                e.consume();
            }
        });

        card.setOnDragOver(e -> {
            topCard = Pile.getPile(e.getSceneX(), e.getSceneY()).getTopCard();
            boolean checkF = topCard.foundationMovable(Integer.valueOf(cardInHand.getNumber()));
            if (checkF) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            boolean checkT = topCard.tableauMovable(Integer.valueOf(cardInHand.getNumber()));
            if (checkT) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });

        card.setOnDragDropped(e -> {
            boolean success = false;
            topCard = Pile.getPile(e.getSceneX(), e.getSceneY()).getTopCard();
            if (topCard.foundationMovable(Integer.valueOf(cardInHand.getNumber()))) {
                cardInHand.setInFoundations(true);
                cardInHand.setInTableau(false);
                Pile.piles.get(topCard.getPileNum()).getTopCard().setIsTopCard(false);
                Pile.piles.get(topCard.getPileNum()).addCard(cardInHand);
                Pile.piles.get(topCard.getPileNum()).getTopCard().setIsTopCard(true);
                success = true;
            }
            if (topCard.tableauMovable(Integer.valueOf(cardInHand.getNumber()))) {
                cardInHand.setInTableau(true);
                cardInHand.setInFoundations(false);
                Pile.piles.get(topCard.getPileNum()).getTopCard().setIsTopCard(false);
                Pile.piles.get(topCard.getPileNum()).addCard(cardInHand);
                Pile.piles.get(topCard.getPileNum()).getTopCard().setIsTopCard(true);
                success = true;
            }
            e.setDropCompleted(success);
            e.consume();
        });

        card.setOnDragDone(e -> {
            if (e.getTransferMode() == TransferMode.MOVE) {
            }
            if (e.getTransferMode() == null) {
                if (Pile.piles.get(card.getPileNum()).getPileSize() > 0)
                    Pile.piles.get(card.getPileNum()).getTopCard().setIsTopCard(false);
                Pile.piles.get(card.getPileNum()).addCard(card);
                Pile.piles.get(card.getPileNum()).getTopCard().setIsTopCard(true);
            }
        });
    }
}
