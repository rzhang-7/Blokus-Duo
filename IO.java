import java.util.Scanner; // Import Scanner class

/*
 * Program name: IO.java
 * Programmer: Raymond Zhang
 * Last modified: 01/06/2024
 * Description: Singleton class to allow for global access of a single Scanner instance.
 */

public class IO {

    /*
     * Method Name: newScanner
     * Return type: Scanner - The Scanner instance that reads from console
     * Description: Returns a singular instance of Scanner to avoid multiple declarations in other programs.
     */
    public static Scanner newScanner() {
        return new Scanner(System.in);
    }
}
