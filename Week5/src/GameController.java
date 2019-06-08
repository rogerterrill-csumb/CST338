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

   public void gameControllerInit()
   {
      // Connect the model's highCardGame object to view
      gameView.setHighCardGame(gameModel.getHighCardGame());

      // Update the view to show the cards IMPORTANT to wait to init until after the highCardGame is set above
      gameView.initTable();

      // Adds the card listener to the cards in view
      addCardListener();

      // Initialize first card

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
      private int playerCard;
      private int computerCard;

      CardListener(int cardIndex)
      {
         this.cardIndex = cardIndex;
      }

      public void actionPerformed(ActionEvent e)
      {
         playerCard = Card.valueOfCard(gameModel.getHighCardGame().getHand(1).inspectCard(cardIndex));
         computerCard = Card.valueOfCard(gameModel.getHighCardGame().getHand(0).inspectCard(cardIndex));

         if(true)
         {
            gameModel.setPlayerWon(true);
            System.out.println("YOU WON");
            gameView.setGameStatus("You updated the Screen");
         }
         else
         {
            gameModel.setPlayerWon(false);
            System.out.println("YOU LOST");
         }

         gameView.removeCard(cardIndex);
      }
   }



}
