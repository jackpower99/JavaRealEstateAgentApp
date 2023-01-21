package sample;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.*;
import java.lang.*;
import java.net.URL;
import java.util.ResourceBundle;



public class AgentPageController implements Initializable {



    @FXML
    private Button detailedPropertyViewButton;


    @FXML
    private TableView<Property> tableView;
    @FXML
    private TableColumn<Property, String> idColumn;
    @FXML
    private TableColumn<Property, String> descriptionColumn;
    @FXML
    private TableColumn<Property, String> addressColumn;
    @FXML
    private TableColumn<Property, String> categoryColumn;
    @FXML
    private TableColumn<Property, String> countyColumn;
    @FXML
    private TableColumn<Property, String> cityColumn;
    @FXML
    private TableColumn<Property, String> eircodeColumn;
    @FXML
    private TableColumn<Property, Integer> priceColumn;
    @FXML
    private TableColumn<Property, String> imageColumn;


    @FXML
    private TextField id;
    @FXML
    private TextField description;
    @FXML
    private TextField address;
    @FXML
    private TextField category;
    @FXML
    private TextField county;
    @FXML
    private TextField city;
    @FXML
    private TextField price;
    @FXML
    private TextField eircode;
    @FXML
    private TextField image;
    @FXML
    private TextField index;
    @FXML
    private TextArea feedback;


    /**
     * This method taked in parameters from TextFields and passes them to add property method in the propertyModel Controller
     * else returns error
     * Then clears the TextFields.
     *
     * @param actionEvent
     * @return
     */

    public void addPropertyBtn(ActionEvent actionEvent) {


        String pid = id.getText();
        String pdescription = description.getText();
        String paddress = address.getText();
        String pcategory = category.getText();
        String pCounty = county.getText();
        String pCity = city.getText();
        String peircode = eircode.getText();
        String pIndex = index.getText();
        //String pImage = image.getText();
        int pprice = Integer.parseInt(price.getText());




        Property property = new Property(pid, pdescription, paddress, pcategory, pCounty, pCity, peircode, pprice);
        PropertyModel.addProperty(property);
        feedback.setText("Property Added");

        PropertyModel.saveFile();
        loadTable();

            id.clear();
            description.clear();
            address.clear();
            category.clear();
            county.clear();
            city.clear();
            eircode.clear();
            price.clear();
            image.clear();


        }

    /**
     * This method reads in list of properties from xtream.
     * itterates through the list and compares id passed in from TextField to id of each Property Object.
     * if found it calls deleteProperty method in propertyModel class and passes id.
     * @param actionEvent
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public void deletePropertyBtn(javafx.event.ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        if (PropertyModel.properties.size() >= 0) {
            System.out.println("1");
            int index1 = tableView.getSelectionModel().getSelectedIndex();

            System.out.println(index1);

            PropertyModel.properties.remove(index1);

            //write to file
            System.out.println("4");
            PropertyModel.saveFile();
            System.out.println("5");
            feedback.setText("Property Deleted");
            loadTable();
        } else {
            feedback.setText("No properties at this index");
        }
    }




    public void editPropertyBtn(javafx.event.ActionEvent actionEvent) {


    }

    public void savePropertyBtn(javafx.event.ActionEvent actionEvent) {


    }



    public void changedDescriptionCell(TableColumn.CellEditEvent editEvent){
//        int index = tableView.getSelectionModel().getSelectedIndex();
        Property propertySelected = tableView.getSelectionModel().getSelectedItem();
        propertySelected.setDescription(editEvent.getNewValue().toString());
        int index = tableView.getSelectionModel().getSelectedIndex();
        Property editProperty = PropertyModel.properties.get(index);
        editProperty.setDescription(editEvent.getNewValue().toString());
        PropertyModel.saveFile();
        loadTable();
        }
//       Property property = PropertyModel.properties.get(index);
//       String newDescription = editEvent.getNewValue().toString();
//       property.setDescription(newDescription);
   //    property.setDescription(editEvent.getNewValue().toString());
//       loadTable();


    public void changedIdCell(TableColumn.CellEditEvent editEvent){
        Property propertySelected = tableView.getSelectionModel().getSelectedItem();
        propertySelected.setId(editEvent.getNewValue().toString());
        int index = tableView.getSelectionModel().getSelectedIndex();
        Property editProperty = PropertyModel.properties.get(index);
        editProperty.setId(editEvent.getNewValue().toString());
        PropertyModel.saveFile();
        loadTable();
    }

    public void changedPriceCell(TableColumn.CellEditEvent edEvent){
        Property propertySelected = tableView.getSelectionModel().getSelectedItem();
        propertySelected.setPrice((Integer) edEvent.getNewValue());
        int index = tableView.getSelectionModel().getSelectedIndex();
        Property editProperty = PropertyModel.properties.get(index);
        editProperty.setPrice(Integer.parseInt(edEvent.getNewValue().toString()));
        PropertyModel.saveFile();
        loadTable();
    }


    /**
     * This method initialiszes (implemented by AgentPageController) sets the TableView to store data from our agents LinkedList as an ObservableList data.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       loadTable();
       tableView.setEditable(true);
       descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
       idColumn.setCellFactory(TextFieldTableCell.forTableColumn());
     //  priceColumn.setCellFactory((Integer) TextFieldTableCell.forTableColumn());

    }

public void loadTable() {


    ObservableList<Property> data = null;
    try {
        data = FXCollections.observableArrayList(PropertyModel.getPropArrayList());
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }


    idColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("id"));
    descriptionColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("description"));
    addressColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("address"));
    categoryColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("category"));
    countyColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("genLocation"));
    cityColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("specLocation"));
    // berColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("ber"));
    eircodeColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("eircode"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<Property, Integer>("price"));
   // imageColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("image"));

    //Load data into Table
    tableView.setItems(data);
}

    /**
     * This method will enable the detailed view button once a row in the table is
     * selected
     */
    public void userClickedOnTable() {
        this.detailedPropertyViewButton.setDisable(false);

    }

    /**
     * When this method is called, it will pass the selected Product object to
     * a the detailed view
     */

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





