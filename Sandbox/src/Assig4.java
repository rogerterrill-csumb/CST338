
// Title:               Optical Barcode Readers and Writers
// Files:               Assig4.java
// Semester:            Summer A, 2019
//
// Author:              Roger Terrill, George Blombach, Dalia Faria, Abby Packham, Carlos Orduna
// Email:               rchicasterrill@csumb.edu, gblombach@csumb.edu, dfaria@csumb.edu, apackham@csumb.edu, cordunacorrales@csumb.edu
// Lecturer's Name:     Jesse Cecil, M.S.
// TA's Name:           Joseph Appleton
// Lab Section:         CST 338

import java.lang.Math;

public class Assig4
{
   public static void main(String[] args)
   {

      String[] sImageIn =
            {
                  "                                               ",
                  "                                               ",
                  "                                               ",
                  "     * * * * * * * * * * * * * * * * * * * * * ",
                  "     *                                       * ",
                  "     ****** **** ****** ******* ** *** *****   ",
                  "     *     *    ****************************** ",
                  "     * **    * *        **  *    * * *   *     ",
                  "     *   *    *  *****    *   * *   *  **  *** ",
                  "     *  **     * *** **   **  *    **  ***  *  ",
                  "     ***  * **   **  *   ****    *  *  ** * ** ",
                  "     *****  ***  *  * *   ** ** **  *   * *    ",
                  "     ***************************************** ",
                  "                                               ",
                  "                                               ",
                  "                                               "

            };

      String[] sImageIn_2 =
            {
                  "                                          ",
                  "                                          ",
                  "* * * * * * * * * * * * * * * * * * *     ",
                  "*                                    *    ",
                  "**** *** **   ***** ****   *********      ",
                  "* ************ ************ **********    ",
                  "** *      *    *  * * *         * *       ",
                  "***   *  *           * **    *      **    ",
                  "* ** * *  *   * * * **  *   ***   ***     ",
                  "* *           **    *****  *   **   **    ",
                  "****  *  * *  * **  ** *   ** *  * *      ",
                  "**************************************    ",
                  "                                          ",
                  "                                          ",
                  "                                          ",
                  "                                          "

            };

      BarcodeImage bc = new BarcodeImage(sImageIn);
      DataMatrix dm = new DataMatrix(bc);

      // First secret message
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();

      // second secret message
      bc = new BarcodeImage(sImageIn_2);
      dm.scan(bc);
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();

      // create your own message
      dm.readText("What a great resume builder this is!");
      dm.displayImageToConsole();
      dm.generateImageFromText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
   }


}

// Phase 1 BarcodeIO interface
interface BarcodeIO
{
   //Takes Barcode object and saves it
   public boolean scan(BarcodeImage bc);

   //Takes String and saves it
   public boolean readText(String text);

   //Takes String and creates Barcode
   public boolean generateImageFromText();

   //Takes Barcode and creates String
   public boolean translateImageToText();

   //Displays String
   public void displayTextToConsole();

   //Displays Barcode
   public void displayImageToConsole();
}

// Phase 2 BarcodeImage class
class BarcodeImage implements Cloneable
{
   // DATA
   public static final int MAX_HEIGHT = 30;
   public static final int MAX_WIDTH = 65;
   private boolean[][] imageData;

   // METHODs

   /**
    * Purpose: Default Constructor -  instantiates a 2D array (MAX_HEIGHT x MAX_WIDTH) and stuffs it all with blanks (false).
    */
   public BarcodeImage()
   {
      imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
      int i, j;
      for (i = 0; i < MAX_HEIGHT; i++)
      {
         for (j = 0; j < MAX_WIDTH; j++)
         {
            imageData[i][j] = false;
         }
      }
   }

   /**
    * Purpose: takes a 1D array of Strings and converts it to the internal 2D array of booleans.
    *
    * @param strData String of data that gets converted to a BarcodeImage
    */
   public BarcodeImage(String[] strData)
   {
      if (checkSize(strData))
      {
         imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
         int i, j;

         for (i = 0; i < strData.length; i++)
         {
            for (j = 0; j < strData[i].length(); j++)
            {
               if (strData[i].charAt(j) == ' ')
                  setPixel(i, j, false);

               else if (strData[i].charAt(j) == '*')
                  setPixel(i, j, true);
            }
         }
      }
   }

   /**
    * Purpose: Gets the boolean value for specified pixel
    *
    * @param row The row of the image
    * @param col The column of the image
    * @return Returns boolean value.
    */
   public boolean getPixel(int row, int col)
   {
      if (imageData != null)
      {
         return imageData[row][col];
      }
      return false;
   }

   /**
    * Purpose: Sets the pixel value to true or false
    *
    * @param row   Row of the BarcodeImage object
    * @param col   Column of the BarcodeImage object
    * @param value The boolean value to change the pixel too
    * @return Returns boolean value, true for successfully changed, and false if not.
    */
   public boolean setPixel(int row, int col, boolean value)
   {
      if (row < MAX_HEIGHT && col < MAX_WIDTH)
      {
         imageData[row][col] = value;
         return true;
      }
      return false;
   }

   // OPTIONAL PRIVATE HELPER METHODS

   /**
    * Purpose: It does the job of checking the incoming data for every
    * conceivable size or null error.  Smaller is okay.  Bigger or null is not.
    *
    * @param data The string passed into the BarcodeImage.
    * @return Returns boolean of true if check passes and false if not.
    */
   private boolean checkSize(String[] data)
   {
      if (data != null)
         return data.length < MAX_HEIGHT && data[0].length() < MAX_WIDTH;
      return false;
   }

   /**
    * Purpose: Displays current BarcodeImage object to console
    */
   public void displayToConsole()
   {
      for (int i = 0; i < MAX_HEIGHT; i++)
      {
         // Going through each row
         for (int j = 0; j < MAX_WIDTH; j++)
         {
            // Knows that element i is also an iterable since it's a String
            if (imageData[i][j])
            {
               System.out.print('*');
            }
            else
            {
               System.out.print(' ');
            }
         }
         System.out.println();
      }
   }

   /**
    * Purpose: Overrides the method of that name in Cloneable interface.
    *
    * @return Returns the cloned object
    * @throws CloneNotSupportedException
    */
   @Override
   public Object clone() throws CloneNotSupportedException
   {
      BarcodeImage copy = (BarcodeImage) super.clone();
      copy.imageData = imageData.clone();
      for (int i = 0; i < MAX_HEIGHT; i++)
      {
         copy.imageData[i] = imageData[i].clone();
      }
      return copy;
   }
}

/**
 * Purpose: This class is a pseudo Datamatrix data structure, not a true Datamatrix,
 * because it does not contain any error correction or encoding.  However, it does have
 * the 2D array format and a left and bottom BLACK "spine" as well as an alternating right
 * and top BLACK-WHITE pattern as seen in the image.
 */
class DataMatrix implements BarcodeIO
{
   // DATA
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';

   /*  A single internal copy of any image scanned-in OR passed-into
     the constructor OR created by BarcodeIO's generateImageFromText().*/
   private BarcodeImage image;

   // A single internal copy of any text read-in OR passed-into
   // the constructor OR created by BarcodeIO's translateImageToText().
   private String text;

   //  Two ints that are typically less than BarcodeImage.MAX_WIDTH
   //  and BarcodeImage.MAX_HEIGHT which represent the actual portion
   //  of the BarcodeImage that has the real signal.  This is dependent
   //  on the data in the image, and can change as the image changes
   //  through mutators.  It can be computed from the "spine" of the image.
   private int actualWidth, actualHeight;

   // METHODS

   /**
    * Purpose: Constructs an empty, but non-null, image and text value.
    * The initial image should be all white, however, actualWidth
    * and actualHeight should start at 0, so it won't really matter
    * what's in this default image, in practice.  The text can be set
    * to blank, "", or something like "undefined".
    */
   public DataMatrix()
   {
      image = new BarcodeImage();
      actualHeight = 0;
      actualWidth = 0;
      text = "";
   }

   /**
    * Purpose: Constructor that sets the image but leaves the text at its default value.
    * Call scan() and avoid duplication of code here.
    *
    * @param image BarcodeImage object
    */
   public DataMatrix(BarcodeImage image)
   {
      if (image == null)
      {
         this.image = new BarcodeImage();
         text = "";
      }
//      this.image = image;
      scan(image);
      text = "";
   }

   /**
    * Purpose: Constructor sets the text but leaves the image at its
    * default value. Call readText() and avoid duplication of code here.
    *
    * @param text The String to be inserted.
    */
   public DataMatrix(String text)
   {
      if (!readText(text))
      {
         this.text = "";
      }
      image = new BarcodeImage();
      actualHeight = 0;
      actualWidth = 0;
   }

   /**
    * Purpose: a mutator for text.  Like the constructor;  in fact it is called by the constructor.
    *
    * @param text The text of the String inserted
    * @return Returns true if string is valid and false otherwise
    */
   public boolean readText(String text)
   {
      if (text == null || (text.length() > BarcodeImage.MAX_WIDTH - 2))
      {
         return false;
      }
      this.text = text;
      return true;
   }


   /**
    * Purpose: a mutator for image.  Like the constructor;  in fact it is
    * called by the constructor.  Besides calling the clone() method
    * of the BarcodeImage class, this method will do a couple of things
    * including calling cleanImage() and then set the actualWidth and actualHeight.
    * Because scan() calls clone(), it should deal with the CloneNotSupportedException
    * by embeddingthe clone() call within a try/catch block.  Don't attempt to hand-off
    * the exception using a "throws" clause in the function header since that will not
    * be compatible with the underlying BarcodeIO interface.  The catches(...) clause
    * can have an empty body that does nothing.
    *
    * @param image a BarcodeImage object
    * @return Returns true if successfully cloned and false if not.
    */
   public boolean scan(BarcodeImage image)
   {
      try
      {
         this.image = (BarcodeImage) image.clone();
      }
      catch (CloneNotSupportedException e)
      {
         return false;
      }

      cleanImage();
      actualHeight = computeSignalHeight();
      actualWidth = computeSignalWidth();

      return true;
   }

   /**
    * Purpose: Would be called from within scan() and would move the signal to the lower-left of the larger 2D array
    */
   private void cleanImage()
   {
      moveImageToLowerLeft();
   }

   /**
    * Purpose: Determines how far from the bottom the bottom spine is by checking the first true bottom element.
    *
    * @return Returns int of offset
    */
   private int findHeightOffsetFromBottom()
   {
      int i, j;

      for (i = BarcodeImage.MAX_HEIGHT - 1; i > 0; i--)
      {
         for (j = 0; j < BarcodeImage.MAX_WIDTH; j++)
         {
            if (image.getPixel(i, j))
            {
               return BarcodeImage.MAX_HEIGHT - i - 1;
            }
         }
      }
      return -1;
   }

   /**
    * Purpose: Determines how far from the left the left spine is by checking the first true left element.
    *
    * @return Returns int of left offset
    */
   private int findWidthOffsetFromLeft()
   {
      int i, j;

      for (i = 0; i < BarcodeImage.MAX_HEIGHT; i++)
      {
         for (j = 0; j < BarcodeImage.MAX_WIDTH; j++)
         {
            if (image.getPixel(i, j))
            {
               return j;
            }
         }
      }
      return -1;
   }

   /**
    * Purpose: Moves the image to lower left
    */
   private void moveImageToLowerLeft()
   {
      int i, j;
      int widthOffset = findWidthOffsetFromLeft();
      int heightOffset = findHeightOffsetFromBottom();

      for (i = BarcodeImage.MAX_HEIGHT - 1; i > heightOffset; i--)
      {
         for (j = 0; j < BarcodeImage.MAX_WIDTH - widthOffset; j++)
         {
            // Copies pixel from first valid position to new position starting at bottom left
            image.setPixel(i, j, image.getPixel(i - heightOffset, j + widthOffset));

            // Changes old position to false
            image.setPixel(i - heightOffset, j + widthOffset, false);
         }
      }
   }

   /**
    * Purpose: Accessor for actualWidth
    *
    * @return Returns the int of actual width
    */
   public int actualWidth()
   {
      return actualWidth;
   }

   /**
    * Purpose: Accessor for actualHeight
    *
    * @return Returns the in of actual height
    */
   public int actualHeight()
   {
      return actualHeight;
   }

   /**
    * Purpose: Determines the the width of the signal by counting the bottom spine
    *
    * @return Returns an int of the width calculated
    */
   private int computeSignalWidth()
   {
      int i;
      int width = 0;
      for (i = 0; i < BarcodeImage.MAX_WIDTH; i++)
      {
         if (image.getPixel(BarcodeImage.MAX_HEIGHT - 1, i))
         {
            width++;
         }
      }
      return width;
   }

   /**
    * Purpose: Determines the height of the signal by counting the left spine
    *
    * @return Returns an int of the height calculated
    */
   private int computeSignalHeight()
   {
      int i, j;
      int height = 0;
      for (i = BarcodeImage.MAX_HEIGHT - 1; i > 0; i--)
      {
         if (image.getPixel(i, 0))
         {
            height++;
         }
      }
      return height;
   }

   /**
    * Purpose: Displays to the console the image of the signal only.
    */
   public void displayImageToConsole()
   {
      int startHeight = BarcodeImage.MAX_HEIGHT - actualHeight;
      for (int j = 0; j < actualWidth + 2; j++)
      {
         System.out.print('-');
      }
      System.out.println();
      for (int i = startHeight; i < BarcodeImage.MAX_HEIGHT; i++)
      {
         System.out.print('|');
         // Going through each row
         for (int j = 0; j < actualWidth; j++)
         {
            // Knows that element i is also an iterable since it's a String
            if (image.getPixel(i, j))
            {
               System.out.print(BLACK_CHAR);
            }
            else
            {
               System.out.print(WHITE_CHAR);
            }
         }
         System.out.println('|');
      }
   }

   /**
    * Purpose: Displays to the console the image of the entire canvas of 30x65
    */
   public void displayRawImage()
   {
      for (int i = 0; i < BarcodeImage.MAX_HEIGHT; i++)
      {
         // Going through each row
         for (int j = 0; j < BarcodeImage.MAX_WIDTH; j++)
         {
            // Knows that element i is also an iterable since it's a String
            if (image.getPixel(i, j))
            {
               System.out.print(BLACK_CHAR);
            }
            else
            {
               System.out.print(WHITE_CHAR);
            }
         }
         System.out.println();
      }
   }

   /**
    * Purpose: Converts the image to the text
    *
    * @return Returns true for a successful conversion
    */
   public boolean translateImageToText()
   {
      String text = "";
      for (int col = 1; col < actualWidth - 1; col++)
      {
         text += readCharFromCol(col);
      }

      this.text = text;
      return true;
   }

   /**
    * Purpose: Checks the specified column and adds the ints of each designated position
    *
    * @param col The int of the column to check
    * @return Returns the char after it is cast from an int
    */
   private char readCharFromCol(int col)
   {
      int value = 0;
      int power = 0;
      for (int row = BarcodeImage.MAX_HEIGHT - 2; row > BarcodeImage.MAX_HEIGHT - actualHeight; row--)
      {
         if (image.getPixel(row, col))
         {
            value += Math.pow(2, power);
         }
         power++;
      }

      return (char) value;
   }

   /**
    * Purpose: Writes the char to the image per designated column.
    *
    * @param col  The column to insert the char into
    * @param code The char to be inserted into column
    * @return Returns true if successfully passed in, false if not.
    */
   private boolean WriteCharToCol(int col, int code)
   {
      int power = 7;
      int values = 0;
      int startingRow = 21;

      if (code > 0 && code < 255)
      {
         for (int i = startingRow; i < BarcodeImage.MAX_HEIGHT; i++)
         {
            values = (int) Math.pow(2, power);

            if ((int) Math.pow(2, power) <= code)
            {
               image.setPixel(i, col, true);
               code -= values;
            }
            else
            {
               image.setPixel(i, col, false);
            }
            power--;
         }
         return true;
      }
      return false;
   }

   /**
    * Purpose: Reads the text string and converts it to image.
    *
    * @return Returns true if successfully converted and false otherwise.
    */
   public boolean generateImageFromText()
   {
      clearImage();
      int length = text.length();
      actualWidth = length + 2; //The 2 is for the left spine and right broken spine

      for (int i = BarcodeImage.MAX_HEIGHT - 1; i > BarcodeImage.MAX_HEIGHT - 11; i--)
      {
         image.setPixel(i, 0, true);
         for (int j = 0; j < actualWidth; j++)
         {
            if (i == 20 && j % 2 == 0)
            {
               image.setPixel(i, j, true);
            }
            if ((i % 2) == 1 && j == actualWidth - 1)
            {
               image.setPixel(i, j, true);
            }
         }
      }

      for (int col = 0; col < length; col++)
      {
         WriteCharToCol(col + 1, (int) text.charAt(col));
      }
      return true;
   }

   /**
    * Purpose: Displays the text to the console
    */
   public void displayTextToConsole()
   {
      System.out.println(text);
   }

   /**
    * Purpose: Makes everything false in the full canvas to clear it.
    */
   private void clearImage()
   {
      for (int row = 0; row < BarcodeImage.MAX_HEIGHT - 1; row++)
         for (int col = 0; col < BarcodeImage.MAX_WIDTH - 1; col++)
            image.setPixel(row, col, false);
   }

}


/***************** Output *******************
 CSUMB CSIT online program is top notch.
 -------------------------------------------
 |* * * * * * * * * * * * * * * * * * * * *|
 |*                                       *|
 |****** **** ****** ******* ** *** *****  |
 |*     *    ******************************|
 |* **    * *        **  *    * * *   *    |
 |*   *    *  *****    *   * *   *  **  ***|
 |*  **     * *** **   **  *    **  ***  * |
 |***  * **   **  *   ****    *  *  ** * **|
 |*****  ***  *  * *   ** ** **  *   * *   |
 |*****************************************|
 You did it!  Great work.  Celebrate.
 ----------------------------------------
 |* * * * * * * * * * * * * * * * * * * |
 |*                                    *|
 |**** *** **   ***** ****   *********  |
 |* ************ ************ **********|
 |** *      *    *  * * *         * *   |
 |***   *  *           * **    *      **|
 |* ** * *  *   * * * **  *   ***   *** |
 |* *           **    *****  *   **   **|
 |****  *  * *  * **  ** *   ** *  * *  |
 |**************************************|
 ----------------------------------------
 |* * * * * * * * * * * * * * * * * * * |
 |*                                    *|
 |**** *** **   ***** ****   *********  |
 |* ************ ************ **********|
 |** *      *    *  * * *         * *   |
 |***   *  *           * **    *      **|
 |* ** * *  *   * * * **  *   ***   *** |
 |* *           **    *****  *   **   **|
 |****  *  * *  * **  ** *   ** *  * *  |
 |**************************************|
 What a great resume builder this is!
 ----------------------------------------
 |* * * * * * * * * * * * * * * * * * * |
 |*                                    *|
 |***** * ***** ****** ******* **** **  |
 |* ************************************|
 |**  *    *  * * **    *    * *  *  *  |
 |* *               *    **     **  *  *|
 |**  *   * * *  * ***  * ***  *        |
 |**      **    * *    *     *    *  * *|
 |** *  * * **   *****  **  *    ** *** |
 |**************************************|
 ****************************************/