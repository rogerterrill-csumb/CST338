public class Assig3
{
   public static void main(String[] args)
   {
      Card one = new Card();
      System.out.print(one.toString());
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
      this.value = value;
      this.suit = suit;
   }

   public String toString()
   {
      if(errorFlag == true)
      {
         return "[invalid]";
      }
      return value + " of " + suit ;
   }

   public boolean set(char value, Suit suit)
   {

      return true;
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
      return true;
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
         return false;
      }
      return true;
   }
}
