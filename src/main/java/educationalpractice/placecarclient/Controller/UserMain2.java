package educationalpractice.placecarclient.Controller;

import educationalpractice.placecarclient.Entity.PM;
import educationalpractice.placecarclient.MainApplication;
import educationalpractice.placecarclient.Service.ErrorAlertServ;
import educationalpractice.placecarclient.Service.PMServ;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static educationalpractice.placecarclient.MainApplication.*;

public class UserMain2 {
    @FXML
    private Button btnBronPM;
    @FXML
    private Button btnOpenCar;
    @FXML
    private Button btnOpenProfile;
    @FXML
    private Text textPrice;
    @FXML
    private TextField txtEntryPm;
    @FXML
    private TextField txtEntryPm1;
    @FXML
    private Text txtNumberPMBron;
    @FXML
    private Text txtRyadBron;
    @FXML
    private Text txtSumma;
    @FXML
    private Text txtTimeBron;
    @FXML
    private Text whoUser;
    @FXML
    private GridPane gridPane;
    @FXML
    void OnNumberPM1(ActionEvent event) {}
    String[] letters = {"A", "B", "C", "D", "E", "F", "J", "K", "O", "P", "T", "U", "V", "W", "X", "Y"};
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    ErrorAlertServ alertServ = new ErrorAlertServ();
    private PMServ serv = new PMServ();
    String color;
    @FXML
    void btnBronPM(ActionEvent event){
        try {if (color.equals("-fx-background-color: #ff0000;") ){
            alertServ.tryRentFullPM();
        } else if (time.isEmpty()) {
            alertServ.tryRentNullPM();
        } else {
                MainApplication.showDialog("user-card.fxml","Автостоянка 'PlaceCar'");
            Stage stage = (Stage) btnBronPM.getScene().getWindow();
            stage.close();}
        }catch (Exception e){
        alertServ.tryRentNullPM();}

    }
    @FXML
    void btnCancelPM(ActionEvent event) {

    }
    @FXML
    void btnOpenCar(ActionEvent event) {
        MainApplication.showDialog("user-car.fxml","Автостоянка 'PlaceCar'");
        Stage stage = (Stage) btnOpenCar.getScene().getWindow();
        stage.close();
    }
    @FXML
    void btnOpenProfile(ActionEvent event) {
        MainApplication.showDialog("user-profile.fxml","Автостоянка 'PlaceCar'");
        Stage stage = (Stage) btnOpenProfile.getScene().getWindow();
        stage.close();
    }
@FXML
private void initialize() {
    txtRyadBron.setText(pmLetter);
    for (String letter : letters) {
        if (pmLetter.equals(letter)) {
            price = 8;
            priceStroke = ", 8 руб/мин";
            textPrice.setText("8 руб/мин");
            break;
        } else {
            price = 5;
            priceStroke = ", 5 руб/мин";
            textPrice.setText("5 руб/мин");
        }
    }
    whoUser.setText(employeeStat.getSurname()+"\n"+employeeStat.getName()+"\n"+employeeStat.getLastname());
    PM pm = new PM();
    List<PM> allPMs = serv.getAll();

    for (Node node : gridPane.getChildren()) {
        if (node instanceof Button) {
            Button button = (Button) node;
            String alo = button.getText();
            pm.setNumberPM(Integer.parseInt(button.getText()));
            pm.setRyadPM(pmLetter);
            try {
                pm.setNumberPM(Integer.parseInt(alo));
                serv.checkUserData(pm);
                if (idPMSer!=null) { // isOccupied() - предполагаемый метод для проверки занятости
                    button.setStyle("-fx-background-color: #ff0000;"); // Красим кнопку в красный, если место занято
                }
            }catch (Exception e){
                System.out.println("Свободная касса");
                button.setStyle("-fx-background-color: #00ff1e;"); // Или в зеленый, если место свободно
            }
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String buttonText = button.getText();
                    System.out.println("Текст кнопки: " + buttonText);
                    txtNumberPMBron.setText(buttonText);
                    pmNumber = buttonText;
                    figure = buttonText;
                    color = button.getStyle();
                    if (button.getStyle().equals("-fx-background-color: red;")){
                        alertServ.full();
                    }
                    Object userData = button.getUserData();
                    if (userData != null) {
                        System.out.println("Данные кнопки: " + userData.toString());
                    }
                    try {
                        calculateTimeDifference();
                    }catch (Exception o){
                        System.out.println("TIME NO SET");
                    }}});}}
}
    public void calculateTimeDifference() {
        String timeEntry = txtEntryPm.getText();
        String timeExit = txtEntryPm1.getText();
        LocalTime entryTime = LocalTime.parse(timeEntry, formatter);
        LocalTime exitTime = LocalTime.parse(timeExit, formatter);
        entry = txtEntryPm.getText();
        exit =txtEntryPm1.getText();
        long minutesBetween = ChronoUnit.MINUTES.between(entryTime, exitTime);
        System.out.println("Разница во времени: " + minutesBetween + " минут");
        txtSumma.setText(String.valueOf(minutesBetween*price));
        txtTimeBron.setText(String.valueOf(minutesBetween)+" минут");
        money = txtSumma.getText();
        time = txtTimeBron.getText();
    }
}
