package educationalpractice.placecarclient.Controller;

import educationalpractice.placecarclient.Entity.Car;
import educationalpractice.placecarclient.Entity.User;
import educationalpractice.placecarclient.Service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class AdminCarsEdit {
    @FXML
    private ComboBox<User> comboHumanCar;
    @FXML
    private Button btnCancelEditCar;
    @FXML
    private Button btnSaveEditCar;
    @FXML
    private TextField strokeColorCar;
    @FXML
    private TextField strokeGosNumberCar;
    @FXML
    private TextField strokeMarkCar;
    @FXML
    private TextField strokeModelCar;
    private boolean addFlag = true;
    private final ErrorAlertServ alertService = new ErrorAlertServ();
    private final UserServ service = new UserServ ();
    private final CarServ servCar= new CarServ();
    @Setter
    @Getter
    private Optional<Car> cars;
    @FXML
    private void initialize() {
        service.getAll();
        servCar.getAll();
        comboHumanCar.setItems(service.getData());
    }
    @FXML
    void btnCancelEditCar(ActionEvent event) {
        Stage stage = (Stage) btnCancelEditCar.getScene().getWindow();
        stage.close();
    }
    @FXML
    void btnSaveEditCar(ActionEvent event) {
        try{
            Car temp =   Car.builder()
                    .markCar(strokeMarkCar.getText())
                    .colorCar(strokeColorCar.getText())
                    .gosNumberCar(strokeGosNumberCar.getText())
                    .modelCar(strokeModelCar.getText())
                    .user(comboHumanCar.getSelectionModel().getSelectedItem())
                    .build();
            if (cars.isEmpty()){
                cars = Optional.of(temp);}
            else {
                temp.setIdCar(cars.get().getIdCar());
            }
            System.out.println(temp);
            cars = Optional.of(temp);
        }catch (Exception e){
            //alertService.addVoid(e);
            e.printStackTrace();
        }
        Stage stage = (Stage) btnSaveEditCar.getScene().getWindow();
        stage.close();
    }
    public  void  start() {
        if (cars.isPresent()) {
            strokeMarkCar.setText(cars.get().getMarkCar());
            strokeModelCar.setText(cars.get().getModelCar());
            strokeGosNumberCar.setText(cars.get().getGosNumberCar());
            strokeColorCar.setText(cars.get().getColorCar());
            comboHumanCar.setValue(cars.get().getUser());
        }
    }
}