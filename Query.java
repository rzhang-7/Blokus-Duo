// Import libraries
import java.io.File;
import java.util.ArrayList;

/*
 * Program name: BlokusDuo
 * Programmer: Raymond Zhang
 * Last modified: 02/06/2024
 * Description: Class that contains methods that involve parsing files and returning usable data.
 */

public class Query {


    /*
     * Method name: getSaveNames()
     * Return type: ArrayList<String> - A list of all saved games in file
     * Description: Provides a list of names for saved games
     */
    public static ArrayList<String> getSaveNames() {
        // Declare variables
        ArrayList<String> saveNames = new ArrayList<>();
        File saveFolder = new File("./saves"); // saves folder directory
        File[] saveFiles = saveFolder.listFiles(); // files in saves folder directory

        // Get all save names names
        for(File f : saveFiles) {
            saveNames.add(f.getName().substring(0,f.getName().lastIndexOf(".txt")));
        }

        return saveNames;
    }
}
