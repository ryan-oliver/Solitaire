import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;

import java.util.ArrayList;

// Represents each deck of cards.

public class Deck {

    static ArrayList<Card> masterDeck;
    static Dragboard db;
    static Card topCard;
    static Card cardInHand;

    Deck() {
    }

    public static String getCardNumber(Card card) {
        return card.getNumber();
    }

    /**
     * Create a deck of Cards
     **/
    static void getCards() {
        // Make masterDeck
        masterDeck = new ArrayList<>();
        int number = 1;
        for (int suit = 1; suit <= 4; suit++) {
            for (int n = 1; n <= 13; n++) {
                Card card = new Card(String.valueOf(number), suit);
                makeDraggable(card);
                masterDeck.add(card);
                number++;
            }
        }
    }

    static Card getCard(String num) {
        return masterDeck.get(Integer.valueOf(num));
    }

    static void makeDraggable(Card card) {
        card.setOnDragDetected(e -> {
            cardInHand = card;
            if (card.isTopCard()) {
                db = card.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(card.getNumber());
                content.putImage(card.getImage());
                db.setContent(content);
                Tableau.tableauPiles.get(card.getTableauPileNum()).removeCard(card);
                Tableau.tableauPiles.get(card.getTableauPileNum()).getTopCard().setIsTopCard(true);
                System.out.println(db.getString() + " startDrag");

                e.consume();
            }
        });

        card.setOnDragOver(e -> {
            topCard = Tableau.getTableau(e.getSceneX(), e.getSceneY()).getTopCard();
            if (topCard.tableauMovable(Integer.valueOf(cardInHand.getNumber()))) {
               e.acceptTransferModes(TransferMode.MOVE);
            }
        });

        card.setOnDragDropped(e -> {
            boolean success = false;
            if (topCard.tableauMovable(Integer.valueOf(cardInHand.getNumber()))) {
                System.out.println(db.getString() + " dropped");
                System.out.println(db.getString() + " is over " + topCard.getNumber());
                Tableau.tableauPiles.get(topCard.getTableauPileNum()).getTopCard().setIsTopCard(false);
                Tableau.tableauPiles.get(topCard.getTableauPileNum()).addCard(cardInHand);
                Tableau.tableauPiles.get(topCard.getTableauPileNum()).getTopCard().setIsTopCard(true);
                success = true;
            }
            e.setDropCompleted(success);
            e.consume();
        });

        card.setOnDragDone(e -> {
            if (e.getTransferMode() == TransferMode.MOVE) {
                System.out.println(db.getString() + " goodDrag");
            }
            if (e.getTransferMode() == null) {
                Tableau.tableauPiles.get(card.getTableauPileNum()).getTopCard().setIsTopCard(false);
                Tableau.tableauPiles.get(card.getTableauPileNum()).addCard(card);
                Tableau.tableauPiles.get(card.getTableauPileNum()).getTopCard().setIsTopCard(true);
                System.out.println(db.getString() + " noDrag");

            }
        });
    }
}
