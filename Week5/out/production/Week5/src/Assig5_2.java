/*
 * Title:               GUI Cards Phase 2
 * Files:               Assig5.java
 * Semester:            Summer A, 2019
 * Date:
 *
 * Author:              Roger Terrill, George Blombach, Dalia Faria,
 *                      Abby Packham, Carlos Orduna
 * Email:               rchicasterrill@csumb.edu, gblombach@csumb.edu,
 *                      dfaria@csumb.edu, apackham@csumb.edu,
 *                      cordunacorrales@csumb.edu
 * Lecturer's Name:     Jesse Cecil, M.S.
 * TA's Name:           Joseph Appleton
 * Lab Section:         CST 338
 */


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Random;


public class Assig5_2
{
   static int NUM_CARDS_PER_HAND = 7;
   static int NUM_PLAYERS = 2;
   static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
   static JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
   static JLabel[] playLabelText = new JLabel[NUM_PLAYERS];

   public static void main(String[] args)
   {

      int card;
      Icon tempIcon;

      //Load Icons for cards from GUICard class
      GUICard.loadCardIcons();

      // establish main frame in which program will run
      CardTable myCardTable
            = new CardTable("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
      myCardTable.setSize(800, 600);
      myCardTable.setLocationRelativeTo(null);
      myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // show everything to the user
      //myCardTable.setVisible(true); Repeated setVisible method. Omitted

      // CREATE LABELS ----------------------------------------------------
      for (card = 0; card < NUM_CARDS_PER_HAND; card++)
      {
         //give the Computer a back card Label
         computerLabels[card] = new JLabel(GUICard.getBackcardIcon());

         //give Human a random Card Label
         tempIcon = GUICard.getIcon(generateRandomCard());
         humanLabels[card] = new JLabel(tempIcon);
      }

      // ADD LABELS TO PANELS -----------------------------------------
      for (card = 0; card < NUM_CARDS_PER_HAND; card++)
      {
         //add indexed label to Computer panel
         myCardTable.pnlComputerHand.add(computerLabels[card]);

         //add indexed label to Human panel
         myCardTable.pnlHumanHand.add(humanLabels[card]);
      }


      // and two random cards in the play region (simulating a computer/hum ply)
      for (card = 0; card < NUM_PLAYERS; card++)
      {
         //getting random card
         tempIcon = GUICard.getIcon(generateRandomCard());
         //assigning 2 labels to playedCards
         playedCardLabels[card] = new JLabel(tempIcon);
         //adding labels to played area
         myCardTable.pnlPlayArea.add(playedCardLabels[card]);
      }


      // show everything to the user
      myCardTable.setVisible(true);

   }

   //generate a random card to be given to a player
   //-Currently can give repeated cards-
   //-It's OK as this is only for testing purposes-
   static Card generateRandomCard()
   {
      Deck deck = new Deck();
      Random randomGen = new Random();
      return deck.inspectCard(randomGen.nextInt(deck.getNumCards()));
   }
}

/*****************************************************************************
 *                        End of Assig5                                      *
 *****************************************************************************/

class CardTable extends JFrame
{
   //CardTable static data
   static int MAX_CARDS_PER_HAND = 56;
   static int MAX_PLAYERS = 2;

   //CardTable private data
   private int numCardsPerHand;
   private int numPlayers;

   //CardTable public data
   //3 panels - One Computer player, One Human player, One play area
   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;

   //Constructor and mutator - Adds panels to the JFrame
   CardTable(String title, int numCardsPerHand, int numPlayers)
   {
      //the string - title - will be displayed on the window frame.
      super(title);

      //BorderLayout manager - BorderLayout(int horizontalGap, int verticalGap)
      setLayout(new BorderLayout());

      //Sets values
      this.numCardsPerHand = numCardsPerHand;
      this.numPlayers = numPlayers;

      //GridLayout(int rows, int columns)
      //defines a panel for each field
      pnlComputerHand =
            new JPanel(new GridLayout(1, numCardsPerHand));
      pnlHumanHand =
            new JPanel(new GridLayout(1, numCardsPerHand));
      pnlPlayArea =
            new JPanel(new GridLayout(2, numPlayers));

      //Place panels to their specific location
      add(pnlComputerHand, BorderLayout.NORTH);
      add(pnlHumanHand, BorderLayout.SOUTH);
      add(pnlPlayArea, BorderLayout.CENTER);

      //Names each section and places a border
      pnlComputerHand.setBorder(new TitledBorder("Computer Hand"));
      pnlHumanHand.setBorder(new TitledBorder("Playing Area"));
      pnlPlayArea.setBorder(new TitledBorder("Your Hand"));

   }

   public int getNumCardsPerHand()
   {
      return numCardsPerHand;
   }

   public int getNumPlayers()
   {
      return numPlayers;
   }
}

/*****************************************************************************
 *                        End of CardTable                                   *
 *****************************************************************************/
class GUICard
{
   //private static GUICard data
   private static Icon[][] iconCards = new ImageIcon[14][4];
   private static Icon iconBack;

   static boolean iconsLoaded = false;

   static void loadCardIcons()
   {
      if (iconsLoaded)
         return;
      for (int cardValue = 0; cardValue < iconCards.length; cardValue++)
      {
         for (int cardSuit = 0; cardSuit < iconCards[cardValue].length; cardSuit++)
         {
            //numCard will return string at i value
            //numSuit will return suit at ii value
            String filename = numCard(cardValue) + numSuit(cardSuit) + ".gif";
            ImageIcon cardImage = new ImageIcon("images/" + filename);
            iconCards[cardValue][cardSuit] = cardImage;
         }
      }
      //create final back card
      iconBack = new ImageIcon("images/BK.gif");
      iconsLoaded = true;
   }

   //  Changes integer to the card value
   static String numCard(int cardNum)
   {
      String[] cardValues = {"A", "2", "3", "4", "5", "6",
            "7", "8", "9", "T", "J", "Q", "K", "X"};
      return cardValues[cardNum];
   }

   //Check
   static String numSuit(int suitNum)
   {
      if (suitNum < 0 || suitNum > 3)
         return "invalid";
      return Card.Suit.values()[suitNum]
            .toString().toUpperCase().substring(0, 1);
   }

   //Check
   private static int valueToInt(Card card)
   {
      return Card.valueOfCard(card);
   }

   //Converts suit to number
   private static int suitToNum(Card card)
   {
      Card.Suit cardSuit = card.getSuit();

      switch (cardSuit)
      {
         case SPADES:
            return 0;
         case HEARTS:
            return 1;
         case DIAMONDS:
            return 2;
         case CLUBS:
            return 3;
         default:
            return -1;
      }
   }

   public static Icon getIcon(Card card)
   {
      return iconCards[valueToInt(card)][suitToNum(card)];
   }

   public static Icon getBackcardIcon()
   {
      return iconBack;
   }
}
/*****************************************************************************
 *                        End of GUICard                                     *
 *****************************************************************************/

/*****************************************************************************
 * Card - A class that provides a card object and checks to see if the card  *
 * created has valid values.                                                 *
 *****************************************************************************/

class Card
{
   public enum Suit
   {CLUBS, DIAMONDS, HEARTS, SPADES}

   public static char[] valuRanks = {'A', '2', '3', '4', '5', '6', '7', '8',
         '9', 'T', 'J', 'Q', 'K', 'X'};
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
      suit = Suit.SPADES;
   }

   /**
    * Purpose: Overloaded constructor with two parameters to initialize card.
    * Preconditions: Access to set() method.
    * Postconditions: Utilizes set method to initialize card.
    *
    * @param value The char value of the card.
    * @param suit  The enum suit of the card
    */
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   /**
    * Purpose: To return a String that displays card value and suit.
    * Preconditions: Initialized card object.
    * Postconditions: Sets value for card object based on parameters.
    *
    * @return Returns a String displaying the value and suit of card or illegal
    * if a errorFlag is true
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
    *
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
    *
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
    *
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
    *
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
    *
    * @return Returns boolean result after checking if both value and suit are equal
    */
   public boolean equals(Card card)
   {

      return (value == card.value && suit == card.suit && errorFlag == card.errorFlag);
   }

   /**
    * Purpose: Checks the validity of the arguments passed into method by
    * checking if they are in our cardValues array
    * Preconditions: card object initialized
    * Postconditions: none
    *
    * @return Returns boolean depending if the value passed into the method is
    * in our approved array list as true and if not in our list,
    * returns false
    */
   private boolean isValid(char value, Suit suit)
   {
      String cardValues = "A23456789TJQKX";
      if (cardValues.indexOf(value) != -1)
      {
         return true;
      }
      return false;
   }

   /**
    * Purpose: Sort an array of cards from smallest to largest
    *
    * @param cardArray Array of cards
    * @param arraySize Number of cards in the array
    */
   public static void arraySort(Card[] cardArray, int arraySize)
   { // cardArray is Hand myCards, arraySize is numCards

      Card temp;

      // Bubble sort algorithm
      for (int card = 0; card < arraySize; card++)
      {
         for (int nextCard = 1; nextCard < (arraySize - card); nextCard++)
         {
            int previousCard = valueOfCard(cardArray[nextCard - 1]);
            int currentCard = valueOfCard(cardArray[nextCard]);

            if (previousCard > currentCard)
            {
               temp = cardArray[nextCard - 1];
               cardArray[nextCard - 1] = cardArray[nextCard];
               cardArray[nextCard] = temp;
            }

         }
      }
   }

   /**
    * Purpose: Return the numerical value of card based on index position
    *
    * @param card Card object whose value we want
    * @return Returns int card value
    */
   static int valueOfCard(Card card)
   {

      // It traverses the valuRanks and check which matches the card value
      // Then it returns the index position as the value
      for (int value = 0; value < valuRanks.length; value++)
      {
         if (card.getValue() == valuRanks[value])
         {
            return value;
         }
      }
      return -1;
   }
}
/*****************************************************************************
 *                        End of Card                                        *
 *****************************************************************************/

/*****************************************************************************
 * Hand - A class that provides a card object and checks to see if the       *
 * card created has valid values.                                            *
 *****************************************************************************/

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
   /* Fix:You should set numCards to 0 in the resetHand().*/
   public void resetHand()
   {
      numCards = 0;
   }

   /**
    * Purpose: Takes card and adds it to myCards array
    * Preconditions: Valid card must exist
    * Postconditions: Adds card to array and iterates numCards +1
    *
    * @param card A valid card object from the card class
    * @return Returns true if successfully took card
    */
   /* Fix: The takeCard() method should be returning a boolean according to the
   state of the fullness of the hand. Yours returns true all of the time,
   which defeats the purpose.*/
   public boolean takeCard(Card card)
   {
      if (numCards < MAX_CARDS)
      {
         myCards[numCards] = new Card(card.getValue(), card.getSuit());
         numCards++;
         return true;
      }
      return false;
   }


   /**
    * Purpose: Reduces number of cards in hand
    * Preconditions: Valid card must exist
    * Postconditions: Decrements numCards 1
    *
    * @return Returns the top card
    */
   /* Fix: playCard() should  check for no more cards in the hand and then
   do something like return a bad card.
   */
   public Card playCard()
   {
      if (numCards > 0)
      {
         numCards--;
         System.out.println(myCards[numCards]);
         return myCards[numCards];
      }
      else
      {
         Card badCard = new Card('0', Card.Suit.SPADES);
         return badCard;
      }


   }

   /**
    * Purpose: Gives the number of cards in hand
    * Preconditions: hand object must exist
    * Postconditions: None
    *
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
    *
    * @return Returns String that displays card in Hand object
    */
   public String toString()
   {
      String str;
      str = "Hand = ( ";
      int i;
      for (i = 0; i < numCards; i++)
      {
         str += (myCards[i].toString());
         if (i < numCards - 1)
         {
            str += ", ";
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
    *
    * @return Returns Card with error flag True or False
    */
   /* Fix: inspectCard() should validate k according to how many cards are in
   the myCards array.
   */
   public Card inspectCard(int k)
   {
      if (k > numCards || k < 0)
      {
         return new Card('0', Card.Suit.SPADES);
      }
      return myCards[k];
   }

   /**
    * Purpose: Sort the card in Hand object
    */
   public void sort()
   {
      Card.arraySort(myCards, numCards);
   }

   /**
    * Purpose: Plays Cards
    *
    * @param cardIndex The index of the card in the array
    * @return Returns a card that was played
    */
   public Card playCard(int cardIndex)
   {
      if (numCards == 0) //error
      {
         //Creates a card that does not work
         return new Card('M', Card.Suit.SPADES);
      }
      //Decreases numCards.
      Card card = myCards[cardIndex];

      numCards--;
      for (int i = cardIndex; i < numCards; i++)
      {
         myCards[i] = myCards[i + 1];
      }

      myCards[numCards] = null;

      return card;
   }
}
/*****************************************************************************
 *                        End of Hand                                        *
 *****************************************************************************/

/*****************************************************************************
 * Deck - A class that provides a card object and checks to see if the card  *
 * created has valid values.                                                 *
 *****************************************************************************/
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
      for (i = 0; i < DECK_SIZE; i++)
      {
         cards[i] = masterPack[i % DECK_SIZE];
         topCard++;
      }
   }

   /**
    * Purpose: Constructor to build multiple decks
    * Preconditions: None
    * Postconditions: Creates a multiple deck of cards
    *
    * @param numPacks The number of pack of cards
    */
   public Deck(int numPacks)
   {
      int i;
      allocateMasterPack();
      for (i = 0; i < numPacks * DECK_SIZE; i++)
      {
         cards[i] = masterPack[i % DECK_SIZE];
         topCard++;
      }

   }

   /**
    * Purpose: Constructor to build multiple decks
    * Preconditions: None
    * Postconditions: Creates a multiple deck of cards
    *
    * @param numPacks The number of pack of cards
    */
   /* Fix: init() should validate numPacks. */
   public void init(int numPacks)
   {
      if (numPacks <= 6)
      {
         int i;
         topCard = 0;

         for (i = 0; i < numPacks * DECK_SIZE; i++)
         {
            cards[i] = masterPack[i % DECK_SIZE];
            topCard++;
         }
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
         int second = (int) (Math.random() * topCard);
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
      if (topCard > 0)
      {
         topCard--;
         Card tempCard = cards[topCard];
         return tempCard;
      }
      return new Card('-', Card.Suit.SPADES);
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
    * false if not valid
    *
    * @param k The value of the index position of card
    */
   public Card inspectCard(int k)
   {
      if (k > topCard)
      {
         return new Card('0', Card.Suit.SPADES);
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
      int masterPackIndex;

      String cardValues = "A23456789TJQKX";

      if (masterPack[0] == null)
      {
         for (masterPackIndex = 0; masterPackIndex < DECK_SIZE; masterPackIndex++)
         {
            if (masterPackIndex / NUM_OF_VALUES == 0)
            {
               masterPack[masterPackIndex] =
                     new Card(cardValues.charAt(masterPackIndex % NUM_OF_VALUES),
                           Card.Suit.SPADES);
            }
            if (masterPackIndex / NUM_OF_VALUES == 1)
            {
               masterPack[masterPackIndex] =
                     new Card(cardValues.charAt(masterPackIndex % NUM_OF_VALUES),
                           Card.Suit.CLUBS);
            }
            if (masterPackIndex / NUM_OF_VALUES == 2)
            {
               masterPack[masterPackIndex] =
                     new Card(cardValues.charAt(masterPackIndex % NUM_OF_VALUES),
                           Card.Suit.HEARTS);
            }
            if (masterPackIndex / NUM_OF_VALUES == 3)
            {
               masterPack[masterPackIndex] =
                     new Card(cardValues.charAt(masterPackIndex % NUM_OF_VALUES),
                           Card.Suit.DIAMONDS);
            }
         }
      }
   }

   /**
    * Get the number of cards in Deck
    *
    * @return Returns int that is the number of cards
    */
   public int getNumCards()
   {
      return topCard;
   }

   /**
    * Purpose: Adds a card to the deck and makes sure each card only has the
    * number of instances equal to or less than number of packs
    *
    * @param card The card to be inserted
    * @return Returns true if successfully added, false if not
    */
   public boolean addCard(Card card)
   {
      // The number of decks
      int deckNum = topCard / DECK_SIZE;

      // Keep track on the number of instances per card
      int cardInstances = 0;

      // If the card matches, it adds to the instance count
      for (int i = 0; i < topCard; i++)
      {
         if (card.equals(cards[i]))
         {
            cardInstances++;
         }
      }

      System.out.println("Card instances is: " + cardInstances);

      // If card instance is equal or more than the number of decks ,it fails.
      if (cardInstances >= deckNum)
      {
         System.out.println("Did not add card");
         return false;
      }
      System.out.println("Added the card to the deck");

      // Take added card and assign it to the top card.
      cards[topCard] = card;

      // Increase the topCard counter since we added a card
      topCard++;
      System.out.println("The topCard Value is: " + topCard);
      return true;
   }

   /**
    * Purpose: Removes a card for the Deck
    *
    * @param card Card to be removed
    * @return Returns true if successfully removed, false if not
    */
   public boolean removeCard(Card card)
   {
      // Traverses array of cards to see if card exists
      for (int cardsIndex = 0; cardsIndex < topCard; cardsIndex++)
      {
         // If card equals a card in deck, it removes it
         if (cards[cardsIndex].equals(card))
         {
            System.out.println("Removed Card Successfully");

            // Sets card to value of topCard
            cards[cardsIndex] = cards[topCard - 1];

            // Decrements topCard
            topCard--;
            return true;
         }
      }

      System.out.println("Did not remove card, none left");
      System.out.println(topCard);
      return false;
   }

   /**
    * Purpose: Sorts the array of cards in deck
    */
   public void sort()
   {
      Card.arraySort(cards, topCard);
   }

   /**
    * Purpose: String to display to console the deck of cards
    *
    * @return String that holds the cards in hand
    */
   public String toString()
   {
      String str;
      str = "Deck = ( ";
      for (int card = 0; card < getNumCards(); card++)
      {
         str += (cards[card].toString());
         if (card < getNumCards() - 1)
         {
            str += ", ";
         }
      }
      str += " )";

      System.out.println(str);
      return str;
   }
}
/*****************************************************************************
 *                        End of Deck                                        *
 *****************************************************************************/