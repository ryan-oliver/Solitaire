import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.util.ArrayList;

// Represents each deck of cards.

public class Deck {

    static ArrayList<Card> masterDeck;
    static Dragboard db;
    static Card topCard;
    static Card cardInHand;

    Deck() {
    }

    /** Create a deck of Cards **/
    static void getCards() {
        // Make masterDeck
        masterDeck = new ArrayList<>();
        int number = 1;
        for (int suit = 1; suit <= 4; suit++) {
            for (int n = 1; n <= 13; n++) {
                Card card = new Card(String.valueOf(number), suit);
                makeDraggable(card);
                masterDeck.add(card);
                if (number == 1 || number == 14 || number == 27 || number == 40)
                    card.setAce(true);
                number++;
            }
        }
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
                Tableau.tableauPiles.get(card.getTableauPileNum()).removeCard(card);
                Tableau.tableauPiles.get(card.getTableauPileNum()).getTopCard().setIsTopCard(true);
                System.out.println(db.getString() + " startDrag");

                e.consume();
            }
        });

        card.setOnDragOver(e -> {
            // In Foundations
            if (e.getSceneY() < 200) {
                if (Foundation.getFoundation(e.getSceneX(), e.getSceneY()).foundationMoveable(cardInHand)) {
                    e.acceptTransferModes(TransferMode.MOVE);
                    System.out.println("In foundatiions");
                }
            }
            // In Tableau
            if (e.getSceneY() > 200) {
                topCard = Tableau.getTableau(e.getSceneX(), e.getSceneY()).getTopCard();
                if (topCard.tableauMovable(Integer.valueOf(cardInHand.getNumber()))) {
                    e.acceptTransferModes(TransferMode.MOVE);
                    System.out.println("In tableau");

                }
            }
        });

        card.setOnDragDropped(e -> {
            // Foundation.foundationPiles.get(cardInHand.getSuit() - 1).addCard(cardInHand);
            boolean success = false;

            if (topCard.tableauMovable(Integer.valueOf(cardInHand.getNumber()))) {
                System.out.println(db.getString() + " (T)dropped");
                System.out.println(db.getString() + " (T)is over " + topCard.getNumber());
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
