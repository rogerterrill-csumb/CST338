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
      // Initialize View table
      gameView.initTable();

      // Update the dollar message
      gameView.setDollars(gameModel.getDollarsMessage());

      // Add listeners to both hit and stay
      addListeners();

      // Set card labels for player
      for( int i = 0; i < gameModel.getHand(gameModel.PLAYER).getNumCards(); i++)
      {
         gameView.setHandLabels(gameModel.PLAYER, i, gameModel.getPlayerCardInHand(i));
      }

      System.out.println(gameModel.getHand(0).toString());
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
         System.out.println(gameView.showDollarInConsole());
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