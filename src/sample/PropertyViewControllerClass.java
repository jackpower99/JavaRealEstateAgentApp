package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Property;

import java.io.IOException;

public class PropertyViewControllerClass {
    @FXML private Label id;
    @FXML private Label description;
    @FXML private Label address;
    @FXML private Label category;
    @FXML private Label county;
    @FXML private Label city;
    @FXML private Label eircode;
    @FXML private Label price;
    @FXML private ImageView image;




    /**
     * This method accepts a person to initialize the view
     * @param property
     */
    public void initData(Property property)
    {
        id.setText(""+property.getId()); //int to String
        description.setText(property.getDescription());
        address.setText(""+property.getAddress());
        category.setText(""+property.getCategory());
        county.setText(property.getGenLocation());
        city.setText(property.getSpecLocation());
        eircode.setText(property.getEircode());
        //image.setImage(property.getImage());
        price.setText(Integer.toString(property.getPrice()));

    }


    /**
     * When this method is called, it will change the Scene to
     * a TableView example
     */
    public void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

}
