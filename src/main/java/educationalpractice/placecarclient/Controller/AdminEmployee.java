package educationalpractice.placecarclient.Controller;


import educationalpractice.placecarclient.Entity.Car;
import educationalpractice.placecarclient.Entity.Employee;
import educationalpractice.placecarclient.Entity.User;
import educationalpractice.placecarclient.MainApplication;
import educationalpractice.placecarclient.Service.*;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static educationalpractice.placecarclient.MainApplication.*;

public class AdminEmployee {

    @FXML
    private TableView<Employee> tableAboutHuman;
    @FXML
    private TableColumn<Employee,String> colFIO;

    @FXML
    private TableColumn<Employee,String> colLogin;

    @FXML
    private TableColumn<Employee,String> colNumberPhone;

    @FXML
    private TableColumn<Employee,String> colPassword;

    @FXML
    private TableColumn<Employee,String> colRole;
    @FXML
    private TableColumn<Car,String> colGosNumber;
    @FXML
    private Button btnOpenCars;
    @FXML
    private Text whoUser;
    @FXML
    private Button btnAddEmployee;

    @FXML
    private Button btnCancelEmployee;

    @FXML
    private Button btnDeleteEmployee;

    @FXML
    private Button btnEditEmployee;

    @FXML
    private Button btnOpenemployee;

    @FXML
    private Button btnOpenHome;
    private final UserServ userServ = new UserServ();
    private final EmployeeServ service = new EmployeeServ();
    private final ErrorAlertServ alertService = new ErrorAlertServ();


    private boolean addFlag = true;
    private Employee getSelectionElement() {
        Employee temp = tableAboutHuman.getSelectionModel().getSelectedItem();
        return temp;
    }

    private Optional<Employee> employee = Optional.empty();

    public void setEmploye(Optional<Employee> employee){
        try{
            this.employee = employee;
            if (employee.isPresent()) {
                if (employee.get().getIdEmployee() != null)
                    service.update(employee.get(), tableAboutHuman.getSelectionModel().getSelectedItem());
                else service.add(employee.get());
            }
        }catch (Exception e){
            alertService.addVoid(e);
        }
    }

    @FXML
    void OpenCars(ActionEvent event) throws IOException {
        MainApplication.start2("Автостоянка 'PlaceCar'");
        Stage stage = (Stage) btnOpenCars.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnAddEmployee(ActionEvent event) {
        try {
            Optional<Employee> employee = Optional.empty();
            MainApplication.showEmployeeDialog(employee);

        }catch (Exception e){alertService.didntStart(e);}
    }

    @FXML
    void btnCancelEmployee(ActionEvent event) {

    }

    @FXML
    void btnDeleteEmployee(ActionEvent event) {
        try {service.delete(getSelectionElement());
        }catch (Exception e){
            alertService.deleteVoid(e);
        }
    }

    @FXML
    void btnEditEmployee(ActionEvent event) {
        try{
            Optional<Employee> employee = Optional.of(getSelectionElement());
            MainApplication.showEmployeeDialog(employee);
        }catch (Exception e){alertService.didntStart(e);}
    }

    @FXML
    void btnOpenemployee(ActionEvent event) {
        MainApplication.showDialog("admin-main.fxml","Главная");
        Stage stage = (Stage) btnOpenemployee.getScene().getWindow();
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
        service.getAll();
        whoUser.setText(userAdmin.getSurname()+"\n"+userAdmin.getName()+"\n"+userAdmin.getLastname());
        if (userAdmin.getRole().equals("Администратор") ) { //роль равна админу или охране
            btnAddEmployee.setVisible(true);
            btnEditEmployee.setVisible(true);
            btnDeleteEmployee.setVisible(true);
            btnCancelEmployee.setVisible(true);
        } else {
            btnAddEmployee.setVisible(false);
            btnEditEmployee.setVisible(false);
            btnDeleteEmployee.setVisible(false);
            btnCancelEmployee.setVisible(false);
        }
        System.out.println("Введенный: "+userInf.getLogin()+" "+userInf.getPassword());
        colFIO.setCellValueFactory(cellData -> Bindings.concat(cellData.getValue().getSurname()," ",cellData.getValue().getName()," ",cellData.getValue().getLastname()," " ));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colNumberPhone.setCellValueFactory(new PropertyValueFactory<>("numberPhone"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tableAboutHuman.setItems(service.getData());
//        ObservableList<Employee> emplIddd = FXCollections.observableArrayList();
//        List<User> users = userServ.getAll();
//        for (Employee employ : emplIddd) {
//            if (!(employ.getIdEmployee().equals(user.getEmployee().getIdEmployee()))) {
//                User user = new User();
//                emplForUser
//                break;}}

    }

}