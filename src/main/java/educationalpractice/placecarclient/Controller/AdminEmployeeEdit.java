package educationalpractice.placecarclient.Controller;


import educationalpractice.placecarclient.Entity.Car;
import educationalpractice.placecarclient.Entity.Employee;
import educationalpractice.placecarclient.Entity.User;
import educationalpractice.placecarclient.MainApplication;
import educationalpractice.placecarclient.Service.EmployeeServ;
import educationalpractice.placecarclient.Service.ErrorAlertServ;
import educationalpractice.placecarclient.Service.UserServ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Optional;

import static educationalpractice.placecarclient.MainApplication.emplForUser;

public class AdminEmployeeEdit {
    @FXML
    private TextField strokeRoleProfile;
    @FXML
    private TextField strokeLactNameProfile;
    @FXML
    private TextField strokeLoginProfile;
    @FXML
    private Button cancel;
    @FXML
    private Button save;
    @FXML
    private TextField strokeNameProfile;
    @FXML
    private TextField strokeNumberPhoneProfile;
    @FXML
    private TextField strokePasswordProfile;
    @FXML
    private TextField strokeSurnameProfile;
    User user = new User();
    private final EmployeeServ service = new EmployeeServ();
    private final UserServ servUser = new UserServ();
    private final ErrorAlertServ alertService = new ErrorAlertServ();
    private boolean addFlag = true;
    @FXML
    private void initialize(){
        service.getAll();}
    @Setter
    @Getter
    private Optional<Employee> employee;
    @FXML
    void btnCancelProfile(ActionEvent event) throws IOException {
        MainApplication.start3("Автостоянка 'PlaceCar'");
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
    @FXML
    void btnEditProfile(ActionEvent event) throws IOException {
        try {
            Employee temp = Employee.builder()
                    .name(strokeNameProfile.getText())
                    .lastname(strokeLactNameProfile.getText())
                    .surname(strokeSurnameProfile.getText())
                    .password(strokePasswordProfile.getText())
                    .login(strokeLoginProfile.getText())
                    .numberPhone(strokeNumberPhoneProfile.getText())
                    .role(strokeRoleProfile.getText())
                    .build();
            if (employee.isEmpty()) {
                employee = Optional.of(temp);
                emplForUser = temp;
            } else {
                temp.setIdEmployee(employee.get().getIdEmployee());
            }
            employee = Optional.of(temp);
        } catch (Exception e) {
            alertService.addVoid(e);
        }
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();

    }

    public  void  start2(){
        if (employee.isPresent()){
            strokeSurnameProfile.setText(employee.get().getSurname());
            strokeLactNameProfile.setText(employee.get().getLastname());
            strokeLoginProfile.setText(employee.get().getLogin());
            strokeNameProfile.setText(employee.get().getName());
            strokePasswordProfile.setText(employee.get().getPassword());
            strokeNumberPhoneProfile.setText(employee.get().getNumberPhone());
            strokeRoleProfile.setText(employee.get().getRole());
        }
    }
}
