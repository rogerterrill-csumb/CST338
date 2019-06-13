class GameModel
{
   // Global constants
   static int NUM_PLAYERS = 2;
   static int MAX_CARDS_PER_HAND = 7;
   static int PLAYER = 0;
   static int COMPUTER = 1;

   // Instance Members
   private int dollars = 10;
   private int bet = 0;

   // Both player and computer array of cards in hand
   private Card[] playerHand = new Card[MAX_CARDS_PER_HAND];

   private Card[] computerHand = new Card[MAX_CARDS_PER_HAND];

   private int numCardsInPlayerHand = 0;
   private int numCardsInComputerHand = 0;

   // Object of the cardGameFramework and values passed in
   private CardGameFramework blackjack;
   private int numPacksPerDeck = 1;
   private int numJokersPerPack = 0;
   private int numUnusedCardsPerPack = 0;
   private Card[] unusedCardsPerPack = null;

   // Default Constructor
   GameModel()
   {
      // Creates an instance of the card game
      blackjack = new CardGameFramework(numPacksPerDeck, numJokersPerPack, numUnusedCardsPerPack, unusedCardsPerPack, NUM_PLAYERS, MAX_CARDS_PER_HAND);

      // Assign two cards to player
      blackjack.takeCard(PLAYER);
      blackjack.takeCard(PLAYER);

      // Assign two cards to computer
      blackjack.takeCard(COMPUTER);
      blackjack.takeCard(COMPUTER);

      System.out.println(blackjack.getHand(PLAYER));
   }

   public Hand getHand(int playerIndex)
   {
      return blackjack.getHand(playerIndex);
   }
}