package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Controller {
    private String userMessage;
    private Boolean connectionStatus = true;

    @FXML
    private TextArea userEntry;

    @FXML
    private TextArea receivedMessages;


    /*
    I think a good idea would be to test the functionality by passing the text from the user entry box
    to the received messages box. Just to make sure everything works alright and formats properly before attempting to
    add genuine communications.
     */
    public void submitButtonClicked(){
        userMessage = userEntry.getText().replaceAll("\n", System.getProperty("line.separator"));
        userEntry.clear();
    }


    public String getUserMessage(){
        return userMessage;
    }

    public void setReceivedMessages(String message){
        receivedMessages.setText(message);
    }

    public void connectionClosed(){
        connectionStatus = false;
        userEntry.setText("Connection closed.");
    }

    public boolean getConnectionStatus(){
        return connectionStatus;
    }
}
