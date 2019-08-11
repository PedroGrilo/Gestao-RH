package gestaorh.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void stop(){
        Controller controller = loader.getController();
        controller.saveFile();

    }
}