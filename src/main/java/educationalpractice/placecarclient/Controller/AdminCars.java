package educationalpractice.placecarclient.Controller;

import educationalpractice.placecarclient.Entity.Car;
import educationalpractice.placecarclient.Entity.User;
import educationalpractice.placecarclient.MainApplication;
import educationalpractice.placecarclient.Service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

import static educationalpractice.placecarclient.MainApplication.userAdmin;

public class AdminCars {

    @FXML
    private Button btnAddCar;
    @FXML
    private Button btnCancelCar;
    @FXML
    private Button btnDeleteCar;
    @FXML
    private Button btnEditCar;
    @FXML
    private TableColumn<Car,String> colColorAuto;
    @FXML
    private TableColumn<User, String> colFIO;
    @FXML
    private TableColumn<Car,String> colGosNumber;
    @FXML
    private TableColumn<Car,String> colMarkAuto;
    @FXML
    private TableColumn<Car,String> colModelAuto;
    @FXML
    private TableColumn<User, String> colNumberPhone;
    @FXML
    private TableView<Car> tableCars;
    @FXML
    private Button btnOpenEmployee;
    @FXML
    private Text whoUser;
    @FXML
    private Button btnOpenHome;
    CarServ serv = new CarServ();
    UserServ userServ = new UserServ();
    ErrorAlertServ alertServ = new ErrorAlertServ();
    private boolean addFlag = true;
    private Optional<Car> cars = Optional.empty();

    public void setCar(Optional<Car> cars){
        try{
            this.cars = cars;
            if (cars.isPresent()) {
                if (cars.get().getIdCar() != null)
                    serv.update(cars.get(), tableCars.getSelectionModel().getSelectedItem());
                else serv.add(cars.get());
            }
        }catch (Exception e){
            alertServ.addVoid(e);
        }
    }
    private Car getSelectionElement() {
        Car temp = tableCars.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    void btnAddCar(ActionEvent event) {
        try {
            Optional<Car> cars = Optional.empty();
            MainApplication.showBookDialog(cars);
        }catch (Exception e){alertServ.didntStart(e);}    }
    @FXML
    void btnEditCar(ActionEvent event){
        try{
            Optional<Car> cars = Optional.of(getSelectionElement());
            MainApplication.showBookDialog(cars);}catch (Exception e){alertServ.didntStart(e);}
    }
    @FXML
    void btnCancelCar(ActionEvent event) {
        tableCars.editableProperty().setValue(false);
    }

    @FXML
    void btnDeleteCar(ActionEvent event) {
        try {serv.delete(getSelectionElement());
        }catch (Exception e){
            alertServ.deleteVoid(e);}
    }
    @FXML
    void btnOpenEmployee(ActionEvent event) {
        MainApplication.showDialog("admin-employee.fxml","Сотрудники");
        Stage stage = (Stage) btnOpenEmployee.getScene().getWindow();
        stage.close();
    }
    @FXML
    void btnOpenHome(ActionEvent event) {
        MainApplication.showDialog("admin-main.fxml","Главная");
        Stage stage = (Stage) btnOpenHome.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void initialize() {
        whoUser.setText(userAdmin.getSurname()+"\n"+userAdmin.getName()+"\n"+userAdmin.getLastname());
        if (userAdmin.getRole().equals("Администратор")) { //роль равна админу или охране
            btnEditCar.setVisible(true);
            btnDeleteCar.setVisible(true);
            btnCancelCar.setVisible(true);
            btnAddCar.setVisible(true);
        } else {
            btnEditCar.setVisible(false);
            btnDeleteCar.setVisible(false);
            btnCancelCar.setVisible(false);
            btnAddCar.setVisible(false);
        }
        serv.getAll();
        userServ.getAll();
        colFIO.setCellValueFactory(new PropertyValueFactory<>("userEmployeeFullName"));
        colColorAuto.setCellValueFactory(new PropertyValueFactory<>("colorCar"));
        colModelAuto.setCellValueFactory(new PropertyValueFactory<>("modelCar"));
        colMarkAuto.setCellValueFactory(new PropertyValueFactory<>("markCar"));
        colGosNumber.setCellValueFactory(new PropertyValueFactory<>("gosNumberCar"));
        colNumberPhone.setCellValueFactory(new PropertyValueFactory<>("userEmployeePhoneNumber"));
        tableCars.setItems(serv.getData());

    }
}
