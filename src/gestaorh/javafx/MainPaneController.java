/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestaorh.javafx;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Sala230_PC4_IPS
 */
public class MainPaneController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Pane pane_home;

    private JFXButton btn_home;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btn_home) {
            pane_home.toFront();
            System.out.println("ola");

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
