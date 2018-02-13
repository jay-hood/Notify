package Model_Version;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;



public class ConnectionController {


    private final Model model;

    @FXML
    private TextField username;

    @FXML
    private TextField address;

    @FXML
    private AnchorPane connValues;

    public ConnectionController(Model model){
        this.model = model;
    }

    public void submitConnectionClicked(){
      model.setAddress(address.getText());
      model.setUsername(username.getText());
      model.getStage().close();
    }

}
