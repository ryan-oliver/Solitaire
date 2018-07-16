import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class MouseEvents {

    final DragContext dragContext = new DragContext();

    public void makeDraggable(final Card card) {
        card.setOnMousePressed(onMousePressedEventHandler);
        card.setOnMouseDragged(onMouseDraggedEventHandler);
        card.setOnMouseReleased(onMouseReleasedEventHandler);
    }

    EventHandler<MouseEvent> onMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent e) {
                    Node node = (Card) e.getSource();
                    node.toFront();
                    dragContext.x = e.getSceneX();
                    dragContext.y = e.getSceneY();
                }
            };

    EventHandler<MouseEvent> onMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent e) {
                    Node node = (Card) e.getSource();
                    node.toFront();
                    node.setTranslateX(e.getSceneX() - dragContext.x);
                    node.setTranslateY(e.getSceneY() - dragContext.y);
                }
            };

    EventHandler<MouseEvent> onMouseReleasedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent e) {
                    if (Card.isCard(e.getSceneX(), e.getSceneY())) {
                        fixPosition(Deck.getCard(e.getSceneX(), e.getSceneY()));
                    }
                }
            };

    private void fixPosition(Card card) {
        // Get card, save old tab pile, get new tab pile, get x y top card new pile, set new card x y, add/remove card
        int pileNum = card.getTableauPileNum();
        Tableau.tableauPiles.get(pileNum).addCard(card);
    }


    class DragContext {
        double x;
        double y;
    }
}
