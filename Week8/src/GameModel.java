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
   private int numCardsPerInitHand = 2;
   private int numPacksPerDeck = 1;
   private int numJokersPerPack = 0;
   private int numUnusedCardsPerPack = 0;
   private Card[] unusedCardsPerPack = null;

   // Default Constructor
   GameModel()
   {
      // Creates an instance of the card game
      blackjack = new CardGameFramework(numPacksPerDeck, numJokersPerPack, numUnusedCardsPerPack, unusedCardsPerPack, NUM_PLAYERS, numCardsPerInitHand);

      // Assign two cards to each player
      blackjack.deal();
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

   // Deal new cards for new round
   public void dealNewRound()
   {
      blackjack.deal();
   }

   // Get the dollars amount
   public String getDollarsMessage()
   {
      return "You have: $" + dollars;
   }

   // Return the total ammount of hand
   public int getPlayerHandTotal()
   {
      int total = 0;
      int numCards = blackjack.getHand(PLAYER).getNumCards();
      int cardValue = 0;

      for( int i = 0 ; i < numCards; i++)
      {
         cardValue = Card.valueOfCard(blackjack.getHand(PLAYER).inspectCard(i));
         if(cardValue > 10)
            cardValue = 10;
         System.out.println(cardValue);
         total +=cardValue;
      }

      return total;
   }
}