package sample;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream;

import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;



public class AgentLoginController {



    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextArea feedback;


    /**
     * This Method takes in Text from TextField username and password and tries to pass the parameters to the Login method below
     * and sets pane to AgentPage.fxml
     * Else output Unsuccessful Login
     * @param e
     * @throws Exception
     */

    public void agentLoginBtn(ActionEvent e) throws Exception {
       if (login(username.getText(), password.getText())) {
            feedback.setText("Successful Login");
            Main.set_pane(0);
        } else {
            feedback.setText("Un-Successful Login");

        }
    }

    /**
     * This method takes in username and password from agentLoginBtn. Loads in agents from stream file
     * and compares username and password from agentLoinBtn and username and pasword of all agent stored in
     * agents LinkedList.
     * @param username
     * @param password
     * @return
     */

    private boolean login(String username, String password) {

        ListClass<Agent> agents;

        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("agents.xml"));
            agents = (ListClass) is.readObject();
            is.close();
        } catch (FileNotFoundException e) {
            agents = new ListClass();
            feedback.setText("Password File not located");
            return false;

        } catch (Exception e) {
            feedback.setText("Error accessing Password File");
            return false;
        }


        for ( int x = 0; x < agents.size(); x++) {
            Agent agent = agents.get(x);
            if (agent.getUsername().equals(username) && agent.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void adminLoginBtn(ActionEvent e) throws Exception {
        Main.set_pane(5);
    }

    public void homeBtn(ActionEvent e) throws Exception {
        Main.set_pane(2);
    }

    public void agentRegisterBtn(ActionEvent e) throws Exception {
        Main.set_pane(3);
    }
}

