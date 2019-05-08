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
//         while(!keyboard.hasNextInt())
//         {
//            System.out.println("adsf");
//            keyboard.next();
//         }
         bet = keyboard.nextInt();
      }while (bet < 0 || bet > 100);
      System.out.println(bet);
      return bet;
   }

   public static TripleString pull()
   {
      System.out.println("Get TripleString");
      TripleString slot = new TripleString();
      return slot;
   }

   public static void display (TripleString thePull, int winnings )
   {
      System.out.println("The Pull " + thePull + " Winnings " + winnings);
   }

   // Main function
   public static void main(String[] args)
   {
      int bet = 0;
      bet = getBet();
      int slotInt;
      TripleString slot = new TripleString();
   }
}

class TripleString
{
   // The data to be used to keep track of the game
   public static final int MAX_PULLS = 40;
   public static final int MAX_LEN = 20;

   private String string1, string2, string3;
   private static int[] pullWinnings  = new int[MAX_PULLS];
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
      if (validString(string1)) {
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
      if (validString(string2)) {
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
      if (validString(string3)) {
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
      pullWinnings[numPulls] = winnings;
      return true;
   }

   public String displayWinnings()
   {
      int winnings = 0;
      String topString = "Thanks for playing at the Casino!\nYour individual winnings were:\n";
      String individualWinnings = "";
      String bottomString = "";
      String finalWinnings= "";

      for(int index = 0; index < numPulls; index++)
      {
         winnings += pullWinnings[index];
         individualWinnings += pullWinnings[index] + " ";
      }
      bottomString = "\nYour total winnings were: $" + winnings + "\n";

      finalWinnings = topString + individualWinnings + bottomString;
      numPulls++;
      return finalWinnings;

   }
}

