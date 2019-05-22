
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
      System.out.println(newCode.getPixel(0,5));
   }


}

// Phase 1 BarcodIO interface
interface BarcodeIO
{
   public boolean scan(BarcodeImage bc);

   public boolean readText(String text);

   public boolean generateImageFromText();

   public boolean translateImageToText();

   public void displayTextToConsole();

   public void displayImageToConsole();
}


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
      imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
      int i, j;
      for (i = 0; i < MAX_HEIGHT; i++)
      {
         for (j = 0; j < MAX_WIDTH; j++)
         {
            imageData[i][j] = false;
         }
      }

      for(i = 0; i < strData.length; i++)
      {
         for(j = 0; j < strData[i].length(); j++)
         {
            if(strData[i].charAt(j) == ' ')
               setPixel(i,j,false);

            else if (strData[i].charAt(j) == '*')
               setPixel(i,j,true);
         }
      }
   }

   public boolean getPixel(int row, int col)
   {
      if(imageData != null)
      {
         return imageData[row][col];
      }
      return false;
   }

   public boolean setPixel(int row, int col, boolean value)
   {
      if(row < MAX_WIDTH && col < MAX_HEIGHT)
      {
         imageData[row][col] = value;
         return true;
      }
      return false;
   }
//
//    // PRIVATE UTILITY METHOD
//    private void checkSize(String[] data)
//    {
//
//    }
//
//    public void displayToConsole()
//    {
//
//    }
//
//    public void clone()
//    {
//
//    }
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