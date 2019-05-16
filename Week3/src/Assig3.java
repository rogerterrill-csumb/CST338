// Title:               Deck of Cards
// Files:               Assig3.java
// Semester:            Summer A, 2019
//
// Author:              Roger Terrill
// Email:               rchicasterrill@csumb.edu
// Lecturer's Name:     Jesse Cecil, M.S.
// TA's Name:           Joseph Appleton
// Lab Section:         CST 338

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
      Card one = new Card('A', Card.Suit.spades);
      Card two = new Card('4', Card.Suit.spades);
      Card three = new Card('J', Card.Suit.clubs);

      Hand myHand = new Hand();
      myHand.takeCard(one);
      myHand.takeCard(two);
      myHand.takeCard(three);
      myHand.toString();
      myHand.playCard();
      myHand.toString();


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
         return "** illegal **\n";
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
   public static final int MAX_CARDS = 50;
   private Card[] myCards;

   public int getNumCards()
   {
      return numCards;
   }

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
      myCards[numCards] = card;
      numCards++;
      return true;
   }

   public Card playCard()
   {
      Card temp = myCards[numCards];
      numCards--;
      return temp;
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
      if(myCards[k] != null)
      {
         return myCards[k];
      }
      return myCards[k].isErrorFlag();
   }
}