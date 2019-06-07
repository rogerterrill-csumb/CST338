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

      // Connect the model's highCardGame object to view
      gameView.setHighCardGame(gameModel.getHighCardGame());

      // Update the view to show the cards IMPORTANT to wait to init
      // Until after the highCardGame is set
      gameView.initTable();

      addCardListener();
   }

   public void addCardListener()
   {
      // Create labels for player and computer
      for( int card = 0; card < GameModel.NUM_CARDS_PER_HAND; card++)
      {
         gameView.getPlayerCardButtons()[card].addActionListener(new CardListener());
      }
   }

   public class CardListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.out.println("Card Clicked");
      }
   }

}
