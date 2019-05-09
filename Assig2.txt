// Title:               Casino
// Files:               Assig2.java
// Semester:            Summer A, 2019
//
// Author:              Roger Terrill
// Email:               rchicasterrill@csumb.edu
// Lecturer's Name:     Jesse Cecil, M.S.
// TA's Name:           Joseph Appleton
// Lab Section:         CST 338

import java.util.Scanner;

public class Assig2
{

   public static int getBet()
   {
      int bet = 0;
      Scanner keyboard = new Scanner(System.in);
      do
      {
         System.out.print("How much would you like to bet (1 - 100) or 0 to quit? ");
         bet = keyboard.nextInt();
      }
      while (bet < 0 || bet > 100);
      return bet;
   }

   private static String randString()
   {
      int randomNum = (int) (Math.random() * 1000) + 1;
      String str = "";
      if (randomNum > 0 && randomNum <= 500)
      {
         str = "BAR";
      }
      else if (randomNum > 500 && randomNum <= 750)
      {
         str = "cherries";
      }
      else if (randomNum > 750 && randomNum <= 875)
      {
         str = "(space)";
      }
      else if (randomNum > 875 && randomNum <= 1000)
      {
         str = "7";
      }
      return str;
   }

   public static TripleString pull()
   {
      TripleString slotPull = new TripleString();
      slotPull.setString1(randString());
      slotPull.setString2(randString());
      slotPull.setString3(randString());
      return slotPull;
   }

   public static int getPayMultiplier(TripleString thePull)
   {
      if ((thePull.getString1() == "cherries") && (thePull.getString2() != "cherries"))
      {
         return 5;
      }
      else if ((thePull.getString1() == "cherries") && (thePull.getString2() == "cherries") && (thePull.getString3() != "cherries"))
      {
         return 15;
      }
      else if ((thePull.getString1() == "cherries") && (thePull.getString2() == "cherries") && (thePull.getString3() == "cherries"))
      {
         return 30;
      }
      else if ((thePull.getString1() == "BAR") && (thePull.getString2() == "BAR") && (thePull.getString3() == "BAR"))
      {
         return 50;
      }
      else if ((thePull.getString1() == "7") && (thePull.getString2() == "7") && (thePull.getString3() == "7"))
      {
         return 100;
      }
      else
      {
         return 0;
      }
   }

   public static void display(TripleString thePull, int winnings)
   {
      System.out.println("whirrrrrr .... and your pull is ...");
      System.out.println(thePull.toString());

      if (winnings == 0)
      {
         System.out.println("sorry, you lose.");
         thePull.saveWinnings(winnings);
         System.out.println();
      }
      else
      {
         System.out.println("congratulations, you win: " + winnings);
         thePull.saveWinnings(winnings);
         System.out.println();
      }

   }

   // Main function
   public static void main(String[] args)
   {
      int bet, winnings;
      do
      {
         bet = getBet();
         TripleString pullString = pull();
         winnings = getPayMultiplier(pullString) * bet;
         if (bet == 0)
         {
            System.out.print(pullString.displayWinnings());
         }
         else
         {
            display(pullString, winnings);
         }

      }
      while (bet != 0);

   }
}

class TripleString
{
   // The data to be used to keep track of the game
   public static final int MAX_PULLS = 40;
   public static final int MAX_LEN = 20;

   private String string1, string2, string3;
   private static int[] pullWinnings = new int[MAX_PULLS];
   private static int numPulls = 0;

   // Default Constructor
   public TripleString()
   {
      string1 = "";
      string2 = "";
      string3 = "";
   }

   // A Private Helper Method
   private boolean validString(String str)
   {
      return str != null && str.length() <= MAX_LEN;
   }

   // Mutators and Accessors
   public String getString1()
   {
      return string1;
   }

   public boolean setString1(String string1)
   {
      if (validString(string1))
      {
         this.string1 = string1;
         return true;
      }

      return false;
   }

   public String getString2()
   {
      return string2;
   }

   public boolean setString2(String string2)
   {
      if (validString(string2))
      {
         this.string2 = string2;
         return true;
      }

      return false;
   }

   public String getString3()
   {
      return string3;
   }

   public boolean setString3(String string3)
   {
      if (validString(string3))
      {
         this.string3 = string3;
         return true;
      }

      return false;
   }

   // Returns all three strings as one string
   public String toString()
   {
      return string1 + " " + string2 + " " + string3;
   }

   // pullWinnings array methods
   public boolean saveWinnings(int winnings)
   {
      if(numPulls <= MAX_PULLS)
      {
         pullWinnings[numPulls] = winnings;
         numPulls++;
         return true;
      }
      return false;

   }

   public String displayWinnings()
   {
      int winnings = 0;
      String topString = "Thanks for playing at the Casino!\nYour individual winnings were:\n";
      String individualWinnings = "";
      String bottomString = "";
      String finalWinnings = "";

      for (int index = 0; index < numPulls; index++)
      {
         winnings += pullWinnings[index];
         individualWinnings += pullWinnings[index] + " ";
      }
      bottomString = "\nYour total winnings were: $" + winnings + "\n";

      finalWinnings = topString + individualWinnings + bottomString;
      return finalWinnings;

   }
}

/***************************Output*********************************
 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 (space) cherries BAR
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 55
 whirrrrrr .... and your pull is ...
 BAR BAR 7
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 555
 How much would you like to bet (1 - 100) or 0 to quit? 555
 How much would you like to bet (1 - 100) or 0 to quit? -2
 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR cherries 7
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR (space) BAR
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR 7 (space)
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 7 cherries BAR
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 7 BAR cherries
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR BAR BAR
 congratulations, you win: 250

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 cherries BAR cherries
 congratulations, you win: 25

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR BAR BAR
 congratulations, you win: 250

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR BAR cherries
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR BAR cherries
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR 7 (space)
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR cherries BAR
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR cherries BAR
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR BAR BAR
 congratulations, you win: 250

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 cherries cherries 7
 congratulations, you win: 75

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 cherries cherries 7
 congratulations, you win: 75

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR (space) (space)
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 (space) cherries cherries
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR (space) BAR
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 cherries BAR cherries
 congratulations, you win: 25

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR 7 BAR
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR 7 7
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 cherries (space) (space)
 congratulations, you win: 25

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR 7 BAR
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR cherries BAR
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 7 BAR BAR
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 7 7 cherries
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 cherries BAR BAR
 congratulations, you win: 25

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 cherries (space) 7
 congratulations, you win: 25

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 (space) BAR cherries
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR cherries BAR
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR cherries BAR
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 7 BAR cherries
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR 7 BAR
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 BAR 7 (space)
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 cherries BAR BAR
 congratulations, you win: 25

 How much would you like to bet (1 - 100) or 0 to quit? 5
 whirrrrrr .... and your pull is ...
 (space) BAR BAR
 sorry, you lose.

 How much would you like to bet (1 - 100) or 0 to quit? 0
 Thanks for playing at the Casino!
 Your individual winnings were:
 0 0 0 0 0 0 0 250 25 250 0 0 0 0 0 250 75 75 0 0 0 25 0 0 25 0 0 0 0 25 25 0 0 0 0 0 0 25 0
 Your total winnings were: $1050
 ******************************************************************************************/