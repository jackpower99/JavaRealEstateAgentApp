

package sample;

import javafx.scene.image.Image;



public class Property {

    private static int propId;

    private String id;
    private String description;
    private String address;
    private String category;
    private String genLocation;
    private String specLocation;
    private String Eircode;
   // private Image pic;
    private int price;


    public Property(String id, String description, String address, String category, String genLocation, String specLocation, String eircode, int price) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.category = category;
        this.genLocation = genLocation;
        this.specLocation = specLocation;
        this.Eircode = eircode;
       // pic = new Image("JavaFXCup.png");
        this.price = price;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGenLocation() {
        return genLocation;
    }

    public void setGenLocation(String genLocation) {
        this.genLocation = genLocation;
    }

    public String getSpecLocation() {
        return specLocation;
    }

    public void setSpecLocation(String specLocation) {
        this.specLocation = specLocation;
    }


    public String getEircode() {
        return Eircode;
    }

    public void setEircode(String eircode) {
        Eircode = eircode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

//    public Image getImage() {
//        return pic;
//    }
//
//    public void setImage(Image image) {
//        this.pic = image;
//    }

    @Override
    public String toString() {
        return "Property{" +
                "id= " + id +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", category='" + category + '\'' +
                ", genLocation='" + genLocation + '\'' +
                ", specLocation='" + specLocation + '\'' +
                ", Eircode='" + Eircode + '\'' +
//                ", Image='" + pic + '\'' +
                ", price=" + price +
                '}';
    }

 //   public Image getImage() {
  //      return pic;
  //  }
}
