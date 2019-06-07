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

      // Update the view to show the cards IMPORTANT to wait to init until after the highCardGame is set above
      gameView.initTable();

      // Adds the card listener to the cards in view
      addCardListener();
   }

   public void addCardListener()
   {
      // Create labels for player and computer
      for( int card = 0; card < GameModel.NUM_CARDS_PER_HAND; card++)
      {
         gameView.getPlayerCardButtons()[card].addActionListener(new CardListener(card));
      }
   }

   public class CardListener implements ActionListener
   {
      // Private members
      private int cardIndex;

      CardListener(int cardIndex)
      {
         this.cardIndex = cardIndex;
      }

      public void actionPerformed(ActionEvent e)
      {
         gameView.removeCard(cardIndex);
         System.out.println("Card Clicked " + cardIndex);
      }
   }

}
