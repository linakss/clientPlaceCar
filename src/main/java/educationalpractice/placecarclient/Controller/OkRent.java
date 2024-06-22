package educationalpractice.placecarclient.Controller;

import educationalpractice.placecarclient.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OkRent {
    @FXML
    private Button btnOKRent;
    @FXML
    void btnOKRent(ActionEvent event) {
        MainApplication.showDialog("user-main1.fxml","Автостоянка 'PlaceCar'");
        Stage stage = (Stage) btnOKRent.getScene().getWindow();
        stage.close();
    }
}
