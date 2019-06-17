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

      // DEBUG
      System.out.println(gameModel.getHand(1).toString());
      System.out.println(gameModel.getHand(0).toString());
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

         // If 21 happens then win
         if(gameModel.getPlayerHandTotal() == 21)
         {
            JOptionPane.showMessageDialog(null,"You got 21! You Won!");

            // Sets the bet if won.
            gameModel.setWinDollars();

            gameView.setDollars(gameModel.getDollarsMessage());

            // Clear all hands and display new hand of player
            gameModel.dealNewRound();

            // Update player hand display
            updatePlayerHandDisplay();

            // Update computer hand as well
            updateComputerHandDIsplay();
         }
         else
         {
            // Deal single card to player from deck
            gameModel.dealCardToPlayer();

            // Update player hand display
            updatePlayerHandDisplay();
         }


         // Checks to to see if the player busted
         if(gameModel.checkPlayerBust())
         {
            JOptionPane.showMessageDialog(null,"You busted");

            // Display the computers hand
            showFullComputerHand();

            // Clear all hands and display new hand of player
            gameModel.dealNewRound();

            // Update player hand display
            updatePlayerHandDisplay();

            // Update computer hand as well
            updateComputerHandDIsplay();

            // Sets the be if loss.
            gameModel.setLoseDollars();

            // Sets the message on the updated dollar amount
            gameView.setDollars(gameModel.getDollarsMessage());

            gameModel.showBothTotalsToConsole();
         }

         // Check to see if money still available
         checkMoney();
      }
   }

   // Stay action listener
   public class stayListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         // Display the computers hand
         showFullComputerHand();

         // If 21 happens then win
         if(gameModel.getPlayerHandTotal() == 21)
         {
            JOptionPane.showMessageDialog(null,"You Won!");

            // Sets the bet if won.
            gameModel.setWinDollars();

            // Clear all hands and display new hand of player
            gameModel.dealNewRound();

            // Update player hand display
            updatePlayerHandDisplay();

            // Update computer hand as well
            updateComputerHandDIsplay();
         }

         // As long as total is less than 17, deal to computer
         while(gameModel.getComputerHandTotal() < 17)
         {
            // Deals card to computer
            gameModel.dealCardToComputer();

            if(gameModel.getComputerHandTotal() == 21)
            {
               // Lose bet
               gameModel.setLoseDollars();

               JOptionPane.showMessageDialog(null,"Computer got 21 and won");

               // Clear all hands and display new hand of player
               gameModel.dealNewRound();

               // Update player hand display
               updatePlayerHandDisplay();

               // Update computer hand as well
               updateComputerHandDIsplay();


            }

            // Checks if the computer is a bust
            if(gameModel.checkComputerBust())
            {
               JOptionPane.showMessageDialog(null,"Computer Busted, You WON!!!");

               // Add winnings
               gameModel.setWinDollars();
               
               // Clear all hands and display new hand of player
               gameModel.dealNewRound();

               // Update player hand display
               updatePlayerHandDisplay();

               // Update computer hand as well
               updateComputerHandDIsplay();

               // Break out of loop
               break;
            }
         }

         // If both players are the same
         if(gameModel.getPlayerHandTotal() ==  gameModel.getComputerHandTotal())
         {
            JOptionPane.showMessageDialog(null,"PUSH");

            gameModel.showBothTotalsToConsole();

            // Clear all hands and display new hand of player
            gameModel.dealNewRound();

            // Update player hand display
            updatePlayerHandDisplay();

            // Update computer hand as well
            updateComputerHandDIsplay();
         }

         // Checks if still have money
         checkMoney();

         gameView.setDollars(gameModel.getDollarsMessage());


//         JOptionPane.showMessageDialog(null,"Brand new hand");
//
//         // Clear all hands and display new hand of player
//         gameModel.dealNewRound();
//
//         // Update player hand display
//         updatePlayerHandDisplay();
//
//         // Update computer hand as well
//         updateComputerHandDIsplay();

         gameModel.showBothTotalsToConsole();
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
      System.out.println("Player " + gameModel.getHand(gameModel.PLAYER).toString());
      System.out.println("Player Hand Total: " + gameModel.getPlayerHandTotal());
   }

   // Updates the display of the computers hand
   public void updateComputerHandDIsplay()
   {
      // Number of cards in hand
      int numberOfCards = gameModel.getHand(gameModel.COMPUTER).getNumCards();

      // Set the number of cards in GameView
      gameView.setPlayerNumCards(numberOfCards);

      // Set the icon for only one card in computer hand
      gameView.setHandLabels(gameModel.COMPUTER,1,gameModel.getComputerCardInHand(1));

      // Set first card to backIcon
      gameView.resetComputerCard();

      // Update the display of the players hands
      gameView.updatePlayersHand();


      // DEBUG
      System.out.println("Computer " + gameModel.getHand(gameModel.COMPUTER).toString());
   }

   // Displays the full hand of computer to see what it had
   private void showFullComputerHand()
   {
      // Number of cards in hand
      int numberOfCards = gameModel.getHand(gameModel.COMPUTER).getNumCards();

      // Set the number of cards in GameView
      gameView.setPlayerNumCards(numberOfCards);

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


}