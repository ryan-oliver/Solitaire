import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.util.ArrayList;

// Represents each deck of cards.

public class Deck {

    static ArrayList<Card> masterDeck;
    static Dragboard db;
    static Card topTCard;
    static Card topFCard;
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
                if (card.isInFoundations()) {

                }
                if (card.isInTableau()) {
                    Tableau.tableauPiles.get(card.getTableauPileNum()).removeCard(card);
                    Tableau.tableauPiles.get(card.getTableauPileNum()).getTopCard().setIsTopCard(true);
                    System.out.println(db.getString() + " startDrag");
                }

                e.consume();
            }
        });

        card.setOnDragOver(e -> {
                    // In Tableau
                    topTCard = Tableau.getTableau(e.getSceneX(), e.getSceneY()).getTopCard();
                    boolean checkT = topTCard.tableauMovable(Integer.valueOf(cardInHand.getNumber()));
                    if (checkT) {
                        e.acceptTransferModes(TransferMode.MOVE);
                        System.out.println("In tableau");

                    }
                });

        card.setOnDragDropped(e -> {
            boolean success = false;
            if (topTCard.tableauMovable(Integer.valueOf(cardInHand.getNumber()))) {
                cardInHand.setInTableau(true);
                System.out.println(db.getString() + " (T)dropped");
                System.out.println(db.getString() + " (T)is over " + topTCard.getNumber());
                Tableau.tableauPiles.get(topTCard.getTableauPileNum()).getTopCard().setIsTopCard(false);
                Tableau.tableauPiles.get(topTCard.getTableauPileNum()).addCard(cardInHand);
                Tableau.tableauPiles.get(topTCard.getTableauPileNum()).getTopCard().setIsTopCard(true);
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
