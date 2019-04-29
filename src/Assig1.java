import java.util.Scanner;

public class Assig1 {
   public static void main(String[] args) {
      Scanner scannerObject = new Scanner(System.in);
      System.out.println("Please enter your first and last name.");
      System.out.println("Please capitalize the first letter for both.");

      String firstName = scannerObject.next();
      String lastName = scannerObject.next();
      String fullName = firstName + " " + lastName;

      int fullNameLength = fullName.length();

      System.out.println("Your full name is: " + fullName + " and is " + fullNameLength + " long.");
   }
}
