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

   // Initializes the gamecontroller
   public void gameControllerInit()
   {
      // Sets the instance variable in GameView for number of cards
      gameView.setPlayerNumCards(gameModel.getNumCardsInPlayersHand());

      // Initialize View table
      gameView.initTable();

      // Update the dollar message
      gameView.setDollars(gameModel.getDollarsMessage());

      // Adds the card icons to the table
      displayInitHands();

      // Add listeners to both hit and stay
      addListeners();
   }

   // Creates hands for both player and computer
   public void displayInitHands()
   {
      // Number of cards in hand
      int numberOfCards = gameModel.getHand(gameModel.PLAYER).getNumCards();

      gameView.setPlayerNumCards(numberOfCards);

      // Set card labels for player
      for( int i = 0; i < numberOfCards; i++)
      {
         gameView.setHandLabels(gameModel.PLAYER, i, gameModel.getPlayerCardInHand(i));
      }

      // Set the card for computer
      gameView.setHandLabels(gameModel.COMPUTER, 1, gameModel.getComputerCardInHand(1));
   }


   // Add action listeners to buttons
   public void addListeners()
   {
      gameView.addActionListenerToHit(new hitListener());
      gameView.addActionListenerToStay(new stayListener());
   }

   /**
    * ACTION LISTENERS
    */
   // Hit action listener
   public class hitListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         // Sets the bet in model from view input
        gameModel.setBet(gameView.getBet());

         // Deal Card to players
         gameModel.dealCardToPlayer();

         if(gameModel.playerBust())
         {
            gameModel.showBothTotalsToConsole();

            // Update player hand display
            updatePlayerHandDisplay();

            // Shows computers hand
            showFullComputerHand();

            JOptionPane.showMessageDialog(null,"You busted");

            // New Round Setup
            newRoundSetup();

            // Sets the be if loss.
            gameModel.setLoseDollars();

            // Sets the message on the updated dollar amount
            gameView.setDollars(gameModel.getDollarsMessage());
         }

         // Update player hand display
         updatePlayerHandDisplay();

         // Check to see if money still available
         checkMoney();

         // Check to see if the deck is empty
         emptyDeck();
      }
   }

   // Stay action listener
   public class stayListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         // Display the computers hand
         showFullComputerHand();

         // As long as total is less than 17, deal to computer
         while(gameModel.getComputerHandTotal() < 17)
         {
            // Deals card to computer
            gameModel.dealCardToComputer();
         }

         if(gameModel.computerBust())
         {
            gameModel.showBothTotalsToConsole();

            // Add winnings
            gameModel.setWinDollars();

            // Show computers hand
            updateComputerHandDisplay();

            // Shows computers hand
            showFullComputerHand();

            JOptionPane.showMessageDialog(null,"Computer Busted, You WON!!!");

            newRoundSetup();


         }
         else if(gameModel.dealerHandWins())
         {
            gameModel.showBothTotalsToConsole();

            // Lose bet
            gameModel.setLoseDollars();

            // Show computers hand
            updateComputerHandDisplay();

            // Update player hand display
            updatePlayerHandDisplay();

            JOptionPane.showMessageDialog(null,"You Lost");

            newRoundSetup();
         }
         else if(gameModel.playerHandWins())
         {
            gameModel.showBothTotalsToConsole();

            // Sets the bet if won.
            gameModel.setWinDollars();

            // Show computers hand
            updateComputerHandDisplay();

            // Update player hand display
            updatePlayerHandDisplay();

            JOptionPane.showMessageDialog(null,"You Won!");

            newRoundSetup();
         }
         else if(gameModel.pushHands())
         {
            gameModel.showBothTotalsToConsole();

            updateComputerHandDisplay();

            JOptionPane.showMessageDialog(null,"PUSH");

            newRoundSetup();
         }

         // Checks if still have money
         checkMoney();

         // Check if there are cards left in deck
         emptyDeck();

         // Update dollars display
         gameView.setDollars(gameModel.getDollarsMessage());
      }
   }

   // Updates the display of the players hand
   public void updatePlayerHandDisplay()
   {
      // Number of cards in hand
      int numberOfCards = gameModel.getHand(gameModel.PLAYER).getNumCards();

      // Set the number of cards in GameView
      gameView.setPlayerNumCards(numberOfCards);

      // Set the icons for the cards
      for(int i = 0; i < numberOfCards; i++)
      {
         gameView.setHandLabels(gameModel.PLAYER,i,gameModel.getPlayerCardInHand(i));
      }

      // Update the display of the players hands
      gameView.updatePlayersHand();

      // DEBUG
      System.out.println("Player Hand Total: " + gameModel.getPlayerHandTotal());
   }

   // Updates the display of the computers hand
   public void updateComputerHandDisplay()
   {
      // Number of cards in hand
      int numberOfCards = gameModel.getHand(gameModel.COMPUTER).getNumCards();

      // Set the number of cards in GameView
      gameView.setComputerNumCards(numberOfCards);

      for(int i = 0; i < numberOfCards; i++)
      {
         // Set the icon for only one card in computer hand
         gameView.setHandLabels(gameModel.COMPUTER,i,gameModel.getComputerCardInHand(i));
      }

      // Update the display of the players hands
      gameView.updateComputersHand();

      // DEBUG
      System.out.println("Computer " + gameModel.getHand(gameModel.COMPUTER).toString());
   }

   // Displays the full hand of computer to see what it had
   private void showFullComputerHand()
   {
      // Number of cards in hand
      int numberOfCards = gameModel.getHand(gameModel.COMPUTER).getNumCards();

      // Set the number of cards in GameView
      gameView.setComputerNumCards(numberOfCards);

      // Set the icons for the cards
      for(int i = 0; i < numberOfCards; i++)
      {
         gameView.setHandLabels(gameModel.COMPUTER,i,gameModel.getComputerCardInHand(i));
      }

      // Update the display of the players hands
      gameView.updatePlayersHand();
   }

   private void checkMoney()
   {
      if(gameModel.outOfMoney())
      {
         JOptionPane.showMessageDialog(null,"You are out of money, GAME OVER!");

         System.exit(0);
      }
   }

   private void emptyDeck()
   {
      if(gameModel.emptyDeck())
      {
         JOptionPane.showMessageDialog(null,"You are out of cards, resetting the deck...");

         gameModel.newDeck();

         newRoundSetup();
      }
   }

   // Setup for a new round which includes new hands displayed
   private void newRoundSetup()
   {
      // Clear all hands and display new hand of player
      gameModel.dealNewRound();

      // Update player hand display
      updatePlayerHandDisplay();

      // Update computer hand as well
      updateComputerHandDisplay();

      // Set first card to backIcon
      gameView.resetComputerCard();
   }


}