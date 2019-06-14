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
      gameView.setNumCards(gameModel.getNumCardsInPlayersHand());

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

      gameView.setNumCards(numberOfCards);

      // Set card labels for player
      for( int i = 0; i < numberOfCards; i++)
      {
         gameView.setHandLabels(gameModel.PLAYER, i, gameModel.getPlayerCardInHand(i));
      }

      // Set the card for computer
      gameView.setHandLabels(gameModel.COMPUTER, 1, gameModel.getComputerCardInHand(1));
   }

   // Updates inforamtion after dealing of card
   public void updateAfterCardDeal()
   {
      gameModel.dealCardToPlayer();

      // Number of cards in hand
      int numberOfCards = gameModel.getHand(gameModel.PLAYER).getNumCards();

      for(int i = 0; i < numberOfCards; i++)
      {
         System.out.println(i);
         gameView.setHandLabels(gameModel.PLAYER,i,gameModel.getPlayerCardInHand(i));
         System.out.println(i);
      }
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
         updateAfterCardDeal();
      }
   }

   // Stay action listener
   public class stayListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.out.println(gameView.showDollarInConsole());
      }
   }

}