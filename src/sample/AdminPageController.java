package sample;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.*;
import java.lang.*;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminPageController implements Initializable {

    /**
     * AgentModel reference to Access all AgentModel methods.
     */
    AgentModel agentmodel;



    @FXML
    private TableView<Agent> tableView;
    @FXML
    private TableColumn<Agent, String> fullNameColumn;
    @FXML
    private TableColumn<Agent, String> contactColumn;
    @FXML
    private TableColumn<Agent, String> usernameColumn;
    @FXML
    private TableColumn<Agent, String> passwordColumn;



    @FXML
    private TextField Fullname;
    @FXML
    private TextField Contact;
    @FXML
    private TextField Username;
    @FXML
    private TextField Password;
    @FXML
    private TextArea feedback;

    /**
     * Method that takes in Parameters from Scenebuilder seen above so Admin can add an Agent
     * Can only add Agent if 1)Username is at least 4 characters long. 2) Username Contains at least 1 lowercase letter.
     * 3) Username Contains at least 1 uppercase letter. 4) Username Contains at least 1 digit.
     * 5) Username is not the same as Password.
     *
     * If all validations passed it then calls Register method and passes the parameters fullname, contact number, username, password.
     * @param actionEvent
     */

    public void addAgentBtn(javafx.event.ActionEvent actionEvent) {

        boolean lower = false;
        boolean upper = false;
        boolean digit = false;
        for (int i = 0; i < Username.getText().length(); i++) {
            char c = Username.getText().charAt(i);
            if (Character.isLowerCase(c)) {
                lower = true;
            } else {
                feedback.setText("Username must contain at least 1 lowercase letter.");
            }
            if (Character.isUpperCase(c)) {
                upper = true;
            } else {
                feedback.setText("Username must contain at least 1 uppercase letter");
            }
            if (Character.isDigit(c)) {
                digit = true;
            } else {
                feedback.setText("Username must contain at least 1 digit");
            }

            if (Username.getText().length() < 4 || Password.getText().length() < 4) {
                feedback.setText("Username and Password need to be 4 characters or more");
            } else if (Password.getText().contains(Username.getText())) {
                feedback.setText("Password cannot be the same as your Username");


            } else {
                if (digit && upper && lower) {
                    if (Username.getText().length() > 4) {
                        register(Username.getText(), Password.getText(), Fullname.getText(), Contact.getText());
                        feedback.setText("Successful Registration"); //Never be seen because of next line
                        //  Main.set_pane();
                    }
                }
            }
        }
    }

    /**
     * This method takes in parameters from addAgentBtn() and creates Agent object.
     * Then adds object to ListClass linkedlist agents and then reads it out to XStream file named agents.xml
     * @param createdUsername username passed in.
     * @param createdPassword password passed in.
     * @param agentName agentName passed in.
     * @param agentNumber agentNumber passed in.
     * @return boolean to confirm or declare error.
     */

    public boolean register(String createdUsername, String createdPassword, String agentName, String agentNumber) {
        // XStream xstream = new XStream(new DomDriver());
//        ListClass<Agent> agents;
//
//        try {
//            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("agents.xml"));
//            agents = (ListClass<Agent>) is.readObject();
//            is.close();
//
//        } catch (FileNotFoundException e) {
//            agents = new ListClass<>();
//            feedback.setText("New Password File");
//
//        } catch (Exception e) {
//            feedback.setText("Error accessing Password File");
//            return false;
//        }

            Agent agent = new Agent(createdUsername, createdPassword, agentName, agentNumber);
            //AgentModel.agents.add(agent);
            AgentModel.addAgent(agent);
            // Main.setAgent(agent);
            PropertyModel.saveFile();
            return true;
    }
//            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("agents.xml"));
//            out.writeObject(agents);
            //out.close();


    public void homeBtn(javafx.event.ActionEvent e) throws Exception {
        Main.set_pane(2);
    }


    /**
     * This method passes selected Agent to deleteAgent in AgentModel class
     * @param actionEvent
     */

    public void deleteAgentBtn(javafx.event.ActionEvent actionEvent) {

        int index = tableView.getSelectionModel().getSelectedIndex();
        PropertyModel.properties.remove(index);

       // AgentModel.saveAgent();

//        int indexDelete = Integer.parseInt(index.getText());
//        if (indexDelete >= 0 && indexDelete < agentmodel.agentsSize()) {
//            PropertyModel.deleteProperty(indexDelete);
//        } else {
//            feedback.setText("Enter a Valid Index from the Table Above");
//        }

    }



    public void changedNumberCell(TableColumn.CellEditEvent editEvent) {
        Agent agentSelected = tableView.getSelectionModel().getSelectedItem();
        agentSelected.setNumber(editEvent.getNewValue().toString());
    }

    public void changedPasswordCell(TableColumn.CellEditEvent editEvent) {
        Agent agentSelected = tableView.getSelectionModel().getSelectedItem();
        agentSelected.setPassword(editEvent.getNewValue().toString());
    }


    /**
     * This method passes selected Agent to editAgent in AgentModel class
     * @param actionEvent
     */




    public void editPropertyBtn(javafx.event.ActionEvent actionEvent) {


        //        int indexEdit = Integer.parseInt(index.getText());
//        if (indexEdit >= 0 && indexEdit < agentmodel.agentsSize()) {
//            PropertyModel.editProperty(indexEdit);
//        } else {
//            feedback.setText("Enter a Valid Index from the Table Above");
//        }

    }

    /**
     * This method initialiszes (implemented by AdminPageController) sets the TableView to store data from our agents LinkedList as an ObservableList data.
     * @param url
     * @param rb
     */


    @Override
    public void initialize(URL url, ResourceBundle rb) {



        loadTable();

    }

   public void loadTable(){
       ObservableList<Agent> data = null;
       try {
           data = FXCollections.observableArrayList(AgentModel.getAgentArrayList());
       } catch (IOException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }


       fullNameColumn.setCellValueFactory(new PropertyValueFactory<Agent, String>("name"));
       contactColumn.setCellValueFactory(new PropertyValueFactory<Agent, String>("number"));
       usernameColumn.setCellValueFactory(new PropertyValueFactory<Agent, String>("username"));
       passwordColumn.setCellValueFactory(new PropertyValueFactory<Agent, String>("password"));

       tableView.setItems(data);
       tableView.setEditable(true);
       contactColumn.setCellFactory(TextFieldTableCell.forTableColumn());
       passwordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
   }





}





