package educationalpractice.placecarclient.Controller;

import educationalpractice.placecarclient.Entity.Card;
import educationalpractice.placecarclient.Entity.Employee;
import educationalpractice.placecarclient.Entity.PM;
import educationalpractice.placecarclient.Entity.User;
import educationalpractice.placecarclient.MainApplication;
import educationalpractice.placecarclient.Service.UserServ;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

import static educationalpractice.placecarclient.MainApplication.*;

public class UserCar {
    @FXML
    private Button btnHelp;
    @FXML
    private Button btnOpenCarUser;
    @FXML
    private Button btnOpenHomeUser;
    @FXML
    private Text txtLastNameProfile;
    @FXML
    private Text txtLetterPM;
    @FXML
    private Text txtNameProfile;
    @FXML
    private Text txtPMNumberProfile;
    @FXML
    private Text txtPMPriceProfile;
    @FXML
    private Text txtPhoneNumberProfile;
    @FXML
    private Text txtSurnameProfile;
    @FXML
    private Text whoUser;
    UserServ userServ = new UserServ();
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
        List<User> users = userServ.getAll();
        ObservableList<Employee> employeeData = FXCollections.observableArrayList();
        ObservableList<PM> pmData = FXCollections.observableArrayList();
        ObservableList<Employee> matchingEmployees = FXCollections.observableArrayList();
        for (User us : users) {
            // Получаем объект Employee через вложенные объекты
            Employee employee = us.getEmployee();
            // Добавляем данные сотрудника в список
            employeeData.add(employee);
        }
        // Проходим по списку сотрудников
        for (int i = 0; i < employeeData.size(); i++) {
            Employee employee1 = employeeData.get(i);
            // Сравниваем сотрудников по идентификатору
            if (employee1.getIdEmployee().equals(employeeStat.getIdEmployee())) {
                // Если идентификаторы совпадают, добавляем сотрудника в список совпадающих
                matchingEmployees.add(employee1);
                // Можно добавить логику для предотвращения повторного добавления одного и того же сотрудника
                break;
            }
        }
        txtSurnameProfile.setText(employeeStat.getSurname());
        txtNameProfile.setText(employeeStat.getName());
        txtLastNameProfile.setText(employeeStat.getLastname());
        txtPhoneNumberProfile.setText(employeeStat.getNumberPhone());
        txtLetterPM.setText(pmLetter);
        txtPMNumberProfile.setText(pmNumber);
        txtPMPriceProfile.setText(money);
        whoUser.setText(employeeStat.getSurname()+"\n"+employeeStat.getName()+"\n"+employeeStat.getSurname());
}
    }
