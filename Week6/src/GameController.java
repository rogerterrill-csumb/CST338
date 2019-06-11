import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

      // Update the view to show the cards IMPORTANT to wait to init until after the highCardGame is set above
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

         while(!gameModel.isPlayersTurn() && gameModel.getHighCardGame().getNumCardsRemainingInDeck() > 0)
         {
            for(int card = 0; card < gameModel.NUM_CARDS_PER_HAND; card++)
            {
               // Set computer card to the value of index of computer hand
               computerCard = gameModel.getHighCardGame().getHand(0).inspectCard(card);

//               System.out.println(gameModel.getHighCardGame().getHand(0).toString());

               System.out.println("Pile card is " + Card.valueOfCard(gameModel.getComputerCard()) + " Comp card is " + Card.valueOfCard(computerCard));
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
            System.out.println("Players Hand");
            System.out.println(gameModel.getHighCardGame().getHand(1).toString());
            System.out.println("Computers Hand");
            System.out.println(gameModel.getHighCardGame().getHand(0).toString());
            System.out.println(gameModel.getComputerCard());
            System.out.println(gameModel.getPlayerCard());
            System.out.println("Cards left in game: " + gameModel.getHighCardGame().getNumCardsRemainingInDeck());
            System.out.println("Is it players turn: " + gameModel.isPlayersTurn());

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
         playerCard = gameModel.getHighCardGame().getHand(1).inspectCard(cardIndex);

         if(gameModel.playerCardCheck(playerCard) && gameModel.isPlayersTurn() && gameModel.getHighCardGame().getNumCardsRemainingInDeck() > 0)
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



         System.out.println(gameModel.getHighCardGame().getNumCardsRemainingInDeck());

         gameView.setGameStatus(gameModel.getGameStatusWithScores());

         //DEBUG
         System.out.println("Players Hand");
         System.out.println(gameModel.getHighCardGame().getHand(1).toString());
         System.out.println("Computers Hand");
         System.out.println(gameModel.getHighCardGame().getHand(0).toString());
         System.out.println(gameModel.getComputerCard());
         System.out.println(gameModel.getPlayerCard());
         System.out.println("Cards left in game: " + gameModel.getHighCardGame().getNumCardsRemainingInDeck());
         System.out.println("Is it players turn: " + gameModel.isPlayersTurn());

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
}