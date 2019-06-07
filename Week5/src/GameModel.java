class GameModel
{
   // Global Constants
   static int NUM_CARDS_PER_HAND = 7;
   static int NUM_PLAYERS = 2;

   // Private members that hold the winning cards
   private Card[] compWinnings = new Card[NUM_PLAYERS * NUM_CARDS_PER_HAND];
   private Card[] playerWinnings = new Card[NUM_PLAYERS * NUM_CARDS_PER_HAND];

   // Keeps track if player won last
   private boolean playerWon = false;

   // Member that holds GameCardFramework object and it's arguments
   private CardGameFramework highCardGame;
   private int numPacksPerDeck = 1;
   private int numJokersPerPack = 2;
   private int numUnusedCardsPerPack = 0;
   private Card[] unusedCardsPerPack = null;

   GameModel()
   {
      // Initialize a new CardGameFramework
      highCardGame = new CardGameFramework(numPacksPerDeck,numJokersPerPack,numUnusedCardsPerPack,unusedCardsPerPack,NUM_PLAYERS,NUM_CARDS_PER_HAND);

      // Deal the cards from CardGameFramework
      highCardGame.deal();

      // DEBUG: Shows hand of the player
      System.out.println("Player Hand" + highCardGame.getHand(1).toString());
      System.out.println("Computers Hand" + highCardGame.getHand(0).toString());
   }

   // Getters and Setters
   public CardGameFramework getHighCardGame()
   {
      return highCardGame;
   }

   public boolean isPlayerWon()
   {
      return playerWon;
   }

   public void setPlayerWon(boolean playerWon)
   {
      this.playerWon = playerWon;
   }
}
