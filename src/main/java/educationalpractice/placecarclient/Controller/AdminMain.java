package educationalpractice.placecarclient.Controller;

import educationalpractice.placecarclient.Entity.PM;
import educationalpractice.placecarclient.MainApplication;
import educationalpractice.placecarclient.Places;
import educationalpractice.placecarclient.Service.PMServ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static educationalpractice.placecarclient.MainApplication.idPMSer;
import static educationalpractice.placecarclient.MainApplication.userAdmin;

public class AdminMain {
    private final PMServ service = new PMServ();
    @FXML
    private AnchorPane tableContainer; // Это AnchorPane из вашего FXML файла
    @FXML
    public static Button btnEditPMColor;
    @FXML
    private Text textEntryDeparture;
    @FXML
    private Text textFIO;
    @FXML
    private Button btnOpenCars;
    @FXML
    private Button btnOpenEmployee;
    @FXML
    private Text whoUser;
    @FXML
    void btnOpenCars(ActionEvent event) throws IOException {
        MainApplication.start2("Автостоянка 'PlaceCar'");
        Stage stage = (Stage) btnOpenCars.getScene().getWindow();
        stage.close();
    }
    @FXML
    void btnOpenEmployee(ActionEvent event) throws IOException{
        MainApplication.start3("Автостоянка 'PlaceCar'");
        Stage stage = (Stage) btnOpenEmployee.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void initialize() {
        whoUser.setText(userAdmin.getSurname()+"\n"+userAdmin.getName()+"\n"+userAdmin.getLastname());
        try {
            textEntryDeparture.setText(idPMSer.getUser().getTimeEntry()+"/"+idPMSer.getUser().getTimeDeparture());
            textFIO.setText(idPMSer.getUser().getEmployee().getName()+" "+idPMSer.getUser().getEmployee().getSurname());
        }catch (Exception e){
            System.out.println("Данные пока пусты");
        }
        Places gridPanelClass = new Places();
        GridPane gridPanel = gridPanelClass.createGridPanel();
        gridPanel.setLayoutX(-350); // Задает положение по оси X
        gridPanel.setLayoutY(0); // Задает положение по оси Y
        tableContainer.getChildren().add(gridPanel);}

}
