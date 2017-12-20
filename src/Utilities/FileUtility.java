package Utilities;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @definition this class have the role
 * of getting a file from the disk and
 * copy it's content : JSON or Txt.
 */

public class FileUtility {

    //Stage variable to use the file chooser
    private Stage mStage;

    //default constructor for the class
    public FileUtility(Stage stage) {
        mStage = stage;
    }

    /**
     * this method will help to show a file chooser , chose the
     * file and return it.
     *
     * @return file
     */
    public File getFile() {
        //a simple file chooser and its configuration
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Choose The File");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Json (*.json)", "*.json"),
                new FileChooser.ExtensionFilter("Text (*.txt)", "*.txt"));

        return fileChooser.showOpenDialog(mStage);
    }

    /**
     * this method will get the JSON Content of a JSON file
     * @param file
     * @return String
     */
    public String getJSONText(File file) {
        //the string builder which will be appending the lines
        StringBuilder builder = new StringBuilder();

        try {
            //fileReader for the file
            FileReader fileReader = new FileReader(file.getAbsolutePath());

            //the buffered rader for reading the lines
            BufferedReader reader = new BufferedReader(fileReader);

            String line;

            while ((line = reader.readLine()) != null) {
                //appending line by line
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * this method will read all lines of the Text File
     * and return back those lines in a List
     * @param file
     * @return List<String>
     */
    public List<String> getTextLines(File file){
        //list for the return
        List<String> lineList = new ArrayList<>();

        try {
            //a file reader for reading the file
            FileReader fileReader = new FileReader(file.getAbsolutePath());

            // the buffered reader for reading the lines
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                lineList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineList;
    }
}
