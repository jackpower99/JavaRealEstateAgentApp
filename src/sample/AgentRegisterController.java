package sample;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;


public class AgentRegisterController {


    @FXML
    private TextField index;

    @FXML
    private TextField agentName;

    @FXML
    private TextField agentNumber;

    @FXML
    private TextField createdUsername;

    @FXML
    private TextField createdPassword;

    @FXML
    private TextField confirmCreatedPassword;

    @FXML
    private TextArea txtArea;


    /**
     * Method that takes in Parameters from Scenebuilder seen above so Admin can add an Agent
     * Can only add Agent if 1)Username is at least 4 characters long. 2) Username Contains at least 1 lowercase letter.
     * 3) Username Contains at least 1 uppercase letter. 4) Username Contains at least 1 digit.
     * 5) Username is not the same as Password.
     *
     * If all validations passed it then calls Register method and passes the parameters fullname, contact number, username, password.
     * @param e
     */
    public void RegisterBtn(ActionEvent e) throws Exception {


        boolean lower = false;
        boolean upper = false;
        boolean digit = false;
        for (int i = 0; i < createdUsername.getText().length(); i++) {
            char c = createdUsername.getText().charAt(i);
            if (Character.isLowerCase(c)) {
                lower = true;
            } else {
                txtArea.setText("Username must contain at least 1 lowercase letter.");
            }
            if (Character.isUpperCase(c)) {
                upper = true;
            } else {
                txtArea.setText("Username must contain at least 1 uppercase letter");
            }
            if (Character.isDigit(c)) {
                digit = true;
            } else {
                txtArea.setText("Username must contain at least 1 digit");
            }

            if (createdUsername.getText().length() < 4 || createdPassword.getText().length() < 4) {
                txtArea.setText("Username and Password need to be 4 characters or more");
            } else if (createdPassword.getText().contains(createdUsername.getText())) {
                txtArea.setText("Password cannot be the same as your Username");
            } else if (!createdPassword.getText().equals(confirmCreatedPassword.getText())) {
                txtArea.setText("Ensure Password and Confirmed Password are the same");
            } else if (createdUsername.getText().equals("") || createdPassword.getText().equals("") || confirmCreatedPassword.getText().equals("")) {
                txtArea.setText("Please Ensure all fields are full");

            } else {
                if (digit && upper && lower) {
                    if (createdUsername.getText().length() > 4) {
                        register(createdUsername.getText(), createdPassword.getText(), agentName.getText(), agentNumber.getText());
                        txtArea.setText("Successful Registration"); //Never be seen because of next line
                        Main.set_pane(1);
                    }
                }
            }
        }
    }




    private boolean register(String createdUsername, String createdPassword, String agentName, String agentNumber) {
        XStream xstream = new XStream(new DomDriver());
        ListClass<Agent> agents;

        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("agents.xml"));
            agents = (ListClass<Agent>) is.readObject();
            is.close();

        } catch (FileNotFoundException e) {
            agents = new ListClass<>();
            txtArea.setText("New Password File");

        } catch (Exception e) {
            txtArea.setText("Error accessing Password File");
            return false;
        }

        try {
            Agent agent = new Agent(createdUsername, createdPassword, agentName, agentNumber);
            agents.add(agent);
            Main.setAgent(agent);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("agents.xml"));
            out.writeObject(agents);
            out.close();
        }
        catch (Exception e) {
            txtArea.setText("Error writing to Password File");
            return false;
        }
        return true;
    }

    public void HomeBtn(ActionEvent e) throws Exception {
        Main.set_pane(2);
    }


    public void ReturnToLoginBtn(ActionEvent e) throws Exception{
        Main.set_pane(1);
    }
}
