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
      System.out.println("Get Bet");
      return 1;
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

   public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      int slotInt;
      TripleString slot = new TripleString();
      slot.setString1("One");
      slot.setString2("Two");
      slot.setString3("Three");
      do{
         System.out.println("How much would you like to bet (1 - 100) or 0 to quit?");
         slotInt = keyboard.nextInt();
         slot.saveWinnings(slotInt);
         slot.displayWinnings();
      } while (slotInt != 0);

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

   public void displayWinnings()
   {
      int winnings = 0;

      System.out.println("Thanks for playing at the Casino!");
      System.out.println("Your individual winnings were:");
      for(int index = 0; index < numPulls; index++)
      {
         winnings += pullWinnings[index];
         System.out.print(pullWinnings[index] + " ");
      }
      System.out.println();
      System.out.println("Your total winnings were: $" + winnings);
      numPulls++;
   }
}

