package sample;

public class Agent {

    public static int agentId =0;

    private int id;
    private String username;
    private String password;
    private String name;
    private String number;


    public Agent(int id, String username, String name, String number) {
        this.id = id;
        this.username = username;
       // this.password = password;
        this.name = name;
        this.number = number;

    }

    public Agent(String username, String password, String name, String number) {
        super();
        this.id = agentId++;
        this.username = username;
        this.password = password;
        this.name = name;
        this.number = number;

    }


    public Agent(int index, String username, String password, String name, String number) {

    super();
        this.id = agentId ++;
        this.username = username;
        this.password = password;
        this.name = name;
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}