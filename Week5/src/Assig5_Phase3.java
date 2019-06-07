/*
 * Title:               GUI Cards Phase 3
 * Files:               Assig5.java
 * Semester:            Summer A, 2019
 * Date:
 *
 * Author:              Roger Terrill, George Blombach, Dalia Faria,
 *                      Abby Packham, Carlos Orduna
 * Email:               rchicasterrill@csumb.edu, gblombach@csumb.edu,
 *                      dfaria@csumb.edu, apackham@csumb.edu,
 *                      cordunacorrales@csumb.edu
 * Lecturer's Name:     Jesse Cecil, M.S.
 * TA's Name:           Joseph Appleton
 * Lab Section:         CST 338
 */


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Assig5_Phase3
{
   public static void main(String[] args)
   {
      GameView gameView = new GameView();

      GameModel gameModel = new GameModel();

      GameController gameController = new GameController(gameView, gameModel);
   }
}

/*****************************************************************************
 *                        End of Assig5                                      *
 *****************************************************************************/

class GameView extends JFrame
{
   // Constants
   private int NUM_CARDS_PER_HAND = GameModel.NUM_CARDS_PER_HAND;
   private int NUM_PLAYERS = GameModel.NUM_PLAYERS;

   // CardTable private members
   private int numCardsPerHand;
   private int numPlayers;

   // JLabels private members to are the Cards Displayed
   private JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   private JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
   private JButton[] playerCardButtons = new JButton[NUM_CARDS_PER_HAND];

   // JLabels private members that display text
   private JLabel gameText = new JLabel();
   private JLabel gameStatus = new JLabel();

   //3 panels - One Computer player, One Human player, One play area
   private JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;

   GameView()
   {
      // Sets the title of the blank table
      super("Card Game Table");

      // Container Layout Manager
      setLayout(new BorderLayout());

      // Panels to display both hands and play area
      pnlComputerHand = new JPanel(new GridLayout(1,numCardsPerHand));
      pnlHumanHand = new JPanel(new GridLayout(1,numCardsPerHand));
      pnlPlayArea = new JPanel(new GridLayout(2,3)); //Remember col num is ignored

      // Place panels to their specific location
      add(pnlComputerHand, BorderLayout.NORTH);
      add(pnlPlayArea, BorderLayout.CENTER);
      add(pnlHumanHand, BorderLayout.SOUTH);

      // Add border titles to each section
      pnlComputerHand.setBorder(new TitledBorder("Computer Hand"));
      pnlPlayArea.setBorder(new TitledBorder("Playing Area"));
      pnlHumanHand.setBorder(new TitledBorder("Your Hand"));

      /**
       * Create labels for each card in each panel
       */

      // Load the icons to use
      GUICard.loadCardIcons();

      // Create labels for player and computer
      for( int card = 0; card < NUM_CARDS_PER_HAND; card++)
      {
         // Load into array the cards
         computerLabels[card] = new JLabel(GUICard.getBackcardIcon());
         playerCardButtons[card] = new JButton(GUICard.getBackcardIcon()); // Come back to this after testing
      }

      // After creating labels above, you then add them to the specified panel
      for( int card = 0; card < NUM_CARDS_PER_HAND; card++)
      {
         // Load cards from array into panel
         pnlComputerHand.add(computerLabels[card]);
         pnlHumanHand.add(playerCardButtons[card]);
      }

      // Foundational Methods to Setup Frame
      setSize(800, 600);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Show the table
      setVisible(true);
   }

   public void initGameView()
   {
      // Game Text Display
      gameText.setText("Welcome to High Card!");
      gameText.setForeground(Color.red);

      gameStatus.setText("Click on card to begin.");
      gameStatus.setForeground(Color.red);
   }
}


class GameModel
{
   // Global Constants
   static int NUM_CARDS_PER_HAND = 7;
   static int NUM_PLAYERS = 2;

   // Private members that hold the winning cards
   private Card[] compWinnings = new Card[NUM_PLAYERS * NUM_CARDS_PER_HAND];
   private Card[] playerWinnings = new Card[NUM_PLAYERS * NUM_CARDS_PER_HAND];

   // Member that holds GameCardFramework object and it's arguments
   private CardGameFramework highCardGame;
   private int numPacksPerDeck = 1;
   private int numJokersPerPack = 2;
   private int numUnusedCardsPerPack = 0;
   private Card[] unusedCardsPerPack = null;

   GameModel()
   {
      highCardGame = new CardGameFramework(numPacksPerDeck,numJokersPerPack,numUnusedCardsPerPack,unusedCardsPerPack,NUM_PLAYERS,NUM_CARDS_PER_HAND);
   }

}

class GameController
{
   // Private members for model and view
   private GameView gameView;
   private GameModel gameModel;

   // Default Constructor
   GameController(GameView gameView, GameModel gameModel)
   {
      // Connect passed in objects to local members
      this.gameView = gameView;
      this.gameModel = gameModel;
   }
}


