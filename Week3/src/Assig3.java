public class Assig3
{
   public static void main(String[] args)
   {
      Card one = new Card('A', Card.Suit.spades);
      Card two = new Card('N', Card.Suit.spades);
      Card three = new Card('J', Card.Suit.clubs);

      System.out.print(one.toString());
      System.out.print(two.toString());
      System.out.print(three.toString());

      one.set('B', Card.Suit.spades);
      two.set('Q', Card.Suit.spades);

      System.out.println();
      System.out.print(one.toString());
      System.out.print(two.toString());
      System.out.print(three.toString());
   }
}

class Card
{
   public enum Suit {clubs, diamonds, hearts, spades}

   private char value;
   private Suit suit;
   private boolean errorFlag;

   public Card()
   {
      value = 'A';
      suit = Suit.spades;
   }

   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   public String toString()
   {
      if(errorFlag)
      {
         return "** illegal **\n";
      }
      return value + " of " + suit + "\n" ;
   }

   public boolean set(char value, Suit suit)
   {
      if(isValid(value, suit))
      {
         this.value = value;
         this.suit = suit;
         errorFlag = false;
         return true;
      }
      errorFlag = true;
      return false;
   }

   public char getValue()
   {
      return value;
   }

   public Suit getSuit()
   {
      return suit;
   }

   public boolean isErrorFlag()
   {
      return errorFlag;
   }

   public boolean equals(Card card)
   {

      return (value == card.value && suit == card.suit);
   }

   private boolean isValid(char value, Suit suit)
   {
      int i;
      char[] cardValues = {'A','2','3','4','5','6','7','8','9','T','J','Q','K'};
      for(i = 0; i < cardValues.length; i++)
      {
         if(value == cardValues[i])
         {
            return true;
         }
      }
      return false;
   }
}
