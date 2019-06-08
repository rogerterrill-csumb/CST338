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
      gameView.setComputerCard(gameModel.getComputerCard());

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
      private Card playerCard;

      CardListener(int cardIndex)
      {
         this.cardIndex = cardIndex;
      }

      public void actionPerformed(ActionEvent e)
      {
         playerCard = gameModel.getHighCardGame().getHand(1).inspectCard(cardIndex);

         gameModel.setPlayerCard(playerCard);

         if(gameModel.compare() == 1)
         {
            gameModel.printCards();
            // Turns on the error as the card has been used
            playerCard.set('M', Card.Suit.SPADES);
            gameModel.getComputerCard().set('M', Card.Suit.SPADES);

            // Displays the card of both people

            gameModel.setPlayerWon(true);
            System.out.println("YOU WON");
            gameView.setGameStatus("You won!");
         }
         else
         {
            gameModel.printCards();
            gameModel.setPlayerWon(false);
            System.out.println("YOU LOST");
            gameView.setGameStatus("You lost");;
         }

         gameView.removeCard(cardIndex);
      }
   }



}
