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
         // Deal single card to player from deck
         gameModel.dealCardToPlayer();

         // Update player hand display
         updatePlayerHandDisplay();
      }
   }

   // Stay action listener
   public class stayListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         // Display the computers hand
         showFullComputerHand();

         JOptionPane.showMessageDialog(null,"Brand new hand");

         // Clear all hands and display new hand of player
         gameModel.dealNewRound();

         // Update player hand display
         updatePlayerHandDisplay();

         // Update computer hand as well
         updateComputerHandDIsplay();



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
   public void showFullComputerHand()
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


}