import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class decrypt_encrypt {

   static int[][] encryptMatrix = { {3, 2}, {5, 3} }; // encryption matrix
   static int[][] decryptMatrix = { {23, 5}, {2, 23} }; // decryption matrix
   
   /* this method fills a given array with the numerical values of a string input */

   public static int[] fillArray(int[] arr, String text) {
      for (int i = 0; i < text.length(); i++) {
            arr[i] = text.charAt(i) - 'A'+1;
         }
         
      return arr;
   }
   
   /* this method takes the numerical values we got and encrypts them using matrice multiplication */

   public static int[] encrypt(int[] encryptedValues, int[][] encryptMatrix, int[] numericalValue) {
      for (int i = 0; i < numericalValue.length-1; i += 2) {

         encryptedValues[i] = ((numericalValue[i] * encryptMatrix[0][0] + numericalValue[i + 1] * encryptMatrix[0][1]) % 26 + 26) % 26;
         encryptedValues[i + 1] = ((numericalValue[i] * encryptMatrix[1][0] + numericalValue[i + 1] * encryptMatrix[1][1]) % 26 + 26) % 26;

         if (encryptedValues[i] == 0) {
            encryptedValues[i] = 26;
         }
         if (encryptedValues[i + 1] == 0) {
            encryptedValues[i + 1] = 26;
         }

      }
      
      return encryptedValues;
   }

   public static int[] decrypt(int[] decryptedValues, int[][] decryptMatrix, int[] numericalValues2) {

      for (int i = 0; i < numericalValues2.length-1; i += 2) {
         decryptedValues[i] = ((numericalValues2[i] * decryptMatrix[0][0] + numericalValues2[i + 1] * decryptMatrix[1][0]) % 26); //% 26 + 26) % 26;
         decryptedValues[i + 1] = ((numericalValues2[i] * decryptMatrix[0][1] + decryptMatrix[1][1] * numericalValues2[i + 1]) % 26); //% 26 + 26) % 26;

         if (decryptedValues[i] == 0) {
            decryptedValues[i] = 26;
         }
         if (decryptedValues[i + 1] == 0) {
            decryptedValues[i + 1] = 26;
         }
      }
      
      return decryptedValues;
   }
   /* this method takes the encrypted values and writes them out to "encrypted_message.txt" 
      it also converts them back into characters
   */

   public static void printEncrypt(int[] encryptedValues, PrintWriter out) {
      for (int value : encryptedValues) {
         char encryptedLetter = (char) (value + 'A' - 1);
         out.print(String.valueOf(encryptedLetter).toLowerCase());
          }
          out.close();
   }

   public static void printDecrypt(int[] decryptedValues, PrintWriter out2) {
      for (int value : decryptedValues) {
         char decryptedLetter = (char) (value + 'A' - 1);
         out2.print(String.valueOf(decryptedLetter).toLowerCase());
          }
          out2.close();
   }
   public static void main(String[] args) throws FileNotFoundException {

      File inputFile = new File("message.txt");
      PrintWriter out = new PrintWriter("encrypted_message.txt");

      Scanner scnr = new Scanner(inputFile);
      String text = scnr.nextLine();
      System.out.println("\n" + "Original message: " + text + "\n");
      String textVal = text.toUpperCase(); // allows us to grab alphabetical value
      scnr.close();

      int[] numericalValue = new int[textVal.length()]; // array for the numerical values of the file
      
      fillArray(numericalValue, textVal);
      
      int[] encryptedValues = new int[numericalValue.length]; // array for the encrypted text
      
      encrypt(encryptedValues, encryptMatrix, numericalValue);
      printEncrypt(encryptedValues, out);

      File inputFile2 = new File("encrypted_message.txt");
      PrintWriter out2 = new PrintWriter("decrypted_message.txt");

      Scanner scan = new Scanner(inputFile2);
      String encryptedText = scan.nextLine();
      System.out.println("Encrypted message: " + encryptedText + "\n");
      String encryptedTextVal = encryptedText.toUpperCase();
      scan.close();

      int[] numericalValues2 = new int[encryptedTextVal.length()];
      
      fillArray(numericalValues2, encryptedTextVal);

      int[] decryptedValues = new int[numericalValues2.length];

      decrypt(decryptedValues, decryptMatrix, numericalValues2);
      printDecrypt(decryptedValues, out2);

      File decryptedFile = new File("decrypted_message.txt");
      Scanner scanner = new Scanner(decryptedFile);
      String decryptedText = scanner.nextLine();
      System.out.println("Decrypted message: " + decryptedText + "\n");
      scanner.close();


      /* 
      for (int i = 0; i < decryptedValues.length; i++) {
         System.out.print(decryptedValues[i] + " ");
      }
      
      for (int i = 0; i < numericalValues2.length; i++) {
         System.out.print(numericalValues2[i] + " ");
      }
      */
   }
}
