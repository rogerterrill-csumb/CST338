/**
 * Title:               Timed High Card Game Phase 3
 * Files:               Assig6_Phase3.java
 * Semester:            Summer A, 2019
 * Date:                June 11, 2019
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


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class Assig6_Phase3
{
   public static void main(String[] args)
   {
      GameView gameView = new GameView();

      GameModel gameModel = new GameModel();

      GameController gameController = new GameController(gameView, gameModel);
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

      // Initialize game
      gameControllerInit();
   }

   /**
    * Initializes the game
    */
   public void gameControllerInit()
   {
      // Connect the model's highCardGame object to gameView class
      gameView.setHighCardGame(gameModel.getHighCardGame());

      // Gets computer card from Model and sends it to View
      gameView.setComputerCard(gameModel.getComputerCard());

      // Set player card from model and sends it to view
      gameView.setPlayerCard(gameModel.getPlayerCard());

      // Update the view to show the cards IMPORTANT to wait to init until after
      // the highCardGame is set above
      gameView.initTable();

      // Adds the card listener to the cards in GameView
      addCardListener();

      // Adds the timer listener to the timer button in GameView
      addTimerListener();

      // Adds the listener to the cannot play button
      addNoPlayListener();
   }

   // Method to add action listener
   public void addCardListener()
   {
      // Adds action listener to each button
      for( int card = 0; card < GameModel.NUM_CARDS_PER_HAND; card++)
      {
         gameView.getPlayerCardButtons()[card].addActionListener(new CardListener(card));
      }
   }

   public void addTimerListener()
   {
      gameView.getStartButton().addActionListener(new TimerThread());
   }

   // Added the cannot play action listener to the no play button
   public void addNoPlayListener()
   {
      gameView.getCannotPlayButton().addActionListener(new noPlayListener());
   }

   public class noPlayListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         Card computerCard;
         // Changes whose turn it is
         gameModel.setPlayersTurn(false);

         // Increment the player cannot play count
         gameModel.incrementPlayerScore();

         while(!gameModel.isPlayersTurn() && gameModel.getHighCardGame()
                 .getNumCardsRemainingInDeck() > 0)
         {
            for(int card = 0; card < gameModel.NUM_CARDS_PER_HAND; card++)
            {
               // Set computer card to the value of index of computer hand
               computerCard = gameModel.getHighCardGame().getHand(0)
                       .inspectCard(card);

//               System.out.println(gameModel.getHighCardGame().getHand(0).toString());

               System.out.println("Pile card is " + Card.valueOfCard(gameModel.
                       getComputerCard()) + " Comp card is " +
                       Card.valueOfCard(computerCard));
               System.out.println(gameModel.computerCardCheck(computerCard));

               // Check to see if that card is within 1 of pile card
               if(gameModel.computerCardCheck(computerCard))
               {
                  // Play the card in computer hand at index found
                  gameModel.getHighCardGame().playCard(0,card);

                  // Take a card from the deck in the highCardGame object
                  gameModel.getHighCardGame().takeCard(0);

                  // Set the playerCard to the card clicked
                  gameModel.setComputerCard(computerCard);

                  // Updates the hand in view to show the new hand
                  gameView.updateAllHandsTable();

                  // Set the top card in pile to clicked card
                  gameView.setComputerPlayedCardLabel(computerCard);

                  // Reset the for loop to check from beginning
                  card = 0;
               }
            }

            // Add cannot play counter +1
            gameModel.incrementComputerScore();

            // Deal two new cards into the pile
            gameModel.dealCardsToPile();

            // Gets computer card from Model and sends it to View
            gameView.setComputerCard(gameModel.getComputerCard());

            // Set player card from model and sends it to view
            gameView.setPlayerCard(gameModel.getPlayerCard());

            // Puts the turn back to the player
            gameModel.setPlayersTurn(true);

            //DEBUG
            debugCurrentStatus();

            // Update the scores
            gameView.setGameStatus(gameModel.getGameStatusWithScores());

            // If there are no components left, the game is over
            if(gameModel.getHighCardGame().getNumCardsRemainingInDeck() == 0)
            {
               System.out.println("GAME OVER");

               gameView.setGameStatus(gameModel.displayFinalScore());
            }
         }

      }
   }

   /**
    * Action listener for button logic
    */
   public class CardListener implements ActionListener
   {
      // Private members
      private int cardIndex;
      private Card playerCard;

      CardListener(int cardIndex)
      {
         this.cardIndex = cardIndex;
      }

      public void actionPerformed(ActionEvent e)
      {

         // DEBUG
         System.out.println(gameModel.getHighCardGame().getHand(1).toString());

         // Assigns the card clicked to the player's card
         playerCard = gameModel.getHighCardGame().getHand(1)
                 .inspectCard(cardIndex);

         // Checks to see if card is within 1, is the players turn, and cards remain in the deck
         if(gameModel.playerCardCheck(playerCard) && gameModel.isPlayersTurn()
                 && gameModel.getHighCardGame().getNumCardsRemainingInDeck() > 0)
         {
            // Play the card in player hand at index clicked
            gameModel.getHighCardGame().playCard(1,cardIndex);

            // Take a card from the deck in the highCardGame object
            gameModel.getHighCardGame().takeCard(1);

            System.out.println(gameModel.playerCardCheck(playerCard));

            //DEBUG
            System.out.println(gameModel.getHighCardGame().getHand(1).toString());

            // Set the playerCard to the card clicked
            gameModel.setPlayerCard(playerCard);

            // Updates the hand in view to show the new hand
            gameView.updateAllHandsTable();

            // Set the top card in pile to clicked card
            gameView.setPlayerPlayedCardLabel(playerCard);
         }
         // Checks card against the other pile
         else if(gameModel.computerCardCheck(playerCard) && gameModel.isPlayersTurn()
                 && gameModel.getHighCardGame().getNumCardsRemainingInDeck() > 0)
         {
            // Play the card in player hand at index clicked
            gameModel.getHighCardGame().playCard(1,cardIndex);

            // Take a card from the deck in the highCardGame object
            gameModel.getHighCardGame().takeCard(1);

            //DEBUG
            System.out.println(gameModel.playerCardCheck(playerCard));
            System.out.println(gameModel.getHighCardGame().getHand(1).toString());

            // Set the playerCard to the card clicked
            gameModel.setComputerCard(playerCard);

            // Updates the hand in view to show the new hand
            gameView.updateAllHandsTable();

            // Set the top card in pile to clicked card
            gameView.setComputerPlayedCardLabel(playerCard);
         }



         System.out.println(gameModel.getHighCardGame().getNumCardsRemainingInDeck());

         gameView.setGameStatus(gameModel.getGameStatusWithScores());

         //DEBUG
         debugCurrentStatus();

         // If there are no components left, the game is over
         if(gameModel.getHighCardGame().getNumCardsRemainingInDeck() == 0)
         {
            System.out.println("GAME OVER");

            gameView.setGameStatus(gameModel.displayFinalScore());
         }
      }
   }

   /**
    * Timer Class
    */
   public class TimerThread implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         // Checks to see the status of the game which is defaulted to false
         if(!gameModel.getTimeStatus())
         {
            // Create Timer Object
            Timer timerThread = new Timer();

            // Start the timer
            timerThread.start();

            // Set timer boolean value to true.
            gameModel.setTimeStatus(true);

            // Changes the display of the button to stop
            gameView.setStartButtonText("Stop");
         }
         else
         {
            // Sets the timer boolean to false
            gameModel.setTimeStatus(false);

            // Changes the display of the button to Start
            gameView.setStartButtonText("Start");
         }
      }

      // Timer with thread to run timer
      private class Timer extends Thread
      {
         public void run()
         {
            while(gameModel.getTimeStatus())
            {
               // Increment the seconds
               gameModel.incrementSecondsonTimer();

               // Wait one second in between
               gameModel.doNothing(gameModel.PAUSE);

               // Update the display
               gameView.setTimerDisplay(gameModel.getSeconds());
            }
         }
      }

   }

   private void debugCurrentStatus()
   {
      System.out.println("Players Hand");
      System.out.println(gameModel.getHighCardGame().getHand(1).toString());
      System.out.println("Computers Hand");
      System.out.println(gameModel.getHighCardGame().getHand(0).toString());
      System.out.println(gameModel.getComputerCard());
      System.out.println(gameModel.getPlayerCard());
      System.out.println("Cards left in game: " + gameModel.getHighCardGame()
              .getNumCardsRemainingInDeck());
      System.out.println("Is it players turn: " + gameModel.isPlayersTurn());
   }
}

class GameModel
{
   // Global Constants
   static int NUM_CARDS_PER_HAND = 7;
   static int NUM_PLAYERS = 2;
   static int PAUSE = 1000;

   // Private members that hold the winning cards
   private Card[] compWinnings = new Card[NUM_PLAYERS * NUM_CARDS_PER_HAND];
   private Card[] playerWinnings = new Card[NUM_PLAYERS * NUM_CARDS_PER_HAND];

   // Status if players turn
   private boolean playersTurn = true;

   // Status of players scores
   private int computerScore = 0;
   private int playerScore = 0;

   // Status of cards won in each hand
   private int computerCardsWon = 0;
   private int playerCardsWon = 0;

   // Status of the text depending on win or loss
   private String playerStatus = "";

   // Computer card index counter
   private int computerCardCounter = 0;

   // Seconds of timer
   private int seconds;

   // Current card values
   private Card playerCard;
   private Card computerCard;

   // Member that holds GameCardFramework object and it's arguments
   private CardGameFramework highCardGame;
   private int numPacksPerDeck = 1;
   private int numJokersPerPack = 2;
   private int numUnusedCardsPerPack = 0;
   private Card[] unusedCardsPerPack = null;

   // Timer status
   private Boolean timeStatus = false;

   GameModel()
   {
      // Initialize a new CardGameFramework
      highCardGame = new CardGameFramework(numPacksPerDeck,numJokersPerPack,
              numUnusedCardsPerPack,unusedCardsPerPack,NUM_PLAYERS,NUM_CARDS_PER_HAND);

      // Deal the cards from CardGameFramework
      highCardGame.deal();

      // Set the first initial computer card to display
      computerCard = highCardGame.getCardFromDeck();

      // Sets the first initial player card
      playerCard = highCardGame.getCardFromDeck();
   }

   // Deal two cards onto the pile
   public void dealCardsToPile()
   {
      // Set the first initial computer card to display
      computerCard = highCardGame.getCardFromDeck();

      // Sets the first initial player card
      playerCard = highCardGame.getCardFromDeck();
   }

   // Compare player card value to the board card value
   public boolean playerCardCheck(Card card)
   {
      int diff = Card.valueOfCard(playerCard) - Card.valueOfCard(card);
      // If player wins
      if( diff == 1 || diff == -1)
      {
         return true;
      }
      return false;
   }

   // Compare player card value to the board card value
   public boolean computerCardCheck(Card card)
   {
      int diff = Card.valueOfCard(computerCard) - Card.valueOfCard(card);
      // If player wins
      if( diff == 1 || diff == -1)
      {
         return true;
      }
      return false;
   }

   // Getters and Setters
   public CardGameFramework getHighCardGame()
   {
      return highCardGame;
   }

   public void setPlayerCard(Card playerCard)
   {
      this.playerCard = playerCard;
   }


   public void setComputerCard(Card computerCard)
   {
      this.computerCard = computerCard;
   }

   public Card getComputerCard()
   {
      return computerCard;
   }

   public Card getPlayerCard()
   {
      return playerCard;
   }

   // Updates the computerCard with the new computerCardCounter
   public void updateComputerCard()
   {
      this.computerCard = highCardGame.getHand(0).inspectCard(computerCardCounter);
   }

   // DEBUG: Prints the string of both cards
   public void printCards()
   {
      System.out.println("Your Card " + playerCard.toString() +
              " and the Computer Card " + computerCard.toString());
   }

   // Increments seconds
   public void incrementSecondsonTimer()
   {
      seconds++;
   }

   // Pause for a second the thread
   public void doNothing(int milliseconds)
   {
      try
      {
         Thread.sleep(milliseconds);
      }
      catch (InterruptedException e)
      {
         System.out.println("Unexpected interrupt");
         System.exit(0);
      }
   }

   // Gets the status of the timer boolean
   public Boolean getTimeStatus()
   {
      return timeStatus;
   }

   // Sets the status of the timer boolean
   public void setTimeStatus(Boolean timeStatus)
   {
      this.timeStatus = timeStatus;
   }

   // Gets the current seconds
   public int getSeconds()
   {
      return seconds;
   }

   // Computer and Player Score Getters and Setters
   public static int getNumCardsPerHand()
   {
      return NUM_CARDS_PER_HAND;
   }

   public static void setNumCardsPerHand(int numCardsPerHand)
   {
      NUM_CARDS_PER_HAND = numCardsPerHand;
   }

   public int getComputerScore()
   {
      return computerScore;
   }

   public void setComputerScore(int computerScore)
   {
      this.computerScore = computerScore;
   }

   public int getPlayerScore()
   {
      return playerScore;
   }

   public void setPlayerScore(int playerScore)
   {
      this.playerScore = playerScore;
   }

   public String getGameStatusWithScores()
   {
      return Integer.toString(playerScore) + " - " +
              Integer.toString(computerScore) + " \n" + playerStatus;
   }

   public String displayFinalScore()
   {
      if(playerScore > computerScore)
      {
         return Integer.toString(playerScore) + " - " +
                 Integer.toString(computerScore) + " \n YOU LOST!";
      }
      else if(playerScore < computerScore)
      {
         return Integer.toString(playerScore) + " - " +
                 Integer.toString(computerScore) + " \n YOU WON!.";
      }
      else
      {
         return Integer.toString(playerScore) + " - " +
                 Integer.toString(computerScore) + " \n DRAW!";
      }

   }

   public String getPlayerStatus()
   {
      return playerStatus;
   }

   public void setPlayerStatus(String playerStatus)
   {
      this.playerStatus = playerStatus;
   }

   public void incrementComputerScore()
   {
      computerScore++;
   }

   public void incrementPlayerScore()
   {
      playerScore++;
   }

   public void takeCardFromDeckforPlayer()
   {
      playerCard = highCardGame.playCard(0, 1);
   }

   public boolean isPlayersTurn()
   {
      return playersTurn;
   }

   public void setPlayersTurn(boolean playersTurn)
   {
      this.playersTurn = playersTurn;
   }

   /**
    * DEBUGGING METHODS
    */
   // DEBUG: Displays the int card values of both cards
   public void displayValueComparison()
   {
      System.out.println("Player card value " + Card.valueOfCard(playerCard));
      System.out.println("Computer card value " + Card.valueOfCard(computerCard));
   }

   // DEBUG: Displays the full player winnings
   public void displayPlayerWinnings()
   {
      for(int card = 0; card < playerCardsWon; card++)
      {
         System.out.println(playerWinnings[card].toString());
      }
   }

   // DEBUG: Displays the full computer winnings
   public void displayComputerWinnings()
   {
      for(int card = 0; card < computerCardsWon; card++)
      {
         System.out.println(compWinnings[card].toString());
      }
   }

   // DEBUG: Displays the hand of either computer or player
   public void displayHand(int hand)
   {
      System.out.println(highCardGame.getHand(hand).toString());
   }
}

class GameView extends JFrame
{
   // Constants
   private int NUM_CARDS_PER_HAND = GameModel.NUM_CARDS_PER_HAND;
   private int NUM_PLAYERS = GameModel.NUM_PLAYERS;

   // CardTable private members
   private int numCardsPerHand;
   private int numPlayers;

   // Cards to be displayed
   private Card computerCard;
   private Card playerCard;


   // JLabels private members to are the Cards Displayed
   private JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   private JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
   private JButton[] playerCardButtons = new JButton[NUM_CARDS_PER_HAND];
   private JButton cannotPlayButton = new JButton("I Cannot Play");

   // JLabels private members that display text
   private JLabel gameText = new JLabel();
   private JLabel gameStatus = new JLabel();
   private JLabel playerCardLabel, computerCardLabel;

   // Timer Display Components
   private JLabel timerDisplay = new JLabel("0:00", SwingConstants.CENTER);
   private JButton startButton = new JButton("Start");

   // 4 panels - One Computer player, One Human player, One play area, Timer Area
   private JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea, pnlTimerDisplay;

   // CardGameFramework object to pass into from model through controller
   private CardGameFramework highCardGame;

   GameView()
   {
      // Sets the title of the blank table
      super("Card Game Table");

      // Foundational Methods to Setup Frame
      setSize(800, 700);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Show the table
      setVisible(true);
   }

   public void initTable()
   {
      // Container Layout Manager
      setLayout(new BorderLayout());

      // Panels to display both hands and play area
      pnlComputerHand = new JPanel(new GridLayout(1,numCardsPerHand)); //Remember col num is ignored if it goes over adds new column
      pnlHumanHand = new JPanel(new GridLayout(1,numCardsPerHand)); //Remember col num is ignored
      pnlPlayArea = new JPanel(new GridLayout(3,3)); //Remember col num is ignored
      pnlTimerDisplay = new JPanel(new GridLayout(2,1)); // Timer display with Button

      // Place panels to their specific location
      add(pnlComputerHand, BorderLayout.NORTH);
      add(pnlPlayArea, BorderLayout.CENTER);
      add(pnlTimerDisplay, BorderLayout.WEST);
      add(pnlHumanHand, BorderLayout.SOUTH);

      // Add border titles to each section
      pnlComputerHand.setBorder(new TitledBorder("Computer Hand"));
      pnlPlayArea.setBorder(new TitledBorder("Playing Area"));
      pnlTimerDisplay.setBorder(new TitledBorder("Timer Button"));
      pnlHumanHand.setBorder(new TitledBorder("Your Hand"));

      // Set the game text style
      gameText.setText("Welcome to Build!");
      gameText.setForeground(Color.red);
      gameText.setHorizontalAlignment(JLabel.CENTER);

      gameStatus.setText("Click on card to begin.");
      gameStatus.setForeground(Color.red);
      gameStatus.setHorizontalAlignment(JLabel.CENTER);

      /**
       * Create labels for each card in each panel
       */
      // Load the icons to use on cards
      GUICard.loadCardIcons();

      // Create labels for player and computer
      for( int card = 0; card < NUM_CARDS_PER_HAND; card++)
      {
         // Load Cards Icons into array the cards
         computerLabels[card] = new JLabel(GUICard.getIcon(highCardGame
                 .getHand(0).inspectCard(card)));
         playerCardButtons[card] = new JButton(GUICard.getIcon(highCardGame
                 .getHand(1).inspectCard(card)));
      }

      // After creating labels above, you then add them to the specified panel
      for( int card = 0; card < NUM_CARDS_PER_HAND; card++)
      {
         // Load cards from array into panel
         pnlComputerHand.add(computerLabels[card]);
         pnlHumanHand.add(playerCardButtons[card]);
      }

      // Create playing card Icons
      playedCardLabels[0] = new JLabel(GUICard.getIcon(playerCard));
      playedCardLabels[1] = new JLabel(GUICard.getIcon(computerCard));

      // Create the text label under each played card
      playerCardLabel = new JLabel("Pile 1", JLabel.CENTER);
      computerCardLabel = new JLabel("Pile 2", JLabel.CENTER);

      /**
       * Add card icons to the play area section in table
       */
      // First Row
      pnlPlayArea.add(playedCardLabels[0]);
      pnlPlayArea.add(playedCardLabels[1]);
      pnlPlayArea.add(gameText);

      // Second Row
      pnlPlayArea.add(playerCardLabel);
      pnlPlayArea.add(computerCardLabel);
      pnlPlayArea.add(gameStatus);

      // Third Row
      pnlPlayArea.add(cannotPlayButton);

      // Add timer components to display
      pnlTimerDisplay.add(startButton);
      pnlTimerDisplay.add(timerDisplay);

      // Show the table
      setVisible(true);
   }

   // Set action listener for player cards
   public void addCardListenerToCards(ActionListener cardListener, int card)
   {
      // Adds the action listener to specific card
      playerCardButtons[card].addActionListener(cardListener);
   }

   public void addTimerButtonListener(ActionListener timerListener)
   {
      startButton.addActionListener(timerListener);
   }

   public void addNotPlayButtonListener(ActionListener buttonListener)
   {
      cannotPlayButton.addActionListener(buttonListener);
   }

   // Getter and Setters
   public void setHighCardGame(CardGameFramework highCardGame)
   {
      this.highCardGame = highCardGame;
   }

   public JButton[] getPlayerCardButtons()
   {
      return playerCardButtons;
   }

   public void setComputerCard(Card computerCard)
   {
      this.computerCard = computerCard;
   }

   public JPanel getPnlHumanHand()
   {
      return pnlHumanHand;
   }

   public JLabel[] getPlayedCardLabels()
   {
      return playedCardLabels;
   }

   // Removes card from player and computer panels
   public void removeCard(int index)
   {
      pnlComputerHand.remove(computerLabels[index]);
      pnlHumanHand.remove(playerCardButtons[index]);
      repaint();
   }

   // Updates the text on Game Status
   public void setGameStatus(String str)
   {
      gameStatus.setText(str);
   }

   // Sets the player played card icon
   public void setPlayerPlayedCardLabel(Card card)
   {
      this.playedCardLabels[0].setIcon(GUICard.getIcon(card));
   }

   // Sets the computer played card icon
   public void setComputerPlayedCardLabel(Card card)
   {
      this.playedCardLabels[1].setIcon(GUICard.getIcon(card));
   }

   // Sets the computer played card to back icon
   public void setComputerBackIcon()
   {
      this.playedCardLabels[0].setIcon(GUICard.getBackcardIcon());
   }

   // Set the seconds
   public void setTimerDisplay(int seconds)
   {
      // Check to see if the seconds needs a leading zero
      if( seconds%60 >= 0 && seconds%60 < 10)
      {
         this.timerDisplay.setText(Integer.toString(seconds/60) + ":0" +
                 Integer.toString(seconds%60));
      }
      else
      {
         this.timerDisplay.setText(Integer.toString(seconds/60) + ":" +
                 Integer.toString(seconds%60));
      }
   }

   public JButton getStartButton()
   {
      return startButton;
   }

   public void setStartButtonText(String str)
   {
      this.startButton.setText(str);
   }

   public void setPlayerCard(Card playerCard)
   {
      this.playerCard = playerCard;
   }

   public JButton getCannotPlayButton()
   {
      return cannotPlayButton;
   }

   public void updateAllHandsTable()
   {
      pnlHumanHand.removeAll();
      pnlComputerHand.removeAll();

      // Create labels for player and computer
      for( int card = 0; card < NUM_CARDS_PER_HAND; card++)
      {
         // Load Cards Icons into array the cards
         playerCardButtons[card].setIcon(GUICard.getIcon(highCardGame.getHand(1)
                 .inspectCard(card)));
         computerLabels[card].setIcon(GUICard.getIcon(highCardGame.getHand(0)
                 .inspectCard(card)));
      }

      // After creating labels above, you then add them to the specified panel
      for( int card = 0; card < NUM_CARDS_PER_HAND; card++)
      {
         // Load cards from array into panel
         pnlHumanHand.add(playerCardButtons[card]);
         pnlComputerHand.add(computerLabels[card]);
      }
      repaint();
   }
}


/*****************************************************************************
 * Card - A class that provides a card object and checks to see if the card  *
 * created has valid values.                                                 *
 *****************************************************************************/
class Card
{
   public enum Suit
   {CLUBS, DIAMONDS, HEARTS, SPADES}

   public static char[] valuRanks = {'A', '2', '3', '4', '5', '6', '7', '8',
           '9', 'T', 'J', 'Q', 'K', 'X'};
   private char value;
   private Suit suit;
   private boolean errorFlag;

   /**
    * Purpose: Constructor with no parameters to initialize card.
    * Preconditions: None.
    * Postconditions: Sets default value for a Card object.
    */
   public Card()
   {
      value = 'A';
      suit = Suit.SPADES;
   }

   /**
    * Purpose: Overloaded constructor with two parameters to initialize card.
    * Preconditions: Access to set() method.
    * Postconditions: Utilizes set method to initialize card.
    *
    * @param value The char value of the card.
    * @param suit  The enum suit of the card
    */
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   /**
    * Purpose: To return a String that displays card value and suit.
    * Preconditions: Initialized card object.
    * Postconditions: Sets value for card object based on parameters.
    *
    * @return Returns a String displaying the value and suit of card or illegal
    * if a errorFlag is true
    */
   public String toString()
   {
      if (errorFlag)
      {
         return "** illegal **";
      }
      return value + " of " + suit;
   }

   /**
    * Purpose: Sets card value and suit if the arguments are valid.
    * Preconditions: empty card initialized
    * Postconditions: Sets the errorFlag to true if valid or false otherwise.
    *
    * @return Returns a boolean of true if arguments are valid and false if not
    */
   public boolean set(char value, Suit suit)
   {
      if (isValid(value, suit))
      {
         this.value = value;
         this.suit = suit;
         errorFlag = false;
         return true;
      }
      errorFlag = true;
      return false;
   }

   /**
    * Purpose: Get value of value.
    * Preconditions: card object successfully set
    * Postconditions: none
    *
    * @return Returns char value of card object
    */
   public char getValue()
   {
      return value;
   }

   /**
    * Purpose: Get enum suit of suit.
    * Preconditions: card object successfully set
    * Postconditions: none
    *
    * @return Returns enum suit
    */
   public Suit getSuit()
   {
      return suit;
   }

   /**
    * Purpose: Get value of errorFlag.
    * Preconditions: card object successfully set
    * Postconditions: none
    *
    * @return Returns char value of card object
    */
   public boolean isErrorFlag()
   {
      return errorFlag;
   }

   /**
    * Purpose: Checks the equality of two objects and there members
    * Preconditions: card object successfully set
    * Postconditions: none
    *
    * @return Returns boolean result after checking if both value and suit are equal
    */
   public boolean equals(Card card)
   {

      return (value == card.value && suit == card.suit && errorFlag == card.errorFlag);
   }

   /**
    * Purpose: Checks the validity of the arguments passed into method by
    * checking if they are in our cardValues array
    * Preconditions: card object initialized
    * Postconditions: none
    *
    * @return Returns boolean depending if the value passed into the method is
    * in our approved array list as true and if not in our list,
    * returns false
    */
   private boolean isValid(char value, Suit suit)
   {
      String cardValues = "A23456789TJQKX";
      if (cardValues.indexOf(value) != -1)
      {
         return true;
      }
      return false;
   }

   /**
    * Purpose: Sort an array of cards from smallest to largest
    *
    * @param cardArray Array of cards
    * @param arraySize Number of cards in the array
    */
   public static void arraySort(Card[] cardArray, int arraySize)
   { // cardArray is Hand myCards, arraySize is numCards

      Card temp;

      // Bubble sort algorithm
      for (int card = 0; card < arraySize; card++)
      {
         for (int nextCard = 1; nextCard < (arraySize - card); nextCard++)
         {
            int previousCard = valueOfCard(cardArray[nextCard - 1]);
            int currentCard = valueOfCard(cardArray[nextCard]);

            if (previousCard > currentCard)
            {
               temp = cardArray[nextCard - 1];
               cardArray[nextCard - 1] = cardArray[nextCard];
               cardArray[nextCard] = temp;
            }

         }
      }
   }

   /**
    * Purpose: Return the numerical value of card based on index position
    *
    * @param card Card object whose value we want
    * @return Returns int card value
    */
   public static int valueOfCard(Card card)
   {

      // It traverses the valuRanks and check which matches the card value
      // Then it returns the index position as the value
      for (int value = 0; value < valuRanks.length; value++)
      {
         if (card.getValue() == valuRanks[value])
         {
            return value;
         }
      }
      return -1;
   }
}

/*****************************************************************************
 * Hand - A class that provides a card object and checks to see if the       *
 * card created has valid values.                                            *
 *****************************************************************************/

class Hand
{
   public static final int MAX_CARDS = 100;
   private Card[] myCards;
   private int numCards;

   /**
    * Purpose: Default constructor to initialize hand object
    * Preconditions: card object
    * Postconditions: Creates a hand
    */
   public Hand()
   {
      myCards = new Card[MAX_CARDS];
      numCards = 0;
   }

   /**
    * Purpose: Empty hand
    * Preconditions: initialized and declared hand
    * Postconditions: Changes numCards back to 0
    */
   /* Fix:You should set numCards to 0 in the resetHand().*/
   public void resetHand()
   {
      numCards = 0;
   }

   /**
    * Purpose: Takes card and adds it to myCards array
    * Preconditions: Valid card must exist
    * Postconditions: Adds card to array and iterates numCards +1
    *
    * @param card A valid card object from the card class
    * @return Returns true if successfully took card
    */
   /* Fix: The takeCard() method should be returning a boolean according to the
   state of the fullness of the hand. Yours returns true all of the time,
   which defeats the purpose.*/
   public boolean takeCard(Card card)
   {
      if (numCards < MAX_CARDS)
      {
         myCards[numCards] = new Card(card.getValue(), card.getSuit());
         numCards++;
         return true;
      }
      return false;
   }


   /**
    * Purpose: Reduces number of cards in hand
    * Preconditions: Valid card must exist
    * Postconditions: Decrements numCards 1
    *
    * @return Returns the top card
    */
   /* Fix: playCard() should  check for no more cards in the hand and then
   do something like return a bad card.
   */
   public Card playCard()
   {
      if (numCards > 0)
      {
         numCards--;
         System.out.println(myCards[numCards]);
         return myCards[numCards];
      }
      else
      {
         Card badCard = new Card('0', Card.Suit.SPADES);
         return badCard;
      }


   }

   /**
    * Purpose: Gives the number of cards in hand
    * Preconditions: hand object must exist
    * Postconditions: None
    *
    * @return Return int of top card
    */
   public int getNumCards()
   {
      return numCards;
   }

   /**
    * Purpose: Displays the cards in the Hand
    * Preconditions: Hand object exist
    * Postconditions: None
    *
    * @return Returns String that displays card in Hand object
    */
   public String toString()
   {
      String str;
      str = "Hand = ( ";
      int card;
      for (card = 0; card < numCards; card++)
      {
         str += (myCards[card].toString());
         if (card < numCards - 1)
         {
            str += ", ";
         }
      }
      str += " )";

      return str;
   }

   /**
    * Purpose: Checks to see if card is still valid and enables errorFlag if not
    * Preconditions: Cards in Hand
    * Postconditions: Changes card errorFlag to true if card is invalid
    *
    * @return Returns Card with error flag True or False
    */
   /* Fix: inspectCard() should validate k according to how many cards are in
   the myCards array.
   */
   public Card inspectCard(int k)
   {
      if (k > numCards || k < 0)
      {
         return new Card('0', Card.Suit.SPADES);
      }
      return myCards[k];
   }

   /**
    * Purpose: Sort the card in Hand object
    */
   public void sort()
   {
      Card.arraySort(myCards, numCards);
   }

   /**
    * Purpose: Plays Cards
    *
    * @param cardIndex The index of the card in the array
    * @return Returns a card that was played
    */
   public Card playCard(int cardIndex)
   {
      if (numCards == 0) //error
      {
         //Creates a card that does not work
         return new Card('M', Card.Suit.SPADES);
      }
      //Decreases numCards.
      Card card = myCards[cardIndex];

      numCards--;
      for (int cardPosition = cardIndex; cardPosition < numCards; cardPosition++)
      {
         myCards[cardPosition] = myCards[cardPosition + 1];
      }

      myCards[numCards] = null;

      return card;
   }
}

/*****************************************************************************
 * Deck - A class that provides a card object and checks to see if the card  *
 * created has valid values.                                                 *
 *****************************************************************************/
class Deck
{
   public static final int DECK_SIZE = 52;
   public static final int MAX_CARDS = 6 * DECK_SIZE;
   public static final int NUM_OF_VALUES = 13;

   private static Card[] masterPack = new Card[DECK_SIZE];
   private Card[] cards = new Card[MAX_CARDS];
   private int topCard = 0;

   /**
    * Purpose: Constructor to build single deck
    * Preconditions: None
    * Postconditions: Creates a single deck of cards
    */
   public Deck()
   {
      int card;
      allocateMasterPack();
      for (card = 0; card < DECK_SIZE; card++)
      {
         cards[card] = masterPack[card % DECK_SIZE];
         topCard++;
      }
   }

   /**
    * Purpose: Constructor to build multiple decks
    * Preconditions: None
    * Postconditions: Creates a multiple deck of cards
    *
    * @param numPacks The number of pack of cards
    */
   public Deck(int numPacks)
   {
      int card;
      allocateMasterPack();
      for (card = 0; card < numPacks * DECK_SIZE; card++)
      {
         cards[card] = masterPack[card % DECK_SIZE];
         topCard++;
      }

   }

   /**
    * Purpose: Constructor to build multiple decks
    * Preconditions: None
    * Postconditions: Creates a multiple deck of cards
    *
    * @param numPacks The number of pack of cards
    */
   /* Fix: init() should validate numPacks. */
   public void init(int numPacks)
   {
      if (numPacks <= 6)
      {
         int card;
         topCard = 0;

         for (card = 0; card < numPacks * DECK_SIZE; card++)
         {
            cards[card] = masterPack[card % DECK_SIZE];
            topCard++;
         }
      }
   }

   /**
    * Purpose: Shuffles the deck of cards
    * Preconditions: Need a full deck of cards
    * Postconditions: Shuffled deck
    */
   public void shuffle()
   {
      for (int card = 0; card < topCard; card++)
      {
         int second = (int) (Math.random() * topCard);
         Card temp = cards[card];
         cards[card] = cards[second];
         cards[second] = temp;
      }
   }

   /**
    * Purpose: Deals the card from the deck
    * Preconditions: Initialized deck
    * Postconditions: Decrements to rid of top card and returns top card
    */
   public Card dealCard()
   {
      if (topCard > 0)
      {
         topCard--;
         Card tempCard = cards[topCard];
         return tempCard;
      }
      return new Card('-', Card.Suit.SPADES);
   }

   /**
    * Purpose: Get the top card int
    * Preconditions: Cards in the deck
    * Postconditions: The int of the top card position
    */
   public int getTopCard()
   {
      return topCard;
   }

   /**
    * Purpose: Checks the validity of card
    * Preconditions: Cards in list
    * Postconditions: Changes the card error attribute to true if valid and
    * false if not valid
    *
    * @param k The value of the index position of card
    */
   public Card inspectCard(int k)
   {
      if (k > topCard)
      {
         return new Card('0', Card.Suit.SPADES);
      }

      return cards[k];
   }

   /**
    * Purpose: Creates the initial pack all other packs reference
    * Preconditions: none
    * Postconditions: Masterpack created
    */
   private static void allocateMasterPack()
   {
      int masterPackIndex;

      String cardValues = "A23456789TJQKX";

      if (masterPack[0] == null)
      {
         for (masterPackIndex = 0; masterPackIndex < DECK_SIZE; masterPackIndex++)
         {
            if (masterPackIndex / NUM_OF_VALUES == 0)
            {
               masterPack[masterPackIndex] =
                       new Card(cardValues.charAt(masterPackIndex % NUM_OF_VALUES),
                               Card.Suit.SPADES);
            }
            if (masterPackIndex / NUM_OF_VALUES == 1)
            {
               masterPack[masterPackIndex] =
                       new Card(cardValues.charAt(masterPackIndex % NUM_OF_VALUES),
                               Card.Suit.CLUBS);
            }
            if (masterPackIndex / NUM_OF_VALUES == 2)
            {
               masterPack[masterPackIndex] =
                       new Card(cardValues.charAt(masterPackIndex % NUM_OF_VALUES),
                               Card.Suit.HEARTS);
            }
            if (masterPackIndex / NUM_OF_VALUES == 3)
            {
               masterPack[masterPackIndex] =
                       new Card(cardValues.charAt(masterPackIndex % NUM_OF_VALUES),
                               Card.Suit.DIAMONDS);
            }
         }
      }
   }

   /**
    * Get the number of cards in Deck
    *
    * @return Returns int that is the number of cards
    */
   public int getNumCards()
   {
      return topCard;
   }

   /**
    * Purpose: Adds a card to the deck and makes sure each card only has the
    * number of instances equal to or less than number of packs
    *
    * @param card The card to be inserted
    * @return Returns true if successfully added, false if not
    */
   public boolean addCard(Card card)
   {
      // The number of decks
      int deckNum = topCard / DECK_SIZE;

      // Keep track on the number of instances per card
      int cardInstances = 0;

      // If the card matches, it adds to the instance count
      for (int cardPosition = 0; cardPosition < topCard; cardPosition++)
      {
         if (card.equals(cards[cardPosition]))
         {
            cardInstances++;
         }
      }

//      System.out.println("Card instances is: " + cardInstances);

      // If card instance is equal or more than the number of decks ,it fails.
      if (cardInstances >= deckNum)
      {
//         System.out.println("Did not add card" + card.toString());
         return false;
      }
//      System.out.println("Added the card to the deck");

      // Take added card and assign it to the top card.
      cards[topCard] = card;

      // Increase the topCard counter since we added a card
      topCard++;
//      System.out.println("The topCard Value is: " + topCard);
      return true;
   }

   /**
    * Purpose: Removes a card for the Deck
    *
    * @param card Card to be removed
    * @return Returns true if successfully removed, false if not
    */
   public boolean removeCard(Card card)
   {
      // Traverses array of cards to see if card exists
      for (int cardsIndex = 0; cardsIndex < topCard; cardsIndex++)
      {
         // If card equals a card in deck, it removes it
         if (cards[cardsIndex].equals(card))
         {
            System.out.println("Removed Card Successfully");

            // Sets card to value of topCard
            cards[cardsIndex] = cards[topCard - 1];

            // Decrements topCard
            topCard--;
            return true;
         }
      }

      System.out.println("Did not remove card, none left");
      System.out.println(topCard);
      return false;
   }

   /**
    * Purpose: Sorts the array of cards in deck
    */
   public void sort()
   {
      Card.arraySort(cards, topCard);
   }

   /**
    * Purpose: String to display to console the deck of cards
    *
    * @return String that holds the cards in hand
    */
   public String toString()
   {
      String str;
      str = "Deck = ( ";
      for (int card = 0; card < getNumCards(); card++)
      {
         str += (cards[card].toString());
         if (card < getNumCards() - 1)
         {
            str += ", ";
         }
      }
      str += " )";

      return str;
   }
}

/*****************************************************************************
 * GUICard - A class that provides a card object icons                       *
 *****************************************************************************/
class GUICard
{
   //private static GUICard data
   private static Icon[][] iconCards = new ImageIcon[14][4];
   private static Icon iconBack;

   static boolean iconsLoaded = false;

   static void loadCardIcons()
   {
      if (iconsLoaded)
         return;
      for (int cardValue = 0; cardValue < iconCards.length; cardValue++)
      {
         for (int cardSuit = 0; cardSuit < iconCards[cardValue].length; cardSuit++)
         {
            //numCard will return string at i value
            //numSuit will return suit at ii value
            String filename = numCard(cardValue) + numSuit(cardSuit) + ".gif";
            ImageIcon cardImage = new ImageIcon("images/" + filename);
            iconCards[cardValue][cardSuit] = cardImage;
         }
      }
      //create final back card
      iconBack = new ImageIcon("images/BK.gif");
      iconsLoaded = true;
   }

   //  Changes integer to the card value
   static String numCard(int cardNum)
   {
      String[] cardValues = {"A", "2", "3", "4", "5", "6",
              "7", "8", "9", "T", "J", "Q", "K", "X"};
      return cardValues[cardNum];
   }

   //Check
   static String numSuit(int suitNum)
   {
      if (suitNum < 0 || suitNum > 3)
         return "invalid";
      return Card.Suit.values()[suitNum]
              .toString().toUpperCase().substring(0, 1);
   }

   //Check
   public static int valueToInt(Card card)
   {
      return Card.valueOfCard(card);
   }

   //Converts suit to number
   private static int suitToNum(Card card)
   {
      Card.Suit cardSuit = card.getSuit();

      switch (cardSuit)
      {
         case CLUBS:
            return 0;
         case DIAMONDS:
            return 1;
         case HEARTS:
            return 2;
         case SPADES:
            return 3;
         default:
            return -1;
      }
   }

   public static Icon getIcon(Card card)
   {
      return iconCards[valueToInt(card)][suitToNum(card)];
   }

   public static Icon getBackcardIcon()
   {
      return iconBack;
   }
}

/*****************************************************************************
 * CardGameFramework - Provides a framework to start a card game             *
 *****************************************************************************/
class CardGameFramework
{
   private static final int MAX_PLAYERS = 50;

   private int numPlayers;
   private int numPacks;            // # standard 52-card packs per deck
   // ignoring jokers or unused cards
   private int numJokersPerPack;    // if 2 per pack & 3 packs per deck, get 6
   private int numUnusedCardsPerPack;  // # cards removed from each pack
   private int numCardsPerHand;        // # cards to deal each player
   private Deck deck;               // holds the initial full deck and gets
   // smaller (usually) during play
   private Hand[] hand;             // one Hand for each player
   private Card[] unusedCardsPerPack;   // an array holding the cards not used
   // in the game.  e.g. pinochle does not
   // use cards 2-8 of any suit

   public CardGameFramework( int numPacks, int numJokersPerPack,
                             int numUnusedCardsPerPack,  Card[] unusedCardsPerPack,
                             int numPlayers, int numCardsPerHand)
   {
      int k;

      // filter bad values
      if (numPacks < 1 || numPacks > 6)
         numPacks = 1;
      if (numJokersPerPack < 0 || numJokersPerPack > 4)
         numJokersPerPack = 0;
      if (numUnusedCardsPerPack < 0 || numUnusedCardsPerPack > 50) //  > 1 card
         numUnusedCardsPerPack = 0;
      if (numPlayers < 1 || numPlayers > MAX_PLAYERS)
         numPlayers = 4;
      // one of many ways to assure at least one full deal to all players
      if  (numCardsPerHand < 1 ||
              numCardsPerHand >  numPacks * (52 - numUnusedCardsPerPack)
                      / numPlayers )
         numCardsPerHand = numPacks * (52 - numUnusedCardsPerPack) / numPlayers;

      // allocate
      this.unusedCardsPerPack = new Card[numUnusedCardsPerPack];
      this.hand = new Hand[numPlayers];
      for (k = 0; k < numPlayers; k++)
         this.hand[k] = new Hand();
      deck = new Deck(numPacks);

      // assign to members
      this.numPacks = numPacks;
      this.numJokersPerPack = numJokersPerPack;
      this.numUnusedCardsPerPack = numUnusedCardsPerPack;
      this.numPlayers = numPlayers;
      this.numCardsPerHand = numCardsPerHand;
      for (k = 0; k < numUnusedCardsPerPack; k++)
         this.unusedCardsPerPack[k] = unusedCardsPerPack[k];

      // prepare deck and shuffle
      newGame();
   }

   // constructor overload/default for game like bridge
   public CardGameFramework()
   {
      this(1, 0, 0, null, 4, 13);
   }

   public Hand getHand(int k)
   {
      // hands start from 0 like arrays

      // on error return automatic empty hand
      if (k < 0 || k >= numPlayers)
         return new Hand();

      return hand[k];
   }

   public Card getCardFromDeck() { return deck.dealCard(); }

   public int getNumCardsRemainingInDeck() { return deck.getNumCards(); }

   public void newGame()
   {
      int k, j;

      // clear the hands
      for (k = 0; k < numPlayers; k++)
         hand[k].resetHand();

      // restock the deck
      deck.init(numPacks);

      // remove unused cards
      for (k = 0; k < numUnusedCardsPerPack; k++)
         deck.removeCard( unusedCardsPerPack[k] );

      // add jokers
      for (k = 0; k < numPacks; k++)
         for ( j = 0; j < numJokersPerPack; j++)
            deck.addCard( new Card('X', Card.Suit.values()[j]) );

      // shuffle the cards
      deck.shuffle();
   }

   public boolean deal()
   {
      // returns false if not enough cards, but deals what it can
      int k, j;
      boolean enoughCards;

      // clear all hands
      for (j = 0; j < numPlayers; j++)
         hand[j].resetHand();

      enoughCards = true;
      for (k = 0; k < numCardsPerHand && enoughCards ; k++)
      {
         for (j = 0; j < numPlayers; j++)
            if (deck.getNumCards() > 0)
               hand[j].takeCard( deck.dealCard() );
            else
            {
               enoughCards = false;
               break;
            }
      }

      return enoughCards;
   }

   void sortHands()
   {
      int k;

      for (k = 0; k < numPlayers; k++)
         hand[k].sort();
   }

   public Card playCard(int playerIndex, int cardIndex)
   {
      // returns bad card if either argument is bad
      if (playerIndex < 0 ||  playerIndex > numPlayers - 1 ||
              cardIndex < 0 || cardIndex > numCardsPerHand - 1)
      {
         //Creates a card that does not work
         return new Card('M', Card.Suit.SPADES);
      }

      // return the card played
      return hand[playerIndex].playCard(cardIndex);

   }


   boolean takeCard(int playerIndex)
   {
      // returns false if either argument is bad
      if (playerIndex < 0 || playerIndex > numPlayers - 1)
         return false;

      // Are there enough Cards?
      if (deck.getNumCards() <= 0)
         return false;

      return hand[playerIndex].takeCard(deck.dealCard());
   }
}



