class GameModel
{
   // Global Constants
   static int NUM_CARDS_PER_HAND = 7;
   static int NUM_PLAYERS = 2;
   static int PAUSE = 1000;

   // Private members that hold the winning cards
   private Card[] compWinnings = new Card[NUM_PLAYERS * NUM_CARDS_PER_HAND];
   private Card[] playerWinnings = new Card[NUM_PLAYERS * NUM_CARDS_PER_HAND];

   // Status if players turn
   private boolean playersTurn = true;

   // Status of players scores
   private int computerScore = 0;
   private int playerScore = 0;

   // Status of cards won in each hand
   private int computerCardsWon = 0;
   private int playerCardsWon = 0;

   // Status of the text depending on win or loss
   private String playerStatus = "";

   // Computer card index counter
   private int computerCardCounter = 0;

   // Seconds of timer
   private int seconds;

   // Current card values
   private Card playerCard;
   private Card computerCard;

   // Member that holds GameCardFramework object and it's arguments
   private CardGameFramework highCardGame;
   private int numPacksPerDeck = 1;
   private int numJokersPerPack = 2;
   private int numUnusedCardsPerPack = 0;
   private Card[] unusedCardsPerPack = null;

   // Timer status
   private Boolean timeStatus = false;

   GameModel()
   {
      // Initialize a new CardGameFramework
      highCardGame = new CardGameFramework(numPacksPerDeck,numJokersPerPack,numUnusedCardsPerPack,unusedCardsPerPack,NUM_PLAYERS,NUM_CARDS_PER_HAND);

      // Deal the cards from CardGameFramework
      highCardGame.deal();

      // Set the first initial computer card to display
      computerCard = highCardGame.getCardFromDeck();

      // Sets the first initial player card
      playerCard = highCardGame.getCardFromDeck();
   }

   // Deal two cards onto the pile
   public void dealCardsToPile()
   {
      // Set the first initial computer card to display
      computerCard = highCardGame.getCardFromDeck();

      // Sets the first initial player card
      playerCard = highCardGame.getCardFromDeck();
   }

   // Compare player and computer played card int values
   public boolean cardCheck(Card card)
   {
      int diff = Card.valueOfCard(playerCard) - Card.valueOfCard(card);
      // If player wins
      if( diff == 1 || diff == -1)
      {
         return true;
      }
      return false;
   }

   // Getters and Setters
   public CardGameFramework getHighCardGame()
   {
      return highCardGame;
   }

   public void setPlayerCard(Card playerCard)
   {
      this.playerCard = playerCard;
   }


   public void setComputerCard(Card computerCard)
   {
      this.computerCard = computerCard;
   }

   public Card getComputerCard()
   {
      return computerCard;
   }

   public Card getPlayerCard()
   {
      return playerCard;
   }

   // Updates the computerCard with the new computerCardCounter
   public void updateComputerCard()
   {
      this.computerCard = highCardGame.getHand(0).inspectCard(computerCardCounter);
   }

   // DEBUG: Prints the string of both cards
   public void printCards()
   {
      System.out.println("Your Card " + playerCard.toString() + " and the Computer Card " + computerCard.toString());
   }

   // Increments seconds
   public void incrementSecondsonTimer()
   {
      seconds++;
   }

   // Pause for a second the thread
   public void doNothing(int milliseconds)
   {
      try
      {
         Thread.sleep(milliseconds);
      }
      catch (InterruptedException e)
      {
         System.out.println("Unexpected interrupt");
         System.exit(0);
      }
   }

   // Gets the status of the timer boolean
   public Boolean getTimeStatus()
   {
      return timeStatus;
   }

   // Sets the status of the timer boolean
   public void setTimeStatus(Boolean timeStatus)
   {
      this.timeStatus = timeStatus;
   }

   // Gets the current seconds
   public int getSeconds()
   {
      return seconds;
   }

   // Computer and Player Score Getters and Setters
   public static int getNumCardsPerHand()
   {
      return NUM_CARDS_PER_HAND;
   }

   public static void setNumCardsPerHand(int numCardsPerHand)
   {
      NUM_CARDS_PER_HAND = numCardsPerHand;
   }

   public int getComputerScore()
   {
      return computerScore;
   }

   public void setComputerScore(int computerScore)
   {
      this.computerScore = computerScore;
   }

   public int getPlayerScore()
   {
      return playerScore;
   }

   public void setPlayerScore(int playerScore)
   {
      this.playerScore = playerScore;
   }

   public String getGameStatusWithScores()
   {
      return Integer.toString(playerScore) + " - " + Integer.toString(computerScore) + " \n" + playerStatus;
   }

   public String displayFinalScore()
   {
      if(playerScore > computerScore)
      {
         return Integer.toString(playerScore) + " - " + Integer.toString(computerScore) + " \n YOU WON THE GAME!";
      }
      else if(playerScore < computerScore)
      {
         return Integer.toString(playerScore) + " - " + Integer.toString(computerScore) + " \n You lost the game.";
      }
      else
      {
         return Integer.toString(playerScore) + " - " + Integer.toString(computerScore) + " \n DRAW!";
      }

   }

   public String getPlayerStatus()
   {
      return playerStatus;
   }

   public void setPlayerStatus(String playerStatus)
   {
      this.playerStatus = playerStatus;
   }

   public void incrementComputerScore()
   {
      computerScore++;
   }

   public void incrementPlayerScore()
   {
      playerScore++;
   }

   public void takeCardFromDeckforPlayer()
   {
      playerCard = highCardGame.playCard(0, 1);
   }

   public boolean isPlayersTurn()
   {
      return playersTurn;
   }

   public void setPlayersTurn(boolean playersTurn)
   {
      this.playersTurn = playersTurn;
   }

   /**
    * DEBUGGING METHODS
    */
   // DEBUG: Displays the int card values of both cards
   public void displayValueComparison()
   {
      System.out.println("Player card value " + Card.valueOfCard(playerCard));
      System.out.println("Computer card value " + Card.valueOfCard(computerCard));
   }

   // DEBUG: Displays the full player winnings
   public void displayPlayerWinnings()
   {
      for(int card = 0; card < playerCardsWon; card++)
      {
         System.out.println(playerWinnings[card].toString());
      }
   }

   // DEBUG: Displays the full computer winnings
   public void displayComputerWinnings()
   {
      for(int card = 0; card < computerCardsWon; card++)
      {
         System.out.println(compWinnings[card].toString());
      }
   }

   // DEBUG: Displays the hand of either computer or player
   public void displayHand(int hand)
   {
      System.out.println(highCardGame.getHand(hand).toString());
   }
}
