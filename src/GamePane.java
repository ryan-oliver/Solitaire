import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;

// Used to interact with the gameBoard

public class GamePane extends Pane {
    
    static Pane gameBoard;
    private static boolean gameStarted = false; // Boolean to know if game has started
    static int num = 1; // Used to make the piles for the game board. Set pile number when created

    GamePane() {
    }

    /** Set game as started so buttons can be enabled/disabled**/
    static void setGameStarted(boolean status) {
        gameStarted = status;
    }

    /** Begin game **/
    static void begin() {
        gameBoard = new Pane();
        gameBoard.setStyle("-fx-background-color: #008000");
        getPiles();
        getButtons();
    }

    /** Add piles to pane **/
    static void getPiles() {
        Pile.piles = new ArrayList<>();
        for(int i = 0, a = 1, n = 220; i < 4; i++, num++, a += 13) {
            Pile.piles.add(new FoundationPile(n += 130, 25, num));
            Pile.piles.get(i).setIsFoundationPile(true);
            gameBoard.getChildren().add(Pile.piles.get(i).getRectangle());
            Card card = new Card(String.valueOf(a), i + 1);
            card.setFill(Color.TRANSPARENT);
            Pile.makeDraggable(card);
            Pile.piles.get(i).addCard(card);
        }
        num = 5;
        for (int t = 4, n = 32; t < 11; t++, num++) { // loops set x location before adding to gameBoard. n = x location
            Pile.piles.add(new TableauPile(n += 130, 210, num)); // creates and adds TableauPile object to Tableau.tableauPiles array
            gameBoard.getChildren().add(Pile.piles.get(t).getRectangle()); // add pile to the gameBoard
        }

        // second row
        for(int t = 11, n = 97; t <= 16; t++, num++) {
            Pile.piles.add(new TableauPile(n += 130, 500, num));
            gameBoard.getChildren().add(Pile.piles.get(t).getRectangle());
        }
    }

    /** Create and deal deck of cards **/
    static void deal() {
        // Set gameStarted to true
        setGameStarted(true);

        // Create deck
        Deck.getCards();

        // Make array list for random numbers
        ArrayList<Integer> randTab = new ArrayList<>();
        for(int i = 4; i <= 16; i++)
            randTab.add(i);
        Collections.shuffle(randTab); // Shuffle the deck

        // Deal cards kings first
        int cardCount = 0;
        int tableauRandomPileNum = 0;
        boolean kings = false;
        while(!kings) {
            for (int k = 12; k <= 51; k += 13) {
                int tableauPileNumForKing = randTab.get(tableauRandomPileNum++);
                Pile.piles.get(tableauPileNumForKing).dealCards(Deck.masterDeck.get(k));
                gameBoard.getChildren().add(Deck.masterDeck.get(k).getCardImage()); // Add card image to gameBoard
                Deck.masterDeck.get(k).setInTableau(true);
                Deck.masterDeck.get(k).setAce(true);
            }
            kings = true;
        }
        ArrayList<Integer> randCard = new ArrayList<>();
        for(int i = 0; i < 51; i++)
            randCard.add(i);
        Collections.shuffle(randCard);
        for(int t = 4; t < 17; t++) {
                while (Pile.piles.get(t).getPileSize() < 4) {
                    if (randCard.get(cardCount) != 12 && randCard.get(cardCount) != 25 && randCard.get(cardCount) != 38 && randCard.get(cardCount) != 51) {
                        Pile.piles.get(t).dealCards(Deck.masterDeck.get(randCard.get(cardCount)));
                        gameBoard.getChildren().add(Deck.masterDeck.get(randCard.get(cardCount)).getCardImage()); // Add card image to gameBoard
                        Deck.masterDeck.get(cardCount).setInTableau(true);
                    }
                    cardCount++;
                }
                Pile.piles.get(t).getTopCard().setIsTopCard(true);
        }

    }

    /** Restart the game with a new deck **/
    static void newGame() {
        gameBoard.getChildren().clear();
        // Clear tableau, foundations, and deck arrays
        Pile.piles.clear();
        Deck.masterDeck.clear();
        num = 1;

        getPiles();
        getButtons();

        // Deal new deck
        Deck.getCards();
        deal();
    }

    /** Add buttons **/
    static void getButtons() {
        // Start button
        Button start = new Button("Start");
        start.setLayoutX(50);
        start.setLayoutY(30);
        start.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        gameBoard.getChildren().add(start);
        start.setOnAction((e) -> {
            if (!gameStarted) { // Prevents start button use after game start
                GamePane.deal();
            }
        });

        // Undo button
        Button undo = new Button("Undo/Redo");
        undo.setLayoutX(50); // X and Y locations of button
        undo.setLayoutY(70);
        undo.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        gameBoard.getChildren().add(undo);
        undo.setOnAction(e -> {
            if (gameStarted) {
                Pile.piles.get(Deck.lastMoved.getPileNum()).removeCard(Deck.lastMoved);
                Pile.piles.get(Deck.lastMoved.getOldPileNum()).addCard(Deck.lastMoved);
            }
        });


        // New Game button
        Button newGame = new Button("New Game");
        newGame.setLayoutX(50);
        newGame.setLayoutY(110);
        newGame.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        gameBoard.getChildren().add(newGame);
        newGame.setOnAction((e) -> {
            if (gameStarted) { // prevents restart button use if game has not started
                GamePane.newGame();
            }
        });
    }
}
