package educationalpractice.placecarclient.Controller;

import educationalpractice.placecarclient.Entity.Card;
import educationalpractice.placecarclient.Entity.Employee;
import educationalpractice.placecarclient.Entity.PM;
import educationalpractice.placecarclient.Entity.User;
import educationalpractice.placecarclient.MainApplication;
import educationalpractice.placecarclient.Service.CardServ;
import educationalpractice.placecarclient.Service.ErrorAlertServ;
import educationalpractice.placecarclient.Service.PMServ;
import educationalpractice.placecarclient.Service.UserServ;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static educationalpractice.placecarclient.MainApplication.*;

public class UserCard {
    @FXML
    private Button btnCancelBron;
    @FXML
    private Button btnHelp;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnOpenCarUser;
    @FXML
    private Button btnOpenEdtCard;
    @FXML
    private Button btnOpenHomeUser;
    @FXML
    private Text txtBankCard;
    @FXML
    private Text txtCVVCard;
    @FXML
    private Text txtNameCard;
    @FXML
    private Text txtNumberCard;
    @FXML
    private Text txtPMAndPrice;
    @FXML
    private Text txtPMJProfile1;
    @FXML
    private Text txtSrokCard;
    @FXML
    private Text txtSumma;
    @FXML
    private Text txtTimeBron;
    @FXML
    private Text whoUser;
    private Map<String, User> usersData = new HashMap<>();
    private Map<String, Card> cardData = new HashMap<>();
    CardServ serv = new CardServ();
    UserServ userServ = new UserServ();
    PMServ pmServ = new PMServ();
    ErrorAlertServ alertServ = new ErrorAlertServ();
    @FXML
    void btnCancelBron(ActionEvent event) {
        MainApplication.showDialog("user-main2.fxml","Помощь");
        Stage stage = (Stage) btnNext.getScene().getWindow();
        stage.close();
    }
    @FXML
    void btnHelp(ActionEvent event) {
        MainApplication.showDialog("help.fxml","Помощь");
    }
    @FXML
    void btnNext(ActionEvent event) throws Exception{
        try{ User user = new User();
        Card card = new Card();
        card.setIdCard(addCardData.getIdCard());
        cardTemporary.getUser().setTimeEntry(entry);
        cardTemporary.getUser().setTimeDeparture(exit);
        user.setIdUser(cardTemporary.getUser().getIdUser());
        userServ.findById(user);
        serv.findById(card);
        serv.update(cardTemporary,cardFind);
        userServ.update(cardTemporary.getUser(),forFind);
        PM pm = new PM();
        pm.setRyadPM(pmLetter);
        pm.setNumberPM(Integer.parseInt(figure));
        pm.setStatusPM("Свободная касса!");
        pm.setUser(user);
        pm.setCard(card);
        pmServ.add(pm);
        MainApplication.showDialog("OkRent.fxml","Автостоянка 'PlaceCar'");
        Stage stage = (Stage) btnNext.getScene().getWindow();
        stage.close();}
        catch (Exception e){
            alertServ.tryRentPMNoCard();
        }
    }

    @FXML
    void btnOpenCarUser(ActionEvent event) {
        MainApplication.showDialog("user-car.fxml","Автостоянка 'PlaceCar'");
        Stage stage = (Stage) btnOpenCarUser.getScene().getWindow();
        stage.close();
    }
    @FXML
    void btnOpenEdtCard(ActionEvent event) {
        MainApplication.showDialog("user-card-bron.fxml","Автостоянка 'PlaceCar'");
        Stage stage = (Stage) btnOpenEdtCard.getScene().getWindow();
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
        whoUser.setText(employeeStat.getSurname()+"\n"+employeeStat.getName()+"\n"+employeeStat.getSurname());
        List<User> users = userServ.getAll();
        ObservableList<Employee> employeeData = FXCollections.observableArrayList();
        ObservableList<Card> cardData = FXCollections.observableArrayList();
        ObservableList<Employee> matchingEmployees = FXCollections.observableArrayList();
        for (User us : users) {
            Employee employee = us.getEmployee();
            employeeData.add(employee);
        }
        for (int i = 0; i < employeeData.size(); i++) {
            Employee employee1 = employeeData.get(i);
            if (employee1.getIdEmployee().equals(employeeStat.getIdEmployee())) {
               matchingEmployees.add(employee1);
               break;
            } else {
                User user = new User();
                user.setEmployee(employeeStat);
                break;
            }
        }
        txtSumma.setText( money);
        txtTimeBron.setText(time);
        txtPMAndPrice.setText(pmLetter+figure+ priceStroke);
        serv.getAll();
        if (cardTemporary.equals(null)){
            txtSrokCard.setText("пусто");
        }else {
            txtSrokCard.setText(cardTemporary.getTermCard());
            txtNumberCard.setText(cardTemporary.getNumberCard());
            txtBankCard.setText(cardTemporary.getBankCard());
            txtNameCard.setText(cardTemporary.getNameCard());
            txtCVVCard.setText(cardTemporary.getCvvCard());
        }}
}
