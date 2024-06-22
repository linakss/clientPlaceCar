package educationalpractice.placecarclient.Controller;

import educationalpractice.placecarclient.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static educationalpractice.placecarclient.MainApplication.employeeStat;
import static educationalpractice.placecarclient.MainApplication.userAdmin;

public class UserProfile {
    @FXML
    private Button btnHelp;
    @FXML
    private Button btnOpenCarUser;
    @FXML
    private Button btnOpenHomeUser;
    @FXML
    private Text txtLastNameProfile;
    @FXML
    private Text txtNameProfile;
    @FXML
    private Text txtPhoneNumberProfile;
    @FXML
    private Text txtSurnameProfile;
    @FXML
    private Text whoUser;
    @FXML
    void btnHelp(ActionEvent event) {
        MainApplication.showDialog("help.fxml","Помощь");
    }
    @FXML
    void btnOpenCarUser(ActionEvent event) {
        MainApplication.showDialog("user-car.fxml","Автостоянка 'PlaceCar'");
        Stage stage = (Stage) btnOpenCarUser.getScene().getWindow();
        stage.close();
    }
    @FXML
    void btnOpenHomeUser(ActionEvent event) {
        MainApplication.showDialog("user-main1.fxml","Автостоянка 'PlaceCar'");
        Stage stage = (Stage) btnOpenHomeUser.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void initialize() {
        txtSurnameProfile.setText(employeeStat.getSurname());
        txtNameProfile.setText(employeeStat.getName());
        txtLastNameProfile.setText(employeeStat.getLastname());
        txtPhoneNumberProfile.setText(employeeStat.getNumberPhone());
        whoUser.setText(employeeStat.getSurname()+"\n"+employeeStat.getName()+"\n"+employeeStat.getLastname());
    }
}
