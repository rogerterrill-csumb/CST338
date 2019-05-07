// Title:               Main
// Files:               Main.class
// Semester:            Summer A, 2019
//
// Author:              Roger Terrill
// Email:               rchicasterrill@csumb.edu
// Lecturer's Name:     Jesse Cecil, M.S.
// TA's Name:           Joseph Appleton
// Lab Section:         CST 338

import java.util.Scanner;
import java.text.DecimalFormat;

public class Main
{
   public static final int MIN_HOURS = 12;
   public static final int MAX_HOURS = 20;

   public static void main(String[] args)
   {

      // Create an Scanner object
      Scanner keyboard = new Scanner(System.in);

      DecimalFormat formattingObject = new DecimalFormat("00.0");

      // Prompt user to enter first and last name
      System.out.println("Please enter your first and last name and");
      System.out.println("please capitalize the first letter for both.");

      // Assign both to specific variables
      String firstName = keyboard.next();
      String lastName = keyboard.next();

      // Concatenate first and last name
      String fullName = firstName + " " + lastName;

      // Return the length of the concatenated string
      int fullNameLength = fullName.length();

      // Display concatenated full name
      System.out.println("Your full name is: " + fullName + " and is " + fullNameLength + " characters long.");

      // Display capitalized full name
      System.out.println("Your full name capitalized: " + fullName.toUpperCase());

      // Display lowercased full name
      System.out.println("Your full name lowercase: " + fullName.toLowerCase());

      // Display prompt asking user how many hours they have spent this week
      System.out.println("Hi " + fullName + ", how many hours have you spent on class this week? ");
      System.out.println("The values should be between " + MIN_HOURS + " and " + MAX_HOURS);
      System.out.println("Also use 3 decimal places as well.");

      // Assign hours to variable
      double hoursSpentOnClass = keyboard.nextDouble();

      // Display the hours entered to one decimal place
      System.out.println("You have spent approximately " + formattingObject.format(hoursSpentOnClass) + " hours this week.");
   }
}

/***************************Output*********************************
Please enter your first and last name and
please capitalize the first letter for both.
Roger Terrill
Your full name is: Roger Terrill and is 13 characters long.
Your full name capitalized: ROGER TERRILL
Your full name lowercase: roger terrill
Hi Roger Terrill, how many hours have you spent on class this week?
The values should be between 12 and 20
Also use 3 decimal places as well.
15.234
You have spent approximately 15.2 hours this week.


Please enter your first and last name and
please capitalize the first letter for both.
Peter Parker
Your full name is: Peter Parker and is 12 characters long.
Your full name capitalized: PETER PARKER
Your full name lowercase: peter parker
Hi Peter Parker, how many hours have you spent on class this week?
The values should be between 12 and 20
Also use 3 decimal places as well.
12.001
You have spent approximately 12.0 hours this week.

 *******************************************************************/


