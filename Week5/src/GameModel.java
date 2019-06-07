class GameModel
{
   // Global Constants
   static int NUM_CARDS_PER_HAND = 7;
   static int NUM_PLAYERS = 2;

   // Private members that hold the winning cards
   private Card[] compWinnings = new Card[NUM_PLAYERS * NUM_CARDS_PER_HAND];
   private Card[] playerWinnings = new Card[NUM_PLAYERS * NUM_CARDS_PER_HAND];

   // Member that holds GameCardFramework object and it's arguments
   private CardGameFramework highCardGame;
   private int numPacksPerDeck = 1;
   private int numJokersPerPack = 2;
   private int numUnusedCardsPerPack = 0;
   private Card[] unusedCardsPerPack = null;

   GameModel()
   {
      highCardGame = new CardGameFramework(numPacksPerDeck,numJokersPerPack,numUnusedCardsPerPack,unusedCardsPerPack,NUM_PLAYERS,NUM_CARDS_PER_HAND);
   }
}
