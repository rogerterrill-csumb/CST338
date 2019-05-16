public class Assig3
{

<<<<<<< HEAD
   public static void main(String[] args) {
      Card one = new Card();
      one.toString();
=======
   public static void main(String[] args)
   {
      Card one = new Card();
      System.out.print(one.toString());
>>>>>>> 7e4f2555711f2f28a1ac0c75ebfda3be0169551a
   }


}

class Card
{
<<<<<<< HEAD
   enum Suit{ clubs, diamonds, hearts, spades };
   char value;
   Suit suit;
   boolean errorFlag;
=======
   enum Suit {clubs, diamonds, hearts, spades}

   private char value;
   private Suit suit;
   private boolean errorFlag;
>>>>>>> 7e4f2555711f2f28a1ac0c75ebfda3be0169551a

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

<<<<<<< HEAD
   public boolean set(char value, Suit suit)
   {

      return true;
   }


   public String toString()
   {
      String str = "";
      System.out.println(value + " of " + suit);
      return str;
=======
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
>>>>>>> 7e4f2555711f2f28a1ac0c75ebfda3be0169551a
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
