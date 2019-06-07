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
   }

}
