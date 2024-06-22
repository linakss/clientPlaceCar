package educationalpractice.placecarclient.Controller;

import educationalpractice.placecarclient.Entity.PM;
import educationalpractice.placecarclient.MainApplication;
import educationalpractice.placecarclient.Service.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static educationalpractice.placecarclient.MainApplication.*;
import static educationalpractice.placecarclient.MainApplication.priceStroke;

public class UserMain1 {
    @FXML
    void OnNumberPMa(ActionEvent event) {}
    @FXML
    void OnNumberPMb(ActionEvent event) {}
    @FXML
    void OnNumberPM1(ActionEvent event) {}
    @FXML
    void btnCancelRyadPM(ActionEvent event) {}
    @FXML
    private GridPane gridPanel;
    @FXML
    private Button btnOpenCar;
    @FXML
    private Button btnOpenPMs;
    @FXML
    private Button btnOpenProfile;
    @FXML
    private Text whoUser;
    private final EmployeeServ serviceEmpl = new EmployeeServ();
    private final UserServ service = new UserServ();
    ErrorAlertServ alertServ = new ErrorAlertServ();
    private PMServ serv = new PMServ();
    private boolean addFlag = true;
    String color;
    String[] letters = {"A", "B", "C", "D", "E", "F", "J", "K", "O", "P", "T", "U", "V", "W", "X", "Y"};

    @FXML
    void btnOpenCar(ActionEvent event) {
        MainApplication.showDialog("user-car.fxml","Автостоянка 'PlaceCar'");
        Stage stage = (Stage) btnOpenCar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnOpenPMs(ActionEvent event) {
        try {if (color.equals("-fx-background-color: #ff0000;") ){
            alertServ.tryRentFullPM();
        }else {
            MainApplication.showDialog("user-main2.fxml","Автостоянка 'PlaceCar'");
            Stage stage = (Stage) btnOpenPMs.getScene().getWindow();
            stage.close();}
        }catch (Exception e){
            alertServ.tryRentNullPM();}
    }

    @FXML
    void btnOpenProfile(ActionEvent event) {
        MainApplication.showDialog("user-profile.fxml","Автостоянка 'PlaceCar'");
        Stage stage = (Stage) btnOpenProfile.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void initialize() {
        serviceEmpl.getAll();
        whoUser.setText(employeeStat.getSurname()+"\n"+employeeStat.getName()+"\n"+employeeStat.getLastname());
        PM pm2 = new PM();
        int i = 0;
        List<PM> allPMS = serv.getAll();
        Map<String, Integer> countMap = new HashMap<>();
        for (PM pm : allPMS) {
            String ryad = pm.getRyadPM(); // Предполагаем, что getRyadPM() возвращает строку с буквой ряда
            countMap.put(ryad, countMap.getOrDefault(ryad, 0) + 1);
        }
        for (Node node : gridPanel.getChildren()) {
            if (node instanceof Button) {
                i++;
                Button button = (Button) node;
                String ryad = button.getText();
                pm2.setNumberPM(i);
                pm2.setRyadPM(ryad);
                int count = countMap.getOrDefault(ryad, 0);
                if (count >= 10) {
                    button.setStyle("-fx-background-color: #ff0000;"); // Красим кнопку в красный, если под буквой 10 мест
                } else {
                    button.setStyle("-fx-background-color: #00ff1e;"); // Или в зеленый, если мест меньше 10
                }
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        // Получение текста с кнопки
                        String buttonText = button.getText();
                        System.out.println("Текст кнопки: " + buttonText);
                        pmLetter = button.getText();
                        color = button.getStyle();
                        if (button.getStyle().equals("-fx-background-color: red;")){
                            alertServ.full();
                        }
                        Object userData = button.getUserData();
                        if (userData != null) {
                            System.out.println("Данные кнопки: " + userData.toString());
                        }
                        for (String letter : letters) {
                            if (pmLetter.contains(letter)) {
                                price = 8;
                                priceStroke = ", 8 руб/мин";
                            } else {
                                price = 5;
                                priceStroke = ", 5 руб/мин";}}}});}}

    }
}
