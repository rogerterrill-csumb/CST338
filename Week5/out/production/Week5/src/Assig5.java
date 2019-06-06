/*
 * Title:               GUI Cards Phase 1
 * Files:               Assig5.java
 * Semester:            Summer A, 2019
 * Date:                June 3, 2019
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


import javax.swing.*;
import java.awt.*;

public class Assig5
{
   // static for the 57 icons and their corresponding labels
   // normally we would not have a separate label for each card, but
   // if we want to display all at once using labels, we need to.

   static final int NUM_CARD_IMAGES = 57; // 52 + 4 jokers + 1 back-of-card image
   static Icon[] icon = new ImageIcon[NUM_CARD_IMAGES];

   static void loadCardIcons()
   {
      // build the file names ("AC.gif", "2C.gif", "3C.gif", "TC.gif", etc.)
      // in a SHORT loop.  For each file name, read it in and use it to
      // instantiate each of the 57 Icons in the icon[] array.

      // String to save the concatenated file name
      String str = "";

      int card = 0;

      // Directory prefix to take into account different IDEs
      // Might need to change prefix to something else ie "images/"
      String directoryPrefix = "images\\";


      // Loop that concatenates strings to create filename corresponding
      // to the images directory
      for (int suit = 0; suit < 4; suit++)
      {
         for (int value = 0; value < 14; value++)
         {
            // Concatenated string representing the filename of the card
            str = directoryPrefix +
                    turnIntIntoCardValue(value) +
                    turnIntIntoCardSuit(suit) + ".gif";
            // Create ImageIcon per card
            icon[card] = new ImageIcon(str);
            card++;
         }
      }

      // Checks for last card and sets it to "BK.gif" and then breaks out of loop.
      str = directoryPrefix + "BK.gif";
      icon[56] = new ImageIcon(str);
   }

   // turns 0 - 13 into "A", "2", "3", ... "Q", "K", "X"
   static String turnIntIntoCardValue(int k)
   {
      // an idea for a helper method (do it differently if you wish)
      String[] cardValues = {"A", "2", "3", "4", "5", "6",
              "7", "8", "9", "T", "J", "Q", "K", "X"};

      // Checks to see if k is outside the range of cardValues index
      if (k < 0 || k > cardValues.length - 1)
      {
         return "Invalid Value";
      }
      return cardValues[k];
   }

   // turns 0 - 3 into "C", "D", "H", "S"
   static String turnIntIntoCardSuit(int j)
   {
      // an idea for another helper method (do it differently if you wish)
      String[] cardSuits = {"C", "D", "H", "S"};

      // Check to see if j outside the range of cardSuits
      if (j < 0 || j > cardSuits.length - 1)
      {
         return "Invalid Suit";
      }
      return cardSuits[j];
   }

   // a simple main to throw all the JLabels out there for the world to see
   public static void main(String[] args)
   {
      int k;

      // prepare the image icon array
      loadCardIcons();

      // establish main frame in which program will run
      JFrame frmMyWindow = new JFrame("Card Room");
      frmMyWindow.setSize(1150, 650);
      frmMyWindow.setLocationRelativeTo(null);
      frmMyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // set up layout which will control placement of buttons, etc.
      FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 20);
      frmMyWindow.setLayout(layout);

      // prepare the image label array
      JLabel[] labels = new JLabel[NUM_CARD_IMAGES];
      for (k = 0; k < NUM_CARD_IMAGES; k++)
         labels[k] = new JLabel(icon[k]);

      // place your 3 controls into frame
      for (k = 0; k < NUM_CARD_IMAGES; k++)
         frmMyWindow.add(labels[k]);

      // show everything to the user
      frmMyWindow.setVisible(true);
   }
}
