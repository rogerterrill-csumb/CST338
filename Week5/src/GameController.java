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

      // Update the view to show the cards IMPORTANT to wait to init until after the highCardGame is set above
      gameView.initTable();

      // Adds the card listener to the cards in view
      addCardListener();
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
         // Set the playerCard to the card clicked
         playerCard = gameModel.getHighCardGame().getHand(1).inspectCard(cardIndex);

         // Set this card to the playerCard in model class
         gameModel.setPlayerCard(playerCard);

         gameModel.displayValueComparison();
         // If player wins
         if(gameModel.compare() == 1)
         {
            gameModel.displayHand(1);
            gameModel.printCards();
            gameModel.addToPlayerWinnings();
            gameModel.setPlayerWon(true);
            gameView.setGameStatus("You won!");
            gameModel.displayPlayerWinnings();
            gameModel.incrementComputerCardCounter();
            gameModel.updateComputerCard();
            gameView.setComputerBackIcon();

         }
         else if(gameModel.compare() == -1)
         {
            gameModel.displayHand(1);
            gameModel.printCards();
            gameModel.addToComputerWinnings();
            gameModel.setPlayerWon(false);
            gameView.setGameStatus("You lost");;
//            gameModel.displayComputerWinnings();
            gameModel.incrementComputerCardCounter();
            gameModel.updateComputerCard();
            gameView.setComputerPlayedCardLabel(gameModel.getComputerCard());
         }
         else
         {
            gameView.setGameStatus("Draw");
         }


         gameView.removeCard(cardIndex);

         if(gameView.getPnlHumanHand().getComponentCount() == 0)
         {
            gameView.setGameStatus("GAME OVER");
         }
      }
   }
}
