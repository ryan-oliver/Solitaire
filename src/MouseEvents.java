import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class MouseEvents {

    static void makeDraggable(Card card) {
        card.setOnDragDetected(e -> {
            Dragboard db = card.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString(card.getNumber());
            content.putImage(card.getImage());
            db.setContent(content);
            Tableau.tableauPiles.get(card.getTableauPileNum()).removeCard(card);
            e.consume();
        });
    }

}