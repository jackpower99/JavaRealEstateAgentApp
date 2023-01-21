package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javafx.scene.layout.AnchorPane;

public class Main extends Application {
    static AnchorPane root;
    static ListClass<AnchorPane> anchor = new ListClass<AnchorPane>();
    private static int sceneIndex = 0;
    private static Agent agent = null;
    private static Property property = null;
    public static ListClass<Property> properties = new ListClass<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("Anchor.fxml"));

        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("AdminLogin.fxml")));//5
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("AdminPage.fxml")));//4
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("AgentRegister.fxml")));//3
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("MainPage.fxml")));//2
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("AgentLogin.fxml")));//1
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("AgentPage.fxml")));//0
//        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("AgentAddProperty.fxml")));//3
//        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("AdminLogin.fxml")));//4
//        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("AdminMain.fxml")));//5


        root.getChildren().add(anchor.get(2));

        primaryStage.setTitle("Property Application");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

    }

    public static void setAgent(Agent agent) {
        Main.agent = agent;
    }

    public static void setProperty(Property property) { Main.property = property; }

    public static Property getProperty() { return property; }

    public static Agent getAgent() {return agent;}

    private void init_app(){
        for(int i=0; i<anchor.size();i++){

        }
    }

    public static AnchorPane get_pane(int index){
        return anchor.get(index);
    }

    public static void set_pane(int index) {
        if (index <= anchor.size()) {
            root.getChildren().remove(anchor.get(sceneIndex));
            root.getChildren().add(anchor.get(index));
            sceneIndex = index;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}



