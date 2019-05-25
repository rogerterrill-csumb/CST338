import java.util.Collections;

/*
 * Title:   Module Four Assignment - Optical Barcode Readers
 * Author:  Team 4 - Abby Packham, Dalia Faria, Carlos Orduna,
 *          Roger Terrill, George Blombach
 * Date:    May 28, 2019
 */

/* Purpose:
 * Preconditions: None
 * Postconditions: None
 */

public class Assig4
{
    //phase 2 main()
    public static void main2(String[] args)
    {
        BarcodeImage test1 = new BarcodeImage();
        test1.setPixel(0, 0, true);
        test1.displayToConsole();

        String imageString[] = new String[10];
        imageString[0] = "* * * * * * * * * * * * * * * * * *";
        imageString[1] = "*                                 *";
        imageString[2] = "***** ** * **** ****** ** **** **  ";
        imageString[3] = "* **************      *************";
        imageString[4] = "**  *  *        *  *   *        *  ";
        imageString[5] = "* **  *     **    * *   * ****   **";
        imageString[6] = "**         ****   * ** ** ***   ** ";
        imageString[7] = "*   *  *   ***  *       *  ***   **";
        imageString[8] = "*  ** ** * ***  ***  *  *  *** *   ";
        imageString[9] = "***********************************";


        //1D constructor test
        BarcodeImage test2 = new BarcodeImage(imageString);
        System.out.println("\nTest2:\n");
        test2.displayToConsole();

        //clone test

    }
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
        dm.readText("All your base are belong to us!");
        dm.generateImageFromText();

        dm.displayTextToConsole();
        dm.displayImageToConsole();
    }
}

/* Name: Class BarcodeImage
 * Purpose:
 * Preconditions: implements Cloneable
 * Postconditions: None
 */
class BarcodeImage implements Cloneable
{
    public static final int MAX_HEIGHT = 30;
    public static final int MAX_WIDTH = 65;
    public boolean imageData[][];

    /* Name: BarcodeImage()
     * Purpose: default constructor
     * Preconditions: None
     * Postconditions: None
     */
    public BarcodeImage()
    {
        //instantiate imageData 2D array
        imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
        //set initial values in array
        for (boolean data[]:imageData)
            for (boolean value:data)
                value = false;
    }

    /* Name: BarcodeImage()
     * Purpose: overloaded constructor
     * Preconditions: takes 1D string of image
     * Postconditions: None
     */
    public BarcodeImage(String[] strData)
    {
        if (checkSize(strData)==true)
        {
            //instantiate imageData 2D array
            imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];

            //copy strData to 2D array
            for(int strHeight = 0; strHeight < strData.length; strHeight++)
            {
                for (int strWidth = 0; strWidth < strData[strHeight].length();
                     strWidth++)
                {
                    if(strData[strHeight].charAt(strWidth) == (char)(42))
                        imageData[strHeight + (MAX_HEIGHT-strData.length)]
                                [strWidth] = true;
                    else
                        imageData[strHeight + (MAX_HEIGHT-strData.length)]
                                [strWidth] = false;
                }
            }
        }
    }

    /* Name: getPixel()
     * Purpose: For the getPixel(), you can use the return value for both the
     *    actual data and also the error condition -- so that we don't "create
     *    a scene" if there is an error; we just return false.
     * Preconditions: Takes integers for array indices
     * Postconditions: Returns boolean
     */
    boolean getPixel(int row, int col)
    {
        return imageData[row][col];
    }

    /* Name: setPixel()
     * Purpose:
     * Preconditions: Takes integers for array indices, and array location value
     * Postconditions: Returns boolean
     */
    boolean setPixel(int row, int col, boolean value)
    {
        imageData[row][col] = value;
        return true;
    }

    /* Name: checkSize()
     * Purpose: Optional - A private utility method is highly recommended, but
     *    not required:  checkSize(String[] data)  It does the job of checking
     *    the incoming data for every conceivable size or null error.  Smaller
     *    is okay.  Bigger or null is not.
     * Preconditions: None
     * Postconditions: None
     */
    private boolean checkSize(String[] data)
    {
        //instantiate boolean check value, assume true for valid data
        boolean results = true;

        //check incoming data
        for(String strData:data)
        {
            //check if null values
            if (strData == null)
                results = false;
            //check if string too long
            if (strData.length() > MAX_WIDTH)
                results = false;
        }

        //check for size too large
        if (data.length > MAX_HEIGHT)
            results = false;

        return results;
    }

    /* Name: displayToConsole()
     * Purpose: Optional - A displayToConsole() method that is useful for
     *    debugging this class, but not very useful for the assignment at large.
     * Preconditions: None
     * Postconditions: Print to console
     */
    public void displayToConsole()
    {
        int zz = 0;
        for (boolean num[]:imageData)
        {
            int z =0;
            for (boolean value:num)
            {
                System.out.print(zz + "-" + z + ": " + value + " ");
                z++;
            }
            zz++;
        }
    }
    /* Name: clone()
     * Purpose: method that overrides the method of that name in Cloneable
     *    interface.
     * Preconditions: None
     * Postconditions: None
     */
    public Object clone()
    {
        try
        {
            BarcodeImage copy = (BarcodeImage)super.clone();
            copy.imageData = imageData.clone();
            for(int i = 0; i < MAX_HEIGHT; i++)
            {
                copy.imageData[i] = imageData[i].clone();
            }
            return copy;
        }
        catch (CloneNotSupportedException e)
        {
            //do nothing
            return null;
        }
    }
}

/* Name: Class DataMatrix
 * Purpose:
 * Preconditions: implements BardCodeIO
 * Postconditions: None
 */
class DataMatrix implements BarcodeIO
{
    public static final char BLACK_CHAR = '*';
    public static final char WHITE_CHAR = ' ';
    private BarcodeImage image;
    private String text;
    private int actualWidth, actualHeight;

    /* Name: DataMatrix()
     * Purpose:  default constructor
     * Preconditions: None
     * Postconditions: None
     */
    DataMatrix()
    {
        //initialize variables
        this.image = new BarcodeImage();
        this.text = "";
        actualWidth = 0;
        actualHeight = 0;
    }

    /* Name: DataMatrix()
     * Purpose:  Overloaded constructor
     * Preconditions: barcodeImage
     * Postconditions: None
     */
    DataMatrix(BarcodeImage image)
    {
        //initialize variables
        scan(image);
        this.text = "";
    }

    /* Name: DataMatrix()
     * Purpose:  Overloaded constructor
     * Preconditions: String
     * Postconditions: None
     */
    DataMatrix(String text)
    {
        //initialize variables
        this.image = new BarcodeImage();
        readText(text);
        actualWidth = 0;
        actualHeight = 0;
    }

    /* Name: getActualHeigth()
     * Purpose:
     * Preconditions: None
     * Postconditions: Returns integer
     */
    public int getActualHeight()
    {
        return this.actualHeight;
    }

    /* Name: scan()
     * Purpose:
     * Preconditions: None
     * Postconditions: None
     */
    public int getActualWidth()
    {
        return this.actualWidth;
    }

    /* Name: computeSignalWidth()
     * Purpose:
     * Preconditions: None
     * Postconditions: Returns integer
     */
    private int computeSignalWidth()
    {
        int imageWidth = 0;
        for (int i = 0;i < image.MAX_WIDTH; i++ )
            if (image.getPixel(image.MAX_HEIGHT-1, i) == true)
                imageWidth++;
        return imageWidth;
    }

    /* Name: computeSignalHeight()
     * Purpose:
     * Preconditions: None
     * Postconditions: Returns integer
     */
    private int computeSignalHeight()
    {
        int imageHeight = 0;
        for (int i = 0;i < image.MAX_HEIGHT; i++ )
            if (image.getPixel(i, 0) == true)
                imageHeight++;
        return imageHeight;
    }

    /* Name: scan()
     * Purpose:
     * Preconditions: None
     * Postconditions: None
     */
    public boolean scan(BarcodeImage bc)
    {
        //clone
        this.image = (BarcodeImage) bc.clone();
        cleanImage();
        this.actualWidth = computeSignalWidth();
        this.actualHeight = computeSignalHeight();

        return true;
    }

    /* Name: readText()
     * Purpose:  Not technically an I/O operation, this method looks at the
     *    internal text stored in the implementing class and produces a
     *    companion BarcodeImage, internally (or an image in whatever format the
     *    implementing class uses).  After this is called, we expect the
     *    implementing object to contain a fully-defined image and text that are
     *    in agreement with each other.
     * Preconditions: String
     * Postconditions: Returns boolean
     */
    public boolean readText(String text)
    {
        if (text != null)
        {
            this.text = text;
            return true;
        }
        else
            return false;
    }

    /* Name: generateImageFromText()
     * Purpose: Not technically an I/O operation, this method looks at the
     *    internal text stored in the implementing class and produces a
     *    companion BarcodeImage, internally (or an image in whatever format
     *    the implementing class uses).  After this is called, we expect the
     *    implementing object to contain a fully-defined image and text that
     *    are in agreement with each other.
     * Preconditions: None
     * Postconditions: Returns boolean
     */
    public boolean generateImageFromText()
    {
        String newtext = this.text;
        //create new object
        image = new BarcodeImage();
        actualWidth = newtext.length();
        actualHeight = 10;
        clearImage();
        // add borders to image
        //set top and bottom border
        for(int i = 0; i < actualWidth; i ++)
        {
            image.setPixel(29, i, true);
            if (i % 2 == 0)
                image.setPixel(0, i, true);
        }
        //set left and right border
        for(int i = 0; i < 10; i++)
        {
            this.image.setPixel(i,0, true);
            if (i % 2 == 0)
                this.image.setPixel(i, this.actualWidth-1, true);
        }

        //get ascii value of string
        for(char letter:newtext.toCharArray())
        {

        }

        //actualWidth = newtext.length();
        //actualHeight = 10;


        return true;
    }

    private boolean WriteCharToCol(int col, int code)
    {
        int power = 7;
        int values = 0;
        int startingRow = 21;

        if(code > 0 && code < 255)
        {
            for(int i=startingRow; i < BarcodeImage.MAX_HEIGHT; i++)
            {
                values = (int)Math.pow(2,power);
//            System.out.println("2^" + power + "= " + values + " and code is:" + code);
//            System.out.println(values <= code);

                if((int)Math.pow(2,power) <= code)
                {
//               System.out.println("setimage at location " + i + " " + col);
                    image.setPixel(i,col, true);
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

    /* Name: public boolean translateImageToText();
     * Purpose:
     * Preconditions: None
     * Postconditions: None
     */
    public boolean translateImageToText()
    {
        String text = "  ";
        int count;
        for (int col = 1; col < actualWidth - 1; col++)
        {
            count = 0;
            for (int row = this.image.MAX_HEIGHT - 2;row >= this.image.MAX_HEIGHT -
                    getActualHeight() + 2; row--)
            {
                if (this.image.getPixel(row,col) == true)
                    count += (Math.pow(2, ((image.MAX_HEIGHT - 2) - row)));
            }
            //add ascii character to string
            text += (char)(count);
        }
        //set text to object
        this.text = text;
        return true;
    }

    /* Name:  displayTextToConsole()
     * Purpose:
     * Preconditions: None
     * Postconditions: None
     */
    public void displayTextToConsole()
    {
        System.out.println(this.text);
    }

    /* Name: displayImageToConsole()
     * Purpose:
     * Preconditions: None
     * Postconditions: None
     */
    public void displayImageToConsole()
    {
        String dash = "-";
        //print top border
        System.out.print(dash.repeat(getActualWidth()+2) + "\n");

        for (int ii = (image.MAX_HEIGHT - getActualHeight());
             ii < image.MAX_HEIGHT; ii++ )
        {
            //print left side border
            System.out.print("|");
            for (int i = 0; i < getActualWidth(); i++)
                if (image.getPixel(ii, i) == true)
                    System.out.print(BLACK_CHAR);
                else
                    System.out.print(WHITE_CHAR);
            //move to nextline
            //print right side border
            System.out.print("|");
            System.out.print("\n");
        }
        //print bottom border
        System.out.print(dash.repeat(getActualWidth()+2) + "\n");
    }

    /* Name: cleanImage()
     * Purpose: moved image to bottom left corner of 2D array
     * Preconditions: None
     * Postconditions: None
     */
    public void cleanImage()
    {
        boolean checkData = false;
        int imageWidth = 0;
        int imageHeight = 0;

        //check vertical placement
        for (int i=0; i < image.MAX_WIDTH; i++)
        {
            //if the bottom row has any data
            if (image.getPixel(image.MAX_HEIGHT - 1,i) == true)
            {
                checkData = true;
            }
        }
        if (checkData == false)
        {
            shiftImageDown();
            cleanImage();
        }

        //check horizontal placement
        checkData = false;
        for (int i=0; i < image.MAX_HEIGHT; i++)
        {
            //if the left column has any data
            if (image.getPixel(i,0) == true)
                checkData = true;
        }
        if (checkData == false)
        {
            shiftImageLeft();
            cleanImage();
        }
    }

    /* Name: shiftImageDown()
     * Purpose: helper function to shift image down/higher in vertical array
     * Preconditions: None
     * Postconditions: None
     */
    private void shiftImageDown()
    {
        for (int i = this.image.MAX_HEIGHT - 1; i > 0; i--)
            for (int ii = 0; ii < this.image.MAX_WIDTH; ii++)
                this.image.setPixel(i, ii, (this.image.getPixel(i - 1, ii)));
    }

    /* Name: shiftImageLeft()
     * Purpose: helper function to shift image left/lower in horizontal array
     * Preconditions: None
     * Postconditions: None
     */
    private void shiftImageLeft()
    {
        for (int i = 0; i < this.image.MAX_HEIGHT; i++)
            for (int ii = 0; ii < this.image.MAX_WIDTH - 1; ii ++)
                this.image.setPixel(i, ii, (this.image.getPixel(i, ii + 1)));
    }

    /* Name: shiftImageLeft()
     * Purpose: helper function to shift image left/lower in horizontal array
     * Preconditions: None
     * Postconditions: None
     */
    public void displayRawImage()
    {

    }

    /* Name: clearImage()
     * Purpose: set all array data to false
     * Preconditions: None
     * Postconditions: None
     */
    private void clearImage()
    {
        for (int row = 0; row < this.getActualHeight() - 1; row ++)
            for (int col = 0; col < this.getActualWidth()- 1; col ++)
                this.image.setPixel(row, col, true);
    }
}

/* Name: Interface BarcodeIO
 * Purpose:
 * Preconditions:
 * Postconditions: None
 */
interface BarcodeIO
{

    /* Name: scan()
     * Purpose:  accepts some image, represented as a BarcodeImage object to be
     *    described below, and stores a copy of this image.  Depending on the
     *    sophistication of the implementing class, the internally stored
     *    image might be an exact clone of the parameter, or a refined,
     *    cleaned and processed image.  Technically, there is no requirement
     *    that an implementing class use a BarcodeImage object internally,
     *    although we will do so.  For the basic DataMatrix option, it will
     *    be an exact clone.  Also, no translation is done here - i.e., any
     *    text string that might be part of an implementing class is not
     *    touched, updated or defined during the scan.
     * Preconditions: None
     * Postconditions: None
     */
    public boolean scan(BarcodeImage bc);

    /* Name: readText()
     * Purpose: accepts a text string to be eventually encoded in an image. No
     *    translation is done here - i.e., any BarcodeImage that might be part of
     *    an implementing class is not touched, updated or defined during the
     *    reading of the text.
     * Preconditions: None
     * Postconditions: None
     */
    public boolean readText(String text);

    /* Name: generateImageFromText(
     * Purpose:  Not technically an I/O operation, this method looks at the
     *    internal text stored in the implementing class and produces a
     *    companion BarcodeImage, internally (or an image in whatever format the
     *    implementing class uses).  After this is called, we expect the
     *    implementing object to contain a fully-defined image and text that are
     *    in agreement with each other.
     * Preconditions: None
     * Postconditions: None
     */
    public boolean generateImageFromText();

    /* Name: translateImageToText();
     * Purpose: Not technically an I/O operation, this method looks at the
     *    internal image stored in the implementing class, and produces a
     *    companion text string, internally.  After this is called, we expect
     *    the implementing object to contain a fully defined image and text that
     *    are in agreement with each other.
     * Preconditions: None
     * Postconditions: None
     */
    public boolean translateImageToText();

    /* Name: displayTextToConsole();
     * Purpose: prints out the text string to the console
     * Preconditions: None
     * Postconditions: None
     */
    public void displayTextToConsole();

    /* Name:
     * Purpose: prints out the image to the console.  In our implementation, we
     *    will do this in the form of a dot-matrix of blanks and asterisks, e.g.
     * Preconditions: None
     * Postconditions: None
     */
    public void displayImageToConsole();

}
/**************** Output *******************

 Phase 3:

 CSUMB CSIT online program is top notch
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
 -------------------------------------------
 You did it!  Great work.  Celebrate
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


 *******************************************/

