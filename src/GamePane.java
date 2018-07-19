import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;

// Used to interact with the gameBoard

public class GamePane extends Pane {

    static Pane gameBoard;
    private static boolean gameStarted = false; // Boolean to know if game has started
    static int num = 1; // Used to make the piles for the game board. Set pile number when created
    static ArrayList<Integer> randCard;
    static ArrayList<Integer> randTab;
    static int moveCounterI = 0;
    static Text moveCounterT;

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
        getText();
    }

    /** Add piles to pane **/
    static void getPiles() {
        Pile.piles = new ArrayList<>();
        for(int i = 0, a = 1, n = -115; i < 4; i++, num++, a += 13) { // a = ace card num
            Pile.piles.add(new FoundationPile(1050, n += 175, num));
            Pile.piles.get(i).setIsFoundationPile(true);
            gameBoard.getChildren().add(Pile.piles.get(i).getRectangle());
            Card card = new Card(String.valueOf(0), i + 1); // Invisible cards that allow ace to transfer to foundations
            card.setFill(Color.TRANSPARENT);
            Pile.makeDraggable(card);
            Pile.piles.get(i).addCard(card);
        }
        num = 5;
        for (int t = 4, n = 0; t < 11; t++, num++) { // loops set x location before adding to gameBoard. n = x location
            Pile.piles.add(new TableauPile(n += 130, 80, num)); // creates and adds TableauPile object to Tableau.tableauPiles array
            gameBoard.getChildren().add(Pile.piles.get(t).getRectangle()); // add pile to the gameBoard
        }

        // second row
        for(int t = 11, n = 67; t <= 16; t++, num++) {
            Pile.piles.add(new TableauPile(n += 130, 440, num));
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
        randTab = new ArrayList<>();
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
            }
            kings = true;
        }
        randCard = new ArrayList<>();
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
        getText();


        // Deal new deck
        deal();
    }

    static void restartGame() {
        gameBoard.getChildren().clear();
        // Clear tableau, foundations, and deck arrays
        Pile.piles.clear();

        num = 1;

        getPiles();
        getButtons();
        getText();

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
            }
            kings = true;
        }
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

    /** Add buttons **/
    static void getButtons() {
        // Start button
        Button start = new Button("Start");
        start.setLayoutX(15);
        start.setLayoutY(80);
        start.setStyle("-fx-border-color: #006400; -fx-border-width: 4px; -fx-font: 12 arial;");
        start.setMinWidth(85);
        gameBoard.getChildren().add(start);
        start.setOnAction((e) -> {
            if (!gameStarted) { // Prevents start button use after game start
                GamePane.deal();
            }
        });

        // Undo button
        Button undo = new Button("Undo/Redo");
        undo.setLayoutX(15); // X and Y locations of button
        undo.setLayoutY(120);
        undo.setStyle("-fx-border-color: #006400; -fx-border-width: 4px; -fx-font: 12 arial;");
        undo.setMinWidth(85);
        gameBoard.getChildren().add(undo);
        undo.setOnAction(e -> {
            if (gameStarted) {
                Pile.piles.get(Deck.lastMoved.getPileNum()).removeCard(Deck.lastMoved);
                Pile.piles.get(Deck.lastMoved.getOldPileNum()).addCard(Deck.lastMoved);
            }
        });


        // New Game button
        Button newGame = new Button("New Game");
        newGame.setLayoutX(15);
        newGame.setLayoutY(160);
        newGame.setStyle("-fx-border-color: #006400; -fx-border-width: 4px; -fx-font: 12 arial;");
        newGame.setMinWidth(85);
        gameBoard.getChildren().add(newGame);
        newGame.setOnAction((e) -> {
            if (gameStarted) { // prevents restart button use if game has not started
                GamePane.newGame();
            }
        });

        // Restart Game button
        Button restartGame = new Button( "Restart");
        restartGame.setLayoutX(15);
        restartGame.setLayoutY(200);
        restartGame.setStyle("-fx-border-color: #006400; -fx-border-width: 4px; -fx-font: 12 arial;");
        restartGame.setMinWidth(85);
        gameBoard.getChildren().add(restartGame);
        restartGame.setOnAction((e) -> {
            if (gameStarted) { // prevents restart button use if game has not started
                GamePane.restartGame();
            }
        });
    }

    /** Add text fields **/
    static void getText() {
        Text topBanner = new Text(150, 55, "BAKERS DOZEN SOLITAIRE");
        topBanner.setFont(Font.loadFont("file:font/font.TTF", 45));
        gameBoard.getChildren().add(topBanner);
    }
}
