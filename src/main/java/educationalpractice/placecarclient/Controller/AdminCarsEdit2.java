package educationalpractice.placecarclient.Controller;


import educationalpractice.placecarclient.Entity.Car;
import educationalpractice.placecarclient.Entity.User;

import educationalpractice.placecarclient.Service.CarServ;
import educationalpractice.placecarclient.Service.ErrorAlertServ;
import educationalpractice.placecarclient.Service.UserServ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static educationalpractice.placecarclient.MainApplication.*;

public class AdminCarsEdit2 {
    @FXML
    private Button btnCancelEditCar;
    @FXML
    private Button btnSaveEditCar;
    @FXML
    private ComboBox<User> comboHumanCar2;
    @FXML
    private TextField strokeColorCar2;
    @FXML
    private TextField strokeGosNumberCar2;
    @FXML
    private TextField strokeMarkCar2;
    @FXML
    private TextField strokeModelCar2;
    private boolean addFlag = true;
    private final ErrorAlertServ alertService = new ErrorAlertServ();
    private final CarServ servCar= new CarServ();
    UserServ serv = new UserServ();

    @FXML
    private void initialize() {
        serv.getAll();
        comboHumanCar2.setItems(serv.getData());
    }

    @FXML
    void btnCancelEditCar(ActionEvent event) {
        Stage stage = (Stage) btnCancelEditCar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnSaveEditCar(ActionEvent event) {
        try {
            Car car = new Car();
            car.setColorCar(strokeColorCar2.getText());
            car.setMarkCar(strokeMarkCar2.getText());
            car.setModelCar(strokeModelCar2.getText());
            car.setGosNumberCar(strokeGosNumberCar2.getText());
            car.setUser(comboHumanCar2.getSelectionModel().getSelectedItem());
            car.setIdCar(selectedData.getIdCar());
            servCar.update(car, car2);

        }catch (Exception e){
            alertService.addVoid(e);
            e.printStackTrace();
        }
        Stage stage = (Stage) btnSaveEditCar.getScene().getWindow();
        stage.close();



    }

}