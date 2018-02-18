package Test;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;



public class ConnectionController {


    private final Model model;

    @FXML
    private TextField username;

    @FXML
    private TextField usName;

    @FXML
    private TextField address;

    @FXML
    private AnchorPane connValues;

    public ConnectionController(Model model){
        this.model = model;
    }

    public void submitConnectionClicked(){
        model.clearConnection();
        model.setAddress(address.getText());
        model.setUsername(username.getText());
        model.getConnectionStage().close();
        model.initConnection();
    }

    public void setUsername(){
        model.setUsername(usName.getText());
        model.getUsernameStage().close();
    }



}
