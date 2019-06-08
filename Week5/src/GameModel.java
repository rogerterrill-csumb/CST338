class GameModel
{
   // Global Constants
   static int NUM_CARDS_PER_HAND = 7;
   static int NUM_PLAYERS = 2;

   // Private members that hold the winning cards
   private Card[] compWinnings = new Card[NUM_PLAYERS * NUM_CARDS_PER_HAND];
   private Card[] playerWinnings = new Card[NUM_PLAYERS * NUM_CARDS_PER_HAND];
   private int computerCardsWon = 0;
   private int playerCardsWon = 0;

   // Computer card index counter
   private int computerCardCounter = 0;

   // Current card values
   private Card playerCard;
   private Card computerCard;

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

      // Set the first initial computer card to display
      computerCard = highCardGame.getHand(0).inspectCard(computerCardCounter);

      // DEBUG: Shows hand of the player
//      System.out.println("Player Hand" + highCardGame.getHand(1).toString());
//      System.out.println("Computers Hand" + highCardGame.getHand(0).toString());
   }

   // Compare two card values
   public int compare()
   {
      if(playerCard.getValue() > computerCard.getValue())
      {
         return 1;
      }
      else if(playerCard.getValue() < computerCard.getValue())
      {
         return -1;
      }
      else
      {
         return 0;
      }
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

   public Card getPlayerCard()
   {
      return playerCard;
   }

   public void setPlayerCard(Card playerCard)
   {
      this.playerCard = playerCard;
   }

   public Card getComputerCard()
   {
      return computerCard;
   }

   public void updateComputerCard()
   {
      this.computerCard = highCardGame.getHand(0).inspectCard(computerCardCounter);
   }

   public void printCards()
   {
      System.out.println("Your Card " + playerCard.toString() + " and the Computer Card " + computerCard.toString());
   }

   public void displayHand(int hand)
   {
      System.out.println(highCardGame.getHand(hand).toString());
   }

   public void addToComputerWinnings()
   {
      // Adds the cards won to the computer deck
      compWinnings[computerCardsWon++] = computerCard;
      compWinnings[computerCardsWon++] = playerCard;
   }

   public void addToPlayerWinnings()
   {
      // Adds the cards won to the computer deck
      playerWinnings[playerCardsWon++] = computerCard;
      playerWinnings[playerCardsWon++] = playerCard;
   }

   public void displayComputerWinnings()
   {
      for(int card = 0; card < computerCardsWon; card++)
      {
         System.out.println(compWinnings[card].toString());
      }
   }

   public void displayPlayerWinnings()
   {
      for(int card = 0; card < playerCardsWon; card++)
      {
         System.out.println(playerWinnings[card].toString());
      }
   }

   public void incrementComputerCardCounter()
   {
      computerCardCounter++;
   }

}
