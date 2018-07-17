import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Collections;

// Used to interact with the gameBoard

public class GamePane extends Pane {
    
    static Pane gameBoard;
    private static boolean gameStarted = false; // Boolean to know if game has started
    static double xCord; // Test to separate tableau from foundations


    GamePane() {
    }

    static void setxCord() {
        gameBoard.setOnMouseDragged(e -> {
            xCord = e.getX();
        });
    }

    static void setGameStarted(boolean status) {
        gameStarted = status;
    }

    static void begin() {
        gameBoard = new Pane();
        gameBoard.setStyle("-fx-background-color: #008000");
        getFoundation();
        getTableau();
        getButtons();
    }

    /** Adds foundations to the pane **/
    static void getFoundation() {
        Foundation.foundationPiles = new ArrayList<>();
        for(int i = 0, n = 220, c = 1; i < 4; i++, c++) {
            Foundation.foundationPiles.add(new FoundationPile(n += 130, 25, c));
            Foundation.foundationPiles.get(i).addCard(new Card(String.valueOf(0), i));
            gameBoard.getChildren().add(Foundation.foundationPiles.get(i).getRectangle());
        }
    }

    /** Add tableau piles to the gameBoard **/
    static void getTableau() {
        Tableau.tableauPiles = new ArrayList<>();
        int c = 1; // number identifier for each pile

        // first row
        for (int t = 0, n = 32; t < 7; t++, c++) { // loops set x location before adding to gameBoard. n = x location
            Tableau.tableauPiles.add(new TableauPile(n += 130, 210, c)); // creates and adds TableauPile object to Tableau.tableauPiles array
            gameBoard.getChildren().add(Tableau.tableauPiles.get(t).getRectangle()); // add pile to the gameBoard
        }

        // second row
        for(int t = 7, n = 97; t <= 12; t++, c++) {
            Tableau.tableauPiles.add(new TableauPile(n += 130, 500, c));
            gameBoard.getChildren().add(Tableau.tableauPiles.get(t).getRectangle());
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
        for(int i = 0; i <= 12; i++)
            randTab.add(i);
        Collections.shuffle(randTab); // Shuffle the deck

        // Deal cards kings first
        int cardCount = 0;
        int tableauRandomPileNum = 0;
        boolean kings = false;
        while(!kings) {
            for (int k = 12; k <= 51; k += 13) {
                int tableauPileNumForKing = randTab.get(tableauRandomPileNum++);
                Tableau.tableauPiles.get(tableauPileNumForKing).dealCards(Deck.masterDeck.get(k));
                gameBoard.getChildren().add(Deck.masterDeck.get(k).getCardImage()); // Add card image to gameBoard
                Deck.masterDeck.get(k).setInTableau(true);
            }
            kings = true;
        }
        ArrayList<Integer> randCard = new ArrayList<>();
        for(int i = 0; i < 51; i++)
            randCard.add(i);
        Collections.shuffle(randCard);
        for(int t = 0; t < 13; t++) {
                while (Tableau.tableauPiles.get(t).getPileSize() < 4) {
                    if (randCard.get(cardCount) != 12 && randCard.get(cardCount) != 25 && randCard.get(cardCount) != 38 && randCard.get(cardCount) != 51) {
                        Tableau.tableauPiles.get(t).dealCards(Deck.masterDeck.get(randCard.get(cardCount)));
                        gameBoard.getChildren().add(Deck.masterDeck.get(randCard.get(cardCount)).getCardImage()); // Add card image to gameBoard
                        Deck.masterDeck.get(cardCount).setInTableau(true);
                    }
                    cardCount++;
                }
                Tableau.tableauPiles.get(t).getTopCard().setIsTopCard(true);
        }

    }

    /** Restart the game with a new deck **/
    static void newGame() {
        // Set gameStarted to true
        setGameStarted(true);

        gameBoard.getChildren().clear();
        getFoundation();
        getTableau();
        getButtons();

        // Clear tableau, foundations, and deck arrays
        for(int i = 0; i < 13; i++)
            Tableau.tableauPiles.get(i).clearPile();
        for(int i = 0; i < 4; i++)
            Foundation.foundationPiles.get(i).clearPile();
        Deck.masterDeck.clear();
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
        Button undo = new Button("Undo");
        undo.setLayoutX(50); // X and Y locations of button
        undo.setLayoutY(70);
        undo.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        gameBoard.getChildren().add(undo);

        // Redo button
        Button redo = new Button("Redo");
        redo.setLayoutX(50);
        redo.setLayoutY(110);
        redo.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        gameBoard.getChildren().add(redo);

        // Restart button
        Button newGame = new Button("New Game");
        newGame.setLayoutX(50);
        newGame.setLayoutY(150);
        newGame.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        gameBoard.getChildren().add(newGame);
        newGame.setOnAction((e) -> {
            if (gameStarted) { // prevents restart button use if game has not started
                GamePane.newGame();
            }
        });
    }
}
