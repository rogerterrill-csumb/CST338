
// Title:               Optical Barcode Readers and Writers
// Files:               Assig4.java
// Semester:            Summer A, 2019
//
// Author:              Roger Terrill
// Email:               rchicasterrill@csumb.edu
// Lecturer's Name:     Jesse Cecil, M.S.
// TA's Name:           Joseph Appleton
// Lab Section:         CST 338

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
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
        int i,j;
        for(i=0;i<MAX_WIDTH;i++)
        {
            for(j=0;j<MAX_HEIGHT;i++)
            {
                imageData[i][j] = false;
            }
        }
    }

    public BarcodeImage(String[] strData)
    {

    }

    public boolean getPixel(int row, int col)
    {

    }

    public boolean setPixel(int row, int col, boolean value)
    {

    }

    // PRIVATE UTILITY METHOD
    private void checkSize(String[] data)
    {

    }

    private void displayToConsole()
    {

    }

    public void clone()
    {

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