package Model_Version;


import javafx.stage.Stage;

public class Model {

    private String username;
    private String address;
    private Stage stage;

    public void setUsername(String username){
        this.username = username;

    }

    public void setAddress(String address){
        this.address = address;

    }

    public String getUsername(){
        return username;
    }

    public String getAddress(){
        return address;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public Stage getStage(){
        return this.stage;
    }

}

