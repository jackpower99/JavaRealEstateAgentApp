package sample;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.fxml.FXML;


import java.io.*;
import java.util.ArrayList;



public class PropertyModel {

    /**
     * Declaring ListClass called properties to store Property Objects in LinkedList called properties.
     */

    @FXML
    public static ListClass<Property> properties;


    public PropertyModel() {
        properties = new ListClass<>();
    }


    /**
     * Method to create and add Property Object to properties
     *
     */


    public static void addProperty(Property property) {
        properties.add(property);
    }

    /**
     * Method to return ArrayList of properties to convert to ObservableArrayList.
     *
     * @return ArrayList made from LinkedList properties
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static ArrayList<Property> getPropArrayList() throws IOException, ClassNotFoundException {
        try {
            loadProperty();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        ListClass<Property> temp;
        ArrayList<Property> propForTable = new ArrayList<>();


        temp = properties;


        if (temp.isEmpty()) {

            return propForTable;

        } else {

            for (int i = 0; i < temp.size(); i++) {
                Property vProperty = (Property) temp.get(i);
                propForTable.add(vProperty);
            }
        }
        return propForTable;

    }


    public static void saveFile() {
        XStream xstream = new XStream(new DomDriver());
        try {

            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("properties.xml"));
            out.writeObject(properties);
            out.close();


        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            properties = new ListClass<>();


        } catch (Exception e) {
            System.out.println(e.toString());


        }
    }


    @FXML
    public static void loadProperty() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("properties.xml"));
        properties = (ListClass<Property>) is.readObject();
        is.close();
    }

//    @FXML
//    public void editProperty(int index, String id, String address, String city, String BER, String eircode, String Description, double price, String category) {
//        for (Property property : properties) {
//            if (property.equals(id)) {
//                properties.get(index).setPropertyId(id);
//                properties.get(index).setAddress(address);
//                properties.get(index).setSpecLocation(city);
//                properties.get(index).setBER(BER);
//                properties.get(index).setEircode(eircode);
//                properties.get(index).setDescription(Description);
//                properties.get(index).setPrice(price);
//                properties.get(index).setCategory(category);
//                //properties.get(index).setImage(image);
//            }
//        }
//    }
//

//
//



}
