
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

      String[] myString = {"    **", "  **  "};
      BarcodeImage newCode = new BarcodeImage(myString);
      BarcodeImage copy = (BarcodeImage)newCode.clone();
      System.out.println(newCode.getPixel(0, 5)); //True
      System.out.println(copy.getPixel(0, 5)); //True
      copy.setPixel(0,5,false);
      System.out.println(newCode.getPixel(0, 5)); //False
      System.out.println(copy.getPixel(0, 5)); //False
      newCode.displayToConsole();
   }


}

// Phase 1 BarcodeIO interface
interface BarcodeIO
{
   public boolean scan(BarcodeImage bc);

   public boolean readText(String text);

   public boolean generateImageFromText();

   public boolean translateImageToText();

   public void displayTextToConsole();

   public void displayImageToConsole();
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
         int i, j;
         for (i = 0; i < MAX_HEIGHT; i++)
         {
            for (j = 0; j < MAX_WIDTH; j++)
            {
               imageData[i][j] = false;
            }
         }

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

   public boolean getPixel(int row, int col)
   {
      if (imageData != null)
      {
         return imageData[row][col];
      }
      return false;
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
      for(boolean[] i : imageData)
      {
         // Going through each row
         for(boolean j: i)
         {
            // Knows that element i is also an iterable since it's a String
            if(j)
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
         BarcodeImage copy = (BarcodeImage)super.clone();
         copy.imageData = (boolean[][])imageData.clone();
         return copy;
      }
      catch (CloneNotSupportedException e)
      {
         return null;
      }

   }
}


//class DataMatrix implements BarcodeIO
//{
//    // METHODS
//    public DataMatrix()
//    {
//
//    }
//
//    public DataMatrix(BarcodeImage image)
//    {
//
//    }
//
//    public DataMatrix(String text)
//    {
//
//    }
//
//    public readText(String text)
//    {
//
//    }
//
//    public scan(BarcodeImage image)
//    {
//
//    }
//
//    public actualWidth()
//    {
//
//    }
//
//    public actualHeight()
//    {
//
//    }
//
//    private int computeSignalWidth()
//    {
//
//    }
//
//    private  int computeSignalHeight()
//    {
//
//    }
//}