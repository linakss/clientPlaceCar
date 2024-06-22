package educationalpractice.placecarclient;


import educationalpractice.placecarclient.Controller.*;
import educationalpractice.placecarclient.Entity.*;
import educationalpractice.placecarclient.Service.SignInServ;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Optional;

public class MainApplication extends Application {
    private Stage stage;
    private FXMLLoader fxmlLoader;
    private static AdminCars adminCars;
    private static AdminEmployee adminEmployee;
    public  static PM idPMSer;
    private static AdminMain adminMainContr;
    private static UserMain1 userMain1Contr;
    @Getter
    @Setter
    public static Car car1 = new Car(); //cucu
    public static Car car2 = new Car(); //opo
    private static WelcomeMain signInController;
    public static Car selectedData = new Car();
    public static SignInServ sign = new SignInServ();
    @Getter
    @Setter
    public static Employee userInf = new Employee(); // данные временного пользователя (юзер)
    @Getter
    @Setter
    public static Employee userAdmin = new Employee(); // обозначение, что прога для админа
    public static Employee emporio = new Employee();
    public static Employee emplForUser = new Employee();
    public static Employee empl = new Employee(); // данные временного пользователя (юзер)
    public static User forFind = new User();
    public static Card cardFind = new Card();

    public static Employee employeeStat = new Employee();//Хранение в себе все данные эмплоера, если таковой вошел в систему
    public static String pmLetter; //parkovkaBukva
    public static String pmNumber;
    public static String figure; // цифра
    public static int price; //piatRub
    public static Card cardTemporary = new Card(); //cardEnerest данные во время редактирования карты
    public static Card addCardData = new Card();
    public static String money;
    public static String time;
    public static String entry ;
    public static String exit ;
    public static String priceStroke;

    @Override
    public void start(Stage stage) throws IOException {
        fxmlLoader = new FXMLLoader(MainApplication.class.getResource("welcome-main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 870, 570);
        stage.setTitle("Автостоянка 'PlaceCar'");
        stage.setResizable(false);
        stage.setScene(scene);
        signInController = fxmlLoader.getController();
        stage.show();
    }
    public static void start2(String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("admin-cars.fxml"));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 870, 570);
        stage.setScene(scene);

        adminCars = fxmlLoader.getController();
        stage.show();
    }
    public static void start3(String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("admin-employee.fxml"));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 870, 570);
        stage.setScene(scene);

        adminEmployee = fxmlLoader.getController();
        stage.show();
    }
    public static void showBookDialog(Optional<Car> cars) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("admin-cars-edit.fxml"));

            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Работа c авто");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            AdminCarsEdit controller = loader.getController();

            controller.setCars(cars);
            controller.start();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
            cars = controller.getCars();
            adminCars.setCar(cars);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showEmployeeDialog(Optional<Employee> employee) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("admin-employee-edit.fxml"));

            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Работа c Employee");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            AdminEmployeeEdit controller = loader.getController();

            controller.setEmployee(employee);
            controller.start2();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
            employee = controller.getEmployee();
            adminEmployee.setEmploye(employee);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startAdmin(String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("admin-main.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 870, 570);
        stage.setTitle("Автостоянка 'PlaceCar'");
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);

        adminMainContr = fxmlLoader.getController();
        stage.show();
    }

    public static void startUser(String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("user-main1.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 870, 570);
        stage.setTitle("Автостоянка 'PlaceCar'");
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        userMain1Contr = fxmlLoader.getController();
        stage.show();
    }

    public static void showDialog(String nameView, String title){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource(nameView));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.setResizable(false);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);

            dialogStage.setScene(scene);
            dialogStage.show();
        } catch (IOException l){
            Alert alert = new Alert(Alert.AlertType.NONE,  "Возникла ошибка!",
                    ButtonType.CLOSE, ButtonType.OK);

            Button button = new Button("Понятно");

            button.setOnAction(e -> {
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.show();
            });
            l.printStackTrace();
        }

    }
    public static void main(String[] args) {
        launch();
    }
}