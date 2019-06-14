class GameModel
{
   // Global constants
   static int NUM_PLAYERS = 2;
   static int MAX_CARDS_PER_HAND = 7;
   static int PLAYER = 0;
   static int COMPUTER = 1;

   // Instance Members
   private int dollars = 20;
   private int bet = 0;

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
   }

   /**
    * GETTERS AND SETTERS
    */

   // Get hand
   public Hand getHand(int playerIndex)
   {
      return blackjack.getHand(playerIndex);
   }

   // Get the player card in hand based on index
   public Card getPlayerCardInHand(int cardIndex)
   {
      return this.blackjack.getHand(PLAYER).inspectCard(cardIndex);
   }

   // Get computer card in hand based on index
   public Card getComputerCardInHand(int cardIndex)
   {
      return this.blackjack.getHand(COMPUTER).inspectCard(cardIndex);
   }

   // Get number of cards in players hand
   public int getNumCardsInPlayersHand()
   {
      return blackjack.getHand(PLAYER).getNumCards();
   }

   // Deal card to player
   public void dealCardToPlayer()
   {
      blackjack.takeCard(PLAYER);
   }

   // Get the dollars amount
   public String getDollarsMessage()
   {
      return "You have: $" + dollars;
   }
}