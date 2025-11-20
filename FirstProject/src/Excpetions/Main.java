package Excpetions;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        //  Simple try-catch (Divide by Zero)
        try {
            int a = 10, b = 0;
            int result = a / b; // ArithmeticException
            System.out.println("Result = " + result);
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
        }

        //  Try with Multiple Catch Blocks
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]); //  ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Caught generic Exception: " + e.getMessage());
        }

        //  FileNotFoundException and IOException
        try {
            FileReader fr = new FileReader("non_existing_file.txt"); //  File not found
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            System.out.println("File content: " + line);
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Caught FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Caught IOException: " + e.getMessage());
        }

        //  Checked Custom Exception
        try {
            validator.validateAge(15);
        } catch (InvalidAgeException e) {
            System.out.println("Caught custom checked exception: " + e.getMessage());
        }

        //  Unchecked Custom Exception
        try {
            validator.validateName(null);
            
        } catch (InvalidNameRuntimeException e) {
            System.out.println("Caught custom runtime exception: " + e.getMessage());
        }

        System.out.println("\nProgram ended gracefully ");
    }
}
