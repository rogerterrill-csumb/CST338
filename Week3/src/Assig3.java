// Title:               Deck of Cards
// Files:               Assig3.java
// Semester:            Summer A, 2019
//
// Author:              Roger Terrill
// Email:               rchicasterrill@csumb.edu
// Lecturer's Name:     Jesse Cecil, M.S.
// TA's Name:           Joseph Appleton
// Lab Section:         CST 338

import java.util.Scanner;
/**
 * Main working class that utilizes the card class to build card objects
 */
public class Assig3
{

   /**
    * Purpose: Initialize cards and display there values.
    * Preconditions: Requires access to the Card class.
    * Postconditions: Prints out card values.
    */
   public static void main(String[] args)
   {

      //********Phase 4************/
      Scanner keyboard = new Scanner(System.in);
      int i;

      // Check to make sure a valid number is entered
      int chooseNumPacks = 0;
      while(chooseNumPacks < 1 || chooseNumPacks > 10)
      {
         System.out.print("How many hands? (1 - 10), please: ");
         chooseNumPacks = keyboard.nextInt();
      }

      // Initialize a hand
      Hand[] hands = new Hand[chooseNumPacks];

      // Initialize a new single deck
      Deck newDeck = new Deck();

      // Initialize each number of players hands
      for(i=0; i< chooseNumPacks; i++)
      {
         hands[i] = new Hand();
      }

      // Deal each player a card
      for(i = 0; i < Deck.DECK_SIZE; i++)
      {
         hands[i%chooseNumPacks].takeCard(newDeck.dealCard());
      }

      // Show all players hands
      for(i = 0; i < chooseNumPacks; i++)
      {
         hands[i].toString();
         System.out.println();
      }

      // Reset all players hands
      for(i = 0; i < chooseNumPacks; i++)
      {
         hands[i].resetHand();
      }

      System.out.println("Here are our hands, from SHUFFLED deck: ");
      // Initialize deck to 1 pack
      newDeck.init(1);

      // Shuffle the deck of cards
      newDeck.shuffle();

      // Deal all the cards
      for(i = 0; i < Deck.DECK_SIZE; i++)
      {
         hands[i%chooseNumPacks].takeCard(newDeck.dealCard());
      }

      // Display each hand
      for(i = 0; i < chooseNumPacks; i++)
      {
         hands[i].toString();
         System.out.println();
      }

      // Testing the sort method in the Card Class
      hands[0].sort();
//      hands[1].sort();

      for(i = 0; i < chooseNumPacks; i++)
      {
         hands[i].toString();
         System.out.println();
      }


      // Initialize a new single deck
      Deck guiDeck = new Deck(3);

      Card newCard = new Card();
      // Testing adding card
      guiDeck.addCard(newCard);

      // Testing removeCard, three ace of spades are created
      // in the new pack so I remove the three and the 4th is false
      // as there are no more Ace of Spades left
      guiDeck.removeCard(newCard);
      guiDeck.removeCard(newCard);
      guiDeck.removeCard(newCard);
      guiDeck.removeCard(newCard);
   }
}

/**
 * A class that provides a card object and checks to see if the card created has valid values.
 */
class Card
{
   public enum Suit {clubs, diamonds, hearts, spades}
   public static char[] valuRanks = {'A','2','3','4','5','6','7','8','9','T','J','Q','K','X'};
   private char value;
   private Suit suit;
   private boolean errorFlag;

   /**
    * Purpose: Constructor with no parameters to initialize card.
    * Preconditions: None.
    * Postconditions: Sets default value for a Card object.
    */
   public Card()
   {
      value = 'A';
      suit = Suit.spades;
   }

   /**
    * Purpose: Overloaded constructor with two parameters to initialize card.
    * Preconditions: Access to set() method.
    * Postconditions: Utilizes set method to initialize card.
    * @param value The char value of the card.
    * @param suit The enum suit of the card
    */
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   /**
    * Purpose: To return a String that displays card value and suit.
    * Preconditions: Initialized card object.
    * Postconditions: Sets value for card object based on parameters.
    * @return Returns a String displaying the value and suit of card or illegal if a errorFlag is true
    */
   public String toString()
   {
      if (errorFlag)
      {
         return "** illegal **";
      }
      return value + " of " + suit;
   }

   /**
    * Purpose: Sets card value and suit if the arguments are valid.
    * Preconditions: empty card initialized
    * Postconditions: Sets the errorFlag to true if valid or false otherwise.
    * @return Returns a boolean of true if arguments are valid and false if not
    */
   public boolean set(char value, Suit suit)
   {
      if (isValid(value, suit))
      {
         this.value = value;
         this.suit = suit;
         errorFlag = false;
         return true;
      }
      errorFlag = true;
      return false;
   }

   /**
    * Purpose: Get value of value.
    * Preconditions: card object successfully set
    * Postconditions: none
    * @return Returns char value of card object
    */
   public char getValue()
   {
      return value;
   }

   /**
    * Purpose: Get enum suit of suit.
    * Preconditions: card object successfully set
    * Postconditions: none
    * @return Returns enum suit
    */
   public Suit getSuit()
   {
      return suit;
   }

   /**
    * Purpose: Get value of errorFlag.
    * Preconditions: card object successfully set
    * Postconditions: none
    * @return Returns char value of card object
    */
   public boolean isErrorFlag()
   {
      return errorFlag;
   }

   /**
    * Purpose: Checks the equality of two objects and there members
    * Preconditions: card object successfully set
    * Postconditions: none
    * @return Returns boolean result after checking if both value and suit are equal
    */
   public boolean equals(Card card)
   {

      return (value == card.value && suit == card.suit);
   }

   /**
    * Purpose: Checks the validity of the arguments passed into method by
    *          checking if they are in our cardValues array
    * Preconditions: card object initialized
    * Postconditions: none
    * @return Returns boolean depending if the value passed into the method is
    *          in our approved array list as true and if not in our list,
    *          returns false
    */
   private boolean isValid(char value, Suit suit)
   {
      String cardValues = "A23456789TJQKX";
      if(cardValues.indexOf(value) != -1)
      {
         return true;
      }
      return false;
   }

   public static void arraySort(Card[] cardArray, int arraySize)
   { // cardArray is Hand myCards, arraySize is numCards

      int n = arraySize;
      Card temp;

      for (int i = 0; i < n; i++)
      {
         for (int j = 1; j < (n - i); j++)
         {
            int value1 = valueOfCard(cardArray[j - 1]);
            int value2 = valueOfCard(cardArray[j]);

            if (value1 > value2)
            {
               temp = cardArray[j - 1];
               cardArray[j - 1] = cardArray[j];
               cardArray[j] = temp;
            }

         }
      }
   }

   static int valueOfCard(Card card)
   {
      for(int i = 0; i < valuRanks.length; i++)
      {
         if(card.getValue() == valuRanks[i])
         {
            return i;
         }
      }
      return 0;
   }
}

/**
 * A class that provides a card object and checks to see if the card created
 * has valid values.
 */
class Hand
{
   public static final int MAX_CARDS = 100;
   private Card[] myCards;
   private int numCards;

   /**
    * Purpose: Default constructor to initialize hand object
    * Preconditions: card object
    * Postconditions: Creates a hand
    */
   public Hand()
   {
      myCards = new Card[MAX_CARDS];
      numCards = 0;
   }

   /**
    * Purpose: Empty hand
    * Preconditions: initialized and declared hand
    * Postconditions: Changes numCards back to 0
    */
   public void resetHand()
   {
      numCards = 0;
   }

   /**
    * Purpose: Takes card and adds it to myCards array
    * Preconditions: Valid card must exist
    * Postconditions: Adds card to array and iterates numCards +1
    * @param card A valid card object from the card class
    * @return Returns true if successfully took card
    */
   public boolean takeCard(Card card)
   {

      myCards[numCards] = new Card(card.getValue(), card.getSuit());
      numCards++;
      return true;
   }

   /**
    * Purpose: Reduces number of cards in hand
    * Preconditions: Valid card must exist
    * Postconditions: Decrements numCards 1
    * @return Returns the top card
    */
   public Card playCard()
   {
      numCards--;
      System.out.println(myCards[numCards]);
      return myCards[numCards];
   }

   /**
    * Purpose: Gives the number of cards in hand
    * Preconditions: hand object must exist
    * Postconditions: None
    * @return Return int of top card
    */
   public int getNumCards()
   {
      return numCards;
   }

   /**
    * Purpose: Displays the cards in the Hand
    * Preconditions: Hand object exist
    * Postconditions: None
    * @return Returns String that displays card in Hand object
    */
   public String toString()
   {
      String str;
      str = "Hand = ( ";
      int i;
      for(i=0; i < numCards; i++)
      {
         str += (myCards[i].toString());
         if(i < numCards -1)
         {
            str+= ", ";
         }
      }
      str += " )";

      System.out.println(str);
      return str;
   }

   /**
    * Purpose: Checks to see if card is still valid and enables errorFlag if not
    * Preconditions: Cards in Hand
    * Postconditions: Changes card errorFlag to true if card is invalid
    * @return Returns Card with error flag True or False
    */
   public Card inspectCard(int k)
   {
      if(k > numCards)
      {
         return new Card('0', Card.Suit.spades);
      }
      return myCards[k];
   }

   public void sort()
   {
      Card.arraySort(myCards, numCards);
   }

   public Card playCard(int cardIndex)
   {
      if ( numCards == 0 ) //error
      {
         //Creates a card that does not work
         return new Card('M', Card.Suit.spades);
      }
      //Decreases numCards.
      Card card = myCards[cardIndex];

      numCards--;
      for(int i = cardIndex; i < numCards; i++)
      {
         myCards[i] = myCards[i+1];
      }

      myCards[numCards] = null;

      return card;
   }
}

/**
 * A class that provides a card object and checks to see if the card
 * created has valid values.
 */
class Deck
{
   public static final int DECK_SIZE = 56;
   public static final int MAX_CARDS = 6 * DECK_SIZE;
   public static final int NUM_OF_VALUES = 14;

   private static Card[] masterPack = new Card[DECK_SIZE];
   private Card[] cards = new Card[MAX_CARDS];
   private int topCard = 0;

   /**
    * Purpose: Constructor to build single deck
    * Preconditions: None
    * Postconditions: Creates a single deck of cards
    */
   public Deck()
   {
      int i;
      allocateMasterPack();
      for(i = 0; i < DECK_SIZE; i++)
      {
         cards[i] = masterPack[i%DECK_SIZE];
         topCard++;
      }
   }

   /**
    * Purpose: Constructor to build multiple decks
    * Preconditions: None
    * Postconditions: Creates a multiple deck of cards
    * @param numPacks The number of pack of cards
    */
   public Deck(int numPacks)
   {
      int i;
      allocateMasterPack();
      for(i = 0; i < numPacks * DECK_SIZE; i++)
      {
         cards[i] = masterPack[i%DECK_SIZE];
         topCard++;
      }

   }

   /**
    * Purpose: Constructor to build multiple decks
    * Preconditions: None
    * Postconditions: Creates a multiple deck of cards
    * @param numPacks The number of pack of cards
    */
   public void init(int numPacks)
   {
      int i;
      topCard = 0;
      for(i = 0; i < numPacks * DECK_SIZE; i++)
      {
         cards[i] = masterPack[i%DECK_SIZE];
         topCard++;

      }
   }

   /**
    * Purpose: Shuffles the deck of cards
    * Preconditions: Need a full deck of cards
    * Postconditions: Shuffled deck
    */
   public void shuffle()
   {
      for (int i = 0; i < topCard; i++)
      {
         int second = (int)(Math.random() * topCard);
         Card temp = cards[i];
         cards[i] = cards[second];
         cards[second] = temp;
      }
   }

   /**
    * Purpose: Deals the card from the deck
    * Preconditions: Initialized deck
    * Postconditions: Decrements to rid of top card and returns top card
    */
   public Card dealCard()
   {
      if(topCard > 0)
      {
         topCard--;
         Card tempCard = cards[topCard];
         return tempCard;
      }
      return new Card('-', Card.Suit.spades);
   }
   /**
    * Purpose: Get the top card int
    * Preconditions: Cards in the deck
    * Postconditions: The int of the top card position
    */
   public int getTopCard()
   {
      return topCard;
   }

   /**
    * Purpose: Checks the validity of card
    * Preconditions: Cards in list
    * Postconditions: Changes the card error attribute to true if valid and
    *                   false if not valid
    * @param k The value of the index position of card
    */
   public Card inspectCard(int k)
   {
      if(k > topCard)
      {
         return new Card('0', Card.Suit.spades);
      }

      return cards[k];
   }

   /**
    * Purpose: Creates the initial pack all other packs reference
    * Preconditions: none
    * Postconditions: Masterpack created
    */
   private static void allocateMasterPack()
   {
      int i;

      String cardValues = "A23456789TJQKX";


      if(masterPack[0] == null)
      {
         for (i = 0; i < DECK_SIZE; i++)
         {
            if(i/ NUM_OF_VALUES == 0)
            {
               masterPack[i] = new Card(cardValues.charAt(i% NUM_OF_VALUES),
                     Card.Suit.spades);
            }
            if(i/ NUM_OF_VALUES == 1)
            {
               masterPack[i] = new Card(cardValues.charAt(i% NUM_OF_VALUES),
                     Card.Suit.clubs);
            }
            if(i/ NUM_OF_VALUES == 2)
            {
               masterPack[i] = new Card(cardValues.charAt(i% NUM_OF_VALUES),
                     Card.Suit.hearts);
            }
            if(i/ NUM_OF_VALUES == 3)
            {
               masterPack[i] = new Card(cardValues.charAt(i% NUM_OF_VALUES),
                     Card.Suit.diamonds);
            }
         }
      }
   }

   public int getNumCards()
   {
      return topCard;
   }

   public boolean addCard(Card card)
   {
      int deckNum = topCard/56;
      int cardInstances = 0;

      for(int i = 0; i < topCard; i++)
      {

//         if((cards[i].getValue() == card.getValue()) && (cards[i].getSuit() == card.getSuit()))
         if(card.equals(cards[i]))
         {
            cardInstances++;
         }
      }
      System.out.println("Card instances is: " + cardInstances);

      if( cardInstances >= deckNum)
      {
         System.out.println("Did not add card");
         return false;
      }
      System.out.println("Added the card to the deck");
      cards[topCard] = card;
      return true;
   }

   public boolean removeCard(Card card)
   {
      for(int i = 0; i < topCard; i++)
      {
         if(cards[i].equals(card))
         {
            System.out.println("Removed Card Successfully");
            cards[i] = cards[topCard-1];
            topCard--;
            return true;
         }
      }

      System.out.println("Did not remove card, none left");
      System.out.println(topCard);
      return false;
   }
}


/*****************Output**************************
 * PHASE 3
 K of diamonds / Q of diamonds / J of diamonds / T of diamonds / 9 of diamonds / 8 of diamonds / 7 of diamonds / 6 of diamonds / 5 of diamonds / 4 of diamonds / 3 of diamonds / 2 of diamonds / A of diamonds / K of hearts / Q of hearts / J of hearts / T of hearts / 9 of hearts / 8 of hearts / 7 of hearts / 6 of hearts / 5 of hearts / 4 of hearts / 3 of hearts / 2 of hearts / A of hearts / K of clubs / Q of clubs / J of clubs / T of clubs / 9 of clubs / 8 of clubs / 7 of clubs / 6 of clubs / 5 of clubs / 4 of clubs / 3 of clubs / 2 of clubs / A of clubs / K of spades / Q of spades / J of spades / T of spades / 9 of spades / 8 of spades / 7 of spades / 6 of spades / 5 of spades / 4 of spades / 3 of spades / 2 of spades / A of spades /
 3 of hearts / J of clubs / 9 of clubs / 4 of clubs / 6 of diamonds / 2 of hearts / 7 of diamonds / Q of spades / 8 of clubs / 7 of clubs / 4 of diamonds / Q of clubs / K of spades / 8 of hearts / J of spades / Q of clubs / 3 of diamonds / 5 of hearts / T of hearts / K of hearts / 6 of hearts / K of spades / T of spades / 3 of clubs / A of diamonds / 8 of spades / K of clubs / 5 of diamonds / 3 of spades / K of diamonds / T of diamonds / A of clubs / 5 of spades / J of hearts / 6 of spades / 3 of hearts / A of spades / 8 of clubs / 9 of spades / 8 of diamonds / 4 of hearts / 9 of clubs / 2 of clubs / T of diamonds / Q of diamonds / K of diamonds / 9 of diamonds / 7 of clubs / 8 of spades / J of diamonds / 3 of clubs / K of hearts /
 K of diamonds / Q of diamonds / J of diamonds / T of diamonds / 9 of diamonds / 8 of diamonds / 7 of diamonds / 6 of diamonds / 5 of diamonds / 4 of diamonds / 3 of diamonds / 2 of diamonds / A of diamonds / K of hearts / Q of hearts / J of hearts / T of hearts / 9 of hearts / 8 of hearts / 7 of hearts / 6 of hearts / 5 of hearts / 4 of hearts / 3 of hearts / 2 of hearts / A of hearts /
 A of spades / Q of hearts / 9 of hearts / 6 of spades / 5 of clubs / 7 of hearts / Q of spades / 3 of diamonds / J of diamonds / Q of diamonds / T of diamonds / 5 of hearts / K of diamonds / A of hearts / 8 of clubs / 5 of spades / 6 of clubs / 2 of spades / 4 of diamonds / 2 of diamonds / 9 of clubs / 6 of hearts / 3 of hearts / 9 of spades / 8 of hearts / 8 of diamonds /
 PHASE4
 How many hands? (1 - 10), please: 6
 Hand = ( K of diamonds, 7 of diamonds, A of diamonds, 8 of hearts, 2 of hearts, 9 of clubs, 3 of clubs, T of spades, 4 of spades )
 Hand = ( Q of diamonds, 6 of diamonds, K of hearts, 7 of hearts, A of hearts, 8 of clubs, 2 of clubs, 9 of spades, 3 of spades )
 Hand = ( J of diamonds, 5 of diamonds, Q of hearts, 6 of hearts, K of clubs, 7 of clubs, A of clubs, 8 of spades, 2 of spades )
 Hand = ( T of diamonds, 4 of diamonds, J of hearts, 5 of hearts, Q of clubs, 6 of clubs, K of spades, 7 of spades, A of spades )
 Hand = ( 9 of diamonds, 3 of diamonds, T of hearts, 4 of hearts, J of clubs, 5 of clubs, Q of spades, 6 of spades )
 Hand = ( 8 of diamonds, 2 of diamonds, 9 of hearts, 3 of hearts, T of clubs, 4 of clubs, J of spades, 5 of spades )
 Here are our hands, from SHUFFLED deck:
 Hand = ( A of diamonds, 4 of hearts, Q of hearts, 8 of diamonds, 5 of clubs, 9 of clubs, 5 of diamonds, K of spades, 2 of spades )
 Hand = ( 9 of diamonds, Q of spades, 5 of spades, Q of clubs, Q of diamonds, J of diamonds, 7 of hearts, T of diamonds, 6 of clubs )
 Hand = ( 4 of diamonds, 3 of hearts, 5 of hearts, J of spades, 6 of hearts, J of hearts, 7 of diamonds, 7 of spades, T of spades )
 Hand = ( 6 of spades, 3 of diamonds, 3 of clubs, 8 of clubs, A of spades, 2 of diamonds, K of clubs, J of clubs, 9 of hearts )
 Hand = ( 7 of clubs, K of hearts, 8 of spades, 4 of spades, 8 of hearts, 9 of spades, T of hearts, K of diamonds )
 Hand = ( 4 of clubs, A of hearts, A of clubs, 2 of clubs, 2 of hearts, 3 of spades, T of clubs, 6 of diamonds )
 **************************************************/