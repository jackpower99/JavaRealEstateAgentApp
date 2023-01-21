package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import java.net.URL;

public class MainPageController implements Initializable {

    PropertyModel model1;

    String ComboCounty;
    String ComboCity;
    String ComboCategory;



    @FXML
    private Button detailedAgentViewButton;
    @FXML
    private TableView<Property> tableView;
    @FXML
    private TableColumn<Property, Integer> idColumn;
    @FXML
    private TableColumn<Property, String> descriptionColumn;
    @FXML
    private TableColumn<Property, String> addressColumn;
    @FXML
    private TableColumn<Property, String> categoryColumn;
    @FXML
    private TableColumn<Property, String> genLocationColumn;
    @FXML
    private TableColumn<Property, String> specLocationColumn;
    @FXML
    private TableColumn<Property, String> eircodeColumn;
    @FXML
    private TableColumn<Property, Double> priceColumn;


    @FXML
    private ComboBox<String> ComboBoxCounty = new ComboBox<>();

    @FXML
    private ComboBox<String> ComboBoxCity = new ComboBox<>();

    @FXML
    private ComboBox<String> ComboBoxCategory = new ComboBox<>();


    ObservableList<String> counties = FXCollections.observableArrayList("Waterford", "Dublin", "Cork", "Galway","All");

    ObservableList<String> cities = FXCollections.observableArrayList("East", "South", "North", "West", "All");

    ObservableList<String> categories = FXCollections.observableArrayList("Detached", "Semi-Detached", "Bungalow", "Two-Storey","All");

    public void agentLoginBtn(ActionEvent e) throws Exception {

        Main.set_pane(1);

    }

    public void agentRegisterBtn(ActionEvent e) throws Exception {
        Main.set_pane(3);

    }

    /**
     * This method takes in values selected from ComboBoxes and iterates through the properties LinkedList comparing values from ComboBoxes
     * to values from each Property object. If Object is found it adds Property to new Linked List that will be converted to an Observable ArrayList
     * and displayed in Table View
     * @param ei
     * @throws Exception
     */
    public void searchBtn(ActionEvent ei) throws Exception {




        int x;

        String ComboCounty = ComboBoxCounty.getValue();
        String ComboCity = ComboBoxCity.getValue();
        String ComboCategory = ComboBoxCategory.getValue();


        ObservableList<Property> temp;


        ListClass<Property> searchPropertiesList = new ListClass<>();

        PropertyModel.loadProperty();
        temp = FXCollections.observableArrayList(PropertyModel.getPropArrayList());

        try{
        if (ComboCounty.equals("All") && ComboCategory.equals("All") && ComboCity.equals("All")) {
            tableView.setItems(temp);
        } else {
            for (int i = 0; i < PropertyModel.properties.size(); i++) {
                Property gotProperties = PropertyModel.properties.get(i);

                if (gotProperties.getSpecLocation().equals(ComboCity) && gotProperties.getGenLocation().equals(ComboCounty) && ComboCategory.equals("All")) {
                    searchPropertiesList.add(gotProperties);

                } else if (gotProperties.getSpecLocation().equals(ComboCity) && ComboCounty.equals("All") && gotProperties.getCategory().equals(ComboCategory)) {
                    searchPropertiesList.add(gotProperties);

                } else if (ComboCity.equals("All") && gotProperties.getCategory().equals(ComboCategory) && gotProperties.getGenLocation().equals(ComboCounty)) {
                    searchPropertiesList.add(gotProperties);

                } else if (gotProperties.getSpecLocation().equals(ComboCity) && gotProperties.getCategory().equals(ComboCategory) && gotProperties.getGenLocation().equals(ComboCounty)) {
                    searchPropertiesList.add(gotProperties);
                }
            }

            tableView.getItems().clear();
            for (x = 0; x < searchPropertiesList.size(); x++) {
                Property sProperty = (Property) searchPropertiesList.get(x);

                tableView.getItems().add(sProperty);

            }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method initialiszes (implemented by MainPageController) sets the TableView to store data from our agents LinkedList as an ObservableList data.
     * @param location
     * @param resources
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        model1 = new PropertyModel();

        ObservableList data = null;
        try {
            System.out.println("iii");
            data = FXCollections.observableArrayList(PropertyModel.getPropArrayList());
            System.out.println("ioeeei");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        idColumn.setCellValueFactory(new PropertyValueFactory<Property, Integer>("id"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("description"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("address"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("category"));
        genLocationColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("genLocation"));
        specLocationColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("specLocation"));
        // berColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("ber"));
        eircodeColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("eircode"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Property, Double>("price"));

        System.out.println("icc");
        tableView.setItems(data);
        System.out.println("iaa");

        ComboCounty = "";
        ComboCity = "";
        ComboCategory = "";


        ComboBoxCounty.setItems(counties);

        ComboBoxCity.setItems(cities);

        ComboBoxCategory.setItems(categories);



    }


    /**
     * This method will enable the detailed view button once a row in the table is
     * selected
     */
    public void userClickedOnTable() {
        this.detailedAgentViewButton.setDisable(false);

    }

    public void changeSceneToDetailedView(javafx.event.ActionEvent actionEvent) throws IOException {
        System.out.println("Change to detailed");

        FXMLLoader loader = new FXMLLoader();
        System.out.println("Change to detailed 1");
        loader.setLocation(getClass().getResource("PropertyView.fxml"));
        Parent tableViewParent = loader.load();
        System.out.println("Change to detailed 2");

        Scene tableViewScene = new Scene(tableViewParent);

        //access the controller and call a method
        PropertyViewControllerClass controller = loader.getController();
        Property propertyToDisplay = (Property) tableView.getSelectionModel().getSelectedItem(); //cast as Product
        System.out.println("Change to detailed 3");

        if (propertyToDisplay == null) //sometimes no Product will have been selected
            return;

        controller.initData(propertyToDisplay);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }



}



