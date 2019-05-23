
// Title:               Optical Barcode Readers and Writers
// Files:               Assig4.java
// Semester:            Summer A, 2019
//
// Author:              Roger Terrill
// Email:               rchicasterrill@csumb.edu
// Lecturer's Name:     Jesse Cecil, M.S.
// TA's Name:           Joseph Appleton
// Lab Section:         CST 338

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

      dm.scan(bc);

      dm.displayRawImage();
//
//      bc.displayToConsole();
//
//      // First secret message
//      dm.translateImageToText();
//      dm.displayTextToConsole();
//      dm.displayImageToConsole();
//
//      // second secret message
//      bc = new BarcodeImage(sImageIn_2);
//      dm.scan(bc);
//      dm.translateImageToText();
//      dm.displayTextToConsole();
//      dm.displayImageToConsole();
//
//      // create your own message
//      dm.readText("What a great resume builder this is!");
//      dm.generateImageFromText();
//      dm.displayTextToConsole();
//      dm.displayImageToConsole();
   }


}

// Phase 1 BarcodeIO interface
interface BarcodeIO
{
   public boolean scan(BarcodeImage bc);

   public boolean readText(String text);

//   public boolean generateImageFromText();
//
//   public boolean translateImageToText();
//
//   public void displayTextToConsole();
//
//   public void displayImageToConsole();
}

// Phase 2 BarcodeImage class
class BarcodeImage implements Cloneable
{
   // DATA
   public static final int MAX_HEIGHT = 30;
   public static final int MAX_WIDTH = 65;
   private boolean[][] imageData;

   // METHOD
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

   public BarcodeImage(String[] strData)
   {
      if (checkSize(strData))
      {
         imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
         int i, j, position;
         int startRow = MAX_HEIGHT - strData.length;

//         for (i = 0; i < MAX_HEIGHT; i++)
//         {
//
//            for (j = 0; j < MAX_WIDTH; j++)
//            {
//               imageData[i][j] = false;
//            }
//         }

         for (i = startRow; i < MAX_HEIGHT; i++)
         {
            // This position variable is important because we want to start at the top of the strData array
            // Despite being at the bottom of the imageData[][]
            position = i - startRow;
            for (j = 0; j < strData[position].length(); j++)
            {
               // If the first element at a particular String index
               if (strData[position].charAt(j) == ' ')
                  setPixel(i, j, false);

               else if (strData[position].charAt(j) == '*')
                  setPixel(i, j, true);
            }
         }
      }
   }

   public boolean getPixel(int row, int col)
   {
      try
      {
         return imageData[row][col];
      }
      catch (Error e)
      {
         return false;
      }
   }

   public boolean setPixel(int row, int col, boolean value)
   {
      if (row < MAX_WIDTH && col < MAX_HEIGHT)
      {
         imageData[row][col] = value;
         return true;
      }
      return false;
   }

   // OPTIONAL PRIVATE HELPER METHODS
   private boolean checkSize(String[] data)
   {
      if (data != null)
         return data.length < MAX_HEIGHT && data[0].length() < MAX_WIDTH;
      return false;
   }

   public void displayToConsole()
   {
      for (boolean[] i : imageData)
      {
         // Going through each row
         for (boolean j : i)
         {
            // Knows that element i is also an iterable since it's a String
            if (j)
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

   @Override
   public Object clone()
   {
      try
      {
         return (BarcodeImage) super.clone();
      }
      catch (CloneNotSupportedException e)
      {
         return null;
      }

   }
}

class DataMatrix implements BarcodeIO
{
   // DATA
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';
   private BarcodeImage image;
   private String text;
   private int actualWidth, actualHeight;

   // METHODS
   public DataMatrix()
   {
      image = new BarcodeImage();
      actualHeight = 0;
      actualWidth = 0;
      text = "";
   }

   public DataMatrix(BarcodeImage image)
   {
      this.image = image;
      text = "";
   }

   public DataMatrix(String text)
   {
      this.text = text;
      image = new BarcodeImage();
      actualHeight = 0;
      actualWidth = 0;
   }

   public boolean readText(String text)
   {
      if (text == null)
      {
         return false;
      }
      this.text = text;
      return true;
   }

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
      actualHeight = 0;
      actualWidth = 0;
      return true;
   }

   public int actualWidth()
   {
      return actualWidth;
   }

   public int actualHeight()
   {
      return actualHeight;
   }

   private int computeSignalWidth()
   {
      return 0;
   }

   private int computeSignalHeight()
   {
      return 0;
   }

   private void cleanImage()
   {
      int i,j;

      for(i = 0; i < BarcodeImage.MAX_HEIGHT; i++ )
      {
         for(j = 0; j < BarcodeImage.MAX_WIDTH; j++)
         {
            if(image.getPixel(i,j))
            {
               System.out.print("*");
            }
            else
            {
               System.out.print(" ");
            }
         }
         System.out.println();
      }
      System.out.println(image.getPixel(27,10));
   }

   private void moveImageToLowerLeft()
   {

   }

   private void shiftImageDown()
   {

   }

   public void displayRawImage()
   {
      for (int i = 0; i<BarcodeImage.MAX_HEIGHT;i++)
      {
         // Going through each row
         for (int j = 0; j<BarcodeImage.MAX_WIDTH; j++)
         {
            // Knows that element i is also an iterable since it's a String
            if (image.getPixel(i,j))
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
}