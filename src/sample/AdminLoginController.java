package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Main;

public class AdminLoginController {

    /**
     * Declaring TextFields
     */
    @FXML
    private TextField username;

    @FXML
    private TextArea feedback;

    /**
     * This Method takes in Text from TextField username and Compares it to the password hard coded in "root".
     * If the Text = "root" set pain to AdminPage.fxml
     * Else output Try Again
     * @param e
     * @throws Exception
     */

    public void AdminLoginBtn(ActionEvent e) throws Exception {


        if (username.getText().equals("root")) {
            Main.set_pane(4);
        } else {
            feedback.clear();
            feedback.setText("Incorrect Username Try Again");
        }
    }

    /**
     * Return to HomePage.fxml
     * @param e
     * @throws Exception
     */

    public void homeBtn(ActionEvent e) throws Exception {
        Main.set_pane(2);
    }

}








