import javafx.scene.paint.Color;

// Represents each individual pile in the tableau

class TableauPile extends Pile {

    private int pileCt = -1;

    /** Construct Tableau pile with green background and dark green border **/
    TableauPile(double xCord, double yCord, int num) {
        super(xCord, yCord, num);
        setFill(Color.TRANSPARENT);
    }

    /** Add card to this pile **/
    void addCard(Card card) {
        Deck.lastMoved = card;
        cardsInPile.add(card);
        pileCt++;
        card.setOldPileNum(card.getPileNum());
        card.setPileNum(num - 1);
        card.setInTableau(true);
        card.setInFoundations(false);
        card.setX(getX() + 2.5);
        card.setY(getY() + (25 * (cardsInPile.size() - 1)));
        card.getCardImage().toFront();
        GamePane.gameBoard.getChildren().add(card);
    }

    int getPileCt() {
        return pileCt;
    }
}
