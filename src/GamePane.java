import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Collections;

// Used to interact with the gameBoard

public class GamePane extends Pane {
    
    static Pane gameBoard;
    private static boolean gameStarted = false; // Boolean to know if game has started


    GamePane() {
    }

    static void setGameStarted(boolean status) {
        gameStarted = status;
    }

    static void begin() {
        gameBoard = new Pane();
        gameBoard.setStyle("-fx-background-color: #008000");
        FoundationPile.getFoundations(GamePane.gameBoard);
        getTableau();
        getButtons();
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
        ArrayList<Integer> rand = new ArrayList<>();
        for(int i = 1; i <= 13; i++)
            rand.add(i);
        Collections.shuffle(rand); // Shuffle the deck

        // Add kings to four random places
        int tableauPileNum;
        for(int i = 0; i < 4; i++) {
            tableauPileNum = rand.get(i) - 1;
            Tableau.tableauPiles.get(tableauPileNum).addCard(Deck.kings.get(i)); // Add king to array of cards in the TableauPile
            gameBoard.getChildren().add(Deck.kings.get(i).getCardImage()); // Add king image to gameBoard. x location set on image request
        }

        // Deal remaining cards
        Collections.shuffle(Deck.deck);
        int cardCt = 0;
        for(int i = 0; i < 13; i++) {
            while(Tableau.tableauPiles.get(i).getPileSize() < 4) { // Deal only four cards
                Tableau.tableauPiles.get(i).addCard(Deck.deck.get(cardCt)); // Add cards to array in TableauPile
                gameBoard.getChildren().add(Deck.deck.get(cardCt).getCardImage()); // Add card image to gameBoard
                cardCt++;
            }
        }
    }

    /** Restart the game with a new deck **/
    static void restart() {
        // Set gameStarted to true
        setGameStarted(true);

        gameBoard.getChildren().clear();
        getTableau();
        getButtons();
        FoundationPile.getFoundations(gameBoard);

        // Clear tableau, foundations, and deck arrays
        for(int i = 0; i < 13; i++) {
            Tableau.tableauPiles.get(i).clearPile();
        }
        Deck.masterDeck.clear();
        // Deal new deck
        Deck.getCards();
        deal();
    }

    static void print() {
        for(int i = 0; i < 13; i++) {
            for(int c = 0; i < Tableau.tableauPiles.get(i).getPileSize(); c++) {
                gameBoard.getChildren().add(Tableau.tableauPiles.get(i).getCardImage(Tableau.tableauPiles.get(i).getCard(c)));
            }
        }
    }

    static void getButtons() {
        // Start button
        Button start = new Button("Start");
        start.setLayoutX(100);
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
        undo.setLayoutX(100); // X and Y locations of button
        undo.setLayoutY(70);
        undo.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        gameBoard.getChildren().add(undo);

        // Redo button
        Button redo = new Button("Redo");
        redo.setLayoutX(100);
        redo.setLayoutY(110);
        redo.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        gameBoard.getChildren().add(redo);

        // Restart button
        Button restart = new Button("Restart");
        restart.setLayoutX(100);
        restart.setLayoutY(150);
        restart.setStyle("-fx-border-color: #006400; -fx-border-width: 4px;");
        gameBoard.getChildren().add(restart);
        restart.setOnAction((e) -> {
            if (gameStarted) { // prevents restart button use if game has not started
                GamePane.restart();
            }
        });
    }
}
