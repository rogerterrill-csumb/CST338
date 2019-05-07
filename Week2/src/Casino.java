// Title:               Casino
// Files:               Casino.java
// Semester:            Summer A, 2019
//
// Author:              Roger Terrill
// Email:               rchicasterrill@csumb.edu
// Lecturer's Name:     Jesse Cecil, M.S.
// TA's Name:           Joseph Appleton
// Lab Section:         CST 338

import java.util.Scanner;

public class Casino
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
      System.out.println("How much would you like to bet (1 - 100) or 0 to quit?");
      String slotString = keyboard.next();
      TripleString slot = new TripleString();
      slot.setString1(slotString);
      System.out.println(slot.getString1());
   }
}

class TripleString
{
   private String string1, string2, string3;
   private static int[] pullWinnings;
   private static int numPulls;

   public static final int MAX_PULLS = 40;
   public static final int MAX_LEN = 20;

   public TripleString()
   {
      string1 = "";
      string2 = "";
      string3 = "";
   }

   private boolean validString(String str)
   {
      return str != null && str.length() <= MAX_LEN;
   }

   public void writeString()
   {
      System.out.println("New Class is connected.");
   }

   public static void writeStaticString()
   {
      System.out.println("New Static Class is connected.");
   }

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
}

