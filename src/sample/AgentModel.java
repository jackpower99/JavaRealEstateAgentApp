package sample;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.fxml.FXML;
import sample.Agent;
import sample.ListClass;

import java.io.*;
import java.util.ArrayList;

public class AgentModel {

    /**
     Declaring ListClass called agents to store Agent Objects in LinkedList called agents.
     */
    @FXML
    public static ListClass agents;


    public AgentModel() {
        agents = new ListClass<>();
    }

    /**
     * Creating Agent Object from parameters Passed in from the AdminPageController Class and AgentRegisterController
     */
    @FXML
    public static void addAgent(Agent agent) {

        agents.add(agent);
    }
//        if (agents.isEmpty()) {
//            try {
//                try {
//                    XStream xstream = new XStream(new DomDriver());
//                    ObjectInputStream xx = xstream.createObjectInputStream(new FileReader("agents.xml"));
//                    agents = (ListClass) xx.readObject();
//                } catch (IOException | ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//                Agent addedAgent = new Agent(username, password, name, number);
//                XStream xstream = new XStream(new DomDriver());
//                agents.add(addedAgent);
//                ObjectOutputStream out = xstream.createObjectOutputStream((new FileWriter("agents.xml")));
//                out.writeObject(agents);
//                out.close();
//
//                return true;
//            } catch (Exception e) {
//                      return false;
//            }
//        } else {
//            try {
//                Agent agent = new Agent(username, password, name, number);
//                XStream xstream = new XStream(new DomDriver());
//                agents.add(agent);
//                ObjectOutputStream out = xstream.createObjectOutputStream((new FileWriter("agents.xml")));
//                out.writeObject(agents);
//                out.close();
//
//
//                return true;
//            } catch (Exception e) {
//               return false;


    public static void loadAgent() throws IOException, ClassNotFoundException {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("agents.xml"));
        agents = (ListClass<Property>) is.readObject();
        is.close();
    }


//


//    }

    /**
     * This method returns an ArrayList of agents called agentsForTable taken from my LinkedList agents.
     * @return ArrayList agentsForTable.
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static ArrayList getAgentArrayList() throws IOException, ClassNotFoundException {


        try {
            loadAgent();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        ListClass<Agent> temp;
        ArrayList<Agent> agentsForTable = new ArrayList<>();


        temp = agents;


        if (temp.isEmpty()) {

            return agentsForTable;

        } else {

            for (int i = 0; i < temp.size(); i++) {
                Agent a = (Agent) temp.get(i);
                agentsForTable.add(a);
            }
        }
        return agentsForTable;



//        ListClass temp;
//        ArrayList<Agent> agentsForTable = new ArrayList<>();
//        XStream xstream = new XStream(new DomDriver());
//        ObjectInputStream one = xstream.createObjectInputStream(new FileReader("agents.xml"));
//        temp = (ListClass) one.readObject();
//        one.close();
//
//        for (int i = 0; i < temp.size(); i++) {
//            Agent vAgent = (Agent) temp.get(i);
//            agentsForTable.add(vAgent);
//        }
//        return agentsForTable;
    }
}
//
//
//
//    @FXML
//    public void editAgent(String username, String password, String name, String number,
//            if (property.equals(id)) {
//                properties.get(index).setAgentId(id);
//                properties.get(index).setAddress(address);
//                properties.get(index).setSpecLocation(city);
//                properties.get(index).setBER(BER);

//            }
//        }
//    }



