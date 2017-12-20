package Application;

import Models.LinearProgram;
import Utilities.FileUtility;
import Utilities.JSONUtility;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;

/**
 * @definition this class is The body of our
 * application, in which the user will choose
 * the file to work on and the result will be
 * displayed in , with other menus and something
 * like that.
 */

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = new AnchorPane();
        FileUtility fileUtility = new FileUtility(stage);
        File file = fileUtility.getFile();

        JSONUtility jsonUtility = new JSONUtility(fileUtility.getJSONText(file));
        LinearProgram h = jsonUtility.getLinearProgram();
        System.out.println("A is :" + Arrays.deepToString(h.getVectorA()));
        System.out.println("B is :" + Arrays.toString(h.getVectorB()));
        System.out.println("C is :" + Arrays.toString(h.getVectorC()));
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("Simplex Application");
        stage.show();
    }

    ;

    public static void main(String[] arguments) {
        launch(arguments);
    }
}
