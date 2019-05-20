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
//      int i;
//      Deck myDeck = new Deck(2);
//      for(i = 0; i< myDeck.getTopCard(); i++)
//      {
//         myDeck.dealCard();
//      }
//
//      System.out.println("\n\n");
//      myDeck.init(2);
//      myDeck.shuffle();
//      for(i = 0; i< myDeck.getTopCard(); i++)
//      {
//         myDeck.dealCard();
//      }
//
//
//      System.out.println("\n\n");
//      Deck newDeck = new Deck();
//      for(i = 0; i< newDeck.getTopCard(); i++)
//      {
//         newDeck.dealCard();
//      }
//
//      System.out.println("\n\n");
//      newDeck.init(1);
//      newDeck.shuffle();
//      for(i = 0; i< newDeck.getTopCard(); i++)
//      {
//         newDeck.dealCard();
//      }

      Scanner keyboard = new Scanner(System.in);
      int i;

      int chooseNumPacks = 0;
      while(chooseNumPacks < 1 || chooseNumPacks > 10)
      {
         System.out.print("How many hands? (1 - 10), please: ");
         chooseNumPacks = keyboard.nextInt();
      }

      Hand[] hands = new Hand[chooseNumPacks];
      Deck newDeck = new Deck();

      for(i=0; i< chooseNumPacks; i++)
      {
         hands[i] = new Hand();
      }

      for(i = 0; i < Deck.DECK_SIZE; i++)
      {
         hands[i%chooseNumPacks].takeCard(newDeck.dealCard());
      }

      for(i = 0; i < chooseNumPacks; i++)
      {
         hands[i].toString();
         System.out.println();
      }

      for(i = 0; i < chooseNumPacks; i++)
      {
         hands[i].resetHand();
      }

      System.out.println("Here are our hands, from SHUFFLED deck: ");
      newDeck.init(1);
      newDeck.shuffle();

      for(i = 0; i < Deck.DECK_SIZE; i++)
      {
         hands[i%chooseNumPacks].takeCard(newDeck.dealCard());
      }

      for(i = 0; i < chooseNumPacks; i++)
      {
         hands[i].toString();
         System.out.println();
      }

   }
}

/**
 * A class that provides a card object and checks to see if the card created has valid values.
 */
class Card
{
   public enum Suit {clubs, diamonds, hearts, spades}

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
    * Purpose: Checks the validity of the arguments passed into method by checking if they are in our cardValues array
    * Preconditions: card object initialized
    * Postconditions: none
    * @return Returns boolean depending if the value passed into the method is in our approved array list as true and if not in our list, returns false
    */
   private boolean isValid(char value, Suit suit)
   {
      String cardValues = "A23456789TJQK";
      if(cardValues.indexOf(value) != -1)
      {
         return true;
      }
      return false;
   }
}

/**
 * A class that provides a card object and checks to see if the card created has valid values.
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

   public Card playCard()
   {
      numCards--;
      System.out.println(myCards[numCards]);
      return myCards[numCards];
   }

   public int getNumCards()
   {
      return numCards;
   }

   /**
    * Purpose: Takes card and adds it to myCards array
    * Preconditions: Zero or more cards existing in array
    * Postconditions: None
    * @return Returns String that displays myCards array
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

   public Card inspectCard(int k)
   {
      if(k > numCards)
      {
         return new Card('0', Card.Suit.spades);
      }
      return myCards[k];
   }
}


class Deck
{
   public static final int MAX_CARDS = 312;
   public static final int DECK_SIZE = 52;

   private static Card[] masterPack = new Card[52];
   private Card[] cards = new Card[MAX_CARDS];
   private int topCard = 0;

   public Deck()
   {
      int i;
      allocateMasterPack();
      for(i = 0; i < DECK_SIZE; i++)
      {
         cards[i] = masterPack[i%52];
         topCard++;
      }
   }

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

   public Card dealCard()
   {
      if(topCard > 0)
      {
         topCard--;
         Card tempCard = cards[topCard];
//         System.out.print(tempCard.toString() + " / ");
         return tempCard;
      }
      return new Card('-', Card.Suit.spades);
   }

   public int getTopCard()
   {
      return topCard;
   }

   public Card inspectCard(int k)
   {
      if(k > topCard)
      {
         return new Card('0', Card.Suit.spades);
      }

      return cards[k];
   }

   private static void allocateMasterPack()
   {
      int i;

      String cardValues = "A23456789TJQK";


      if(masterPack[0] == null)
      {
         for (i = 0; i < DECK_SIZE; i++)
         {
            if(i/13 == 0)
            {
               masterPack[i] = new Card(cardValues.charAt(i%13), Card.Suit.spades);
//               System.out.println(masterPack[i].toString());
            }
            if(i/13 == 1)
            {
               masterPack[i] = new Card(cardValues.charAt(i%13), Card.Suit.clubs);
//               System.out.println(masterPack[i].toString());
            }
            if(i/13 == 2)
            {
               masterPack[i] = new Card(cardValues.charAt(i%13), Card.Suit.hearts);
//               System.out.println(masterPack[i].toString());
            }
            if(i/13 == 3)
            {
               masterPack[i] = new Card(cardValues.charAt(i%13), Card.Suit.diamonds);
//               System.out.println(masterPack[i].toString());
            }
         }
      }
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