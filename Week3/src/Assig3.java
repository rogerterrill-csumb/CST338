public class Assig3
{

   public static void main(String[] args) {
      Card one = new Card();
      one.toString();
   }


}

class Card
{
   enum Suit{ clubs, diamonds, hearts, spades };
   char value;
   Suit suit;
   boolean errorFlag;

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

   public boolean set(char value, Suit suit)
   {

      return true;
   }


   public String toString()
   {
      String str = "";
      System.out.println(value + " of " + suit);
      return str;
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

      return true;
   }
}
