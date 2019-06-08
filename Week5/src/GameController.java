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
            // Adds played cards to player winnings
            gameModel.addToPlayerWinnings();

            // Sets game won status
            gameView.setGameStatus("You won!");

            // Increments card count to check to access next computer card
            gameModel.incrementComputerCardCounter();

            // Update to the new card in the model
            gameModel.updateComputerCard();

            // Set the computer played card icon to back icon
            gameView.setComputerBackIcon();

         }
         else if(gameModel.compare() == -1)
         {
            // Add current cards to computer winnings
            gameModel.addToComputerWinnings();

            // Set status to lost
            gameView.setGameStatus("You lost");;

            // Increments card counter to access next computer card in hand
            gameModel.incrementComputerCardCounter();

            // Update card in model to next card
            gameModel.updateComputerCard();

            // Sets the icon of the computer card to display
            gameView.setComputerPlayedCardLabel(gameModel.getComputerCard());
         }
         else
         {
            // Displays draw
            gameView.setGameStatus("Draw");
         }

         // Removes clicked card in player panel and one from computer panel
         gameView.removeCard(cardIndex);

         // If there are no components left, the game is over
         if(gameView.getPnlHumanHand().getComponentCount() == 0)
         {
            gameView.setGameStatus("GAME OVER");
         }
      }
   }
}
